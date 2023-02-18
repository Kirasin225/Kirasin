import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;


public class Queries {

    public static void bestAskBid(ArrayList<Data> dataArrayList,PrintStream stream) {
        int max = Integer.MIN_VALUE;
        HashMap<Integer, Integer> priceSizeMap = new HashMap<>();
        Data data = new Data();
        data = dataArrayList.get(0);
        if (dataArrayList.size() > 1) {
            for (Data data1 : dataArrayList) {
                int price = data1.getPrice();
                int size = data1.getSize();
                priceSizeMap.put(price, size);
            }
            for (Integer price : priceSizeMap.keySet()) {
                if (max < price) {
                    max = price;
                }
            }
            stream.println(max + "," + priceSizeMap.get(max));
            priceSizeMap.clear();
            max = Integer.MIN_VALUE;


        } else {
            int size = data.getSize();
            int price = data.getPrice();
            stream.println(price + "," + size);

        }
    }

    public static void getSizeOfPrice(ArrayList<Data> bidData, ArrayList<Data> askData, int sizeOfPrice,PrintStream stream) {
        HashMap<Integer, Integer> bidSizeOfPrice = new HashMap<>();
        HashMap<Integer, Integer> askSizeOfPrice = new HashMap<>();

        for (int i = 0; i < bidData.size(); i++) {
            int bidPriceBuff = bidData.get(i).getPrice();
            int bidSizeBuff = bidData.get(i).getSize();
            if (bidSizeOfPrice.containsKey(bidPriceBuff)) {
                int insideSize = bidSizeOfPrice.get(bidPriceBuff);
                int result = bidSizeBuff + insideSize;
                bidSizeOfPrice.put(bidPriceBuff, result);
            } else {
                bidSizeOfPrice.put(bidPriceBuff, bidSizeBuff);
            }
        }
        for (int i = 0; i < askData.size(); i++) {
            int askPriceBuff = askData.get(i).getPrice();
            int askSizeBuff = askData.get(i).getSize();
            if (askSizeOfPrice.containsKey(askPriceBuff)) {
                int insideSize = askSizeOfPrice.get(askPriceBuff);
                int result = askSizeBuff + insideSize;
                askSizeOfPrice.put(askPriceBuff, result);
            } else {
                askSizeOfPrice.put(askPriceBuff, askSizeBuff);
            }
        }
        if (bidSizeOfPrice.containsKey(sizeOfPrice)) {
            stream.print(bidSizeOfPrice.get(sizeOfPrice));
        } else if (askSizeOfPrice.containsKey(sizeOfPrice)) {
            stream.print(askSizeOfPrice.get(sizeOfPrice));
        }
    }
}