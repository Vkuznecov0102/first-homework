package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class StartFinish {

    protected WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void quit() {
        webDriver.quit();
    }
}
