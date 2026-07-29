package dao;

import entity.Product;
import entity.Sale;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDaoImpl implements SaleDao {
    private final ProductDao productDao;

    public SaleDaoImpl() {
        this.productDao = new ProductDaoImpl();
    }

    private void fillStatement(PreparedStatement statement, Sale sale) throws SQLException {
        statement.setInt(1, sale.getProduct().getId());

        statement.setInt(2, sale.getQuantity());

        statement.setDouble(3, sale.getTotalPrice());

    }


    @Override
    public void save(Sale sale) {
        String sql = """
                INSERT INTO sales
                (product_id, quantity, total_price)
                VALUES (?, ?, ?)
                """;
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            fillStatement(statement, sale);

            statement.executeUpdate();
        } catch (Exception e) {e.printStackTrace();
        }
    }

    private Sale mapToSale(ResultSet resultSet) throws SQLException {
        int saleId = resultSet.getInt("id");

        int quantity = resultSet.getInt("quantity");

        double totalPrice = resultSet.getDouble("total_price");

        LocalDateTime saleDate = resultSet.getTimestamp("sale_date").toLocalDateTime();

        int productId = resultSet.getInt("product_id");
        Product product = productDao.findById(productId);
        return new Sale(saleId, product, quantity, totalPrice, saleDate);


    }

    @Override
    public Sale findById(int id) {
        String sql = "SELECT * FROM sales WHERE id = ?";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapToSale(resultSet);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<Sale> findAll() {

        List<Sale> sales = new ArrayList<>();

        String sql = "SELECT * FROM sales";
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                sales.add(mapToSale(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM sales WHERE id = ?";

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, id);

                statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


