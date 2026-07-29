import entity.*;
import service.ProductService;
import service.SaleService;
import util.FileManager;
import util.InputHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final ProductService productService = new ProductService();

    private static final SaleService saleService = new SaleService();

    public static void main(String[] args) {

        while (true) {

            printMenu();

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addProduct();
                    break;

                case 2:
                    listProducts();
                    break;

                case 3:
                    updateProduct();
                    break;

                case 4:
                    deleteProduct();
                    break;

                case 5:
                    sellProduct();
                    break;

                case 6:
                    listSales();
                    break;
                case 7:
                    FileManager.exportProducts(
                            productService.getAllProducts());
                    break;

                case 8:
                    FileManager.exportSales(
                            saleService.getAllSales());
                    break;
                case 0:
                    System.out.println("Goodbye...");
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void listSales() {

        List<Sale> sales = saleService.getAllSales();

        if (sales.isEmpty()) {
            System.out.println("No sales found.");
            return;
        }

        for (Sale sale : sales) {
            System.out.println(sale);
        }
    }

    private static void sellProduct() {
        int productId = InputHelper.readInt("Product Id: ");
        int quantity = InputHelper.readInt("Quantity: ");
        try {

            saleService.saleProduct(productId, quantity);

            System.out.println("Sale completed successfully.");

        } catch (IllegalArgumentException e) {

            System.out.println(e.getMessage());

        }
    }

    private static void deleteProduct() {

        int id = InputHelper.readInt("Product Id: ");

        Product product = productService.getProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        productService.deleteProduct(id);

        System.out.println("Product deleted successfully.");
    }

    private static void updateProduct() {

        int id = InputHelper.readInt("Product Id: ");

        Product product = productService.getProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println(product);

        String newName = InputHelper.readString("New Name: ");
        double newPrice = InputHelper.readDouble("New Price: ");

        product.changeName(newName);
        product.changePrice(newPrice);

        productService.updateProduct(product);

        System.out.println("Product updated successfully.");
    }

    private static void listProducts() {

        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void printMenu() {
        System.out.println("\n===== MARKET MANAGEMENT SYSTEM =====");
        System.out.println("1 - Add Product");
        System.out.println("2 - List Products");
        System.out.println("3 - Update Product");
        System.out.println("4 - Delete Product");
        System.out.println("5 - Sell Product");
        System.out.println("6 - List Sales");
        System.out.println("7- Export Products");
        System.out.println("8- Export Sales ");
        System.out.println("0 - Exit");
        System.out.print("Choice: ");
    }

    private static void addProduct() {
        String barcode = InputHelper.readString("Barcode: ");
        String name = InputHelper.readString("Name: ");
        double price = InputHelper.readDouble("Price: ");
        int stock = InputHelper.readInt("Stock: ");
        int choice = InputHelper.readChoice("""
                1 - Food
                2 - Electronic
                3 - Cleaning
                Choice: """, 1, 3);
        Product product;
        switch (choice) {

            case 1 -> {
                LocalDate expirationDate =
                        InputHelper.readDate("Expiration Date (yyyy-MM-dd): ");

                product = new FoodProduct(barcode, name, Category.FOOD, price, 0, stock, expirationDate);


            }

            case 2 -> {
                int warranty = InputHelper.readInt("Warranty (Months): ");


                product = new ElectronicProduct(barcode, name, Category.ELECTRONICS, price, 0, stock, warranty);


            }

            case 3 -> {
                String usageArea = InputHelper.readString("Usage Area: ");
                product = new CleaningProduct(barcode, name, Category.CLEANING, price, 0, stock, usageArea);


            }

            default -> {
                return;
            }
        }


    }

}