import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePrimeFaces {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
        dsl = new DSL(driver);
    }

    @After
    public void finalizeWebDriver() {
        driver.quit();
    }

    @Test
    public void interactsButtonPrime() {
        // id do input do radio button = j_idt253:console:0

        // buscando o radio button pela classe
        dsl.clickRadioButton(By.xpath("//input[@id='j_idt253:console:0']/../../div/span[@class='ui-radiobutton-icon ui-icon ui-icon-blank ui-c']"));

        // buscando o unico span que existe dentro da div
        //        dsl.clickRadioButton(By.xpath("//input[@id='j_idt253:console:0']/../..//span"));
        Assert.assertTrue(dsl.isSelectedRadioButton("j_idt253:console:0"));

        dsl.clickRadioButton(By.xpath("//label[.='Option2']/..//span"));
        Assert.assertTrue(dsl.isSelectedRadioButton("j_idt253:console:1"));
    }

}
