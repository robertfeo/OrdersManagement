package app.amagon.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id", nullable = false)
    private int customerId;

    @Basic
    @Column(name = "surname", nullable = false, length = 255)
    private String surname;

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Basic
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Basic
    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    public Customer(int customerId,String surname,String name,String address,String city) {
        this.setCustomerId(customerId);
        this.setSurname(surname);
        this.setName(name);
        this.setAddress(address);
        this.setCity(city);
    }

    public Customer() {}

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerId != customer.customerId) return false;
        if (surname != null ? !surname.equals(customer.surname) : customer.surname != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        if (city != null ? !city.equals(customer.city) : customer.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    public List<Order> getOrdersByCustomerId() {
        return orders;
    }

    public void setOrdersByCustomerId(List<Order> ordersList) {
        this.orders = ordersList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", orders=" + orders +
                '}';
    }
}
