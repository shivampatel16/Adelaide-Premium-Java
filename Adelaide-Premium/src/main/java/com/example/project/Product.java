package com.example.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/***
 * Represents a product in the Adelaide Premium application
 * Declares the products_list that will store details of all the products in the database
 * Implements the getter and setter methods for the private fields in the class
 * Provides functionality to update product quantities and add new products to the PRODUCTS table in the database
 * @author Shivam Patel
 */
public class Product {
  private int ID; // Stores Product ID
  private String category; // Stores Product category
  private String name; // Stores Product name
  private String size; // Stores Product size
  private String colour; // Stores Product colour
  private int quantity; // Stores Product quantity
  private int price; // Stores Product price
  static ArrayList<Product> products_list = new ArrayList<>(); // Store details of all products in the database

  /***
   * Constructor to set the default values for the fields in the Product class
   */
  protected Product() {
    ID = 0;
    category = "default_category";
    name = "default_name";
    size = "default_size";
    colour = "default_colour";
    quantity = 0;
    price = 0;
  }

  /***
   * Set Product ID of a product
   * @param ID Product ID of a product
   */
  protected void setID(int ID) {
    this.ID = ID;
  }

  /***
   * Set category of a product
   * @param category Category of a product
   */
  protected void setCategory(String category) {
    this.category = category;
  }

  /***
   * Set name of a product
   * @param name Name of a product
   */
  protected void setName(String name) {
    this.name = name;
  }

  /***
   * Set size of a product
   * @param size Size of a product
   */
  protected void setSize(String size) {
    this.size = size;
  }

  /***
   * Set colour of a product
   * @param colour Colour of a product
   */
  protected void setColour(String colour) {
    this.colour = colour;
  }

  /***
   * Set quantity of a product
   * @param quantity Quantity of a product
   */
  protected void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /***
   * Set price of a product
   * @param price Price of a product
   */
  protected void setPrice(int price) {
    this.price = price;
  }

  /***
   * Get Product ID of a product
   * @return Product ID of a product
   */
  protected int getID() {
    return ID;
  }

  /***
   * Get category of a product
   * @return Category of a product
   */
  protected String getCategory() {
    return category;
  }

  /***
   * Get name of a product
   * @return Name of a product
   */
  protected String getName() {
    return name;
  }

  /***
   * Get size of a product
   * @return Size of a product
   */
  protected String getSize() {
    return size;
  }

  /***
   * Get colour of a product
   * @return Colour of a product
   */
  protected String getColour() {
    return colour;
  }

  /***
   * Get quantity of a product
   * @return Quantity of a product
   */
  protected int getQuantity() {
    return quantity;
  }

  /***
   * Get price of a product
   * @return Price of a product
   */
  protected int getPrice() {
    return price;
  }

  /***
   * Get the data from the PRODUCTS table in the database
   */
  protected void getProductsFromDatabase() {
    Database d = new Database();
    d.getProductsData();
  }

  /***
   * Update the quantity of a product in the PRODUCTS table in the database
   * @param product_ID Product ID of a product
   * @param product_purchase_quantity Quantity to be updated for a product
   * @param operation Operation (addition or subtraction) to be performed on the product's current quantity
   */
  protected void updateProductQuantity(
      int product_ID, int product_purchase_quantity, char operation) {
    Connection c;
    Statement stmt;

    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);

      // Create a table
      stmt = c.createStatement();

      ResultSet rs =
          stmt.executeQuery("SELECT QUANTITY FROM PRODUCTS WHERE ID = " + product_ID + ";");

      int new_product_quantity = Integer.parseInt(rs.getString(1));
      if (operation == '-') {
        new_product_quantity -= product_purchase_quantity;
        if (new_product_quantity < 0) {
          new_product_quantity = 0;
        }
      } else if (operation == '+') {
        new_product_quantity += product_purchase_quantity;
      }

      // SQL command to update quantities for a product in the PRODUCTS table in the database
      String sql =
          "UPDATE PRODUCTS "
              + "SET QUANTITY = "
              + new_product_quantity
              + " WHERE ID = "
              + product_ID
              + ";";
      stmt.executeUpdate(sql);
      c.commit();

      stmt.close();
      c.close();
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }

  /***
   * Register new product in the PRODUCTS table in the database
   * @param product_ID Product ID of a product
   * @param category Category of a product
   * @param name Name of a product
   * @param size Size of a product
   * @param colour Colour of a product
   * @param quantity Quantity of a product
   * @param price Price of a product
   */
  protected void registerProduct(
      int product_ID,
      String category,
      String name,
      String size,
      String colour,
      int quantity,
      int price) {
    Connection c;
    Statement stmt;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);

      // Create a table
      stmt = c.createStatement();

      // SQL command to add new product in the PRODUCTS table in the database
      String sql =
          "INSERT INTO PRODUCTS (ID, CATEGORY, NAME, SIZE, COLOUR, QUANTITY, PRICE) "
              + "VALUES ("
              + product_ID
              + ", '"
              + category
              + "', '"
              + name
              + "', '"
              + size
              + "', '"
              + colour
              + "', "
              + quantity
              + ", "
              + price
              + ");";
      stmt.executeUpdate(sql);
      c.commit();

      stmt.close();
      c.close();
    } catch (Exception e) {
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
  }
}