package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class ContentPage extends BasePage {

    protected static WebDriverWait wait;

    public ContentPage(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver, 25);
    }

    @FindBy(id = "firstHeading")
    public WebElement firstHeading;

    @FindBy(css = "#p-lang > div > ul > li")
    public List<WebElement> langOptions;

    public String url(){
        return  driver.getCurrentUrl();
    }

    public List<WebElement> displayedLanguageOptions(){
        var displayedOptions = langOptions.stream().filter(lang -> !lang.getAttribute("style").equalsIgnoreCase("display: none;")).collect(Collectors.toList());

        return  displayedOptions.stream().map(lang -> lang.findElement(By.tagName("a"))).collect(Collectors.toList());
    }

    public void waitForTheContentPageToLoad(){
        wait.until(ExpectedConditions.visibilityOfAllElements(displayedLanguageOptions()));
    }

    public String selectFirstOptionFromDisplayedLanguageList() {
        String fistLangOnTheList = displayedLanguageOptions().get(0).getAttribute("hreflang");
        displayedLanguageOptions().get(0).click();
        waitForTheContentPageToLoad();
        return fistLangOnTheList;
    }
}
