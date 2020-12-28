package stepDefinitions;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ContentPage;
import pageObjects.HomePage;
import utils.Driver;

import java.text.MessageFormat;

import static org.junit.Assert.assertTrue;

public class WikiSearchSteps {

    private String otherLangShortName;
    private HomePage homePage;
    private ContentPage contentPage;

    @Before(order = 1)
    public void setUp(){
        homePage = new HomePage(Driver.driver);
        contentPage = new ContentPage(Driver.driver);
    }

    @Given("I am on the wikipedia homepage")
    public void iAmOnTheWikipediaHomepage() {
        homePage.open();
        Driver.attachScreenshot();
    }

    @And("the search box is defaulted to {string}")
    public void theSearchBoxIsDefaultedTo(String language) {
        if(!homePage.selectedLanguage().getText().equalsIgnoreCase(language))
            homePage.selectLanguage(language);
    }

    @When("I search for the term {string}")
    public void iSearchForTheTerm(String searchTerm) {
        homePage.searchInput.sendKeys(searchTerm);
        homePage.searchButton.click();
        contentPage.waitForTheContentPageToLoad();
        Driver.attachScreenshot();
    }

    @Then("I am on the content page with first heading {string}")
    public void iAmOnTheContentPageWithFirstHeading(String searchTerm) {
        assertTrue(MessageFormat.format("The content heading is {0} instead of {1}",contentPage.firstHeading.getText().toLowerCase(),  searchTerm.toLowerCase()), contentPage.firstHeading.getText().equalsIgnoreCase(searchTerm));
    }

    @And("I should see the {string} in the Url")
    public void iShouldSeeTheInTheUrl(String lang) {
        assertTrue(MessageFormat.format("The current Url {0} does not contain the short name {1}",contentPage.url(),  lang.toLowerCase()), contentPage.url().startsWith("https://"+ lang.toLowerCase()));
    }

    @And("I should see the options to view the content in other languages")
    public void iShouldSeeTheOptionsToViewTheContentInOtherLanguages() {
        assertTrue("Other language options not displayed", contentPage.displayedLanguageOptions().size()> 0);
    }

    @When("I click the first option to view the content in a different language")
    public void iClickTheFirstOptionToViewTheContentInADifferentLanguage() {
        otherLangShortName = contentPage.selectFirstOptionFromDisplayedLanguageList();
        Driver.attachScreenshot();
    }

    @Then("I should see the correct short name of the new language in the Url")
    public void iShouldSeeTheCorrectShortNameOfTheNewLanguageInTheUrl() {
        assertTrue(MessageFormat.format("The current Url {0} does not contain the short name {1}",contentPage.url(),  otherLangShortName), contentPage.url().startsWith("https://" + otherLangShortName));
        otherLangShortName = null;
    }

    @Then("I should see {string} in the language options")
    public void iShouldSeeInTheLanguageOptions(String language) {
        assertTrue(MessageFormat.format("The language list does not contain {0} as an option",language),contentPage.displayedLanguageOptions().stream().filter( lang -> lang.getText().equalsIgnoreCase(language)).count() == 1);
    }
}