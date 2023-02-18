import java.util.ArrayList;

public class Order {
    public static void buy (ArrayList<Data> askList, int size) {
        int min = Integer.MAX_VALUE;
        for (Data data : askList){
            int buffForPrice = data.getPrice();
            if (min > buffForPrice){
                min = buffForPrice;
            }
        }
        for (Data data : askList){
            if(data.getPrice() == min){
                if (data.getSize()>=size) {
                    data.setSize(data.getSize() - size);
                }
                else data.setSize(0);
            }
        }
    }
    public static void sell (ArrayList<Data> bidList, int size) {
        int max = Integer.MIN_VALUE;
        for (Data data : bidList){
            int buffForPrice = data.getPrice();
            if (max < buffForPrice){
                max = buffForPrice;
            }
        }
        for (Data data : bidList){
            if(data.getPrice() == max){
                if (data.getSize()>=size) {
                    data.setSize(data.getSize() - size);
                }
                 else data.setSize(0);
            }
        }
    }
}
