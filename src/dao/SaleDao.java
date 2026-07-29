package dao;

import entity.Product;
import entity.Sale;

import java.time.LocalDateTime;
import java.util.List;

public interface   SaleDao  {

    void save(Sale sale);
    Sale findById(int id);
    List<Sale> findAll();
    void delete(int id);


}
