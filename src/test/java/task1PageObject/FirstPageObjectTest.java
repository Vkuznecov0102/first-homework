package task1PageObject;

import base.StartFinish;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstPageObjectTest extends StartFinish {

    private FirstPageObject firstPageObject=new FirstPageObject(webDriver);

    @Test
    public void gladiolusTest() throws InterruptedException {
        firstPageObject.enterGoogle();
        firstPageObject.enterGladiolus();
        assertTrue(firstPageObject.searchWikipedia());
    }
}
