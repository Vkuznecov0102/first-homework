package task13;

import base.StartFinish;
import org.junit.jupiter.api.Test;

public class IphoneClassTest extends StartFinish {

    private final IphoneClass ipc = new IphoneClass(webDriver);

    @Test
    public void mainTest() {
        ipc.openYandex();
        ipc.findMarket();
        ipc.clickMarket();
        ipc.switchToTab();
        ipc.chooseElectronic();
        ipc.chooseApple();
        ipc.chooseTwelve();
        ipc.checkIphone();
    }
}
