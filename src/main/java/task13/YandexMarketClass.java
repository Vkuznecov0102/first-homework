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
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YandexMarketClass {

    private WebDriver webDriver;
    String marketXpath = "//*[@class='home-link services-new__item services-new__item_search_yes']";

    public YandexMarketClass(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void threadSleep() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Открыть яндекс")
    public void openYandex() {
        webDriver = new ChromeDriver();
        webDriver.get("https://yandex.ru");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @Step("Найти маркет")
    public void findMarket() {
        webDriver.findElement(By.xpath(marketXpath));
    }

    @Step("Нажать на маркет")
    public void clickMarket() {
        WebElement element = webDriver.findElement(By.xpath(marketXpath));
        element.click();
    }

    @Step("Переключиться на вкладку маркет")
    public void switchToTab() {
        String oldTab = webDriver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(newTab.get(1));
        assertEquals("Яндекс.Маркет — покупки с быстрой доставкой", webDriver.getTitle());
    }

    @Step("Выбрать компьютеры и ноутбуки")
    public void chooseComputers() {
        String catalog = "//*[@class='_2AMPZ _3CFK9 _2VvV8 _3WgR5']";
        String computers = "//*[@class='_1UCDW'][contains(text(),'Компьютеры')]";
        String notebooks = "//*[contains(text(),'Ноутбуки') and not(contains(text(),'Ноутбуки и планшеты'))]";

        webDriver.findElement(By.xpath(catalog)).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        var element = webDriver.findElement(By.xpath(computers));
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        Actions actions = new Actions(webDriver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(element).build().perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(webDriver.findElement(By.xpath(notebooks))).click().build().perform();
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        assertEquals("Ноутбуки — купить на Яндекс.Маркете", webDriver.getTitle());
    }

    @Step("Выбрать диапазон цен от 10000 до 30000")
    public void sendKeysFromTenToThirty() {
        String from = "glpricefrom";
        String to = "glpriceto";
        webDriver.findElement(By.id(from)).sendKeys("10000");
        webDriver.findElement(By.id(to)).sendKeys("30000");
    }

    @Step("Выбрать HP и Lenovo")
    public void chooseHpLenovo() {
        String hpId = "//*[@class='_1exhF']//*[contains(text(),'HP')]";
        String lenovoId = "//*[@class='_1exhF']//*[contains(text(),'Lenovo')]";
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        var hp = webDriver.findElement(By.xpath(hpId));
        var lenovo = webDriver.findElement(By.xpath(lenovoId));
        //здесь тест может упасть,потому что Яндекс убирает куда-то раздел "производители"

        Actions actions = new Actions(webDriver);
        actions.moveToElement(hp).build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(hpId))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lenovoId))).click();
    }

    @Step("Выбрать 12 вместо 48")
    public void chooseTwelve() {
        String buttonXpath = "//*[@class='vLDMf']";
        String chooseXpath = "//*[@class='_1KpjX _35Paz']";
        webDriver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        threadSleep();
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(buttonXpath)))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(chooseXpath)))).click();
    }

    @Step("Проверка результатов поиска")
    public void getSearchResults() {
        threadSleep();
        String resultList = "//*[@class='_2vCnw cia-vs cia-cs']";
        String firstElement = "//*[@class='_2vCnw cia-vs cia-cs']//*[@class='_2UHry _2vVOc']//*[@data-tid='ce80a508']";
        String compareString = "//*[@class='G4KLq']//input[@id='header-search']";
        String result = "//span[@class='JqPid']";
        String header = ".//h1";

        List<WebElement> results = webDriver.findElements(By.xpath(resultList));
        assertEquals(results.size(), 12);
        String firstResult = results.get(0).findElement(By.xpath(firstElement)).getText();
        webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath(compareString)).sendKeys(firstResult);
        webDriver.findElement(By.xpath(result)).click();
        threadSleep();
        WebElement sameNote = webDriver.findElement(By.xpath(header));
        String sameNoteText = sameNote.getText();
        threadSleep();
        assertEquals(firstResult, sameNoteText);

        //без Thread.sleep не работает
    }

}