import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGoogle {

    private WebDriver driver;

    @Before
    public void initializerWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(300, 300));
        driver.get("http://www.google.com.br");
    }

    @After
    public void finalizeWebDriver() {
        driver.quit();
    }

    @Test
    public void teste() {
        Assert.assertEquals("Google", driver.getTitle());
    }

}
