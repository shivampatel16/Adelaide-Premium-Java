package com.example.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/***
 * Represents a payment in the Adelaide Premium application
 * Provides functionality to add payment details to PAYMENTS table in the database
 * @author Shivam Patel
 */
public class Payment {

  /***
   * Add verified payment details to PAYMENTS table in the database
   * @param customer_ID Customer ID of customer who made the payment
   * @param card_number Card number of customer who made the payment
   * @param expiry_month Expiry month of card
   * @param expiry_year Expiry year of card
   * @param cvv CVV of card
   * @param name_on_card Name on card used by customer to make the payment
   * @param otp OTP received by customer to make the payment
   */
  protected void addPaymentDetails(
      int customer_ID,
      long card_number,
      int expiry_month,
      int expiry_year,
      int cvv,
      String name_on_card,
      int otp) {
    Connection c;
    Statement stmt;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      c.setAutoCommit(false);

      // Create a table
      stmt = c.createStatement();

      // SQL command to add new payment details in the PAYMENTS table in the database
      String sql =
          "INSERT INTO PAYMENTS (CUSTOMER_ID, CARD_NUMBER, EXPIRY_MONTH, EXPIRY_YEAR, CVV, NAME_ON_CARD, OTP) "
              + "VALUES ("
              + customer_ID
              + ", "
              + card_number
              + ", "
              + expiry_month
              + ", "
              + expiry_year
              + ", "
              + cvv
              + ", '"
              + name_on_card
              + "', "
              + otp
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
