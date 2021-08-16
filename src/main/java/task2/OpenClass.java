package task2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenClass {
    private WebDriver webDriver;

    public OpenClass(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public void enterGoogle(){
        webDriver=new ChromeDriver();
        webDriver.get("https://google.com");
    }

    public void enterOpen() throws InterruptedException {
        webDriver.findElement(By.name("q")).sendKeys("Открытие");
        Thread.sleep(5000);
        webDriver.findElement(By.name("btnK")).click();
    }

    public boolean searchOpen(){
        String text="https://www.open.ru";
        WebElement element=webDriver.findElement(By.partialLinkText(text));
        return element.getText().contains(text);
    }

    public void goToOpenRu(){
        String text="https://www.open.ru";
        WebElement element= webDriver.findElement(By.xpath("//*[@class='g']//*[contains(text(),'https://www.open.ru')]"));
        element.click();
    }

    public void goToExchangeRates(){
        WebElement element= webDriver.findElement(By.xpath("//*[@class='main-page-exchange__table main-page-exchange__table--online']"));
    }

    public void comparing(){
        List<WebElement> moneyFields = webDriver.findElements(By.xpath("//*[@class='main-page-exchange__table main-page-exchange__table--online']" +
                "//*/tr[@class='main-page-exchange__row main-page-exchange__row--with-border']"));
        WebElement[] usdField = new WebElement[1];
        WebElement[] eurField = new WebElement[1];

        moneyFields.forEach(x->{
            if(x.getText().contains("USD")){
                usdField[0] = webDriver.findElement(By.xpath("//*/tr[@class='main-page-exchange__row main-page-exchange__row--with-border']//*[contains(text(),'USD')]"));
            } else if(x.getText().contains("EUR")){
                eurField[0] = webDriver.findElement(By.xpath("//*/tr[@class='main-page-exchange__row main-page-exchange__row--with-border']//*[contains(text(),'EUR')]"));
            }
        });

        List<WebElement> usd = usdField[0].findElements(By.xpath("//*/span[@class='main-page-exchange__rate']"));
        List<WebElement> eur = eurField[0].findElements(By.xpath("//*/span[@class='main-page-exchange__rate']"));
        double bankBuyUSD = Double.parseDouble(usd.get(0).getText().replace(",","."));
        double bankSellUSD = Double.parseDouble(usd.get(1).getText().replace(",","."));

        double bankBuyEUR = Double.parseDouble(eur.get(0).getText().replace(",","."));
        double bankSellEUR = Double.parseDouble(eur.get(1).getText().replace(",","."));

        assertTrue(bankSellUSD > bankBuyUSD && bankSellEUR > bankBuyEUR);
    }
}
