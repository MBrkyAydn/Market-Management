package util;

import entity.Product;
import entity.Sale;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileManager {
    public static void exportProducts(List<Product> products) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("products.txt"))) {
            for (Product product : products) {

                writer.write(product.toString());

                writer.newLine();

                writer.write("--------------------------------");

                writer.newLine();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void exportSales(List<Sale> sales) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("sales.txt"))) {

            for (Sale sale : sales) {

                writer.write(sale.toString());

                writer.newLine();

                writer.write("--------------------------------");

                writer.newLine();
            }

            System.out.println("Sales exported successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
