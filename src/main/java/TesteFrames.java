import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFrames {

    @Test
    public void testFrame() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(300, 300));
        driver.get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();

        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Frame OK!", msg);
        alert.accept();

        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);

        driver.quit();
    }
}
