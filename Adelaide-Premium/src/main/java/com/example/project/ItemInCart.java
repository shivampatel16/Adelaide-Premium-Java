package com.example.project;

import java.util.ArrayList;

/***
 * Represents an item in the cart in the Adelaide Premium application
 * Declares an ArrayList called item_in_cart to store details of an item in the cart
 * Provides functionality to add an item in the cart
 * @author Shivam Patel
 */
public class ItemInCart {

  // Store details of an item in the cart
  ArrayList<Object> item_in_cart = new ArrayList<>();

  /***
   * Adds new item to be added to the cart later on
   * @param item Item to be added to the cart
   */
  protected void addItem(Object item) {
    item_in_cart.add(item);
  }
}
