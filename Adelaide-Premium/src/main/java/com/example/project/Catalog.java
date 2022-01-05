package com.example.project;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/***
 * Represents a catalog of random items in the PRODUCTS table of the database
 * Generate random catalog of products with discount
 * @author Shivam Patel
 */
public class Catalog {
  // Represents a random catalog
  private ArrayList<ArrayList<String>> catalog = new ArrayList<>();

  /***
   * Creates a random catalog of 15 products from the products database
   * @return Generated random catalog of products with discount
   */
  protected ArrayList<ArrayList<String>> generateRandomCatalog() {
    int total_products = Product.products_list.size();

    // List of random numbers
    ArrayList<Integer> random_numbers = new ArrayList<>();

    int randomNum;
    // Generate 15 random numbers and store data for 15 random products in ArrayList
    for (int i = 0; i < 15; i++) {
      randomNum = ThreadLocalRandom.current().nextInt(1, total_products);
      if (!random_numbers.contains(randomNum)) {
        random_numbers.add(randomNum);

        // Represents a random product
        ArrayList<String> random_product = new ArrayList<>();

        // Add details of the random product to the ArrayList
        random_product.add(String.valueOf(Product.products_list.get(randomNum).getID()));
        random_product.add(Product.products_list.get(randomNum).getCategory());
        random_product.add(Product.products_list.get(randomNum).getName());
        random_product.add(Product.products_list.get(randomNum).getSize());
        random_product.add(Product.products_list.get(randomNum).getColour());
        random_product.add(String.valueOf(Product.products_list.get(randomNum).getPrice()));

        // Add random product to random catalog
        catalog.add(random_product);
      }
    }
    return catalog;
  }
}
