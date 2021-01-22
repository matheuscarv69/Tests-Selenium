package tests;

import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteAlert {

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

    @Test
    public void testeAlertSimples() {
        dsl.clickButton("alert");

        String messageAlert = dsl.getTextAlertAccept();
        Assert.assertEquals("Alert Simples", messageAlert);

        dsl.write("elementosForm:nome", messageAlert);
    }

    @Test
    public void testeAlertConfirm() {
        dsl.clickButton("confirm");
        Assert.assertEquals("Confirm Simples", dsl.getTextAlertAccept());

        Assert.assertEquals("Confirmado", dsl.getTextAlertAccept());

        dsl.clickButton("confirm");
        Assert.assertEquals("Confirm Simples", dsl.getTextAlertDismiss());
        Assert.assertEquals("Negado", dsl.getTextAlertAccept());
    }

    @Test
    public void testeAlertPrompt() {
        dsl.clickButton("prompt");

        Assert.assertEquals("Digite um numero", dsl.getTextAlert());
        dsl.writeTextAlertAccept("12");

        Assert.assertEquals("Era 12?", dsl.getTextAlertAccept());
        Assert.assertEquals(":D", dsl.getTextAlertAccept());

        dsl.clickButton("prompt");

        Assert.assertEquals("Digite um numero", dsl.getTextAlertDismiss());
        Assert.assertEquals("Era null?", dsl.getTextAlertDismiss());
        Assert.assertEquals(":(", dsl.getTextAlertDismiss());
    }


}