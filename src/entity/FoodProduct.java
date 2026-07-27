package entity;

import java.time.LocalDate;

public class FoodProduct extends   Product{
    private LocalDate expirationDate;

    public FoodProduct(String barcode, String name, Category category, double price, int id, int stock, LocalDate expirationDate) {
        super(barcode, name, category, price, id, stock);
        this.expirationDate = expirationDate;
    }

    @Override
    public double calculateTax() {
        return getPrice()*0.10;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

}
