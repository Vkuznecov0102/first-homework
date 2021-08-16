package task1PageFactory;

import base.StartFinish;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageFactoryTest extends StartFinish {

    @Test
    public void mainTest(){
        webDriver.get("https://www.google.com/");
        task1PageFactory.PageFactory pageFactoryTest= PageFactory.initElements(webDriver, task1PageFactory.PageFactory.class);
        pageFactoryTest.find("гладиолус");
        List<WebElement> resultSearch = webDriver.findElements(By.xpath("//*[@class='g']//*[contains(text(),'https://ru.wikipedia.org')]"));
        assertTrue(resultSearch.stream().anyMatch(x->x.getText().contains("https://ru.wikipedia.org")),
                "Статьи не найдены");
    }
}
