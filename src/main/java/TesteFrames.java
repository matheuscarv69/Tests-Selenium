import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrames {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(300, 300));
        driver.get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finalizeWebDriver() {
        driver.quit();
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
        WebElement frame = driver.findElement(By.id("frame2"));
        dsl.executeJs("window.scrollBy(0, arguments[0])", frame.getLocation().y);

        dsl.enterFrame("frame2");

        dsl.clickButton("frameButton");
        String msg = dsl.getTextAlertAccept();
        Assert.assertEquals("Frame OK!", msg);
    }
}
