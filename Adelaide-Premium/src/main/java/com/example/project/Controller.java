package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/***
 * Represents class to control front end activities of Adelaide Premium application
 * Connects the front end activities to back end tasks and database
 * Implements the action methods for numerous functionalities of Adelaide Premium application
 * @author Shivam Patel
 */
public class Controller implements Initializable {

  @FXML private MenuItem register_product_menu;
  @FXML private MenuItem update_quantity_menu;
  @FXML private MenuItem register_customer_menu;
  @FXML private VBox main_page_header;
  @FXML private VBox main_page_footer;
  @FXML private VBox main_page_vbox;
  @FXML private MenuItem random_catalog;
  @FXML private MenuItem report_quarterly_sales;
  @FXML private MenuItem report_monthly;
  @FXML private MenuItem report_customer_demographics;
  @FXML private MenuItem report_stocktake;
  @FXML private Label customer_verification_label;
  @FXML private Button verify_customer;
  @FXML private TextField customer_id_value;
  @FXML private Label customer_id_label;
  @FXML private Button place_order;
  @FXML private MenuBar menubar;
  @FXML private VBox products_vbox;

  static int customer_found_flag = 0; // Stores status of the event if customer exists in the database
  static int customer_id = 0; // Stores Customer ID of customer placing the order
  static int cart_back_order_flag = 0; // Stores status of the event if the cart contains any back ordered products

  /***
   * Set customer found flag
   * @param customer_found_flag 1 if customer is found; 0 if customer is not found
   */
  public static void setCustomer_found_flag(int customer_found_flag) {
    Controller.customer_found_flag = customer_found_flag;
  }

  /***
   * Set customer ID of customer
   * @param customer_id Customer ID of customer
   */
  protected static void setCustomer_id(int customer_id) {
    Controller.customer_id = customer_id;
  }

  /***
   * Set back order status of cart
   * @param back_order_flag 1 if the order is back order; 0 if all items in cart are in stock
   */
  protected static void setCart_back_order_flag(int back_order_flag) {
    Controller.cart_back_order_flag = back_order_flag;
  }

  /***
   * Get customer found flag status
   * @return Customer's found flag
   */
  public static int getCustomer_found_flag() {
    return customer_found_flag;
  }

  /***
   * Get Customer ID of customer
   * @return  Customer ID of customer
   */
  protected static int getCustomer_id() {
    return customer_id;
  }

  /***
   * Get back order status of cart
   * @return  Back order status of cart
   */
  protected static int getCart_back_order_flag() {
    return cart_back_order_flag;
  }

  // Stores list of all Products in database
  ArrayList<Product> products_list = Product.products_list;

  /***
   * Generates the loading page of Adelaide Premium application
   * @param location URL location
   * @param resources Resource Bundle
   */
  public void initialize(URL location, ResourceBundle resources) {

    // Create object of Cart class
    Cart cart_object = new Cart();

    int shirt_image_count = 0; // Stores count of shirt images
    int jacket_image_count = 0; // Stores count of jacket images
    int trousers_image_count = 0; // Stores count of trousers images

    // Design loading page of Adelaide Premium application
    Label label_browse = new Label("Browse Products");
    label_browse.setFont(new Font(30));
    label_browse.setTextFill(Color.web("#0076a3"));
    products_vbox.getChildren().addAll(label_browse);
    label_browse.setPadding(new Insets(0, 0, 30, 0));

    customer_id_label.setFont(new Font(15));
    customer_verification_label.setFont(new Font(15));

    // List all the shirts in the database
    Label browse_shirts = new Label("Shirts");
    browse_shirts.setFont(new Font(30));
    browse_shirts.setTextFill(Color.web("#EE9A4D"));
    browse_shirts.setPadding(new Insets(0, 0, 30, 0));
    products_vbox.getChildren().addAll(browse_shirts);

    for (Product product : products_list) {

      // If product is a shirt, design layout for shirts
      if (Objects.equals(product.getCategory(), "shirt")) {
        Label id_text = new Label();
        id_text.setFont(new Font(15));
        id_text.setStyle("-fx-font-weight: bold");
        Label category_text = new Label();
        category_text.setFont(new Font(15));
        category_text.setStyle("-fx-font-weight: bold");
        Label name_text = new Label();
        name_text.setFont(new Font(15));
        name_text.setStyle("-fx-font-weight: bold");
        Label size_text = new Label();
        size_text.setFont(new Font(15));
        size_text.setStyle("-fx-font-weight: bold");
        Label colour_text = new Label();
        colour_text.setFont(new Font(15));
        colour_text.setStyle("-fx-font-weight: bold");
        Label quantity_text = new Label();
        quantity_text.setFont(new Font(15));
        quantity_text.setStyle("-fx-font-weight: bold");
        Label price_text = new Label();
        price_text.setFont(new Font(15));
        price_text.setStyle("-fx-font-weight: bold");

        Label id_value = new Label();
        id_value.setFont(new Font(15));
        Label category_value = new Label();
        category_value.setFont(new Font(15));
        Label name_value = new Label();
        name_value.setFont(new Font(15));
        Label size_value = new Label();
        size_value.setFont(new Font(15));
        Label colour_value = new Label();
        colour_value.setFont(new Font(15));
        Label quantity_value = new Label();
        quantity_value.setFont(new Font(15));
        Label price_value = new Label();
        price_value.setFont(new Font(15));

        Label purchase_quantity_text = new Label();
        purchase_quantity_text.setFont(new Font(15));
        purchase_quantity_text.setStyle("-fx-font-weight: bold");
        TextField purchase_quantity_value = new TextField();
        purchase_quantity_value.setPromptText("Enter purchase quantity");

        Label label_add_to_cart_verification = new Label();
        Label label_item_total_cost = new Label();

        id_text.setText("Product ID: ");
        category_text.setText("Category: ");
        name_text.setText("Name: ");
        size_text.setText("Size: ");
        colour_text.setText("Colour: ");
        quantity_text.setText("Available Quantity: ");
        price_text.setText("Price: Rs. ");

        purchase_quantity_text.setText("Purchase Quantity:   ");

        id_value.setText(String.valueOf(product.getID()));
        category_value.setText(product.getCategory());
        name_value.setText(product.getName());
        size_value.setText(product.getSize());
        colour_value.setText(product.getColour());
        quantity_value.setText(String.valueOf(product.getQuantity()));
        price_value.setText(String.valueOf(product.getPrice()));

        VBox product_details_vbox = new VBox();
        VBox product_purchase_vbox = new VBox();
        VBox image_vbox = new VBox();
        HBox product_details_purchase_hbox = new HBox();

        Image image = null;

        shirt_image_count += 1;
        try {
          image = new Image(new FileInputStream("img/shirt/" + shirt_image_count + ".JPG"));
        } catch (FileNotFoundException e) {
          System.out.println("File not found!");
        }

        if (shirt_image_count >= 10) {
          shirt_image_count = 0;
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(180);
        imageView.setFitWidth(180);
        image_vbox.getChildren().addAll(imageView);

        HBox hBox_id = new HBox();
        HBox hBox_category = new HBox();
        HBox hBox_name = new HBox();
        HBox hBox_size = new HBox();
        HBox hBox_colour = new HBox();
        HBox hBox_quantity = new HBox();
        HBox hBox_price = new HBox();

        hBox_id.getChildren().addAll(id_text, id_value);
        hBox_category.getChildren().addAll(category_text, category_value);
        hBox_name.getChildren().addAll(name_text, name_value);
        hBox_size.getChildren().addAll(size_text, size_value);
        hBox_colour.getChildren().addAll(colour_text, colour_value);
        hBox_quantity.getChildren().addAll(quantity_text, quantity_value);
        hBox_price.getChildren().addAll(price_text, price_value);

        product_details_vbox
            .getChildren()
            .addAll(
                hBox_id,
                hBox_category,
                hBox_name,
                hBox_size,
                hBox_colour,
                hBox_quantity,
                hBox_price);

        HBox hBox_purchase_quantity = new HBox();
        HBox hBox_add_to_cart = new HBox();
        HBox hBox_add_to_cart_verification = new HBox();
        HBox hBox_item_total_cost = new HBox();

        // Add To Cart button for shirts
        Button button_add_to_cart = new Button("Add To Cart");
        button_add_to_cart.setOnAction(
            event -> {
              // instructions executed when the button is clicked
              label_add_to_cart_verification.setText("");
              label_item_total_cost.setText("");
              try {

                ItemInCart item_in_cart_object = new ItemInCart();
                int unit_value = Integer.parseInt(price_value.getText());
                int quantity = Integer.parseInt(purchase_quantity_value.getText());
                int back_order_flag = 0;

                if (quantity > product.getQuantity()) {
                  back_order_flag = 1;
                  Controller.setCart_back_order_flag(1);
                }

                int cost = unit_value * quantity;

                String product_ids =
                    (id_value.getText() + " ")
                        .repeat(Integer.parseInt(purchase_quantity_value.getText()));

                item_in_cart_object.addItem(product_ids);
                item_in_cart_object.addItem(cost);
                item_in_cart_object.addItem(back_order_flag);

                cart_object.addToCart(item_in_cart_object.item_in_cart);

                if (back_order_flag == 0) {
                  label_add_to_cart_verification.setFont(new Font(15));
                  label_add_to_cart_verification.setTextFill(Color.web("#006400"));
                  label_add_to_cart_verification.setText("Product added to cart successfully!");
                } else {
                  label_add_to_cart_verification.setFont(new Font(15));
                  label_add_to_cart_verification.setTextFill(Color.web("#800000"));
                  label_add_to_cart_verification.setText(
                      "Not enough stock. \nProduct set as back ordered in cart.");
                }

                label_item_total_cost.setFont(new Font(15));
                label_item_total_cost.setText("Product's total cost: Rs. " + cost);

              } catch (NumberFormatException e) { // catch NumberFormatException
                label_add_to_cart_verification.setTextFill(Color.web("#800000"));
                label_add_to_cart_verification.setFont(new Font(15));
                label_add_to_cart_verification.setText(
                    "Incorrect Purchase Quantity. Please verify.");
              }
            });

        hBox_purchase_quantity
            .getChildren()
            .addAll(purchase_quantity_text, purchase_quantity_value);
        hBox_add_to_cart.getChildren().addAll(button_add_to_cart);
        hBox_add_to_cart_verification.getChildren().addAll(label_add_to_cart_verification);
        hBox_item_total_cost.getChildren().addAll(label_item_total_cost);

        product_purchase_vbox
            .getChildren()
            .addAll(
                hBox_purchase_quantity,
                hBox_add_to_cart,
                hBox_add_to_cart_verification,
                hBox_item_total_cost);

        product_details_vbox.setPrefWidth(400);
        product_purchase_vbox.setPrefWidth(400);

        product_details_vbox.setPadding(new Insets(20, 0, 30, 100));
        product_purchase_vbox.setPadding(new Insets(20, 0, 30, 20));
        image_vbox.setPadding(new Insets(25, 20, 30, 150));

        product_details_vbox.setSpacing(10);
        product_purchase_vbox.setSpacing(10);

        product_details_purchase_hbox
            .getChildren()
            .addAll(image_vbox, product_details_vbox, product_purchase_vbox);
        product_details_purchase_hbox.setSpacing(30);
        product_details_purchase_hbox.prefWidthProperty().setValue(300);
        products_vbox.getChildren().add(product_details_purchase_hbox);
      }
    }

    // List all the trousers in the database
    Label browse_trousers = new Label("Trousers");
    browse_trousers.setFont(new Font(30));
    browse_trousers.setTextFill(Color.web("#EE9A4D"));
    browse_trousers.setPadding(new Insets(0, 0, 30, 0));
    products_vbox.getChildren().addAll(browse_trousers);

    for (Product product : products_list) {

      // If product is a trousers, design layout for trousers
      if (Objects.equals(product.getCategory(), "trousers")) {
        Label id_text = new Label();
        id_text.setFont(new Font(15));
        id_text.setStyle("-fx-font-weight: bold");
        Label category_text = new Label();
        category_text.setFont(new Font(15));
        category_text.setStyle("-fx-font-weight: bold");
        Label name_text = new Label();
        name_text.setFont(new Font(15));
        name_text.setStyle("-fx-font-weight: bold");
        Label size_text = new Label();
        size_text.setFont(new Font(15));
        size_text.setStyle("-fx-font-weight: bold");
        Label colour_text = new Label();
        colour_text.setFont(new Font(15));
        colour_text.setStyle("-fx-font-weight: bold");
        Label quantity_text = new Label();
        quantity_text.setFont(new Font(15));
        quantity_text.setStyle("-fx-font-weight: bold");
        Label price_text = new Label();
        price_text.setFont(new Font(15));
        price_text.setStyle("-fx-font-weight: bold");

        Label id_value = new Label();
        id_value.setFont(new Font(15));
        Label category_value = new Label();
        category_value.setFont(new Font(15));
        Label name_value = new Label();
        name_value.setFont(new Font(15));
        Label size_value = new Label();
        size_value.setFont(new Font(15));
        Label colour_value = new Label();
        colour_value.setFont(new Font(15));
        Label quantity_value = new Label();
        quantity_value.setFont(new Font(15));
        Label price_value = new Label();
        price_value.setFont(new Font(15));

        Label purchase_quantity_text = new Label();
        purchase_quantity_text.setFont(new Font(15));
        purchase_quantity_text.setStyle("-fx-font-weight: bold");
        TextField purchase_quantity_value = new TextField();
        purchase_quantity_value.setPromptText("Enter purchase quantity");

        Label label_add_to_cart_verification = new Label();
        Label label_item_total_cost = new Label();

        id_text.setText("Product ID: ");
        category_text.setText("Category: ");
        name_text.setText("Name: ");
        size_text.setText("Size: ");
        colour_text.setText("Colour: ");
        quantity_text.setText("Available Quantity: ");
        price_text.setText("Price: Rs. ");

        purchase_quantity_text.setText("Purchase Quantity:   ");

        id_value.setText(String.valueOf(product.getID()));
        category_value.setText(product.getCategory());
        name_value.setText(product.getName());
        size_value.setText(product.getSize());
        colour_value.setText(product.getColour());
        quantity_value.setText(String.valueOf(product.getQuantity()));
        price_value.setText(String.valueOf(product.getPrice()));

        VBox product_details_vbox = new VBox();
        VBox product_purchase_vbox = new VBox();
        VBox image_vbox = new VBox();
        HBox product_details_purchase_hbox = new HBox();

        Image image = null;

        trousers_image_count += 1;
        try {
          image = new Image(new FileInputStream("img/trousers/" + trousers_image_count + ".JPG"));
        } catch (FileNotFoundException e) {
          System.out.println("File not found!");
        }

        if (trousers_image_count >= 10) {
          trousers_image_count = 0;
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(180);
        imageView.setFitWidth(180);
        image_vbox.getChildren().addAll(imageView);

        HBox hBox_id = new HBox();
        HBox hBox_category = new HBox();
        HBox hBox_name = new HBox();
        HBox hBox_size = new HBox();
        HBox hBox_colour = new HBox();
        HBox hBox_quantity = new HBox();
        HBox hBox_price = new HBox();

        hBox_id.getChildren().addAll(id_text, id_value);
        hBox_category.getChildren().addAll(category_text, category_value);
        hBox_name.getChildren().addAll(name_text, name_value);
        hBox_size.getChildren().addAll(size_text, size_value);
        hBox_colour.getChildren().addAll(colour_text, colour_value);
        hBox_quantity.getChildren().addAll(quantity_text, quantity_value);
        hBox_price.getChildren().addAll(price_text, price_value);

        product_details_vbox
            .getChildren()
            .addAll(
                hBox_id,
                hBox_category,
                hBox_name,
                hBox_size,
                hBox_colour,
                hBox_quantity,
                hBox_price);

        HBox hBox_purchase_quantity = new HBox();
        HBox hBox_add_to_cart = new HBox();
        HBox hBox_add_to_cart_verification = new HBox();
        HBox hBox_item_total_cost = new HBox();

        // Add To Cart button for trousers
        Button button_add_to_cart = new Button("Add To Cart");
        button_add_to_cart.setOnAction(
            event -> {
              // instructions executed when the button is clicked
              label_add_to_cart_verification.setText("");
              label_item_total_cost.setText("");
              try {

                ItemInCart item_in_cart_object = new ItemInCart();
                int unit_value = Integer.parseInt(price_value.getText());
                int quantity = Integer.parseInt(purchase_quantity_value.getText());
                int back_order_flag = 0;

                if (quantity > product.getQuantity()) {
                  back_order_flag = 1;
                  Controller.setCart_back_order_flag(1);
                }

                int cost = unit_value * quantity;

                String product_ids =
                    (id_value.getText() + " ")
                        .repeat(Integer.parseInt(purchase_quantity_value.getText()));

                item_in_cart_object.addItem(product_ids);
                item_in_cart_object.addItem(cost);
                item_in_cart_object.addItem(back_order_flag);

                cart_object.addToCart(item_in_cart_object.item_in_cart);

                if (back_order_flag == 0) {
                  label_add_to_cart_verification.setFont(new Font(15));
                  label_add_to_cart_verification.setTextFill(Color.web("#006400"));
                  label_add_to_cart_verification.setText("Product added to cart successfully!");
                } else {
                  label_add_to_cart_verification.setFont(new Font(15));
                  label_add_to_cart_verification.setTextFill(Color.web("#800000"));
                  label_add_to_cart_verification.setText(
                      "Not enough stock. \nProduct set as back ordered in cart.");
                }

                label_item_total_cost.setFont(new Font(15));
                label_item_total_cost.setText("Product's total cost: Rs. " + cost);
              } catch (NumberFormatException e) { // catch NumberFormatException
                label_add_to_cart_verification.setTextFill(Color.web("#800000"));
                label_add_to_cart_verification.setFont(new Font(15));
                label_add_to_cart_verification.setText(
                    "Incorrect Purchase Quantity. Please verify.");
              }
            });

        hBox_purchase_quantity
            .getChildren()
            .addAll(purchase_quantity_text, purchase_quantity_value);
        hBox_add_to_cart.getChildren().addAll(button_add_to_cart);
        hBox_add_to_cart_verification.getChildren().addAll(label_add_to_cart_verification);
        hBox_item_total_cost.getChildren().addAll(label_item_total_cost);

        product_purchase_vbox
            .getChildren()
            .addAll(
                hBox_purchase_quantity,
                hBox_add_to_cart,
                hBox_add_to_cart_verification,
                hBox_item_total_cost);

        product_details_vbox.setPrefWidth(400);
        product_purchase_vbox.setPrefWidth(400);

        product_details_vbox.setPadding(new Insets(20, 0, 30, 100));
        product_purchase_vbox.setPadding(new Insets(20, 0, 30, 20));
        image_vbox.setPadding(new Insets(25, 20, 30, 150));

        product_details_vbox.setSpacing(10);
        product_purchase_vbox.setSpacing(10);

        product_details_purchase_hbox
            .getChildren()
            .addAll(image_vbox, product_details_vbox, product_purchase_vbox);
        product_details_purchase_hbox.setSpacing(30);
        product_details_purchase_hbox.prefWidthProperty().setValue(300);
        products_vbox.getChildren().add(product_details_purchase_hbox);
      }
    }

    // List all the jackets in the database
    Label browse_jackets = new Label("Jackets");
    browse_jackets.setFont(new Font(30));
    browse_jackets.setTextFill(Color.web("#EE9A4D"));
    browse_jackets.setPadding(new Insets(0, 0, 30, 0));
    products_vbox.getChildren().addAll(browse_jackets);

    for (Product product : products_list) {

      // If product is a jacket, design layout for jackets
      if (Objects.equals(product.getCategory(), "jacket")) {
        Label id_text = new Label();
        id_text.setFont(new Font(15));
        id_text.setStyle("-fx-font-weight: bold");
        Label category_text = new Label();
        category_text.setFont(new Font(15));
        category_text.setStyle("-fx-font-weight: bold");
        Label name_text = new Label();
        name_text.setFont(new Font(15));
        name_text.setStyle("-fx-font-weight: bold");
        Label size_text = new Label();
        size_text.setFont(new Font(15));
        size_text.setStyle("-fx-font-weight: bold");
        Label colour_text = new Label();
        colour_text.setFont(new Font(15));
        colour_text.setStyle("-fx-font-weight: bold");
        Label quantity_text = new Label();
        quantity_text.setFont(new Font(15));
        quantity_text.setStyle("-fx-font-weight: bold");
        Label price_text = new Label();
        price_text.setFont(new Font(15));
        price_text.setStyle("-fx-font-weight: bold");

        Label id_value = new Label();
        id_value.setFont(new Font(15));
        Label category_value = new Label();
        category_value.setFont(new Font(15));
        Label name_value = new Label();
        name_value.setFont(new Font(15));
        Label size_value = new Label();
        size_value.setFont(new Font(15));
        Label colour_value = new Label();
        colour_value.setFont(new Font(15));
        Label quantity_value = new Label();
        quantity_value.setFont(new Font(15));
        Label price_value = new Label();
        price_value.setFont(new Font(15));

        Label purchase_quantity_text = new Label();
        purchase_quantity_text.setFont(new Font(15));
        purchase_quantity_text.setStyle("-fx-font-weight: bold");
        TextField purchase_quantity_value = new TextField();
        purchase_quantity_value.setPromptText("Enter purchase quantity");

        Label label_add_to_cart_verification = new Label();
        Label label_item_total_cost = new Label();

        id_text.setText("Product ID: ");
        category_text.setText("Category: ");
        name_text.setText("Name: ");
        size_text.setText("Size: ");
        colour_text.setText("Colour: ");
        quantity_text.setText("Available Quantity: ");
        price_text.setText("Price: Rs. ");

        purchase_quantity_text.setText("Purchase Quantity:   ");

        id_value.setText(String.valueOf(product.getID()));
        category_value.setText(product.getCategory());
        name_value.setText(product.getName());
        size_value.setText(product.getSize());
        colour_value.setText(product.getColour());
        quantity_value.setText(String.valueOf(product.getQuantity()));
        price_value.setText(String.valueOf(product.getPrice()));

        VBox product_details_vbox = new VBox();
        VBox product_purchase_vbox = new VBox();
        VBox image_vbox = new VBox();
        HBox product_details_purchase_hbox = new HBox();

        Image image = null;

        jacket_image_count += 1;
        try {
          image = new Image(new FileInputStream("img/jacket/" + jacket_image_count + ".JPG"));
        } catch (FileNotFoundException e) {
          System.out.println("File not found!");
        }

        if (jacket_image_count >= 10) {
          jacket_image_count = 0;
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(180);
        imageView.setFitWidth(180);
        image_vbox.getChildren().addAll(imageView);

        HBox hBox_id = new HBox();
        HBox hBox_category = new HBox();
        HBox hBox_name = new HBox();
        HBox hBox_size = new HBox();
        HBox hBox_colour = new HBox();
        HBox hBox_quantity = new HBox();
        HBox hBox_price = new HBox();

        hBox_id.getChildren().addAll(id_text, id_value);
        hBox_category.getChildren().addAll(category_text, category_value);
        hBox_name.getChildren().addAll(name_text, name_value);
        hBox_size.getChildren().addAll(size_text, size_value);
        hBox_colour.getChildren().addAll(colour_text, colour_value);
        hBox_quantity.getChildren().addAll(quantity_text, quantity_value);
        hBox_price.getChildren().addAll(price_text, price_value);

        product_details_vbox
            .getChildren()
            .addAll(
                hBox_id,
                hBox_category,
                hBox_name,
                hBox_size,
                hBox_colour,
                hBox_quantity,
                hBox_price);

        HBox hBox_purchase_quantity = new HBox();
        HBox hBox_add_to_cart = new HBox();
        HBox hBox_add_to_cart_verification = new HBox();
        HBox hBox_item_total_cost = new HBox();

        // Add To Cart button for jackets
        Button button_add_to_cart = new Button("Add To Cart");
        button_add_to_cart.setOnAction(
            event -> {
              // instructions executed when the button is clicked
              label_add_to_cart_verification.setText("");
              label_item_total_cost.setText("");
              try {

                ItemInCart item_in_cart_object = new ItemInCart();
                int unit_value = Integer.parseInt(price_value.getText());
                int quantity = Integer.parseInt(purchase_quantity_value.getText());
                int back_order_flag = 0;

                if (quantity > product.getQuantity()) {
                  back_order_flag = 1;
                  Controller.setCart_back_order_flag(1);
                }

                int cost = unit_value * quantity;

                String product_ids =
                    (id_value.getText() + " ")
                        .repeat(Integer.parseInt(purchase_quantity_value.getText()));

                item_in_cart_object.addItem(product_ids);
                item_in_cart_object.addItem(cost);
                item_in_cart_object.addItem(back_order_flag);

                cart_object.addToCart(item_in_cart_object.item_in_cart);

                if (back_order_flag == 0) {
                  label_add_to_cart_verification.setFont(new Font(15));
                  label_add_to_cart_verification.setTextFill(Color.web("#006400"));
                  label_add_to_cart_verification.setText("Product added to cart successfully!");
                } else {
                  label_add_to_cart_verification.setFont(new Font(15));
                  label_add_to_cart_verification.setTextFill(Color.web("#800000"));
                  label_add_to_cart_verification.setText(
                      "Not enough stock. \nProduct set as back ordered in cart.");
                }

                label_item_total_cost.setFont(new Font(15));
                label_item_total_cost.setText("Product's total cost: Rs. " + cost);

              } catch (NumberFormatException e) { // catch NumberFormatException
                label_add_to_cart_verification.setTextFill(Color.web("#800000"));
                label_add_to_cart_verification.setFont(new Font(15));
                label_add_to_cart_verification.setText(
                    "Incorrect Purchase Quantity. Please verify.");
              }
            });

        hBox_purchase_quantity
            .getChildren()
            .addAll(purchase_quantity_text, purchase_quantity_value);
        hBox_add_to_cart.getChildren().addAll(button_add_to_cart);
        hBox_add_to_cart_verification.getChildren().addAll(label_add_to_cart_verification);
        hBox_item_total_cost.getChildren().addAll(label_item_total_cost);

        product_purchase_vbox
            .getChildren()
            .addAll(
                hBox_purchase_quantity,
                hBox_add_to_cart,
                hBox_add_to_cart_verification,
                hBox_item_total_cost);

        product_details_vbox.setPrefWidth(400);
        product_purchase_vbox.setPrefWidth(400);

        product_details_vbox.setPadding(new Insets(20, 0, 30, 100));
        product_purchase_vbox.setPadding(new Insets(20, 0, 30, 20));
        image_vbox.setPadding(new Insets(25, 20, 30, 150));

        product_details_vbox.setSpacing(10);
        product_purchase_vbox.setSpacing(10);

        product_details_purchase_hbox
            .getChildren()
            .addAll(image_vbox, product_details_vbox, product_purchase_vbox);
        product_details_purchase_hbox.setSpacing(30);
        product_details_purchase_hbox.prefWidthProperty().setValue(300);
        products_vbox.getChildren().add(product_details_purchase_hbox);
      }
    }

    // Set header and footer
    Image adelaide_premium_header = null;
    Image adelaide_premium_footer = null;
    try {
      adelaide_premium_header =
          new Image(new FileInputStream("img/adelaide_premium_main_page.JPG"));
      adelaide_premium_footer = new Image(new FileInputStream("img/adelaide_premium_footer.JPG"));

    } catch (FileNotFoundException e) {
      System.out.println("File not found!");
    }

    ImageView imageView_header = new ImageView(adelaide_premium_header);
    imageView_header.setFitHeight(430);
    imageView_header.setFitWidth(1260);

    ImageView imageView_footer = new ImageView(adelaide_premium_footer);
    imageView_footer.setFitHeight(320);
    imageView_footer.setFitWidth(1260);

    main_page_header.getChildren().addAll(imageView_header);
    main_page_footer.getChildren().addAll(imageView_footer);
  }

  /***
   * Generates cart page with payment details after the order is placed
   * @param actionEvent Action Event when Place Order button is clicked
   * @throws IOException When files cannot be read properly
   */
  @FXML
  protected void onPlaceOrderClick(ActionEvent actionEvent) throws IOException {

    // If customer is not verified, do not place order
    if (customer_found_flag == 0) {
      customer_verification_label.setTextFill(Color.web("#800000"));
      customer_verification_label.setText("Please verify the customer before placing the order!");
      return;
    }

    // Create objects for Order, Customer and Product classes
    Order order_object = new Order();
    Customer customer_object = new Customer();
    Product product_object = new Product();

    // New window (Stage)
    Stage cartWindow = new Stage();
    cartWindow.setTitle("Cart Details");

    // Create HashMap to store details cart
    HashMap<String, String> cart_map = new HashMap<>();
    int total_cost = 0;
    StringBuilder products_for_order_DB_StringBuilder = new StringBuilder();
    int new_shirt_count = 0;
    int new_jacket_count = 0;
    int new_trousers_count = 0;

    // Iterate over every element of Cart to calculate the total cost
    for (int i = 0; i < Cart.cart.size(); i++) {
      String[] p_ids = Cart.cart.get(i).get(0).toString().split(" ");
      for (String p_id : p_ids) {
        if (cart_map.containsKey(p_id)) {
          String updated_count_with_backorder_flag =
                  (Integer.parseInt(cart_map.get(p_id).split(" ")[0]) + 1)
                          + " "
                          + Cart.cart.get(i).get(2);
          cart_map.put(p_id, updated_count_with_backorder_flag);
        } else {
          String count_with_backorder_flag = "1 " + Cart.cart.get(i).get(2);
          cart_map.put(p_id, count_with_backorder_flag);
        }
      }
      total_cost += Integer.parseInt(String.valueOf(Cart.cart.get(i).get(1)));
    }

    // For updating ORDERS table
    for (Map.Entry<String, String> entry : cart_map.entrySet()) {
      String product_i =
          (entry.getKey() + " ")
              .repeat(Integer.parseInt(entry.getValue().toString().split(" ")[0]));
      products_for_order_DB_StringBuilder.append(product_i);
    }
    String products_for_order_DB = products_for_order_DB_StringBuilder.toString();

    // For updating CUSTOMERS table
    for (Map.Entry<String, String> entry : cart_map.entrySet()) {
      int product_id = Integer.parseInt(entry.getKey());
      int product_count = Integer.parseInt(entry.getValue().toString().split(" ")[0]);

      for (Product product : products_list) {
        if (product.getID() == product_id) {
          if (Objects.equals(product.getCategory(), "shirt")) {
            new_shirt_count += product_count;
          } else if (Objects.equals(product.getCategory(), "jacket")) {
            new_jacket_count += product_count;
          } else if (Objects.equals(product.getCategory(), "trousers")) {
            new_trousers_count += product_count;
          }
        }
      }
    }

    // Design cart page of the application
    VBox vbox_cart = new VBox();

    VBox vbox_customer_details = new VBox();
    Label customer_id_text = new Label();
    Label customer_name_text = new Label();
    Label customer_email_text = new Label();
    Label customer_phone_text = new Label();
    Label customer_address_text = new Label();

    Label customer_id_value = new Label();
    customer_id_value.setFont(new Font(15));
    customer_id_value.setTextFill(Color.web("#4682B4"));

    Label customer_name_value = new Label();
    customer_name_value.setFont(new Font(15));
    customer_name_value.setTextFill(Color.web("#4682B4"));

    Label customer_email_value = new Label();
    customer_email_value.setFont(new Font(15));
    customer_email_value.setTextFill(Color.web("#4682B4"));

    Label customer_phone_value = new Label();
    customer_phone_value.setFont(new Font(15));
    customer_phone_value.setTextFill(Color.web("#4682B4"));

    Label customer_address_value = new Label();
    customer_address_value.setFont(new Font(15));
    customer_address_value.setTextFill(Color.web("#4682B4"));

    customer_id_text.setText("Customer ID : ");
    customer_id_text.setFont(new Font(15));
    customer_id_text.setTextFill(Color.web("#4682B4"));
    customer_id_text.setStyle("-fx-font-weight: bold");

    customer_name_text.setText("Name            : ");
    customer_name_text.setFont(new Font(15));
    customer_name_text.setTextFill(Color.web("#4682B4"));
    customer_name_text.setStyle("-fx-font-weight: bold");

    customer_email_text.setText("Email             : ");
    customer_email_text.setFont(new Font(15));
    customer_email_text.setTextFill(Color.web("#4682B4"));
    customer_email_text.setStyle("-fx-font-weight: bold");

    customer_phone_text.setText("Phone           : ");
    customer_phone_text.setFont(new Font(15));
    customer_phone_text.setTextFill(Color.web("#4682B4"));
    customer_phone_text.setStyle("-fx-font-weight: bold");

    customer_address_text.setText("Address        : ");
    customer_address_text.setFont(new Font(15));
    customer_address_text.setTextFill(Color.web("#4682B4"));
    customer_address_text.setStyle("-fx-font-weight: bold");

    for (int i = 0; i < Customer.customers_list.size(); i++) {
      if (Customer.customers_list.get(i).getCustomer_ID() == Controller.getCustomer_id()) {
        customer_id_value.setText(String.valueOf(Customer.customers_list.get(i).getCustomer_ID()));
        customer_name_value.setText(
            Customer.customers_list.get(i).getFirst_name()
                + " "
                + Customer.customers_list.get(i).getLast_name());
        customer_email_value.setText(Customer.customers_list.get(i).getEmail());
        customer_phone_value.setText("+91-" + Customer.customers_list.get(i).getPhone_number());
        customer_address_value.setText(
            Customer.customers_list.get(i).getAddress()
                + "\n"
                + Customer.customers_list.get(i).getPin_code()
                + ", "
                + Customer.customers_list.get(i).getCountry());
      }
    }

    HBox hbox_customer_id = new HBox(customer_id_text, customer_id_value);
    HBox hbox_customer_name = new HBox(customer_name_text, customer_name_value);
    HBox hbox_customer_email = new HBox(customer_email_text, customer_email_value);
    HBox hbox_customer_phone = new HBox(customer_phone_text, customer_phone_value);
    HBox hbox_customer_address = new HBox(customer_address_text, customer_address_value);

    vbox_customer_details
        .getChildren()
        .addAll(
            hbox_customer_id,
            hbox_customer_name,
            hbox_customer_email,
            hbox_customer_phone,
            hbox_customer_address);

    Label secondLabel = new Label("Cart Details");
    secondLabel.setFont(new Font(30));
    secondLabel.setTextFill(Color.web("#0076a3"));
    secondLabel.setAlignment(Pos.BASELINE_CENTER);

    Label label_total_cost = new Label("Total Cost = Rs. " + total_cost);
    label_total_cost.setFont(new Font(20));
    label_total_cost.setStyle("-fx-font-weight: bold");
    label_total_cost.setAlignment(Pos.BASELINE_CENTER);

    Label label_back_order = new Label();
    label_back_order.setAlignment(Pos.BASELINE_CENTER);

    // If cart contains back ordered items
    if (cart_back_order_flag == 1) {
      label_back_order.setText(
          "This order contains back-ordered products. Non-Back Ordered products will be delivered in 7 days. Back Ordered products will be delivered in 14 days.");
      label_back_order.setFont(new Font(15));
      label_back_order.setTextFill(Color.web("#800000"));
    } else { // If cart does not contain any back-ordered items
      label_back_order.setText("All products in the cart will be delivered in 7 days.");
      label_back_order.setFont(new Font(15));
      label_back_order.setTextFill(Color.web("#006400"));
    }

    VBox products_in_cart_vbox = new VBox();

    int shirt_image_count = 0;
    int jacket_image_count = 0;
    int trousers_image_count = 0;

    // Iterate over every product in cart
    for (Map.Entry<String, String> entry : cart_map.entrySet()) {
      int product_id = Integer.parseInt(entry.getKey());
      int product_quantity = Integer.parseInt(entry.getValue().split(" ")[0]);
      int product_back_order_flag = Integer.parseInt(entry.getValue().split(" ")[1]);

      Label id_text = new Label();
      Label category_text = new Label();
      Label name_text = new Label();
      Label size_text = new Label();
      Label colour_text = new Label();

      Label purchase_quantity_text = new Label();
      Label price_text = new Label();
      Label product_cost_text = new Label();
      Label back_order_text = new Label();
      back_order_text.setFont(new Font(15));

      Label id_value = new Label();
      id_value.setFont(new Font(15));

      Label category_value = new Label();
      category_value.setFont(new Font(15));

      Label name_value = new Label();
      name_value.setFont(new Font(15));

      Label size_value = new Label();
      size_value.setFont(new Font(15));

      Label colour_value = new Label();
      colour_value.setFont(new Font(15));

      Label purchase_quantity_value = new Label();
      purchase_quantity_value.setFont(new Font(15));

      Label price_value = new Label();
      price_value.setFont(new Font(15));

      Label product_cost_value = new Label();
      product_cost_value.setFont(new Font(15));

      id_text.setText("Product ID: ");
      id_text.setFont(new Font(15));
      id_text.setStyle("-fx-font-weight: bold");

      category_text.setText("Category: ");
      category_text.setStyle("-fx-font-weight: bold");
      category_text.setFont(new Font(15));

      name_text.setText("Name: ");
      name_text.setStyle("-fx-font-weight: bold");
      name_text.setFont(new Font(15));

      size_text.setText("Size: ");
      size_text.setStyle("-fx-font-weight: bold");
      size_text.setFont(new Font(15));

      colour_text.setText("Colour: ");
      colour_text.setStyle("-fx-font-weight: bold");
      colour_text.setFont(new Font(15));

      purchase_quantity_text.setText("Purchased Quantity: ");
      purchase_quantity_text.setStyle("-fx-font-weight: bold");
      purchase_quantity_text.setFont(new Font(15));

      price_text.setText("Individual Price: Rs. ");
      price_text.setStyle("-fx-font-weight: bold");
      price_text.setFont(new Font(15));

      product_cost_text.setText("Total Product Cost: Rs. ");
      product_cost_text.setStyle("-fx-font-weight: bold");
      product_cost_text.setFont(new Font(15));

      String matched_product_category = null;

      for (Product product : products_list) {
        if (product.getID() == product_id) {
          id_value.setText(String.valueOf(product.getID()));
          category_value.setText(product.getCategory());
          name_value.setText(product.getName());
          size_value.setText(product.getSize());
          colour_value.setText(product.getColour());
          price_value.setText(String.valueOf(product.getPrice()));
          product_cost_value.setText(
                  String.valueOf(product.getPrice() * product_quantity));

          matched_product_category = product.getCategory();
        }
      }
      purchase_quantity_value.setText(String.valueOf(product_quantity));

      if (product_back_order_flag == 1) {
        back_order_text.setText("Back Ordered Product");
        back_order_text.setTextFill(Color.web("#800000"));
      } else {
        back_order_text.setText("In Stock");
        back_order_text.setTextFill(Color.web("#006400"));
      }

      VBox product_details_vbox = new VBox();
      VBox product_purchase_vbox = new VBox();
      HBox product_details_purchase_hbox = new HBox();

      VBox image_vbox = new VBox();
      Image image = null;

      if (Objects.equals(matched_product_category, "shirt")) {
        shirt_image_count += 1;
        try {
          image = new Image(new FileInputStream("img/shirt/" + shirt_image_count + ".JPG"));
        } catch (FileNotFoundException e) {
          System.out.println("File not found!");
        }
      } else if (Objects.equals(matched_product_category, "jacket")) {
        jacket_image_count += 1;
        try {
          image = new Image(new FileInputStream("img/jacket/" + jacket_image_count + ".JPG"));
        } catch (FileNotFoundException e) {
          System.out.println("File not found!");
        }
      } else if (Objects.equals(matched_product_category, "trousers")) {
        trousers_image_count += 1;
        try {
          image = new Image(new FileInputStream("img/trousers/" + trousers_image_count + ".JPG"));
        } catch (FileNotFoundException e) {
          System.out.println("File not found!");
        }
      }

      if (shirt_image_count >= 10) {
        shirt_image_count = 0;
      }
      if (jacket_image_count >= 10) {
        jacket_image_count = 0;
      }
      if (trousers_image_count >= 10) {
        trousers_image_count = 0;
      }

      ImageView imageView = new ImageView(image);
      imageView.setFitHeight(180);
      imageView.setFitWidth(180);
      image_vbox.getChildren().addAll(imageView);

      HBox hBox_id = new HBox();
      HBox hBox_category = new HBox();
      HBox hBox_name = new HBox();
      HBox hBox_size = new HBox();
      HBox hBox_colour = new HBox();

      HBox hBox_purchase_quantity = new HBox();
      HBox hBox_price = new HBox();
      HBox hBox_product_cost = new HBox();
      HBox hBox_back_order = new HBox();

      hBox_id.getChildren().addAll(id_text, id_value);
      hBox_category.getChildren().addAll(category_text, category_value);
      hBox_name.getChildren().addAll(name_text, name_value);
      hBox_size.getChildren().addAll(size_text, size_value);
      hBox_colour.getChildren().addAll(colour_text, colour_value);

      hBox_purchase_quantity.getChildren().addAll(purchase_quantity_text, purchase_quantity_value);
      hBox_price.getChildren().addAll(price_text, price_value);
      hBox_product_cost.getChildren().addAll(product_cost_text, product_cost_value);
      hBox_back_order.getChildren().addAll(back_order_text);

      product_details_vbox
          .getChildren()
          .addAll(hBox_id, hBox_category, hBox_name, hBox_size, hBox_colour);
      product_purchase_vbox
          .getChildren()
          .addAll(hBox_purchase_quantity, hBox_price, hBox_product_cost, hBox_back_order);

      product_details_vbox.setPrefWidth(500);
      product_purchase_vbox.setPrefWidth(500);

      product_details_vbox.setPadding(new Insets(20, 20, 30, 150));
      product_purchase_vbox.setPadding(new Insets(20, 20, 30, 100));
      image_vbox.setPadding(new Insets(20, 20, 30, 150));

      product_details_vbox.setSpacing(10);
      product_purchase_vbox.setSpacing(10);

      product_details_purchase_hbox
          .getChildren()
          .addAll(image_vbox, product_details_vbox, product_purchase_vbox);
      product_details_purchase_hbox.prefWidthProperty().setValue(300);
      products_in_cart_vbox.getChildren().add(product_details_purchase_hbox);
    }

    Label label_payment = new Label("Payment");
    label_payment.setFont(new Font(30));
    label_payment.setTextFill(Color.web("#0076a3"));
    label_payment.setAlignment(Pos.BASELINE_CENTER);

    VBox vBox_payment_1 = new VBox();
    VBox vBox_payment_2 = new VBox();

    Label card_number_text = new Label("Card Number:  ");
    card_number_text.setFont(new Font(15));
    card_number_text.setStyle("-fx-font-weight: bold");
    TextField card_number_value = new TextField();
    card_number_value.setPromptText("Enter card number");
    HBox hBox_card_number = new HBox(card_number_text, card_number_value);

    Label expiry_month_text = new Label("Expiry Month:  ");
    expiry_month_text.setFont(new Font(15));
    expiry_month_text.setStyle("-fx-font-weight: bold");
    TextField expiry_month_value = new TextField();
    expiry_month_value.setPromptText("Enter expiry month");
    HBox hBox_expiry_month = new HBox(expiry_month_text, expiry_month_value);

    Label expiry_year_text = new Label("Expiry Year:      ");
    expiry_year_text.setFont(new Font(15));
    expiry_year_text.setStyle("-fx-font-weight: bold");
    TextField expiry_year_value = new TextField();
    expiry_year_value.setPromptText("Enter expiry year");
    HBox hBox_expiry_year = new HBox(expiry_year_text, expiry_year_value);

    Label cvv_text = new Label("CVV:     ");
    cvv_text.setFont(new Font(15));
    cvv_text.setStyle("-fx-font-weight: bold");
    TextField cvv_value = new TextField();
    cvv_value.setPromptText("Enter CVV");
    HBox hBox_cvv = new HBox(cvv_text, cvv_value);

    Label name_on_card_text = new Label("Name:  ");
    name_on_card_text.setFont(new Font(15));
    name_on_card_text.setStyle("-fx-font-weight: bold");
    TextField name_on_card_value = new TextField();
    name_on_card_value.setPromptText("Enter name on card");
    HBox hBox_name_on_card = new HBox(name_on_card_text, name_on_card_value);

    Label otp_text = new Label("OTP:     ");
    otp_text.setFont(new Font(15));
    otp_text.setStyle("-fx-font-weight: bold");
    TextField otp_value = new TextField();
    otp_value.setPromptText("Enter OTP");
    HBox hBox_otp = new HBox(otp_text, otp_value);

    vBox_payment_1.getChildren().addAll(hBox_card_number, hBox_expiry_month, hBox_expiry_year);
    vBox_payment_2.getChildren().addAll(hBox_name_on_card, hBox_cvv, hBox_otp);

    vBox_payment_1.setPrefWidth(600);
    vBox_payment_2.setPrefWidth(600);

    vBox_payment_1.setPadding(new Insets(20, 20, 30, 250));
    vBox_payment_2.setPadding(new Insets(20, 20, 30, 150));

    vBox_payment_1.setSpacing(10);
    vBox_payment_2.setSpacing(10);

    HBox final_payment_hbox = new HBox();

    final_payment_hbox.getChildren().addAll(vBox_payment_1, vBox_payment_2);
    final_payment_hbox.prefWidthProperty().setValue(700);

    Label label_payment_error = new Label();

    Button button_make_payment = new Button("Make Payment");

    // When Make Payment button is clicked
    button_make_payment.setOnAction(
        event -> {
          // instructions executed when the button is clicked
          label_payment_error.setText("");
          try {

            Payment payment_object = new Payment();

            long card_number = Long.parseLong(card_number_value.getText());
            int expiry_month = Integer.parseInt(expiry_month_value.getText());
            int expiry_year = Integer.parseInt(expiry_year_value.getText());
            int cvv = Integer.parseInt(cvv_value.getText());
            int otp = Integer.parseInt(otp_value.getText());
            String name_on_card = name_on_card_value.getText();

            // Error handling
            if (String.valueOf(card_number).length() != 16) {
              label_payment_error.setFont(new Font(15));
              label_payment_error.setTextFill(Color.web("#800000"));
              label_payment_error.setText("Incorrect card number! Please verify.");
              return;
            }

            if (expiry_month > 12 || expiry_month < 1) {
              label_payment_error.setFont(new Font(15));
              label_payment_error.setTextFill(Color.web("#800000"));
              label_payment_error.setText("Incorrect expiry month! Please verify.");
              return;
            }

            if (expiry_year > 2040 || expiry_year < 2021) {
              label_payment_error.setFont(new Font(15));
              label_payment_error.setTextFill(Color.web("#800000"));
              label_payment_error.setText("Incorrect expiry year! Please verify.");
              return;
            }

            if (String.valueOf(cvv).length() != 3) {
              label_payment_error.setFont(new Font(15));
              label_payment_error.setTextFill(Color.web("#800000"));
              label_payment_error.setText("Incorrect CVV! Please verify.");
              return;
            }

            if (String.valueOf(otp).length() != 6) {
              label_payment_error.setFont(new Font(15));
              label_payment_error.setTextFill(Color.web("#800000"));
              label_payment_error.setText("Incorrect OTP! Please verify.");
              return;
            }

            payment_object.addPaymentDetails(
                customer_id, card_number, expiry_month, expiry_year, cvv, name_on_card, otp);

            label_payment_error.setTextFill(Color.web("#006400"));
            label_payment_error.setText(
                "Payment received successfully! Please close the window can continue browsing.");
            label_payment_error.setFont(new Font(15));

          } catch (NumberFormatException e) { // catch NumberFormatException
            label_payment_error.setFont(new Font(15));
            label_payment_error.setTextFill(Color.web("#800000"));
            label_payment_error.setText(
                "All values except the name on card must be a numerical value. Please verify.");
          }
        });

    vbox_cart
        .getChildren()
        .addAll(
            vbox_customer_details,
            secondLabel,
            label_total_cost,
            label_back_order,
            products_in_cart_vbox,
            label_payment,
            final_payment_hbox,
            button_make_payment,
            label_payment_error);
    vbox_cart.prefWidthProperty().bind(cartWindow.widthProperty().multiply(0.99));
    vbox_cart.setPadding(new Insets(20, 20, 50, 20));
    vbox_cart.setSpacing(20);
    vbox_cart.setAlignment(Pos.CENTER);

    // Set header and footer
    Image adelaide_premium_header = new Image(new FileInputStream("img/adelaide_premium.JPG"));
    ImageView imageView_header = new ImageView(adelaide_premium_header);
    imageView_header.setFitHeight(430);
    imageView_header.setFitWidth(1260);

    Image adelaide_premium_footer =
        new Image(new FileInputStream("img/adelaide_premium_footer.JPG"));
    ImageView imageView_footer = new ImageView(adelaide_premium_footer);
    imageView_footer.setFitHeight(320);
    imageView_footer.setFitWidth(1260);

    VBox vBox_with_image = new VBox();
    vBox_with_image.getChildren().addAll(imageView_header, vbox_cart, imageView_footer);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vBox_with_image);

    Scene cartScene = new Scene(scrollPane, 1275, 655);

    cartWindow.setScene(cartScene);

    cartWindow.show();
    vbox_cart.requestFocus();

    order_object.addOrderToDatabase(
        customer_id, products_for_order_DB, total_cost, Controller.getCart_back_order_flag());

    customer_object.addCustomerPurchaseQuantities(
        customer_id, new_shirt_count, new_jacket_count, new_trousers_count);

    for (Map.Entry<String, String> entry : cart_map.entrySet()) {
      int product_i = Integer.parseInt(entry.getKey());
      int product_purchase_quantity = Integer.parseInt(entry.getValue().split(" ")[0]);
      product_object.updateProductQuantity(product_i, product_purchase_quantity, '-');
    }

    customer_verification_label.setText("");
    Controller.setCustomer_found_flag(0);
  }

  /***
   * Verifies the customer when the Verify Customer button is pressed
   * Check if customer ID is present in CUSTOMERS table in the database
   * If customer is not present, request for registration
   * @param actionEvent Action Event when Verify Customer button is clicked
   */
  @FXML
  protected void onVerifyCustomerClick(ActionEvent actionEvent) {
    int entered_customer_id; // Stores customer ID entered by user

    Controller.setCustomer_found_flag(0);
    customer_verification_label.setText("");

    try {
      entered_customer_id = Integer.parseInt(customer_id_value.getText());

      // Error checking
      if (String.valueOf(entered_customer_id).equals("")) {
        customer_verification_label.setTextFill(Color.web("#800000"));
        customer_verification_label.setText("Please enter a valid customer ID!");
        return;
      }

      // Check in Customer ID is present in the CUSTOMERS table in the database
      for (int i = 0; i < Customer.customers_list.size(); i++) {
        if (Customer.customers_list.get(i).getCustomer_ID() == entered_customer_id) {
          Controller.setCustomer_found_flag(1);
          Controller.setCustomer_id(entered_customer_id);
          customer_verification_label.setTextFill(Color.web("#006400"));
          customer_verification_label.setText(
              "Customer verified successfully. Proceed with the order.");
        }
      }

      // If customer is not found
      if (Controller.getCustomer_found_flag() == 0) {
        customer_verification_label.setTextFill(Color.web("#800000"));
        customer_verification_label.setText(
            "Unregistered customer. Please request him/her for registration.");
      }
    } catch (NumberFormatException e) { // catch NumberFormatException
      customer_verification_label.setTextFill(Color.web("#800000"));
      customer_verification_label.setText("Customer ID needs to be an integer. Please verify.");
    }
  }

  /***
   * Generate Stocktake report showing details of each product when Stocktake report menu is clicked
   * @param actionEvent Action Event when Stocktake report menu is clicked
   * @throws FileNotFoundException When files cannot be read properly
   */
  @FXML
  protected void onStocktakeClick(ActionEvent actionEvent) throws FileNotFoundException {
    // New window (Stage)
    Stage stocktakeWindow = new Stage();
    stocktakeWindow.setTitle("Stocktake Report");

    // Design Stocktake Report
    VBox vbox_stocktake = new VBox();
    vbox_stocktake.setSpacing(30);
    vbox_stocktake.setPadding(new Insets(50, 60, 50, 60));

    HBox hbox_table = new HBox();
    VBox vBox_id = new VBox();
    vBox_id.setSpacing(10);
    VBox vBox_category = new VBox();
    vBox_category.setSpacing(10);
    VBox vBox_name = new VBox();
    vBox_name.setSpacing(10);
    VBox vBox_size = new VBox();
    vBox_size.setSpacing(10);
    VBox vBox_colour = new VBox();
    vBox_colour.setSpacing(10);
    VBox vBox_quantity = new VBox();
    vBox_quantity.setSpacing(10);
    VBox vBox_price = new VBox();
    vBox_price.setSpacing(10);

    Label label_id_column = new Label("Product ID");
    label_id_column.setStyle("-fx-font-weight: bold");
    label_id_column.setFont(new Font(15));
    vBox_id.getChildren().addAll(label_id_column);
    vBox_id.setAlignment(Pos.CENTER);

    Label label_category_column = new Label("Product Category");
    label_category_column.setStyle("-fx-font-weight: bold");
    label_category_column.setFont(new Font(15));
    vBox_category.getChildren().addAll(label_category_column);
    vBox_category.setAlignment(Pos.CENTER);

    Label label_name_column = new Label("Product Name");
    label_name_column.setStyle("-fx-font-weight: bold");
    label_name_column.setFont(new Font(15));
    vBox_name.getChildren().addAll(label_name_column);
    vBox_name.setAlignment(Pos.CENTER);

    Label label_size_column = new Label("Product Size");
    label_size_column.setStyle("-fx-font-weight: bold");
    label_size_column.setFont(new Font(15));
    vBox_size.getChildren().addAll(label_size_column);
    vBox_size.setAlignment(Pos.CENTER);

    Label label_colour_column = new Label("Product Colour");
    label_colour_column.setStyle("-fx-font-weight: bold");
    label_colour_column.setFont(new Font(15));
    vBox_colour.getChildren().addAll(label_colour_column);
    vBox_colour.setAlignment(Pos.CENTER);

    Label label_quantity_column = new Label("Available Quantity");
    label_quantity_column.setStyle("-fx-font-weight: bold");
    label_quantity_column.setFont(new Font(15));
    vBox_quantity.getChildren().addAll(label_quantity_column);
    vBox_quantity.setAlignment(Pos.CENTER);

    Label label_price_column = new Label("Per Unit Price (in Rs.)");
    label_price_column.setStyle("-fx-font-weight: bold");
    label_price_column.setFont(new Font(15));
    vBox_price.getChildren().addAll(label_price_column);
    vBox_price.setAlignment(Pos.CENTER);

    // Iterate over all products in the PRODUCTS table in the database and add them to the stocktake report
    for (Product product : products_list) {

      Label label_id = new Label(String.valueOf(product.getID()));
      label_id.setFont(new Font(15));
      vBox_id.getChildren().addAll(label_id);

      Label label_category =
              new Label(String.valueOf(product.getCategory().toUpperCase()));
      label_category.setFont(new Font(15));
      vBox_category.getChildren().addAll(label_category);

      Label label_name = new Label(String.valueOf(product.getName()));
      label_name.setFont(new Font(15));
      vBox_name.getChildren().addAll(label_name);

      Label label_size = new Label(String.valueOf(product.getSize()));
      label_size.setFont(new Font(15));
      vBox_size.getChildren().addAll(label_size);

      Label label_colour = new Label(String.valueOf(product.getColour()));
      label_colour.setFont(new Font(15));
      vBox_colour.getChildren().addAll(label_colour);

      Label label_quantity = new Label(String.valueOf(product.getQuantity()));
      label_quantity.setFont(new Font(15));
      vBox_quantity.getChildren().addAll(label_quantity);

      Label label_price = new Label(String.valueOf(product.getPrice()));
      label_price.setFont(new Font(15));
      vBox_price.getChildren().addAll(label_price);
    }

    hbox_table.setSpacing(30);
    hbox_table
        .getChildren()
        .addAll(
            vBox_id, vBox_category, vBox_name, vBox_size, vBox_colour, vBox_quantity, vBox_price);
    hbox_table.setAlignment(Pos.CENTER);

    Label stocktake_report_title = new Label("Stocktake Report");
    stocktake_report_title.setFont(new Font(30));
    stocktake_report_title.setTextFill(Color.web("#0076a3"));

    vbox_stocktake.getChildren().addAll(stocktake_report_title, hbox_table);
    vbox_stocktake.setAlignment(Pos.CENTER);

    // Set header and footer
    Image adelaide_premium_header = new Image(new FileInputStream("img/adelaide_premium.JPG"));
    ImageView imageView_header = new ImageView(adelaide_premium_header);
    imageView_header.setFitHeight(430);
    imageView_header.setFitWidth(1260);

    Image adelaide_premium_footer =
        new Image(new FileInputStream("img/adelaide_premium_footer.JPG"));
    ImageView imageView_footer = new ImageView(adelaide_premium_footer);
    imageView_footer.setFitHeight(320);
    imageView_footer.setFitWidth(1260);

    VBox vBox_with_image = new VBox();
    vBox_with_image.getChildren().addAll(imageView_header, vbox_stocktake, imageView_footer);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vBox_with_image);

    Scene stocktakeScene = new Scene(scrollPane, 1275, 655);

    stocktakeWindow.setScene(stocktakeScene);

    stocktakeWindow.show();
  }

  /***
   * Generate Customer Demographics Report when the Customer Demographics report menu is clicked
   * Shows how the purchase behaviour varies across customers
   * @param actionEvent Action Event when Customer Demographics report menu is clicked
   * @throws ParseException When TextField's input type do not match with the required type
   * @throws FileNotFoundException When files cannot be read properly
   */
  @FXML
  protected void onCustomerDemographicsClick(ActionEvent actionEvent)
      throws ParseException, FileNotFoundException {
    // New window (Stage)
    Stage customerDemographicsWindow = new Stage();
    customerDemographicsWindow.setTitle("Customer Demographics Report");

    // Design Customer Demographics Report
    VBox vbox_customer_demographics = new VBox();
    vbox_customer_demographics.setSpacing(30);
    vbox_customer_demographics.setPadding(new Insets(50, 100, 50, 100));

    // Initialize values of fields to find total count
    int age_15_less_shirt = 0;
    int age_15_less_jacket = 0;
    int age_15_less_trouser = 0;

    int age_16_25_shirt = 0;
    int age_16_25_jacket = 0;
    int age_16_25_trouser = 0;

    int age_26_40_shirt = 0;
    int age_26_40_jacket = 0;
    int age_26_40_trouser = 0;

    int age_41_60_shirt = 0;
    int age_41_60_jacket = 0;
    int age_41_60_trouser = 0;

    int age_61_plus_shirt = 0;
    int age_61_plus_jacket = 0;
    int age_61_plus_trouser = 0;

    // Iterate over all customers to find the product quantities purchased across the various age groups
    for (int i = 0; i < Customer.customers_list.size(); i++) {
      String dob = Customer.customers_list.get(i).getDOB();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date date = formatter.parse(dob);
      Instant instant = date.toInstant();
      ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
      LocalDate givenDate = zone.toLocalDate();

      Period period = Period.between(givenDate, LocalDate.now());
      int age = period.getYears();

      if (age <= 15) { // 15-
        age_15_less_shirt += Customer.customers_list.get(i).getPast_purchase_shirt();
        age_15_less_jacket += Customer.customers_list.get(i).getPast_purchase_jacket();
        age_15_less_trouser += Customer.customers_list.get(i).getPast_purchase_trousers();
      } else if (age <= 25) { // 16 to 25
        age_16_25_shirt += Customer.customers_list.get(i).getPast_purchase_shirt();
        age_16_25_jacket += Customer.customers_list.get(i).getPast_purchase_jacket();
        age_16_25_trouser += Customer.customers_list.get(i).getPast_purchase_trousers();
      } else if (age <= 40) { // 26 to 40
        age_26_40_shirt += Customer.customers_list.get(i).getPast_purchase_shirt();
        age_26_40_jacket += Customer.customers_list.get(i).getPast_purchase_jacket();
        age_26_40_trouser += Customer.customers_list.get(i).getPast_purchase_trousers();
      } else if (age <= 60) { // 41 to 60
        age_41_60_shirt += Customer.customers_list.get(i).getPast_purchase_shirt();
        age_41_60_jacket += Customer.customers_list.get(i).getPast_purchase_jacket();
        age_41_60_trouser += Customer.customers_list.get(i).getPast_purchase_trousers();
      } else { // 60+
        age_61_plus_shirt += Customer.customers_list.get(i).getPast_purchase_shirt();
        age_61_plus_jacket += Customer.customers_list.get(i).getPast_purchase_jacket();
        age_61_plus_trouser += Customer.customers_list.get(i).getPast_purchase_trousers();
      }
    }

    // Defining the x axis
    CategoryAxis xAxis = new CategoryAxis();

    xAxis.setCategories(
        FXCollections.<String>observableArrayList(
            Arrays.asList("15-", "16-25", "26-40", "41-60", "61+")));
    xAxis.setLabel("Customer Age");

    // Defining the y axis
    NumberAxis yAxis = new NumberAxis();
    yAxis.setLabel("Purchased Quantities");

    // Create a StackedBarChart to show details of Customer Demographics Report
    StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);
    stackedBarChart.setTitle("Historic Products Purchase by Customer's Age\n\n\n\n");

    // Initiate Shirt series for stacked bar chart
    final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
    series1.setName("     Shirt"); // set name to series

    // Add data to series
    series1.getData().add(new XYChart.Data<>("15-", age_15_less_shirt));
    series1.getData().add(new XYChart.Data<>("16-25", age_16_25_shirt));
    series1.getData().add(new XYChart.Data<>("26-40", age_26_40_shirt));
    series1.getData().add(new XYChart.Data<>("41-60", age_41_60_shirt));
    series1.getData().add(new XYChart.Data<>("61+", age_61_plus_shirt));

    // Initiate Jacket series for stacked bar chart
    final XYChart.Series<String, Number> series2 = new XYChart.Series<>();
    series2.setName("     Jacket"); // set name to series

    // Add data to series
    series2.getData().add(new XYChart.Data<>("15-", age_15_less_jacket));
    series2.getData().add(new XYChart.Data<>("16-25", age_16_25_jacket));
    series2.getData().add(new XYChart.Data<>("26-40", age_26_40_jacket));
    series2.getData().add(new XYChart.Data<>("41-60", age_41_60_jacket));
    series2.getData().add(new XYChart.Data<>("61+", age_61_plus_jacket));

    // Initiate Jacket series for stacked bar chart
    final XYChart.Series<String, Number> series3 = new XYChart.Series<>();
    series3.setName("     Trousers"); // set name to series

    // Add data to series
    series3.getData().add(new XYChart.Data<>("15-", age_15_less_trouser));
    series3.getData().add(new XYChart.Data<>("16-25", age_16_25_trouser));
    series3.getData().add(new XYChart.Data<>("26-40", age_26_40_trouser));
    series3.getData().add(new XYChart.Data<>("41-60", age_41_60_trouser));
    series3.getData().add(new XYChart.Data<>("61+", age_61_plus_trouser));

    // Add all series to stacked bar chart instance
    stackedBarChart.getData().addAll(series1, series2, series3);

    int total_shirts_purchased;
    int total_jackets_purchased;
    int total_trousers_purchased;

    total_shirts_purchased =
        age_15_less_shirt + age_16_25_shirt + age_16_25_shirt + age_41_60_shirt + age_61_plus_shirt;
    total_jackets_purchased =
        age_15_less_jacket
            + age_16_25_jacket
            + age_16_25_jacket
            + age_41_60_jacket
            + age_61_plus_jacket;
    total_trousers_purchased =
        age_15_less_trouser
            + age_16_25_trouser
            + age_16_25_trouser
            + age_41_60_trouser
            + age_61_plus_trouser;

    CategoryAxis xaxis = new CategoryAxis();
    NumberAxis yaxis = new NumberAxis();
    xaxis.setLabel("Product Categories");
    yaxis.setLabel("Quantities By All Customers");

    // Create a BarChart to show details of Customer Demographics Report
    BarChart<String, Integer> barChart = new BarChart(xaxis, yaxis);
    barChart.setTitle("Historic Purchase Of All Customers By Product Categories\n\n\n\n");

    XYChart.Series<String, Integer> series = new XYChart.Series<>();
    series.getData().add(new XYChart.Data("Shirt", total_shirts_purchased));
    series.getData().add(new XYChart.Data("Jacket", total_jackets_purchased));
    series.getData().add(new XYChart.Data("Trousers", total_trousers_purchased));

    barChart.getData().add(series);
    barChart.setLegendVisible(false);

    barChart.setScaleX(1);
    barChart.setScaleY(1);
    stackedBarChart.setScaleX(1);
    stackedBarChart.setScaleY(1);

    HBox hBox_charts = new HBox(stackedBarChart, barChart);
    hBox_charts.setSpacing(50);

    Label customer_demographics_report_title = new Label("Customer Demographics Report");
    customer_demographics_report_title.setFont(new Font(30));
    customer_demographics_report_title.setTextFill(Color.web("#0076a3"));

    vbox_customer_demographics
        .getChildren()
        .addAll(customer_demographics_report_title, hBox_charts);
    vbox_customer_demographics.setAlignment(Pos.CENTER);

    // Set header and footer
    Image adelaide_premium_header = new Image(new FileInputStream("img/adelaide_premium.JPG"));
    ImageView imageView_header = new ImageView(adelaide_premium_header);
    imageView_header.setFitHeight(430);
    imageView_header.setFitWidth(1260);

    Image adelaide_premium_footer =
        new Image(new FileInputStream("img/adelaide_premium_footer.JPG"));
    ImageView imageView_footer = new ImageView(adelaide_premium_footer);
    imageView_footer.setFitHeight(320);
    imageView_footer.setFitWidth(1260);

    VBox vBox_with_image = new VBox();
    vBox_with_image
        .getChildren()
        .addAll(imageView_header, vbox_customer_demographics, imageView_footer);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vBox_with_image);

    Scene customerDemographicsScene = new Scene(scrollPane, 1275, 655);
    customerDemographicsWindow.setScene(customerDemographicsScene);
    customerDemographicsWindow.show();
  }

  /***
   * Generates Quarterly Sales Report when the Quarterly Sales report menu is clicked
   * Shows the sales report for the last quarter (last 90 days)
   * @param actionEvent Action Event when Quarterly Sales report menu is clicked
   * @throws ParseException When TextField's input type do not match with the required type
   * @throws FileNotFoundException When files cannot be read properly
   */
  @FXML
  protected void onQuarterlySalesClicked(ActionEvent actionEvent)
      throws ParseException, FileNotFoundException {
    // New window (Stage)
    Stage quarterlySalesWindow = new Stage();
    quarterlySalesWindow.setTitle("Quarterly Sales Report");

    // Design Quarterly Sales Report
    VBox vbox_quarterly_sales = new VBox();
    vbox_quarterly_sales.setSpacing(30);
    vbox_quarterly_sales.setPadding(new Insets(50, 170, 50, 170));

    LocalDate start_date = LocalDate.now().minusDays(90);
    LocalDate end_date = LocalDate.now();

    Label quarterly_sales_report_title =
        new Label("Quarterly Sales Report from " + start_date + " to " + end_date);
    quarterly_sales_report_title.setFont(new Font(30));
    quarterly_sales_report_title.setTextFill(Color.web("#0076a3"));

    int shirt_sales = 0;
    int jacket_sales = 0;
    int trousers_sales = 0;
    int total_sales;

    // Iterate over all orders to calculate total sales across various categories
    for (int i = 0; i < Order.orders_list.size(); i++) {
      String order_date = Order.orders_list.get(i).getDate();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      Date date = formatter.parse(order_date);
      Instant instant = date.toInstant();
      ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
      LocalDate givenDate = zone.toLocalDate();

      Period period = Period.between(givenDate, LocalDate.now());
      int days = period.getDays();

      if (days <= 90) {
        String[] products_in_order = Order.orders_list.get(i).getProducts().split(" ");
        for (String s : products_in_order) {
          for (int k = 0; k < Product.products_list.size(); k++) {
            if (Product.products_list.get(k).getID() == Integer.parseInt(s)) {
              if (Objects.equals(Product.products_list.get(k).getCategory(), "shirt")) {
                shirt_sales += Product.products_list.get(k).getPrice();
              } else if (Objects.equals(Product.products_list.get(k).getCategory(), "jacket")) {
                jacket_sales += Product.products_list.get(k).getPrice();
              } else if (Objects.equals(Product.products_list.get(k).getCategory(), "trousers")) {
                trousers_sales += Product.products_list.get(k).getPrice();
              }
            }
          }
        }
      }
    }

    total_sales = shirt_sales + jacket_sales + trousers_sales;

    HBox hBox_quarterly_sales = new HBox();
    HBox hBox_table = new HBox();

    Label table_title = new Label("Quarterly Sales Across Categories");
    table_title.setFont(new Font(17));

    hBox_table.setPadding(new Insets(0, 0, 0, 0));
    VBox vBox_piechart = new VBox();

    VBox vBox_table_category = new VBox();
    vBox_table_category.setSpacing(30);

    VBox vBox_table_sales = new VBox();
    vBox_table_sales.setSpacing(30);

    Label label_category = new Label("Product Category");
    label_category.setStyle("-fx-font-weight: bold");
    label_category.setFont(new Font(15));

    Label label_shirt_text = new Label("Shirt");
    label_shirt_text.setFont(new Font(15));
    Label label_jacket_text = new Label("Jacket");
    label_jacket_text.setFont(new Font(15));
    Label label_trousers_text = new Label("Trousers");
    label_trousers_text.setFont(new Font(15));
    Label label_all_category_text = new Label("All Categories");
    label_all_category_text.setFont(new Font(15));

    Label label_sales = new Label("Total Sales In Previous Quarter");
    label_sales.setStyle("-fx-font-weight: bold");
    label_sales.setFont(new Font(15));

    Label label_shirt_value = new Label("Rs. " + String.valueOf(shirt_sales));
    label_shirt_value.setFont(new Font(15));
    Label label_jacket_value = new Label("Rs. " + String.valueOf(jacket_sales));
    label_jacket_value.setFont(new Font(15));
    Label label_trousers_value = new Label("Rs. " + String.valueOf(trousers_sales));
    label_trousers_value.setFont(new Font(15));
    Label label_all_category_value = new Label("Rs. " + String.valueOf(total_sales));
    label_all_category_value.setFont(new Font(15));

    vBox_table_category
        .getChildren()
        .addAll(
            label_category,
            label_shirt_text,
            label_jacket_text,
            label_trousers_text,
            label_all_category_text);
    vBox_table_sales
        .getChildren()
        .addAll(
            label_sales,
            label_shirt_value,
            label_jacket_value,
            label_trousers_value,
            label_all_category_value);

    // Preparing ObservbleList object
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(
            new PieChart.Data(
                "Shirts (" + Math.round(((double) shirt_sales / total_sales) * 100) + "%)",
                shirt_sales),
            new PieChart.Data(
                "Jackets (" + Math.round(((double) jacket_sales / total_sales) * 100) + "%)",
                jacket_sales),
            new PieChart.Data(
                "Trousers (" + Math.round(((double) trousers_sales / total_sales) * 100) + "%)",
                trousers_sales));

    // Creating a Pie chart
    PieChart pieChart = new PieChart(pieChartData);

    // Setting the title of the Pie chart
    pieChart.setTitle("Pie Chart Showing Quarterly Sales Report");

    // setting the direction to arrange the data
    pieChart.setClockwise(true);

    // Setting the length of the label line
    pieChart.setLabelLineLength(50);

    // Setting the labels of the pie chart visible
    pieChart.setLabelsVisible(true);

    // Setting the start angle of the pie chart
    pieChart.setStartAngle(180);

    vBox_piechart.getChildren().addAll(pieChart);

    vBox_table_sales.setAlignment(Pos.CENTER);
    vBox_table_category.setAlignment(Pos.CENTER);
    hBox_table.getChildren().addAll(vBox_table_category, vBox_table_sales);
    hBox_table.setSpacing(30);

    VBox vBox_table_with_title = new VBox();
    vBox_table_with_title.getChildren().addAll(table_title, hBox_table);
    vBox_table_with_title.setSpacing(50);
    vBox_table_with_title.setPadding(new Insets(5, 0, 200, 0));
    vBox_table_with_title.setAlignment(Pos.CENTER);

    hBox_quarterly_sales.getChildren().addAll(vBox_table_with_title, vBox_piechart);
    hBox_quarterly_sales.setSpacing(100);

    vbox_quarterly_sales.getChildren().addAll(quarterly_sales_report_title, hBox_quarterly_sales);
    vbox_quarterly_sales.setSpacing(50);
    vbox_quarterly_sales.setAlignment(Pos.CENTER);

    // Set header and footer
    Image adelaide_premium_header = new Image(new FileInputStream("img/adelaide_premium.JPG"));
    ImageView imageView_header = new ImageView(adelaide_premium_header);
    imageView_header.setFitHeight(430);
    imageView_header.setFitWidth(1260);

    Image adelaide_premium_footer =
        new Image(new FileInputStream("img/adelaide_premium_footer.JPG"));
    ImageView imageView_footer = new ImageView(adelaide_premium_footer);
    imageView_footer.setFitHeight(320);
    imageView_footer.setFitWidth(1260);

    VBox vBox_with_image = new VBox();
    vBox_with_image.getChildren().addAll(imageView_header, vbox_quarterly_sales, imageView_footer);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vBox_with_image);

    Scene quarterlySalesScene = new Scene(scrollPane, 1275, 655);

    quarterlySalesWindow.setScene(quarterlySalesScene);

    quarterlySalesWindow.show();
  }

  /***
   * Sorts HashMap based upon values
   * @param hm HashMap to sort based on values
   * @return Sorted HashMap based on values
   */
  protected HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
    // Create a list from elements of HashMap
    List<Map.Entry<String, Integer>> list =
        new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

    // Sort the list
    Collections.sort(
        list,
        new Comparator<Map.Entry<String, Integer>>() {
          public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return (o2.getValue()).compareTo(o1.getValue());
          }
        });

    // put data from sorted list to hashmap
    HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> aa : list) {
      temp.put(aa.getKey(), aa.getValue());
    }
    return temp;
  }

  /***
   * Generates Monthly Report when the Monthly report menu is clicked
   * Shows the top 10 items in each category in the previous month
   * @param actionEvent Action Event when Monthly report menu is clicked
   * @throws FileNotFoundException When files cannot be read properly
   */
  @FXML
  protected void onMonthClick(ActionEvent actionEvent) throws FileNotFoundException {
    // New window (Stage)
    Stage monthWindow = new Stage();
    monthWindow.setTitle("Monthly Report");

    // Design the Monthly Report
    VBox vbox_month = new VBox();
    vbox_month.setSpacing(30);
    vbox_month.setPadding(new Insets(50, 30, 50, 30));

    LocalDate now = LocalDate.now(); // current date
    LocalDate earlier = now.minusMonths(1); // current date in last month

    String previous_month = earlier.getMonth().toString(); // java.time.Month = OCTOBER

    Label month_report_title =
        new Label("Monthly Report - Top 10 Products In Each Category For " + previous_month);
    month_report_title.setFont(new Font(30));
    month_report_title.setTextFill(Color.web("#0076a3"));

    // Create HashMaps for each category to store number of units sold for each of them
    HashMap<String, Integer> shirt_count_map = new HashMap<>();
    HashMap<String, Integer> jacket_count_map = new HashMap<>();
    HashMap<String, Integer> trousers_count_map = new HashMap<>();

    // Iterate over all items in the orders_list to count the number of units sold for each category
    for (int i = 0; i < Order.orders_list.size(); i++) {
      String order_date = Order.orders_list.get(i).getDate();

      int past_month = Integer.parseInt(order_date.split("-")[1]);
      int current_month = Integer.parseInt(LocalDate.now().toString().split("-")[1]);

      if (current_month - past_month == 1) {
        String[] products_in_order = Order.orders_list.get(i).getProducts().split(" ");
        for (String s : products_in_order) {
          for (int k = 0; k < Product.products_list.size(); k++) {
            if (Product.products_list.get(k).getID() == Integer.parseInt(s)) {
              if (Objects.equals(Product.products_list.get(k).getCategory(), "shirt")) {
                if (shirt_count_map.containsKey(s)) {
                  shirt_count_map.put(
                          s, shirt_count_map.get(s) + 1);
                } else {
                  shirt_count_map.put(s, 1);
                }
              } else if (Objects.equals(Product.products_list.get(k).getCategory(), "jacket")) {
                if (jacket_count_map.containsKey(s)) {
                  jacket_count_map.put(
                          s, jacket_count_map.get(s) + 1);
                } else {
                  jacket_count_map.put(s, 1);
                }
              } else if (Objects.equals(Product.products_list.get(k).getCategory(), "trousers")) {
                if (trousers_count_map.containsKey(s)) {
                  trousers_count_map.put(
                          s, trousers_count_map.get(s) + 1);
                } else {
                  trousers_count_map.put(s, 1);
                }
              }
            }
          }
        }
      }
    }

    Map<String, Integer> sorted_shirt_count_map = sortByValue(shirt_count_map);
    Map<String, Integer> sorted_jacket_count_map = sortByValue(jacket_count_map);
    Map<String, Integer> sorted_trousers_count_map = sortByValue(trousers_count_map);

    VBox vBox_top_10_data = new VBox();

    // Design for top 10 Shirts
    Label label_top_10_shirt = new Label("Top 10 Shirts");
    label_top_10_shirt.setFont(new Font(20));
    label_top_10_shirt.setTextFill(Color.web("#800000"));

    HBox hBox_top_10_shirt = new HBox();

    VBox vBox_top_10_shirt_PID = new VBox();
    vBox_top_10_shirt_PID.setAlignment(Pos.CENTER);
    vBox_top_10_shirt_PID.setSpacing(10);
    vBox_top_10_shirt_PID.setPrefWidth(90);
    VBox vBox_top_10_shirt_name = new VBox();
    vBox_top_10_shirt_name.setAlignment(Pos.CENTER);
    vBox_top_10_shirt_name.setSpacing(10);
    vBox_top_10_shirt_name.setPrefWidth(220);
    VBox vBox_top_10_shirt_size = new VBox();
    vBox_top_10_shirt_size.setAlignment(Pos.CENTER);
    vBox_top_10_shirt_size.setSpacing(10);
    vBox_top_10_shirt_size.setPrefWidth(100);
    VBox vBox_top_10_shirt_colour = new VBox();
    vBox_top_10_shirt_colour.setAlignment(Pos.CENTER);
    vBox_top_10_shirt_colour.setSpacing(10);
    vBox_top_10_shirt_colour.setPrefWidth(120);
    VBox vBox_top_10_shirt_price = new VBox();
    vBox_top_10_shirt_price.setAlignment(Pos.CENTER);
    vBox_top_10_shirt_price.setSpacing(10);
    vBox_top_10_shirt_price.setPrefWidth(150);
    VBox vBox_top_10_shirt_purchase_quantity = new VBox();
    vBox_top_10_shirt_purchase_quantity.setAlignment(Pos.CENTER);
    vBox_top_10_shirt_purchase_quantity.setSpacing(10);
    vBox_top_10_shirt_purchase_quantity.setPrefWidth(220);

    Label product_id_1 = new Label("Product ID");
    product_id_1.setStyle("-fx-font-weight: bold");
    product_id_1.setFont(new Font(15));

    Label product_name_1 = new Label("Product Name");
    product_name_1.setStyle("-fx-font-weight: bold");
    product_name_1.setFont(new Font(15));

    Label product_size_1 = new Label("Product Size");
    product_size_1.setStyle("-fx-font-weight: bold");
    product_size_1.setFont(new Font(15));

    Label product_colour_1 = new Label("Product Colour");
    product_colour_1.setStyle("-fx-font-weight: bold");
    product_colour_1.setFont(new Font(15));

    Label product_price_1 = new Label("Per Unit Price (in Rs.)");
    product_price_1.setStyle("-fx-font-weight: bold");
    product_price_1.setFont(new Font(15));

    Label product_quantity_1 = new Label("Quantities Sold In Last Month");
    product_quantity_1.setStyle("-fx-font-weight: bold");
    product_quantity_1.setFont(new Font(15));

    vBox_top_10_shirt_PID.getChildren().addAll(product_id_1);
    vBox_top_10_shirt_name.getChildren().addAll(product_name_1);
    vBox_top_10_shirt_size.getChildren().addAll(product_size_1);
    vBox_top_10_shirt_colour.getChildren().addAll(product_colour_1);
    vBox_top_10_shirt_price.getChildren().addAll(product_price_1);
    vBox_top_10_shirt_purchase_quantity.getChildren().addAll(product_quantity_1);

    for (Map.Entry<String, Integer> en : sorted_shirt_count_map.entrySet()) {
      for (Product product : products_list) {
        if (product.getID() == Integer.parseInt(en.getKey())) {

          Label pid = new Label(String.valueOf(product.getID()));
          pid.setFont(new Font(15));
          vBox_top_10_shirt_PID.getChildren().addAll(pid);

          Label name = new Label(String.valueOf(product.getName()));
          name.setFont(new Font(15));
          vBox_top_10_shirt_name.getChildren().addAll(name);

          Label size = new Label(String.valueOf(product.getSize()));
          size.setFont(new Font(15));
          vBox_top_10_shirt_size.getChildren().addAll(size);

          Label colour = new Label(String.valueOf(product.getColour()));
          colour.setFont(new Font(15));
          vBox_top_10_shirt_colour.getChildren().addAll(colour);

          Label price = new Label(String.valueOf(product.getPrice()));
          price.setFont(new Font(15));
          vBox_top_10_shirt_price.getChildren().addAll(price);

          Label quantity = new Label(String.valueOf(en.getValue()));
          quantity.setFont(new Font(15));
          vBox_top_10_shirt_purchase_quantity.getChildren().addAll(quantity);
        }
      }
    }

    hBox_top_10_shirt
        .getChildren()
        .addAll(
            vBox_top_10_shirt_PID,
            vBox_top_10_shirt_name,
            vBox_top_10_shirt_size,
            vBox_top_10_shirt_colour,
            vBox_top_10_shirt_price,
            vBox_top_10_shirt_purchase_quantity);
    hBox_top_10_shirt.setSpacing(60);

    // Design for top 10 Jackets
    Label label_top_10_jacket = new Label("Top 10 Jackets");
    label_top_10_jacket.setFont(new Font(20));
    label_top_10_jacket.setTextFill(Color.web("#800000"));

    HBox hBox_top_10_jacket = new HBox();

    VBox vBox_top_10_jacket_PID = new VBox();
    vBox_top_10_jacket_PID.setAlignment(Pos.CENTER);
    vBox_top_10_jacket_PID.setSpacing(10);
    vBox_top_10_jacket_PID.setPrefWidth(90);
    VBox vBox_top_10_jacket_name = new VBox();
    vBox_top_10_jacket_name.setAlignment(Pos.CENTER);
    vBox_top_10_jacket_name.setSpacing(10);
    vBox_top_10_jacket_name.setPrefWidth(220);
    VBox vBox_top_10_jacket_size = new VBox();
    vBox_top_10_jacket_size.setAlignment(Pos.CENTER);
    vBox_top_10_jacket_size.setSpacing(10);
    vBox_top_10_jacket_size.setPrefWidth(100);
    VBox vBox_top_10_jacket_colour = new VBox();
    vBox_top_10_jacket_colour.setAlignment(Pos.CENTER);
    vBox_top_10_jacket_colour.setSpacing(10);
    vBox_top_10_jacket_colour.setPrefWidth(120);
    VBox vBox_top_10_jacket_price = new VBox();
    vBox_top_10_jacket_price.setAlignment(Pos.CENTER);
    vBox_top_10_jacket_price.setSpacing(10);
    vBox_top_10_jacket_price.setPrefWidth(150);
    VBox vBox_top_10_jacket_purchase_quantity = new VBox();
    vBox_top_10_jacket_purchase_quantity.setAlignment(Pos.CENTER);
    vBox_top_10_jacket_purchase_quantity.setSpacing(10);
    vBox_top_10_jacket_purchase_quantity.setPrefWidth(220);

    Label product_id_2 = new Label("Product ID");
    product_id_2.setStyle("-fx-font-weight: bold");
    product_id_2.setFont(new Font(15));

    Label product_name_2 = new Label("Product Name");
    product_name_2.setStyle("-fx-font-weight: bold");
    product_name_2.setFont(new Font(15));

    Label product_size_2 = new Label("Product Size");
    product_size_2.setStyle("-fx-font-weight: bold");
    product_size_2.setFont(new Font(15));

    Label product_colour_2 = new Label("Product Colour");
    product_colour_2.setStyle("-fx-font-weight: bold");
    product_colour_2.setFont(new Font(15));

    Label product_price_2 = new Label("Per Unit Price (in Rs.)");
    product_price_2.setStyle("-fx-font-weight: bold");
    product_price_2.setFont(new Font(15));

    Label product_quantity_2 = new Label("Quantities Sold In Last Month");
    product_quantity_2.setStyle("-fx-font-weight: bold");
    product_quantity_2.setFont(new Font(15));

    vBox_top_10_jacket_PID.getChildren().addAll(product_id_2);
    vBox_top_10_jacket_name.getChildren().addAll(product_name_2);
    vBox_top_10_jacket_size.getChildren().addAll(product_size_2);
    vBox_top_10_jacket_colour.getChildren().addAll(product_colour_2);
    vBox_top_10_jacket_price.getChildren().addAll(product_price_2);
    vBox_top_10_jacket_purchase_quantity.getChildren().addAll(product_quantity_2);

    for (Map.Entry<String, Integer> en : sorted_jacket_count_map.entrySet()) {
      for (Product product : products_list) {
        if (product.getID() == Integer.parseInt(en.getKey())) {
          Label pid = new Label(String.valueOf(product.getID()));
          pid.setFont(new Font(15));
          vBox_top_10_jacket_PID.getChildren().addAll(pid);

          Label name = new Label(String.valueOf(product.getName()));
          name.setFont(new Font(15));
          vBox_top_10_jacket_name.getChildren().addAll(name);

          Label size = new Label(String.valueOf(product.getSize()));
          size.setFont(new Font(15));
          vBox_top_10_jacket_size.getChildren().addAll(size);

          Label colour = new Label(String.valueOf(product.getColour()));
          colour.setFont(new Font(15));
          vBox_top_10_jacket_colour.getChildren().addAll(colour);

          Label price = new Label(String.valueOf(product.getPrice()));
          price.setFont(new Font(15));
          vBox_top_10_jacket_price.getChildren().addAll(price);

          Label quantity = new Label(String.valueOf(en.getValue()));
          quantity.setFont(new Font(15));
          vBox_top_10_jacket_purchase_quantity.getChildren().addAll(quantity);
        }
      }
    }

    hBox_top_10_jacket
        .getChildren()
        .addAll(
            vBox_top_10_jacket_PID,
            vBox_top_10_jacket_name,
            vBox_top_10_jacket_size,
            vBox_top_10_jacket_colour,
            vBox_top_10_jacket_price,
            vBox_top_10_jacket_purchase_quantity);
    hBox_top_10_jacket.setSpacing(60);

    // Design for top 10 Trousers
    Label label_top_10_trousers = new Label("Top 10 Trousers");
    label_top_10_trousers.setFont(new Font(20));
    label_top_10_trousers.setTextFill(Color.web("#800000"));

    HBox hBox_top_10_trousers = new HBox();

    VBox vBox_top_10_trousers_PID = new VBox();
    vBox_top_10_trousers_PID.setAlignment(Pos.CENTER);
    vBox_top_10_trousers_PID.setSpacing(10);
    vBox_top_10_trousers_PID.setPrefWidth(90);

    VBox vBox_top_10_trousers_name = new VBox();
    vBox_top_10_trousers_name.setAlignment(Pos.CENTER);
    vBox_top_10_trousers_name.setSpacing(10);
    vBox_top_10_trousers_name.setPrefWidth(220);

    VBox vBox_top_10_trousers_size = new VBox();
    vBox_top_10_trousers_size.setAlignment(Pos.CENTER);
    vBox_top_10_trousers_size.setSpacing(10);
    vBox_top_10_trousers_size.setPrefWidth(100);

    VBox vBox_top_10_trousers_colour = new VBox();
    vBox_top_10_trousers_colour.setAlignment(Pos.CENTER);
    vBox_top_10_trousers_colour.setSpacing(10);
    vBox_top_10_trousers_colour.setPrefWidth(120);

    VBox vBox_top_10_trousers_price = new VBox();
    vBox_top_10_trousers_price.setAlignment(Pos.CENTER);
    vBox_top_10_trousers_price.setSpacing(10);
    vBox_top_10_trousers_price.setPrefWidth(150);

    VBox vBox_top_10_trousers_purchase_quantity = new VBox();
    vBox_top_10_trousers_purchase_quantity.setAlignment(Pos.CENTER);
    vBox_top_10_trousers_purchase_quantity.setSpacing(10);
    vBox_top_10_trousers_purchase_quantity.setPrefWidth(220);

    Label product_id_3 = new Label("Product ID");
    product_id_3.setStyle("-fx-font-weight: bold");
    product_id_3.setFont(new Font(15));

    Label product_name_3 = new Label("Product Name");
    product_name_3.setStyle("-fx-font-weight: bold");
    product_name_3.setFont(new Font(15));

    Label product_size_3 = new Label("Product Size");
    product_size_3.setStyle("-fx-font-weight: bold");
    product_size_3.setFont(new Font(15));

    Label product_colour_3 = new Label("Product Colour");
    product_colour_3.setStyle("-fx-font-weight: bold");
    product_colour_3.setFont(new Font(15));

    Label product_price_3 = new Label("Per Unit Price (in Rs.)");
    product_price_3.setStyle("-fx-font-weight: bold");
    product_price_3.setFont(new Font(15));

    Label product_quantity_3 = new Label("Quantities Sold In Last Month");
    product_quantity_3.setStyle("-fx-font-weight: bold");
    product_quantity_3.setFont(new Font(15));

    vBox_top_10_trousers_PID.getChildren().addAll(product_id_3);
    vBox_top_10_trousers_name.getChildren().addAll(product_name_3);
    vBox_top_10_trousers_size.getChildren().addAll(product_size_3);
    vBox_top_10_trousers_colour.getChildren().addAll(product_colour_3);
    vBox_top_10_trousers_price.getChildren().addAll(product_price_3);
    vBox_top_10_trousers_purchase_quantity.getChildren().addAll(product_quantity_3);

    for (Map.Entry<String, Integer> en : sorted_trousers_count_map.entrySet()) {
      for (Product product : products_list) {
        if (product.getID() == Integer.parseInt(en.getKey())) {
          Label pid = new Label(String.valueOf(product.getID()));
          pid.setFont(new Font(15));
          vBox_top_10_trousers_PID.getChildren().addAll(pid);

          Label name = new Label(String.valueOf(product.getName()));
          name.setFont(new Font(15));
          vBox_top_10_trousers_name.getChildren().addAll(name);

          Label size = new Label(String.valueOf(product.getSize()));
          size.setFont(new Font(15));
          vBox_top_10_trousers_size.getChildren().addAll(size);

          Label colour = new Label(String.valueOf(product.getColour()));
          colour.setFont(new Font(15));
          vBox_top_10_trousers_colour.getChildren().addAll(colour);

          Label price = new Label(String.valueOf(product.getPrice()));
          price.setFont(new Font(15));
          vBox_top_10_trousers_price.getChildren().addAll(price);

          Label quantity = new Label(String.valueOf(en.getValue()));
          quantity.setFont(new Font(15));
          vBox_top_10_trousers_purchase_quantity.getChildren().addAll(quantity);
        }
      }
    }

    hBox_top_10_trousers
        .getChildren()
        .addAll(
            vBox_top_10_trousers_PID,
            vBox_top_10_trousers_name,
            vBox_top_10_trousers_size,
            vBox_top_10_trousers_colour,
            vBox_top_10_trousers_price,
            vBox_top_10_trousers_purchase_quantity);
    hBox_top_10_trousers.setSpacing(60);

    vBox_top_10_data
        .getChildren()
        .addAll(
            label_top_10_shirt,
            hBox_top_10_shirt,
            label_top_10_jacket,
            hBox_top_10_jacket,
            label_top_10_trousers,
            hBox_top_10_trousers);
    vBox_top_10_data.setSpacing(30);

    vbox_month.getChildren().addAll(month_report_title, vBox_top_10_data);
    vbox_month.setAlignment(Pos.CENTER);

    // Set header and footer
    Image adelaide_premium_header = new Image(new FileInputStream("img/adelaide_premium.JPG"));
    ImageView imageView_header = new ImageView(adelaide_premium_header);
    imageView_header.setFitHeight(430);
    imageView_header.setFitWidth(1260);

    Image adelaide_premium_footer =
        new Image(new FileInputStream("img/adelaide_premium_footer.JPG"));
    ImageView imageView_footer = new ImageView(adelaide_premium_footer);
    imageView_footer.setFitHeight(320);
    imageView_footer.setFitWidth(1260);

    VBox vBox_with_image = new VBox();
    vBox_with_image.getChildren().addAll(imageView_header, vbox_month, imageView_footer);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vBox_with_image);

    Scene monthScene = new Scene(scrollPane, 1275, 655);

    monthWindow.setScene(monthScene);

    monthWindow.show();
  }

  /***
   * Generates random catalog with discounted products when the Generate Random Catalog menu is clicked
   * Each item in the random catalog is on a 15% discount
   * @param actionEvent Action Event when Generate Random Catalog menu is clicked
   * @throws FileNotFoundException When files cannot be read properly
   */
  @FXML
  protected void onGenerateCatalogClick(ActionEvent actionEvent) throws FileNotFoundException {
    Catalog c = new Catalog();
    ArrayList<ArrayList<String>> randomCatalog = c.generateRandomCatalog();

    // New window (Stage)
    Stage randomCatalogWindow = new Stage();
    randomCatalogWindow.setTitle("Discounted Product Catalog");

    // Design Random Catalog for 15 random products on 15% discount
    VBox vbox_random_catalog = new VBox();
    vbox_random_catalog.setSpacing(30);
    vbox_random_catalog.setPadding(new Insets(50, 60, 50, 60));

    VBox vBox_random_product = new VBox();

    HBox hbox_random_catalog_table = new HBox();

    int shirt_image_count = 0;
    int jacket_image_count = 0;
    int trousers_image_count = 0;

    // Iterate over each item in the Random Catalog and display the details of each of them on the report
    for (ArrayList<String> strings : randomCatalog) {

      Label id_text = new Label();
      id_text.setFont(new Font(15));
      id_text.setStyle("-fx-font-weight: bold");

      Label category_text = new Label();
      category_text.setFont(new Font(15));
      category_text.setStyle("-fx-font-weight: bold");

      Label name_text = new Label();
      name_text.setFont(new Font(15));
      name_text.setStyle("-fx-font-weight: bold");

      Label size_text = new Label();
      size_text.setFont(new Font(15));
      size_text.setStyle("-fx-font-weight: bold");

      Label colour_text = new Label();
      colour_text.setFont(new Font(15));
      colour_text.setStyle("-fx-font-weight: bold");

      Label actual_price_text = new Label();
      actual_price_text.setFont(new Font(15));
      actual_price_text.setStyle("-fx-font-weight: bold");

      Label discounted_price_text = new Label();
      discounted_price_text.setFont(new Font(15));
      discounted_price_text.setStyle("-fx-font-weight: bold");

      Label id_value = new Label();
      id_value.setFont(new Font(15));
      Label category_value = new Label();
      category_value.setFont(new Font(15));
      Label name_value = new Label();
      name_value.setFont(new Font(15));
      Label size_value = new Label();
      size_value.setFont(new Font(15));
      Label colour_value = new Label();
      colour_value.setFont(new Font(15));
      Label actual_price_value = new Label();
      actual_price_value.setFont(new Font(15));
      Label discounted_price_value = new Label();
      discounted_price_value.setFont(new Font(15));

      id_text.setText("Product ID: ");
      category_text.setText("Product Category: ");
      name_text.setText("Product Name: ");
      size_text.setText("Product Size: ");
      colour_text.setText("Product Colour: ");
      actual_price_text.setText("Actual Price: Rs. ");
      discounted_price_text.setText("Discounted Price: Rs. ");

      id_value.setText(String.valueOf(strings.get(0)));
      category_value.setText(strings.get(1).toUpperCase());
      name_value.setText(strings.get(2));
      size_value.setText(strings.get(3));
      colour_value.setText(strings.get(4));
      int actual_price = (int) Math.round(Integer.parseInt(strings.get(5)) * 1.15);
      actual_price_value.setText(String.valueOf(actual_price));
      discounted_price_value.setText(strings.get(5));

      VBox product_details_vbox = new VBox();
      VBox product_price_vbox = new VBox();
      VBox image_vbox = new VBox();
      HBox product_details_purchase_hbox = new HBox();

      Image image = null;

      if (Objects.equals(strings.get(1), "shirt")) {
        shirt_image_count += 1;
        try {
          image = new Image(new FileInputStream("img/shirt/" + shirt_image_count + ".JPG"));
        } catch (FileNotFoundException e) {
          System.out.println("File not found!");
        }
      } else if (Objects.equals(strings.get(1), "jacket")) {
        jacket_image_count += 1;
        try {
          image = new Image(new FileInputStream("img/jacket/" + jacket_image_count + ".JPG"));
        } catch (FileNotFoundException e) {
          System.out.println("File not found!");
        }
      } else if (Objects.equals(strings.get(1), "trousers")) {
        trousers_image_count += 1;
        try {
          image = new Image(new FileInputStream("img/trousers/" + trousers_image_count + ".JPG"));
        } catch (FileNotFoundException e) {
          System.out.println("File not found!");
        }
      }

      if (shirt_image_count >= 10) {
        shirt_image_count = 0;
      }
      if (jacket_image_count >= 10) {
        jacket_image_count = 0;
      }
      if (trousers_image_count >= 10) {
        trousers_image_count = 0;
      }

      ImageView imageView = new ImageView(image);
      imageView.setFitHeight(180);
      imageView.setFitWidth(180);
      image_vbox.getChildren().addAll(imageView);

      HBox hBox_id = new HBox();
      HBox hBox_category = new HBox();
      HBox hBox_name = new HBox();
      HBox hBox_size = new HBox();
      HBox hBox_colour = new HBox();
      HBox hBox_actual_price = new HBox();
      HBox hBox_discounted_price = new HBox();

      hBox_id.getChildren().addAll(id_text, id_value);
      hBox_category.getChildren().addAll(category_text, category_value);
      hBox_name.getChildren().addAll(name_text, name_value);
      hBox_size.getChildren().addAll(size_text, size_value);
      hBox_colour.getChildren().addAll(colour_text, colour_value);
      hBox_actual_price.getChildren().addAll(actual_price_text, actual_price_value);
      hBox_discounted_price.getChildren().addAll(discounted_price_text, discounted_price_value);

      Image image_discount_percent = new Image(new FileInputStream("img/off.PNG"));
      ImageView imageDiscountView = new ImageView(image_discount_percent);
      imageDiscountView.setFitHeight(120);
      imageDiscountView.setFitWidth(120);

      product_details_vbox
              .getChildren()
              .addAll(hBox_id, hBox_category, hBox_name, hBox_size, hBox_colour);

      product_price_vbox
              .getChildren()
              .addAll(hBox_actual_price, hBox_discounted_price, imageDiscountView);

      product_details_vbox.setPrefWidth(500);
      product_price_vbox.setPrefWidth(400);

      product_details_vbox.setPadding(new Insets(20, 0, 30, 100));
      product_price_vbox.setPadding(new Insets(20, 0, 30, 40));
      image_vbox.setPadding(new Insets(25, 20, 30, 90));

      product_details_vbox.setSpacing(10);
      product_price_vbox.setSpacing(10);

      product_details_purchase_hbox
              .getChildren()
              .addAll(image_vbox, product_details_vbox, product_price_vbox);
      product_details_purchase_hbox.setSpacing(30);
      product_details_purchase_hbox.prefWidthProperty().setValue(300);
      vBox_random_product.getChildren().add(product_details_purchase_hbox);
    }

    Label random_catalog_title = new Label("Discounted Product Catalog");
    random_catalog_title.setFont(new Font(30));
    random_catalog_title.setTextFill(Color.web("#0076a3"));

    Label random_catalog_message =
        new Label(
            "The following products are on a 15% discount each for a limited time. Hurry, make your purchase!!");
    random_catalog_message.setFont(new Font(20));
    random_catalog_message.setTextFill(Color.web("#800000"));

    vbox_random_catalog
        .getChildren()
        .addAll(random_catalog_title, random_catalog_message, vBox_random_product);
    vbox_random_catalog.setAlignment(Pos.CENTER);

    // Set header and footer
    Image adelaide_premium_header = new Image(new FileInputStream("img/adelaide_premium.JPG"));
    ImageView imageView_header = new ImageView(adelaide_premium_header);
    imageView_header.setFitHeight(430);
    imageView_header.setFitWidth(1260);

    Image adelaide_premium_footer =
        new Image(new FileInputStream("img/adelaide_premium_footer.JPG"));
    ImageView imageView_footer = new ImageView(adelaide_premium_footer);
    imageView_footer.setFitHeight(320);
    imageView_footer.setFitWidth(1260);

    VBox vBox_with_image = new VBox();
    vBox_with_image.getChildren().addAll(imageView_header, vbox_random_catalog, imageView_footer);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vBox_with_image);

    Scene randomCatalogScene = new Scene(scrollPane, 1275, 655);

    randomCatalogWindow.setScene(randomCatalogScene);

    randomCatalogWindow.show();
  }

  /***
   * Registers an unregistered customer to the Adelaide Premium database
   * After registration, the customer can proceed with placing the order
   * @param actionEvent Action Event when Register Customer menu is clicked
   */
  @FXML
  protected void onRegisterCustomerClick(ActionEvent actionEvent) {
    Stage dialog_register_customer = new Stage();
    dialog_register_customer.initModality(Modality.APPLICATION_MODAL);
    dialog_register_customer.setTitle("Register Customer");

    // Design new customer registration form
    VBox vbox_register_customer = new VBox();
    vbox_register_customer.setSpacing(30);
    vbox_register_customer.setPadding(new Insets(20, 10, 10, 160));

    Label id_text = new Label("Customer ID");
    id_text.setFont(new Font(15));
    Label password_text = new Label("Password");
    password_text.setFont(new Font(15));
    Label fname_text = new Label("First Name");
    fname_text.setFont(new Font(15));
    Label lname_text = new Label("Last Name");
    lname_text.setFont(new Font(15));
    Label email_text = new Label("Email");
    email_text.setFont(new Font(15));
    Label DOB_text = new Label("DOB");
    DOB_text.setFont(new Font(15));
    Label phone_text = new Label("Phone");
    phone_text.setFont(new Font(15));
    Label address_text = new Label("Address");
    address_text.setFont(new Font(15));
    Label pin_text = new Label("PIN Code");
    pin_text.setFont(new Font(15));

    TextField id = new TextField();
    id.setPromptText("Enter Customer ID");
    TextField password = new TextField();
    password.setPromptText("Enter password");
    TextField fname = new TextField();
    fname.setPromptText("Enter first name");
    TextField lname = new TextField();
    lname.setPromptText("Enter last name");
    TextField email = new TextField();
    email.setPromptText("Enter email");
    TextField DOB = new TextField();
    DOB.setPromptText("YYYY-MM-DD format");
    TextField phone = new TextField();
    phone.setPromptText("XXX-XXX-XXXX format");
    TextField address = new TextField();
    address.setPromptText("Enter address");
    TextField pin = new TextField();
    pin.setPromptText("Enter PIN code");

    VBox vBox_labels =
        new VBox(
            id_text,
            password_text,
            fname_text,
            lname_text,
            email_text,
            DOB_text,
            phone_text,
            address_text,
            pin_text);
    VBox vBox_textfields = new VBox(id, password, fname, lname, email, DOB, phone, address, pin);

    vBox_labels.setSpacing(24);
    vBox_textfields.setSpacing(20);

    HBox hBox_main = new HBox(vBox_labels, vBox_textfields);
    hBox_main.setSpacing(20);

    Label main_label = new Label("Customer Registration Form");
    main_label.setFont(new Font(20));
    main_label.setTextFill(Color.web("#0076a3"));

    Label registration_status = new Label();
    registration_status.setTextFill(Color.web("#006400"));

    Button register = new Button("Register Customer");

    // When Register Customer button is clicked
    register.setOnAction(
        event -> {
          Customer c = new Customer();

          registration_status.setText("");
          try {
            int id_check = Integer.parseInt(id.getText());
            String password_check = password.getText();
            String fname_check = fname.getText();
            String lname_check = lname.getText();
            String email_check = email.getText();
            String DOB_check = DOB.getText();
            String phone_check = phone.getText();
            String address_check = address.getText();
            int pin_check = Integer.parseInt(pin.getText());

            if (!email_check.contains("@")) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect E-Mail! Please verify.");
              return;
            }

            if (String.valueOf(DOB_check).length() != 10) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect DOB! Please verify.");
              return;
            }

            if (String.valueOf(phone_check).length() != 12) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect Phone! Please verify.");
              return;
            }

            if (pin_check < 100000 | pin_check > 999999) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect PIN! Please verify.");
              return;
            }

            c.registerCustomer(
                id_check,
                password_check,
                fname_check,
                lname_check,
                email_check,
                DOB_check,
                phone_check,
                address_check,
                pin_check);

            registration_status.setFont(new Font(15));
            registration_status.setTextFill(Color.web("#006400"));
            registration_status.setText("Customer Registration Successful!");
          } catch (NumberFormatException e) {
            registration_status.setFont(new Font(15));
            registration_status.setTextFill(Color.web("#800000"));
            registration_status.setText("Incorrect input types! Please verify.");
          }
        });

    vbox_register_customer
        .getChildren()
        .addAll(main_label, hBox_main, register, registration_status);
    vbox_register_customer.setAlignment(Pos.CENTER);
    hBox_main.setAlignment(Pos.CENTER);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vbox_register_customer);

    Scene dialogScene = new Scene(scrollPane, 600, 600);
    dialog_register_customer.setScene(dialogScene);
    dialog_register_customer.setResizable(false);
    dialog_register_customer.show();
  }

  /***
   * Registers an unregistered product to the Adelaide Premium database
   * @param actionEvent Action Event when Register Product menu is clicked
   */
  @FXML
  protected void onRegisterProductClick(ActionEvent actionEvent) {
    Stage dialog_register_product = new Stage();
    dialog_register_product.initModality(Modality.APPLICATION_MODAL);
    dialog_register_product.setTitle("Register Product");

    // Design new product registration form
    VBox vbox_register_product = new VBox();
    vbox_register_product.setSpacing(30);
    vbox_register_product.setPadding(new Insets(20, 10, 10, 130));

    Label id_text = new Label("Product ID");
    id_text.setFont(new Font(15));
    Label category_text = new Label("Category");
    category_text.setFont(new Font(15));
    Label name_text = new Label("Product Name");
    name_text.setFont(new Font(15));
    Label size_text = new Label("Size");
    size_text.setFont(new Font(15));
    Label colour_text = new Label("Colour");
    colour_text.setFont(new Font(15));
    Label quantity_text = new Label("Quantity");
    quantity_text.setFont(new Font(15));
    Label price_text = new Label("Price");
    price_text.setFont(new Font(15));

    TextField id = new TextField();
    id.setPromptText("Enter product ID");
    TextField category = new TextField();
    category.setPromptText("shirt/jacket/trousers format");
    TextField name = new TextField();
    name.setPromptText("Enter product name");
    TextField size = new TextField();
    size.setPromptText("S/M/L format");
    TextField colour = new TextField();
    colour.setPromptText("Enter colour");
    TextField quantity = new TextField();
    quantity.setPromptText("Enter available quantity");
    TextField price = new TextField();
    price.setPromptText("Enter price in Rs.");

    VBox vBox_labels =
        new VBox(
            id_text, category_text, name_text, size_text, colour_text, quantity_text, price_text);
    VBox vBox_textfields = new VBox(id, category, name, size, colour, quantity, price);

    vBox_labels.setSpacing(24);
    vBox_textfields.setSpacing(20);

    HBox hBox_main = new HBox(vBox_labels, vBox_textfields);
    hBox_main.setSpacing(20);

    Label main_label = new Label("Product Registration Form");
    main_label.setFont(new Font(20));
    main_label.setTextFill(Color.web("#0076a3"));

    Label registration_status = new Label();
    registration_status.setTextFill(Color.web("#006400"));

    Button register = new Button("Register Product");

    // When Register Product button is clicked
    register.setOnAction(
        event -> {
          Product p = new Product();

          registration_status.setText("");
          try {
            int id_check = Integer.parseInt(id.getText());
            String category_check = category.getText();
            String name_check = name.getText();
            String size_check = size.getText();
            String colour_check = colour.getText();
            int quantity_check = Integer.parseInt(quantity.getText());
            int price_check = Integer.parseInt(price.getText());

            // Error checking
            if (!Objects.equals(category_check, "shirt")
                && !Objects.equals(category_check, "jacket")
                && !Objects.equals(category_check, "trousers")) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect Category! Please verify.");
              return;
            }

            if (!Objects.equals(size_check, "S")
                && !Objects.equals(size_check, "M")
                && !Objects.equals(size_check, "L")) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect Size! Please verify.");
              return;
            }

            if (quantity_check < 1) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect Quantity! Please verify.");
              return;
            }

            if (price_check < 1) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect Price! Please verify.");
              return;
            }

            p.registerProduct(
                id_check,
                category_check,
                name_check,
                size_check,
                colour_check,
                quantity_check,
                price_check);

            registration_status.setFont(new Font(15));
            registration_status.setTextFill(Color.web("#006400"));
            registration_status.setText("Product Registration Successful!");
          } catch (NumberFormatException e) {
            registration_status.setFont(new Font(15));
            registration_status.setTextFill(Color.web("#800000"));
            registration_status.setText("Incorrect input types! Please verify.");
          }
        });

    vbox_register_product
        .getChildren()
        .addAll(main_label, hBox_main, register, registration_status);
    vbox_register_product.setAlignment(Pos.CENTER);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vbox_register_product);

    Scene dialogScene = new Scene(scrollPane, 550, 510);
    dialog_register_product.setScene(dialogScene);
    dialog_register_product.setResizable(false);
    dialog_register_product.show();
  }

  /***
   * Update quantity of products present in the Adelaide Premium database
   * @param actionEvent Action Event when Update Product Quantity menu is clicked
   */
  @FXML
  protected void onUpdateQuantityClick(ActionEvent actionEvent) {
    Stage dialog_add_quantity = new Stage();
    dialog_add_quantity.initModality(Modality.APPLICATION_MODAL);
    dialog_add_quantity.setTitle("Add Product Quantity");

    // Design product quantity update form
    VBox vbox_add_quantity = new VBox();
    vbox_add_quantity.setSpacing(30);
    vbox_add_quantity.setPadding(new Insets(20, 10, 10, 40));

    Label id_text = new Label("Product ID");
    id_text.setFont(new Font(15));
    Label quantity_text = new Label("Quantity To Be Added");
    quantity_text.setFont(new Font(15));

    TextField id = new TextField();
    id.setPromptText("Enter Product ID");
    TextField quantity = new TextField();
    quantity.setPromptText("Added Stock");

    VBox vBox_labels = new VBox(id_text, quantity_text);
    VBox vBox_textfields = new VBox(id, quantity);

    vBox_labels.setSpacing(24);
    vBox_textfields.setSpacing(20);

    HBox hBox_main = new HBox(vBox_labels, vBox_textfields);
    hBox_main.setSpacing(20);

    Label main_label = new Label("Product Stock Update Form");
    main_label.setFont(new Font(20));
    main_label.setTextFill(Color.web("#0076a3"));

    Label registration_status = new Label();
    registration_status.setTextFill(Color.web("#006400"));

    Button register = new Button("Add Quantity");

    // When Add Quantity button is clicked
    register.setOnAction(
        event -> {
          Product p = new Product();

          registration_status.setText("");
          try {
            int id_check = Integer.parseInt(id.getText());
            int quantity_check = Integer.parseInt(quantity.getText());

            int id_check_flag = 0;
            for (Product product : products_list) {
              if (product.getID() == id_check) {
                id_check_flag = 1;
                break;
              }
            }

            // Error checking
            if (id_check_flag != 1) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect Product ID! Please verify.");
              return;
            }

            if (quantity_check < 1) {
              registration_status.setFont(new Font(15));
              registration_status.setTextFill(Color.web("#800000"));
              registration_status.setText("Incorrect Quantity! Please verify.");
              return;
            }

            p.updateProductQuantity(
                Integer.parseInt(id.getText()), Integer.parseInt(quantity.getText()), '+');

            registration_status.setFont(new Font(15));
            registration_status.setTextFill(Color.web("#006400"));
            registration_status.setText("Product Quantity Updated!");
          } catch (NumberFormatException e) {
            registration_status.setFont(new Font(15));
            registration_status.setTextFill(Color.web("#800000"));
            registration_status.setText("Incorrect input types! Please verify.");
          }
        });

    main_label.setPadding(new Insets(10, 0, 10, 0));
    vbox_add_quantity.getChildren().addAll(main_label, hBox_main, register, registration_status);
    vbox_add_quantity.setAlignment(Pos.CENTER);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vbox_add_quantity);

    Scene dialogScene = new Scene(scrollPane, 400, 320);
    dialog_add_quantity.setScene(dialogScene);
    dialog_add_quantity.setResizable(false);
    dialog_add_quantity.show();
  }
}