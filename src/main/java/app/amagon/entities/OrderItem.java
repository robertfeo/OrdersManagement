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
    private long quantity;

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private int price;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
    public int hashCode() {
        int result = itemId;
        result = 31 * result + orderId;
        result = 31 * result + productId;
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + price;
        return result;
    }

    public Order getOrderByOrderId() {
        return order;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.order = orderByOrderId;
    }

    public Product getProductByProductId() {
        return product;
    }

    public void setProductByProductId(Product productByProductId) {
        this.product = productByProductId;
    }
}
