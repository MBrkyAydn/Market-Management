package entity;

public abstract class Product {
    private String barcode, name;
    private double price;
    private int id, stock;
    private Category category;

    public Product(String barcode, String name, Category category, double price, int id, int stock) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public abstract double calculateTax();

    @Override
    public String toString() {
        return "Product{" +
                "barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", stock=" + stock +
                ", category=" + category +
                '}';
    }

    public void increaseStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        stock += amount;
    }

    public void decreaseStock(int amount) throws InstantiationException {
        if (amount > stock || amount <= 0) {
            throw new InstantiationException("\"Insufficient stock.\"");
        }stock-=amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public Category getCategory() {
        return category;
    }
}
