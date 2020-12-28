package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    public static WebDriver driver;

    @Before(order = 0)
    public void driverSetUp(Scenario scenario){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        TestRunner.scenario = scenario;
    }

    @After()
    public void tearDown(Scenario scenario){
        try {
            if(scenario.isFailed()){
                attachScreenshot();
            }
        }finally {
            driver.close();
        }
    }

    public static void attachScreenshot() {
        TestRunner.scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", TestRunner.scenario.getName());
    }
}
