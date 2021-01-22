import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteDesafioComboPrimeFaces {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        dsl = new DSL(driver);
    }

    @After
    public void finalizeWebDriver() {
        driver.quit();
    }

    @Test
    public void interactsComboPrime() {
        dsl.selectComboPrimeFace("j_idt253:option", "Option2");

        Assert.assertTrue(dsl.getTextComponent("j_idt253:option_label").equals("Option2"));

    }

}
