package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    public static WebDriverWait wait;
    private final String homeUrl = "https://www.wikipedia.org/";

    public HomePage(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver, 25);
    }

    @FindBy(id = "searchInput")
    public WebElement searchInput;

    @FindBy(id = "searchLanguage")
    public WebElement languageOptions;

    @FindBy(css = "#search-form > fieldset > button")
    public WebElement searchButton;

    @FindBy(css = "#searchLanguage > option")
    private List<WebElement> langOptions;

    public void selectLanguage(String language){
        Select options = new Select(languageOptions);
        options.selectByVisibleText(language);
    }
    public WebElement selectedLanguage(){
        return  langOptions.stream().filter(lang -> lang.getAttribute("selected") != null).collect(Collectors.toList()).get(0);
    }

    public void open(){
        driver.get(homeUrl);
        wait.until(ExpectedConditions.visibilityOf(searchInput));
    }
}
