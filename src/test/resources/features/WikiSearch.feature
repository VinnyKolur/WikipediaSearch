@WikiSearch
Feature: Wikipedia Search
  As a user I should be able to search with search term
  so that I am on the correct content page


  #I chose scenario outline here because the same values for inputs and verification is used a few times
  # in the scenario steps so specifying them at one place would be better
  Scenario Outline: Search with a key term in English and navigate to a different language
    Given I am on the wikipedia homepage
    And the search box is defaulted to "<language>"
    When I search for the term "<search_term>"
    Then I am on the content page with first heading "<search_term>"
    And I should see the "<short_name>" in the Url
    And  I should see the options to view the content in other languages
    When I click the first option to view the content in a different language
    Then I should see the correct short name of the new language in the Url
    Then I should see "<language>" in the language options
    Examples:
    |search_term|language|short_name|
    |Google     |English |    EN    |

#Intentionally made it to fail to show the screenshot capture within the cucumber report
  Scenario Outline: Fail scenario for test reporting
    Given I am on the wikipedia homepage
    And the search box is defaulted to "<language>"
    When I search for the term "<search_term>"
    Then I am on the content page with first heading "<search_term>"
    And I should see the "<short_name>" in the Url
    And  I should see the options to view the content in other languages
    When I click the first option to view the content in a different language
    Then I should see the correct short name of the new language in the Url
    Then I should see "Python" in the language options
    Examples:
      |search_term|language|short_name|
      |Google     |English |    EN    |