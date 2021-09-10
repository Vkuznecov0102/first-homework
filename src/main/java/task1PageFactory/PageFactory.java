package task1PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PageFactory {
    private final WebDriver driver;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(how = How.NAME, name = "q")
    WebElement searchField;

    @FindBy(how = How.NAME, name = "btnK")
    WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//*[@class='g']//*/h3[@class][contains(text(),'Википедия')]")
    List<WebElement> allElements;

    public void find(String keyword) {
        searchField.click();
        searchField.sendKeys(keyword);
        searchButton.click();
    }

    public List<WebElement> getAllElements() {
        return allElements;
    }
}
