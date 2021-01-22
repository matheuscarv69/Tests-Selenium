package tests;

import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteFrames {


    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        getDriver().get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finalizeWebDriver() {
        killDriver();
    }

    @Test
    public void testFrame() {
        dsl.enterFrame("frame1");
        dsl.clickButton("frameButton");

        String msg = dsl.getTextAlertAccept();
        Assert.assertEquals("Frame OK!", msg);

        dsl.quitFrame();
        dsl.write("elementosForm:nome", msg);
    }

    @Test
    public void testFrameOculto() {
        WebElement frame = getDriver().findElement(By.id("frame2"));
        dsl.executeJs("window.scrollBy(0, arguments[0])", frame.getLocation().y);

        dsl.enterFrame("frame2");

        dsl.clickButton("frameButton");
        String msg = dsl.getTextAlertAccept();
        Assert.assertEquals("Frame OK!", msg);
    }
}
