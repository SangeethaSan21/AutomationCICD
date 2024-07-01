@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given I logged in with username <name> and password <Password>
    When I add the product <Productname> to cart
    And Checkout <Productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmationpage.

    Examples: 
      | name  										| Password 			|	Productname			|
      | bhanusan@gmail.com 				|  Simple@1 		|	ADIDAS ORIGINAL |
      #| sangeethasan@gmail.com 		|  Simple@21 		|ZARA COAT 3			|
    