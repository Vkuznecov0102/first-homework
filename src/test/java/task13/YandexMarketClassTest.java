package task13;

import base.StartFinish;
import org.junit.jupiter.api.Test;

public class YandexMarketClassTest extends StartFinish {

    private final YandexMarketClass ymc = new YandexMarketClass(webDriver);

    @Test
    public void goToMarket() {
        ymc.openYandex();
        ymc.findMarket();
        ymc.clickMarket();
        ymc.switchToTab();
        ymc.chooseComputers();
        ymc.sendKeysFromTenToThirty();
        ymc.chooseHpLenovo();
        ymc.chooseTwelve();
        ymc.getSearchResults();
    }
}
