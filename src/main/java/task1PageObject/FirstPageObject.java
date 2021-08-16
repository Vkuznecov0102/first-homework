package task1PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstPageObject {
    private WebDriver webDriver;

    public FirstPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterGoogle(){
        webDriver=new ChromeDriver();
        webDriver.get("https://google.com");
    }

    public void enterGladiolus() throws InterruptedException {
        webDriver.findElement(By.name("q")).sendKeys("гладиолус");
        Thread.sleep(5000);
        webDriver.findElement(By.name("btnK")).click();
    }

    public boolean searchWikipedia(){
        String text="https://ru.wikipedia.org";
        WebElement element=webDriver.findElement(By.partialLinkText(text));
        return element.getText().contains(text);
    }

}
