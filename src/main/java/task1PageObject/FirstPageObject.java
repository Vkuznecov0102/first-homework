package task1PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstPageObject {
    private WebDriver webDriver;

    public FirstPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void enterGoogle() {
        webDriver = new ChromeDriver();
        String googleUrl = "https://google.com";
        webDriver.get(googleUrl);
    }

    public void enterGladiolus() {
        String googleClass = "q";
        String wordForSearch = "гладиолус";
        webDriver.findElement(By.name(googleClass)).sendKeys(wordForSearch);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        String button = "btnK";
        wait.until(ExpectedConditions.elementToBeClickable(
                webDriver.findElement(By.name(button)))).click();
    }

    public boolean searchWikipedia() {
        String linkText = "https://ru.wikipedia.org";
        WebElement element = webDriver.findElement(By.partialLinkText(linkText));
        return element.getText().contains(linkText);
    }

}
