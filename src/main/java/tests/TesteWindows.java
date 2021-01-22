package tests;

import core.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteWindows {

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

    // janela com identificador
    @Test
    public void testWindowEasy() {
        dsl.clickButton("buttonPopUpEasy");

        dsl.enterWindow("Popup");
        dsl.write(By.tagName("textarea"), "Deu certo! Popup easy");
        String msg = getDriver().findElement(By.tagName("textarea")).getTagName();
        msg.concat(" da popup");

        getDriver().close();
        dsl.enterWindow("");

        dsl.write(By.tagName("textarea"), "Janela principal\n\n" + msg);
    }

    // janela sem identificador
    @Test
    public void testWindowHard() {
        dsl.clickButton("buttonPopUpHard");

        dsl.enterWindow((String) getDriver().getWindowHandles().toArray()[1]);
        dsl.write(By.tagName("textarea"), "Deu certo! Popup hard");
        String msg = getDriver().findElement(By.tagName("textarea")).getTagName();
        msg.concat(" da popup hard");

        dsl.enterWindow((String) getDriver().getWindowHandles().toArray()[0]);
        dsl.write(By.tagName("textarea"), "Janela principal\n\n" + msg);
    }
}
