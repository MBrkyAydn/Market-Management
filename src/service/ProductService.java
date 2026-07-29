package service;

import dao.ProductDao;
import dao.ProductDaoImpl;
import entity.Product;

import java.util.List;

public class ProductService {
    private final ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDaoImpl();
    }
    public void addProduct(Product product) {
        productDao.save(product);
    }
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
    public Product getProductById(int id) {
        return productDao.findById(id);
    }
    public void updateProduct(Product product) {
        productDao.update(product);
    }
    public void deleteProduct(int id) {
        productDao.delete(id);
    }


}
