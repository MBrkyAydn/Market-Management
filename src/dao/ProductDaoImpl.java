package dao;

import entity.Category;
import entity.FoodProduct;
import entity.Product;
import util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO products\n" +
                "(barcode, name, category, price, stock,\n" +
                " expiration_date, warranty_period, usage_area)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getBarcode());
            statement.setString(2, product.getName());
            statement.setString(3, product.getCategory().name());

            statement.setDouble(4, product.getPrice());

            statement.setInt(5, product.getStock());
            if (product instanceof FoodProduct food) {
                statement.setDate(6, Date.valueOf(food.getExpirationDate()));

                statement.setNull(7, Types.INTEGER);

                statement.setNull(8, Types.VARCHAR);
            }
            statement.executeUpdate();
            System.out.println("Product added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Product findById(int id) {String sql = "SELECT * FROM products WHERE id = ?";
      try{Connection connection = DatabaseConnection.getConnection();
          PreparedStatement statement = connection.prepareStatement(sql);
          statement.setInt(1, id);

          ResultSet resultSet = statement.executeQuery();
          if(resultSet.next()){ int idd = resultSet.getInt("id");
          String barcode = resultSet.getString("barcode");
          String name = resultSet.getString("name");

          double price = resultSet.getDouble("price");
          int stock = resultSet.getInt("stock");
              Category category = Category.valueOf(resultSet.getString("category"));


          }


      }catch (SQLException e) {e.printStackTrace();}
        return null;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }
}
