package com.example.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

/***
 * Represents a customer in the Adelaide Premium application
 * Declares the customers_list that will store details of all the customers in the database
 * Implements the getter and setter methods for the private fields in the class
 * Provides functionality to update quantities purchased by customers and register new customers to the CUSTOMERS table in the database
 * @author Shivam Patel
 */
public class Customer {
  private int customer_ID; // Stores customer ID
  private String password; // Stores customer password
  private String first_name; // Stores customer first name
  private String last_name; // Stores customer last name
  private String email; // Stores customer email
  private String DOB; // Stores customer date of birth
  private String phone_number; // Stores customer phone number
  private int past_purchase_shirt; // Stores customer's past purchase quantities for shirts
  private int past_purchase_jacket; // Stores customer's past purchase quantities for jackets
  private int past_purchase_trousers; // Stores customer's past purchase quantities for trousers
  private String address; // Stores customer address
  private int pin_code; // Stores customer pin_code
  private String country; // Stores customer country
  static ArrayList<Customer> customers_list = new ArrayList<>(); // Store details of all customers in the database

  /***
   * Constructor to set the default values for the fields in the Customer class
   */
  protected Customer() {
    customer_ID = 0;
    password = "default_password";
    first_name = "default_first_name";
    last_name = "default_last_name";
    email = "default@email";
    DOB = "0000-00-00";
    phone_number = "000-000-0000";
    past_purchase_shirt = 0;
    past_purchase_jacket = 0;
    past_purchase_trousers = 0;
    address = "default_address";
    pin_code = 0;
    country = "default_country";
  }

  /***
   * Set the Customer ID of a customer
   * @param customer_ID Customer ID of a customer
   */
  protected void setCustomer_ID(int customer_ID) {
    this.customer_ID = customer_ID;
  }

  /***
   * Set the password of a customer
   * @param password Password of a customer
   */
  protected void setPassword(String password) {
    this.password = password;
  }

  /***
   * Set the first name of a customer
   * @param first_name First name of a customer
   */
  protected void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  /***
   * Set the last name of a customer
   * @param last_name Last name of a customer
   */
  protected void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  /***
   * Set the email of a customer
   * @param email Email of a customer
   */
  protected void setEmail(String email) {
    this.email = email;
  }

  /***
   * Set the date of birth of a customer
   * @param DOB Date of birth of a customer
   */
  protected void setDOB(String DOB) {
    this.DOB = DOB;
  }

  /***
   * Set the phone number of a customer
   * @param phone_number Phone number of a customer
   */
  protected void setPhone_number(String phone_number) {
    this.phone_number = phone_number;
  }

  /***
   * Set the past purchased shirt quantities of a customer
   * @param past_purchase_shirt Past purchased shirt quantities of a customer
   */
  protected void setPast_purchase_shirt(int past_purchase_shirt) {
    this.past_purchase_shirt = past_purchase_shirt;
  }

  /***
   * Set the past purchased jacket quantities of a customer
   * @param past_purchase_jacket Past purchased jacket quantities of a customer
   */
  protected void setPast_purchase_jacket(int past_purchase_jacket) {
    this.past_purchase_jacket = past_purchase_jacket;
  }

  /***
   * Set the past purchased trousers quantities of a customer
   * @param past_purchase_trousers Past purchased trousers quantities of a customer
   */
  protected void setPast_purchase_trousers(int past_purchase_trousers) {
    this.past_purchase_trousers = past_purchase_trousers;
  }

  /***
   * Set the address of a customer
   * @param address Address of a customer
   */
  protected void setAddress(String address) {
    this.address = address;
  }

  /***
   * Set the pin code of a customer
   * @param pin_code Pin code of a customer
   */
  protected void setPin_code(int pin_code) {
    this.pin_code = pin_code;
  }

  /***
   * Set the country of a customer
   * @param country Country of a customer
   */
  protected void setCountry(String country) {
    this.country = country;
  }

  /***
   * Get Customer ID of a customer
   * @return Customer ID of a customer
   */
  protected int getCustomer_ID() {
    return customer_ID;
  }

  /***
   * Get password of a customer
   * @return Password of a customer
   */
  public String getPassword() {
    return password;
  }

  /***
   * Get first name of a customer
   * @return First name of a customer
   */
  protected String getFirst_name() {
    return first_name;
  }

  /***
   * Get last name of a customer
   * @return Last name of a customer
   */
  protected String getLast_name() {
    return last_name;
  }

  /***
   * Get email of a customer
   * @return Email of a customer
   */
  protected String getEmail() {
    return email;
  }

  /***
   * Get date of birth of a customer
   * @return Date of birth of a customer
   */
  protected String getDOB() {
    return DOB;
  }

  /***
   * Get phone number of a customer
   * @return Phone number of a customer
   */
  protected String getPhone_number() {
    return phone_number;
  }

  /***
   * Get past purchased quantities of shirt for a customer
   * @return Past purchased quantities of shirt for a customer
   */
  protected int getPast_purchase_shirt() {
    return past_purchase_shirt;
  }

  /***
   * Get past purchased quantities of jacket for a customer
   * @return Past purchased quantities of jacket for a customer
   */
  protected int getPast_purchase_jacket() {
    return past_purchase_jacket;
  }

  /***
   * Get past purchased quantities of trousers for a customer
   * @return Past purchased quantities of trousers for a customer
   */
  protected int getPast_purchase_trousers() {
    return past_purchase_trousers;
  }

  /***
   * Get address of a customer
   * @return Address of a customer
   */
  protected String getAddress() {
    return address;
  }

  /***
   * Get pin-code of a customer
   * @return Pin-code of a customer
   */
  protected int getPin_code() {
    return pin_code;
  }

  /***
   * Get country of a customer
   * @return Country of a customer
   */
  protected String getCountry() {
    return country;
  }

  /***
   * Get the data from the CUSTOMERS table in the database
   */
  protected void getCustomersFromDatabase() {
    Database d = new Database();
    d.getCustomersData();
  }

  /***
   * Add new quantities purchased by customer to the CUSTOMERS table in database
   * @param customer_ID Customer ID of customer
   * @param new_shirt_count New shirts purchased
   * @param new_jacket_count New jackets purchased
   * @param new_trousers_count New trousers purchased
   */
  protected void addCustomerPurchaseQuantities(
      int customer_ID, int new_shirt_count, int new_jacket_count, int new_trousers_count) {
    Connection c;
    Statement stmt;

    int updated_shirt_count = 0; // Stores new quantity of shirts purchased by customer
    int updated_jacket_count = 0; // Stores new quantity of jackets purchased by customer
    int updated_trousers_count = 0; // Stores new quantity of trousers purchased by customer

    // Update the products quantities purchased by customer
    for (Customer customer : customers_list) {
      if (customer.getCustomer_ID() == customer_ID) {
        updated_shirt_count = customer.getPast_purchase_shirt() + new_shirt_count;
        updated_jacket_count = customer.getPast_purchase_jacket() + new_jacket_count;
        updated_trousers_count =
                customer.getPast_purchase_trousers() + new_trousers_count;
      }
    }
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);

      // Create a table
      stmt = c.createStatement();

      // SQL command to update past purchase quantities for customer in the CUSTOMERS table in the database
      String sql =
          "UPDATE CUSTOMERS "
              + "SET PAST_PURCHASE_SHIRT = "
              + updated_shirt_count
              + ", PAST_PURCHASE_JACKET = "
              + updated_jacket_count
              + ", PAST_PURCHASE_TROUSERS = "
              + updated_trousers_count
              + " WHERE ID = "
              + customer_ID
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
   * Register new customer in the CUSTOMER table in the database
   * @param customer_ID Stores customer ID
   * @param password Stores customer password
   * @param first_name Stores customer first name
   * @param last_name Stores customer last name
   * @param email Stores customer email
   * @param DOB Stores customer date of birth
   * @param phone_number Stores customer phone number
   * @param address Stores customer address
   * @param pin_code Stores customer pin code
   */
  protected void registerCustomer(
      int customer_ID,
      String password,
      String first_name,
      String last_name,
      String email,
      String DOB,
      String phone_number,
      String address,
      int pin_code) {
    Connection c;
    Statement stmt;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);

      // Create a table
      stmt = c.createStatement();

      // SQL command to add details of new customer in the CUSTOMERS table in the database
      String sql =
          "INSERT INTO CUSTOMERS (ID, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, DOB, PHONE_NUMBER, PAST_PURCHASE_SHIRT, PAST_PURCHASE_JACKET, PAST_PURCHASE_TROUSERS, ADDRESS, PIN_CODE, COUNTRY) "
              + "VALUES ("
              + customer_ID
              + ", '"
              + password
              + "', '"
              + first_name
              + "', '"
              + last_name
              + "', '"
              + email
              + "', '"
              + DOB
              + "', '"
              + phone_number
              + "', "
              + 0
              + ", "
              + 0
              + ", "
              + 0
              + ", '"
              + address
              + "', "
              + pin_code
              + ", '"
              + "India"
              + "');";
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