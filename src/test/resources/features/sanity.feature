
@tag
Feature: Sanity Check for Flipkart

  @tag1
  Scenario: Add Item to Cart
    Given Launch Flipkart Shopping Application
    And Search "Iphone 14" in search bar
    When Click the first mobile on the list
    And Click on Add to Cart button
    And Click Cart Icon
    Then I validate the the Item in the cart
    And Remove Item from the Cart


