package app.amagon.entities;

public class InvoiceItem {
    private int position;
    private int itemID;
    private String itemDescription;
    private int amount;
    private double itemPrice;
    private double totalPrice;

    public InvoiceItem(int position, int itemID, String itemDescription, int amount, double itemPrice, double totalPrice) {
        this.position = position;
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.amount = amount;
        this.itemPrice = itemPrice;
        this.totalPrice = totalPrice;
    }

    public int getPosition() {
        return position;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getAmount() {
        return amount;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
