import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<String> inputArray = new ArrayList<>();
        File link = new File("input.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (FileReader input = new FileReader(link);
             BufferedReader reader = new BufferedReader(input)) {
            while (reader.ready()) {

                String inputFiles = reader.readLine();
                inputArray.add(inputFiles);

            }

        }
        try (PrintStream stream = new PrintStream(baos)) {


            ArrayList<Data> askBuff = new ArrayList<>();
            ArrayList<Data> bidBuff = new ArrayList<>();

            for (String str : inputArray) {

                if (str.startsWith("u,")) {

                    if (str.endsWith("bid")) {

                        String[] strMass = str.split(",");
                        int price = Integer.parseInt(strMass[1]);
                        int size = Integer.parseInt(strMass[2]);
                        String type = strMass[3];
                        if (price > 0 && price < (int) (Math.pow(10, 9)) && size >= 0 && size < (int) (Math.pow(10, 8))) {
                            bidBuff.add(Update.Update(price, size, type));
                        }

                    } else if (str.endsWith("ask")) {

                        String[] strMass = str.split(",");
                        int price = Integer.parseInt(strMass[1]);
                        int size = Integer.parseInt(strMass[2]);
                        String type = strMass[3];
                        if (price > 0 && price < (int) (Math.pow(10, 9)) && size >= 0 && size < (int) (Math.pow(10, 8))) {
                            askBuff.add(Update.Update(price, size, type));
                        }
                    }

                }
                if (str.startsWith("q,")) {

                    if (str.endsWith("bid")) {
                        if (bidBuff.size() > 0) {
                            Queries.bestAskBid(bidBuff, stream);
                        }
                    } else if (str.endsWith("ask")) {
                        if (askBuff.size() > 0) {
                            Queries.bestAskBid(askBuff, stream);
                        }
                    } else {
                        String[] strMass = str.split(",");
                        int sizeOfPrice = Integer.parseInt(strMass[2]);
                        Queries.getSizeOfPrice(bidBuff, askBuff, sizeOfPrice, stream);
                    }

                }
                if (str.startsWith("o,")) {
                    String[] strMass = str.split(",");
                    String buyOrSell = strMass[1];
                    int sizeToBuy = Integer.parseInt(strMass[2]);
                    if (buyOrSell.equals("buy")) {
                        Order.buy(askBuff, sizeToBuy);
                    }
                    if (buyOrSell.equals("sell")) {
                        Order.sell(bidBuff, sizeToBuy);
                    }
                }
            }
        }
        String result = baos.toString();
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(result);
        }
    }
}
