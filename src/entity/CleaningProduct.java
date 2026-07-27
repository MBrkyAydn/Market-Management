package entity;

public class CleaningProduct extends Product {
private String usageArea;

    public CleaningProduct( String barcode, String name, Category category, double price,int id, int stock, String usageArea) {
        super( barcode, name, category, price, id,stock);
        this.usageArea = usageArea;
    }

    public String getUsageArea() {
        return usageArea;
    }
    @Override
    public double calculateTax() {
        return getPrice()*0.10;
    }

    public void setUsageArea(String usageArea) {
        this.usageArea = usageArea;
    }
}
