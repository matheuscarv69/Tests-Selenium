package challenges;

import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;


public class TesteDesafioComboPrimeFaces {

    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        dsl = new DSL();
    }

    @After
    public void finalizeWebDriver() {
        killDriver();
    }

    @Test
    public void interactsComboPrime() {
        dsl.selectComboPrimeFace("j_idt253:option", "Option2");

        Assert.assertTrue(dsl.getTextComponent("j_idt253:option_label").equals("Option2"));

    }

}
