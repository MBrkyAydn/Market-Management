# 🛒 Market Management System

A robust, console-based Market Management System built with **Java** and **MySQL (JDBC)** following **Object-Oriented Programming (OOP)** principles and **Layered Architecture**.

---

## 📌 Features

- **Product Management (CRUD):**
  - Add new products under specialized categories:
    - 🍎 **Food Products:** Tracks expiration dates with custom tax rates.
    - 💻 **Electronic Products:** Tracks warranty periods (in months).
    - 🧼 **Cleaning Products:** Tracks intended usage areas.
  - List all registered products with real-time stock and pricing details.
  - Update product details (Name, Price, Stock).
  - Delete products by ID.
- **Sales Transactions:**
  - Sell products with automatic stock validation and real-time inventory deduction.
  - Calculate total sales amounts and register transactions.
  - View all past sales records with timestamps.
- **File Export (Reporting):**
  - Export product inventory to `products.txt`.
  - Export sales transaction history to `sales.txt`.
- **Robust Input Handling:**
  - Input validation utility (`InputHelper`) to prevent format exceptions and runtime crashes.

---

## 🏛️ System Architecture & Design Patterns

The project is structured using a clean **Layered Architecture** pattern:

```text
src/
├── dao/                  # Data Access Object pattern for MySQL persistence
│   ├── ProductDao.java
│   ├── ProductDaoImpl.java
│   ├── SaleDao.java
│   └── SaleDaoImpl.java
├── entity/               # Domain models & OOP class hierarchy
│   ├── Category.java
│   ├── Product.java      # Abstract base class
│   ├── FoodProduct.java
│   ├── ElectronicProduct.java
│   ├── CleaningProduct.java
│   └── Sale.java
├── service/              # Business logic layer
│   ├── ProductService.java
│   └── SaleService.java
├── util/                 # Utility helpers
│   ├── DatabaseConnection.java
│   ├── FileManager.java
│   └── InputHelper.java
└── Main.java             # Console application entry point & menu loop
