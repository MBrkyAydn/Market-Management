package entity;

import java.time.LocalDateTime;

public class Sale {
    private int id, quantity;
    private Product product;
    private double totalPrice;
    private LocalDateTime date;

    public Sale(int quantity, Product product, double totalPrice) {

        this.quantity = quantity;
        this.product = product;
        this.totalPrice = totalPrice;

    }

    public Sale(int id, Product product, int quantity, double totalPrice, LocalDateTime date) {


        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public int getId() {
        return id;
    }
}
