package app.amagon.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item", schema = "amagon", catalog = "DB2_Projekt")
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "item_id", nullable = false)
    private int itemId;

    @Basic
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Basic
    @Column(name = "product_id", nullable = false)
    private int productId;

    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private double price;

    public OrderItem(int itemID, int orderId, int productId, int quantity, double price) {
        this.itemId = itemID;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem() {}

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (itemId != orderItem.itemId) return false;
        if (orderId != orderItem.orderId) return false;
        if (productId != orderItem.productId) return false;
        if (quantity != orderItem.quantity) return false;
        if (price != orderItem.price) return false;

        return true;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
