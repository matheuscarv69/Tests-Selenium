package tests;

import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TestePrimeAjax {

    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
        dsl = new DSL();
    }

    @After
    public void finalizeWebDriver() {
        killDriver();
    }

    @Test
    public void writeTextFieldAjax() {
        dsl.write(By.xpath("//*[@id='j_idt252:name']"), "test");
        dsl.clickButton("j_idt252:j_idt256");

        /**
         Coloquei o wait aqui depois dos métodos de escrever e click em botão
         por que esses métodos já estão acessando os elementos diretamente pelo id,
         caso não tivesse esse id disponível e tivesse mais algum pré carregamento
         ocorrendo antes dessas ações, seria bom colocar o wait antes deles.
         **/

        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.textToBe(By.id("j_idt252:display"), "test"));
        Assert.assertEquals("test", dsl.getTextComponent("j_idt252:display"));
    }


}
