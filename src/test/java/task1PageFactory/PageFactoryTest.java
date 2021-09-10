package task1PageFactory;

import base.StartFinish;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageFactoryTest extends StartFinish {

    private final String wikiURL = "https://ru.wikipedia.org";

    @Test
    public void mainTest() {
        String URL = "https://www.google.com/";
        String searchWord = "гладиолус";
        String wikiXpath = "//*[@class='g']//*[contains(text(),'https://ru.wikipedia.org')]";

        webDriver.get(URL);
        task1PageFactory.PageFactory pageFactoryTest = PageFactory.initElements(webDriver, task1PageFactory.PageFactory.class);

        pageFactoryTest.find(searchWord);

        List<WebElement> resultSearch = webDriver.findElements(By.xpath(wikiXpath));
        assertTrue(resultSearch.stream().anyMatch(x -> x.getText().contains(wikiURL)),
                "Статьи не найдены");
    }
}
