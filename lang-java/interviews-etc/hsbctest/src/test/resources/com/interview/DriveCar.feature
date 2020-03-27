Feature: DriveCar

  Scenario: Car Turns Clockwise
    Given the Car is in position X = 1 and Y = 1 and facing NORTH
    When the Car turns CLOCKWISE
    Then the Car is still in the same position
    But the Car is facing EAST

  Scenario: Car Drives Forwards North
    Given the Car is in position X = 1 and Y = 1 and facing NORTH
    When the Car drives FORWARDS 1
    Then the Car is facing NORTH
    But is in position X = 1 and Y = 2

  Scenario: Car Drives Forwards East
    Given the Car is in position X = 1 and Y = 1 and facing EAST
    When the Car drives FORWARDS 1
    Then the Car is facing EAST
    But is in position X = 2 and Y = 1

  Scenario: Car Drives Forwards West
    Given the Car is in position X = 1 and Y = 1 and facing WEST
    When the Car drives FORWARDS 1
    Then an exception is thrown

  Scenario: Car Drives Forwards Twice East
    Given the Car is in position X = 1 and Y = 1 and facing EAST
    When the Car drives FORWARDS 2
    Then the Car is facing EAST
    But is in position X = 3 and Y = 1
