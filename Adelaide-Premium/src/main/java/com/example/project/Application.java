package com.example.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/***
 * Main class for the Adelaide Premium application
 * Reads CUSTOMERS, PRODUCTS and ORDERS tables from the database
 * Launches the Adelaide Premium application
 * @author Shivam Patel
 */
public class Application extends javafx.application.Application {

  /***
   * Overrides the start() method, loads main fxml file and shows the landing page of Adelaide Premium
   * @param stage Main stage of the application
   * @throws IOException When the stage cannot be created
   */
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1275, 655);
    stage.setTitle("Adelaide Premium");
    stage.setScene(scene);
    stage.show();
  }

  /***
   * Create objects of Customer, Order and Product
   * Read CUSTOMERS, PRODUCTS and ORDERS tables from the database
   */
  public void readDatabase() {
    // Create object of Customer class
    Customer c = new Customer();
    c.getCustomersFromDatabase();

    // Create object of Order class
    Order o = new Order();
    o.getOrdersFromDatabase();

    // Create object of Product class
    Product p = new Product();
    p.getProductsFromDatabase();
  }

  /***
   * Main method to create object of Application class to read the tables from the database
   * Launch the landing page of Adelaide Premium application
   * @param args Stores arguments from command line
   */
  public static void main(String[] args) {

    Application a = new Application();
    a.readDatabase();

    launch();
  }
}