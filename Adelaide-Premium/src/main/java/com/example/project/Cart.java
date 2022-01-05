package com.example.project;

import java.util.ArrayList;

/***
 * Represents a cart in the Adelaide Premium application
 * Declares an ArrayList called cart to store all the items in the cart
 * Provides functionality to add an item to the cart
 * @author Shivam Patel
 */
public class Cart {

  // Store details of the cart
  static ArrayList<ArrayList<Object>> cart = new ArrayList<>();

  /***
   * Add item to cart
   * @param item Item to be added to cart
   */
  protected void addToCart(ArrayList<Object> item) {
    cart.add(item);
  }
}
