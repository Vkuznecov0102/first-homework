package task13;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IphoneClass {

    private WebDriver webDriver;
    String marketXpath = "//*[@class='home-link services-new__item services-new__item_search_yes']";

    public IphoneClass(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void threadSleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Открыть Яндекс")
    public void openYandex() {
        String url = "https://yandex.ru";
        webDriver = new ChromeDriver();
        webDriver.get(url);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Step("Найти маркет")
    public void findMarket() {
        webDriver.findElement(By.xpath(marketXpath));
    }

    @Step("Нажать на маркет")
    public void clickMarket() {
        webDriver.findElement(By.xpath(marketXpath)).click();
    }

    @Step("Переключиться на маркет")
    public void switchToTab() {
        String oldTab = webDriver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(newTab.get(1));
        assertEquals("Яндекс.Маркет — покупки с быстрой доставкой", webDriver.getTitle());
    }

    @Step("Выбрать смартфоны")
    public void chooseElectronic() {
        String catalogButton = "//*[@class='_2AMPZ _3CFK9 _2VvV8 _3WgR5']";
        String electronics = "//li[@data-zone-data='{\"id\":\"97009095\"}']";
        String smartphones = "//*[contains(text(),'Смартфоны') and not(contains(text(),'Смартфоны и аксессуары'))]";

        webDriver.findElement(By.xpath(catalogButton)).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        var element = webDriver.findElement(By.xpath(electronics));
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).build().perform();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions.moveToElement(webDriver.findElement(By.xpath(smartphones))).click().build().perform();
        assertEquals("Мобильные телефоны — купить на Яндекс.Маркете", webDriver.getTitle());
    }

    @Step("Выбрать Apple")
    public void chooseApple() {
        Map<String, String> companies = new HashMap<>();
        companies.put("Apple", "7893318_153043");
        companies.put("Nokia", "7893318_152987");
        companies.put("OnePlus", "7893318_10796752");
        companies.put("ZTE", "7893318_1007740");
        companies.put("realme", "7893318_16713696");
        companies.put("Samsung", "7893318_153061");
        companies.put("Xiaomi", "7893318_7701962");


        String appleId = companies.get("Apple");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        var apple = webDriver.findElement(By.id(appleId));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(apple).click().build().perform();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Step("Выбрать 12 вместо 48")
    public void chooseTwelve() {
        threadSleep();
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(webDriver.findElement(By.xpath("//*[@class='vLDMf']")))).click();
        webDriver.findElement(By.xpath("//*[@class='_1KpjX _35Paz']")).click();
        threadSleep();
        //без Thread.sleep не работает.
    }

    @Step("Проверка на соответствие 12")
    public void checkIphone() {
        String resultList = "//*[@class='_2vCnw cia-vs cia-cs']";
        List<WebElement> results = webDriver.findElements(By.xpath(resultList));
        assertEquals(results.size(), 12);
    }

}
