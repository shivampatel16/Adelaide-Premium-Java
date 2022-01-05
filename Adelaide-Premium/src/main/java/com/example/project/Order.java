package com.example.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

/***
 * Represents an order in the Adelaide Premium application
 * Declares the orders_list that will store details of all the orders in the database
 * Implements the getter and setter methods for the private fields in the class
 * Provides functionality to add new order to the ORDERS table in the database
 * @author Shivam Patel
 */
public class Order {
  private int order_ID; // Stores Order ID
  private String date; // Stores Order Date
  private int customer_ID; // Stores Customer ID of customer who made the order
  private String products; // Stores products in the order
  private int total_cost; // Stores total cost of order
  static ArrayList<Order> orders_list = new ArrayList<>(); // Store details of all orders in the database

  /***
   * Constructor to set the default values for the fields in the Order class
   */
  protected Order() {
    order_ID = 0;
    date = "default_date";
    customer_ID = 0;
    products = "default_products";
    total_cost = 0;
  }

  /***
   * Set the Order ID of an Order
   * @param order_ID Order ID of an Order
   */
  protected void setOrder_ID(int order_ID) {
    this.order_ID = order_ID;
  }

  /***
   * Set the date of an Order
   * @param date Date of an Order
   */
  protected void setDate(String date) {
    this.date = date;
  }

  /***
   * Set the Customer ID of customer who made the order
   * @param customer_ID Customer ID of customer who made the order
   */
  protected void setCustomer_ID(int customer_ID) {
    this.customer_ID = customer_ID;
  }

  /***
   * Set the products of an Order
   * @param products Products of an Order
   */
  protected void setProducts(String products) {
    this.products = products;
  }

  /***
   * Set the total cost of an Order
   * @param total_cost Total Cost of an Order
   */
  protected void setTotal_cost(int total_cost) {
    this.total_cost = total_cost;
  }

  /***
   * Get the order ID of an Order
   * @return Order ID of an Order
   */
  protected int getOrder_ID() {
    return order_ID;
  }

  /***
   * Get the date of an Order
   * @return Date of an Order
   */
  protected String getDate() {
    return date;
  }

  /***
   * Get Customer ID of customer who made the order
   * @return Customer ID of customer
   */
  protected int getCustomer_ID() {
    return customer_ID;
  }

  /***
   * Get the products of an Order
   * @return Products of an Order
   */
  protected String getProducts() {
    return products;
  }

  /***
   * Get the total cost of an Order
   * @return Total Cost of an Order
   */
  protected int getTotal_cost() {
    return total_cost;
  }

  /***
   * Get the data from the ORDERS table in the database
   */
  protected void getOrdersFromDatabase() {
    Database d = new Database();
    d.getOrdersData();
  }

  /***
   * Add new order details to the database
   * @param customer_ID Customer ID of customer who made the order
   * @param products Products in the order
   * @param total_cost Total cost of the order
   * @param back_order_flag Flag for storing the back order status of the order
   */
  protected void addOrderToDatabase(
      int customer_ID, String products, int total_cost, int back_order_flag) {
    Connection c;
    Statement stmt;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);

      // Create a table
      stmt = c.createStatement();

      // SQL command to add details of new order in the ORDERS table in the database
      String sql =
          "INSERT INTO ORDERS (ORDER_ID,DATE,CUSTOMER_ID,PRODUCTS,TOTAL_COST, BACK_ORDER) "
              + "VALUES ("
              + (orders_list.size() + 1)
              + ", '"
              + java.time.LocalDate.now()
              + "', "
              + customer_ID
              + ", '"
              + products
              + "', "
              + total_cost
              + ", "
              + back_order_flag
              + ");";
      stmt.executeUpdate(sql);
      c.commit();

      stmt.close();
      c.close();
    } catch (Exception e) { // Catch exception while storing data to ORDERS table
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }
}
