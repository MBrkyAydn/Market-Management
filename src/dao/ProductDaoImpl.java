package dao;

import com.mysql.cj.exceptions.DataReadException;
import entity.*;
import util.DatabaseConnection;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {


    private Product mapToProduct(ResultSet resultSet) throws SQLException {
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
                        idd,
                        stock,
                        resultSet.getDate("expiration_date").toLocalDate());

            case ELECTRONICS:
                return new ElectronicProduct(
                        barcode,
                        name,
                        category,
                        price,
                        idd,
                        stock,
                        resultSet.getInt("warranty_period")
                );
            case CLEANING:
                return new CleaningProduct(
                        barcode,
                        name,
                        category,
                        price,
                        idd,
                        stock,
                        resultSet.getString("usage_area"));

            default:
                throw new IllegalArgumentException("Unknown category.");
        }
    }

    private void fillStatement(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(1, product.getBarcode());
        statement.setString(2, product.getName());
        statement.setString(3, product.getCategory().name());
        statement.setDouble(4, product.getPrice());
        statement.setInt(5, product.getStock());
        if (product instanceof FoodProduct food) {
            statement.setDate(6, Date.valueOf(food.getExpirationDate()));
            statement.setNull(7, Types.INTEGER);
            statement.setNull(8, Types.VARCHAR);

        } else if (product instanceof ElectronicProduct electronic) {
            statement.setNull(6, Types.DATE);
            statement.setInt(7, electronic.getWarrantyPeriod());
            statement.setNull(8, Types.INTEGER);
        } else if (product instanceof CleaningProduct cleaning) {
            statement.setNull(6, Types.DATE);
            statement.setNull(7, Types.INTEGER);
            statement.setString(8, cleaning.getUsageArea());

        }
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO products\n" +
                "(barcode, name, category, price, stock,\n" +
                " expiration_date, warranty_period, usage_area)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            fillStatement(statement, product);

            statement.executeUpdate();
            System.out.println("Product added successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products  set barcode = ?, name = ?, category = ?, price = ?, stock = ? , expiration_date = ? ,warranty_period= ?, usage_area = ?   where id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            fillStatement(statement, product);
            statement.setInt(9, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void delete(int id) {
String sql = "DELETE FROM products where id = ?";
try {
    Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, id);
    statement.executeUpdate();
    System.out.println("Product deleted successfully.");


}catch (SQLException e){
    e.printStackTrace();
}

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
                return mapToProduct(resultSet);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Product not found.");
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
            while (resultSet.next()) {
              products.add(mapToProduct(resultSet));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


}
