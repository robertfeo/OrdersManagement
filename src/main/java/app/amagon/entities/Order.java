package app.amagon.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @Basic
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Basic
    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @OneToMany(mappedBy = "orderByOrderId")
    private List<OrderItem> orderItemsByOrderId;

    public Order(int orderId, Date orderDate, int customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }

    public Order() {}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (orderId != order.orderId) return false;
        if (customerId != order.customerId) return false;
        return Objects.equals(orderDate, order.orderDate);
    }

    public List<OrderItem> getOrderItemsByOrderId() {
        return orderItemsByOrderId;
    }

    public void setOrderItemsByOrderId(List<OrderItem> orderItemsByOrderId) {
        this.orderItemsByOrderId = orderItemsByOrderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", customerId=" + customerId +
                ", orderItemsByOrderId=" + orderItemsByOrderId.toString() +
                '}';
    }
}
