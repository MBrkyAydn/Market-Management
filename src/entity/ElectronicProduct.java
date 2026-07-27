package entity;

public class ElectronicProduct extends Product {
private int warrantyPeriod;

    public ElectronicProduct(  String barcode, String name, Category category, double price,int id, int stock, int warrantyPeriod) {
        super( barcode, name, category, price,id, stock);
        this.warrantyPeriod = warrantyPeriod;
    }
    @Override
    public double calculateTax() {
        return getPrice()*0.20;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
}
