package task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenClass {
    private WebDriver webDriver;

    public OpenClass(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void enterGoogle() {
        webDriver = new ChromeDriver();
        String url = "https://google.com";
        webDriver.get(url);
    }

    public void enterOpen() {
        String search = "q";
        String bankName = "Открытие";
        String button = "btnK";
        webDriver.findElement(By.name(search)).sendKeys(bankName);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.name(button))).click();
    }

    public boolean searchOpen() {
        String openUrl = "https://www.open.ru";
        WebElement element = webDriver.findElement(By.partialLinkText(openUrl));
        return element.getText().contains(openUrl);
    }

    public void goToOpenRu() {
        String containsOpenRu = "//*[@class='g']//*[contains(text(),'https://www.open.ru')]";
        WebElement element = webDriver.findElement(By.xpath(containsOpenRu));
        element.click();
    }

    public void goToExchangeRates() {
        String exchangeRates = "//*[@class='main-page-exchange__table main-page-exchange__table--online']";
        webDriver.findElement(By.xpath(exchangeRates));
    }

    public void comparing() {
        String firstField = "//table[@class='main-page-exchange__table main-page-exchange__table--online']";
        String secondField = "//*/tr[@class='main-page-exchange__row main-page-exchange__row--with-border']";
        List<WebElement> moneyFields = webDriver.findElements(By.xpath(firstField + secondField));

        WebElement[] usdField = new WebElement[1];
        WebElement[] eurField = new WebElement[1];

        String xPathContainsUsd = "//*/tr[@class='main-page-exchange__row main-page-exchange__row--with-border']//*[contains(text(),'USD')]";
        String xPathContainsEur = "//*/tr[@class='main-page-exchange__row main-page-exchange__row--with-border']//*[contains(text(),'EUR')]";

        moneyFields.forEach(x -> {
            if (x.getText().contains("USD")) {
                usdField[0] = webDriver.findElement(By.xpath(xPathContainsUsd));
            } else if (x.getText().contains("EUR")) {
                eurField[0] = webDriver.findElement(By.xpath(xPathContainsEur));
            }
        });

        String xPathForUsd = "//*/span[@class='main-page-exchange__rate']";
        List<WebElement> usd = usdField[0].findElements(By.xpath(xPathForUsd));
        double bankBuyUSD = Double.parseDouble(usd.get(0).getText().replace(",", "."));
        double bankSellUSD = Double.parseDouble(usd.get(1).getText().replace(",", "."));

        double bankBuyEUR = Double.parseDouble(usd.get(2).getText().replace(",", "."));
        double bankSellEUR = Double.parseDouble(usd.get(3).getText().replace(",", "."));

        System.out.println(bankBuyUSD + " " + bankSellUSD + " " + bankBuyEUR + " " + bankSellEUR);

        assertTrue(bankSellUSD > bankBuyUSD && bankSellEUR > bankBuyEUR);
    }
}
