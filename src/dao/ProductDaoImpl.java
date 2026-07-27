package dao;

import entity.*;
import util.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public Product findById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idd = resultSet.getInt("id");
                String barcode = resultSet.getString("barcode");
                String name = resultSet.getString("name");

                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                Category category = Category.valueOf(resultSet.getString("category"));
                switch (category) {
                    case FOOD:
                        return new FoodProduct(
                                barcode,
                                name,
                                category,
                                price,
                                id,
                                stock,
                                resultSet.getDate("expiration_date").toLocalDate());

                    case ELECTRONICS:
                        return new ElectronicProduct(
                                barcode,
                                name,
                                category,
                                price,
                                id,
                                stock,
                                resultSet.getInt("warranty_period")
                        );
                    case CLEANING:
                        return new CleaningProduct(
                                barcode,
                                name,
                                category,
                                price,
                                id,
                                stock,
                                resultSet.getString("usage_area")
                        );

                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        PreparedStatement statement = null;
        try {
            Connection connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int idd = resultSet.getInt("id");
                String barcode = resultSet.getString("barcode");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");
                Category category = Category.valueOf(resultSet.getString("category"));
                switch (category) {
                    case FOOD: products.add(new FoodProduct(barcode,name,category,price,idd,stock,resultSet.getDate("expiration_date").toLocalDate()));
                        break;
                        case ELECTRONICS: products.add(new ElectronicProduct(barcode,name,category,price,idd,stock,resultSet.getInt("warranty_period")));break;
                    case CLEANING:products.add(new CleaningProduct(barcode,name,category,price,idd,stock,resultSet.getString("usage_area")));break;

                }return products;
            }
        } catch (SQLException e) {

        }
        return List.of();
    }
}
