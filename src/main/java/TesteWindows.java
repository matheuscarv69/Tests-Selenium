import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteWindows {

    // janela com identificador
    @Test
    public void testWindowEasy() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(300, 300));
        driver.get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("buttonPopUpEasy")).click();

        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo! Popup easy");
        String msg = driver.findElement(By.tagName("textarea")).getTagName();
        msg.concat(" da popup");

        driver.close();
        driver.switchTo().window("");

        driver.findElement(By.tagName("textarea")).sendKeys("Janela principal\n\n" + msg);

        driver.quit();
    }

    // janela sem identificador
    @Test
    public void testWindowHard() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(300, 300));
        driver.get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("buttonPopUpHard")).click();

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo! Popup hard");
        String msg = driver.findElement(By.tagName("textarea")).getTagName();
        msg.concat(" da popup hard");

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        driver.findElement(By.tagName("textarea")).sendKeys("Janela principal\n\n" + msg);

        driver.quit();
    }
}
