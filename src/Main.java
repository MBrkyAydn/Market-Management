import util.DatabaseConnection;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            System.out.println("Connection successful.");

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }

}