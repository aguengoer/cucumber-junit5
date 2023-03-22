Feature: Person management

  Scenario: Create a new person
    Given there are no person
    When I create a person with name "TestName" and with age 26
    Then the person list should have 1 person with the name "TestName"


  Scenario Outline: Create a new person with multiple inputs
    Given there are no person
    When I create a person with name "<name>" and with age <age>
    Then the person list should have 1 person with the name "<name>"
    Examples:
      | name   | age |
      | Didier | 26  |
      | Ara    | 26  |
      | Test   | 1   |
