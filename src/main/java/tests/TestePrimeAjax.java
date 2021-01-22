package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CampoTreinamentoPage;
import utils.DSL;

public class TestePrimeAjax {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
        dsl = new DSL(driver);
    }

    @After
    public void finalizeWebDriver() {
        driver.quit();
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

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBe(By.id("j_idt252:display"), "test"));
        Assert.assertEquals("test", dsl.getTextComponent("j_idt252:display"));
    }


}
