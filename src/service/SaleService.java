package service;

import dao.SaleDao;
import dao.SaleDaoImpl;
import entity.Product;
import entity.Sale;

import java.util.List;

public class SaleService {

    private final SaleDao saleDao;
    private final ProductService productService;

    public SaleService() {
        this.saleDao = new SaleDaoImpl();
        this.productService = new ProductService();
    }

    public void saleProduct(int productId, int quantity) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found.");
        }
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Not enough stock.");
        }
        product.decreaseStock(quantity);

        double totalPrice = product.getPrice() * quantity;

        Sale sale = new Sale(quantity,product,  totalPrice);

        productService.updateProduct(product);

        saleDao.save(sale);

    }
    public List<Sale> getAllSales() {
        return saleDao.findAll();
    }

    public Sale getSaleById(int id) {
        return saleDao.findById(id);
    }

    public void deleteSale(int id) {
        saleDao.delete(id);
    }
}