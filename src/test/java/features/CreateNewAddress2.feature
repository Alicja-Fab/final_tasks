Feature: Create new address
  Scenario Outline: User logs in and creates new address
    Given The user is on the "https://mystore-testlab.coderslab.pl/index.php" website and click the sign in button
    And Fills in email "john_doe@test.com" and password "n5Kq8Zwjsvj" and clicks the sign in button.
    When Clicks on the addresses button.
    And Clicks on the create new address button
    And Fills in the form with "<alias>", "<address>", "<city>", "<zip code>", "<country>", "<phone>" and clicks save
    Then This is the end for now
    Examples:
     | alias | address     | city      | zip code | country        | phone         |
     | New   | 20 Guest Rd | Cambridge | CB1 2AL  | United Kingdom | +447111038229 |

