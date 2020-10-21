@rates
Feature: Test latest and specific rates api are working properly

  # positive scenarios latest foreign exchange rates api
  Scenario: Test if valid latest api for Foreign Exchange rates is accessed it should respond with 200 ok
    Given Valid endpoint for latest foreign exchange rates api
    When Endpoint is accessed to get the results
    Then The response status code should be 200

  Scenario: Test if valid latest api for Foreign Exchange rates is accessed then valid response should appear
    Given Valid endpoint for latest foreign exchange rates api
    When Endpoint is accessed to get the results
    Then The response should contain all the expected details

 # negative scenarios latest foreign exchange rates api
  Scenario Outline: Test if invalid api is provided for Foreign Exchange rate, error response should appear
    Given Invalid endpoint with path <path> for foreign exchange rates api
    When Endpoint is accessed to get the results
    Then Error response should appear with code 400
    Examples:
      | path    |
      | invalid |
      | )       |
      | 1234    |
      | null    |
      | +       |



# positive scenarios specific date foreign exchange rates api
  Scenario Outline: Test if valid specific date api for Foreign Exchange rates is accessed then status code should be 200
    Given Valid endpoint for specific date <specific-date> foreign exchange rates api
    When Endpoint is accessed to get the results
    Then The response status code should be 200
    Examples:
      | specific-date |
      | 2019-08-09    |

  Scenario Outline: Test if valid specific date api for Foreign Exchange rates is accessed then valid response should appear
    Given Valid endpoint for specific date <specific-date> foreign exchange rates api
    When Endpoint is accessed to get the results
    Then The response should contain all the expected details
    Examples:
      | specific-date |
      | 2019-08-09    |
      | 2019-10-10    |
      # future date test
      | 2022-01-01    |