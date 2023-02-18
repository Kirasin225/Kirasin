public class Data {
    private int price = 1;
    private int size = 0;
    private String type = null;

    public Data(int price, int size, String type) {
        this.price = price;
        this.size = size;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {

        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Data() {
    }
}
