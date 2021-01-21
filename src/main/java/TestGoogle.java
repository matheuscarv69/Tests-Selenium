import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGoogle {

    @Test
    public void teste() {

//        WebDriver driver = new FirefoxDriver();
        WebDriver driver = new ChromeDriver();
//        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.manage().window().setSize(new Dimension(300, 300));
        driver.get("http://www.google.com.br");

        Assert.assertEquals("Google", driver.getTitle());

        driver.quit();
    }

}
