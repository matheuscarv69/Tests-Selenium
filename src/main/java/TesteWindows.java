import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteWindows {

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

    // janela com identificador
    @Test
    public void testWindowEasy() {
        dsl.clickButton("buttonPopUpEasy");

        driver.switchTo().window("Popup");
        dsl.write(By.tagName("textarea"), "Deu certo! Popup easy");
        String msg = driver.findElement(By.tagName("textarea")).getTagName();
        msg.concat(" da popup");

        driver.close();
        driver.switchTo().window("");

        dsl.write(By.tagName("textarea"), "Janela principal\n\n" + msg);
    }

    // janela sem identificador
    @Test
    public void testWindowHard() {
        dsl.clickButton("buttonPopUpHard");

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        dsl.write(By.tagName("textarea"), "Deu certo! Popup hard");
        String msg = driver.findElement(By.tagName("textarea")).getTagName();
        msg.concat(" da popup hard");

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        dsl.write(By.tagName("textarea"), "Janela principal\n\n" + msg);
    }
}
