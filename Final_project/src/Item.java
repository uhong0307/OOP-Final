public class Item {
    String name;
    int price;
    int amount;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        this.amount = 1;
    }

    public Item(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Item: " + name + " | Price: " + price + " | Amount: " + amount;
    }
}
