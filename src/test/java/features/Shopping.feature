Feature: Buy some items
  Scenario Outline: User logs in and buys some items

    Given The user is on the "https://mystore-testlab.coderslab.pl" website and clicks the sign in button
    And Fills in the email "john_doe@test.com", password "n5Kq8Zwjsvj" and clicks the sign in button
    When Clicks on the clothes category
    And Clicks on the photo of Hummingbird Printed Sweater
    And Chooses "<size>"
    And Checks the if the discount is 20%
    And Chooses 5 pieces
    And Adds to the cart
    And Goes to the checkout
    And Confirms the address
    And Chooses the pick up in store option
    And Chooses Pay by Check
    And Clicks on the order with an obligation to pay button
    And Takes the screenshot with the order confirmation and the total sum
    Then The order is placed

    Examples:
    | size |
    |  M   |
    |  S   |
    |  L   |
    |  XL  |






