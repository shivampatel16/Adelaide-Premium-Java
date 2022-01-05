package com.example.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/***
 * Represents the database of Adelaide Premium application
 * Collects data from the ORDERS, CUSTOMERS, and PRODUCTS tables from the database
 * @author Shivam Patel
 */
public class Database {

  /***
   * Collects data from the ORDERS table from the database
   * Stores the data into a static ArrayList created in the Order class
   */
  protected void getOrdersData() {
    Connection con;
    Statement stmt;
    try {
      Class.forName("org.sqlite.JDBC");
      con = DriverManager.getConnection("jdbc:sqlite:test.db");
      con.setAutoCommit(false);

      // Create a table
      stmt = con.createStatement();

      ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM ORDERS;");
      rs1.next();
      int order_count = rs1.getInt(1);

      // Select records
      ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS;");

      // Read every row in the table and add to orders_list
      while (rs.next()) {
        Order o = new Order();
        int order_id = rs.getInt("order_id");
        String date = rs.getString("date");
        int customer_id = rs.getInt("customer_id");
        String products = rs.getString("products");
        int total_cost = rs.getInt("total_cost");

        o.setOrder_ID(order_id);
        o.setDate(date);
        o.setCustomer_ID(customer_id);
        o.setProducts(products);
        o.setTotal_cost(total_cost);

        // Add row details to orders_list
        Order.orders_list.add(o);
      }
      rs.close();
      stmt.close();
      con.close();
    } catch (Exception e) { // Catch exception while accessing the table
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  /***
   * Collects data from the CUSTOMERS table from the database
   * Stores the data into a static ArrayList created in the Customer class
   */
  protected void getCustomersData() {
    Connection con;
    Statement stmt;
    try {
      Class.forName("org.sqlite.JDBC");
      con = DriverManager.getConnection("jdbc:sqlite:test.db");
      con.setAutoCommit(false);

      // Create a table
      stmt = con.createStatement();

      ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM CUSTOMERS;");
      rs1.next();
      int customer_count = rs1.getInt(1);

      // Select records
      ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMERS;");

      // Read every row in the table and add to customers_list
      while (rs.next()) {
        Customer c = new Customer();

        int customer_ID = rs.getInt("id");
        String password = rs.getString("password");
        String first_name = rs.getString("first_name");
        String last_name = rs.getString("last_name");
        String email = rs.getString("email");
        String DOB = rs.getString("DOB");
        String phone_number = rs.getString("phone_number");
        int past_purchase_shirt = rs.getInt("past_purchase_shirt");
        int past_purchase_jacket = rs.getInt("past_purchase_jacket");
        int past_purchase_trousers = rs.getInt("past_purchase_trousers");
        String address = rs.getString("address");
        int pin_code = rs.getInt("pin_code");
        String country = rs.getString("country");

        c.setCustomer_ID(customer_ID);
        c.setPassword(password);
        c.setFirst_name(first_name);
        c.setLast_name(last_name);
        c.setEmail(email);
        c.setDOB(DOB);
        c.setPhone_number(phone_number);
        c.setPast_purchase_shirt(past_purchase_shirt);
        c.setPast_purchase_jacket(past_purchase_jacket);
        c.setPast_purchase_trousers(past_purchase_trousers);
        c.setAddress(address);
        c.setPin_code(pin_code);
        c.setCountry(country);

        // Add row details to customers_list
        Customer.customers_list.add(c);
      }

      rs.close();
      stmt.close();
      con.close();
    } catch (Exception e) { // Catch exception while accessing the table
      System.out.println("Error in getCustomersFromDatabase.");
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  /***
   * Collects data from the PRODUCTS table from the database
   * Stores the data into a static ArrayList created in the Product class
   */
  protected void getProductsData() {
    Connection con;
    Statement stmt;
    try {
      Class.forName("org.sqlite.JDBC");
      con = DriverManager.getConnection("jdbc:sqlite:test.db");
      con.setAutoCommit(false);

      // Create a table
      stmt = con.createStatement();

      ResultSet rs1 = stmt.executeQuery("SELECT COUNT(*) FROM PRODUCTS;");
      rs1.next();

      // Select records
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS;");

      // Read every row in the table and add to products_list
      while (rs.next()) {
        Product p = new Product();

        int product_id = rs.getInt("id");
        String category = rs.getString("category");
        String name = rs.getString("name");
        String size = rs.getString("size");
        String colour = rs.getString("colour");
        int quantity = rs.getInt("quantity");
        int price = rs.getInt("price");

        p.setID(product_id);
        p.setCategory(category);
        p.setName(name);
        p.setSize(size);
        p.setColour(colour);
        p.setQuantity(quantity);
        p.setPrice(price);

        // Add row details to products_list
        Product.products_list.add(p);
      }
      rs.close();
      stmt.close();
      con.close();
    } catch (Exception e) { // Catch exception while accessing the table
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }
}
