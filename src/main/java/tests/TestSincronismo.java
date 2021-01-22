package tests;

import core.DSL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TestSincronismo {

    private DSL dsl;

    @Before
    public void initializerWebDriver() {
//      Local correto para se colocar a espera implicita
//      getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        getDriver().get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }

    @After
    public void finalizeWebDriver() {
        killDriver();
    }

    @Test
    public void interactsButtonFixedDelayResponse() throws InterruptedException {
        dsl.clickButton("buttonDelay");

        // Espera Fixa
        Thread.sleep(5000);

        dsl.write("novoCampo", "Deu certo!");
    }

    @Test
    public void interactsButtonImplicitDelayResponse() throws InterruptedException {
        dsl.clickButton("buttonDelay");

        /**
         * Espera implicita
         - É boa prática colocar essa espera antes de todos os testes, no nosso caso colocaremos
         no construtor.

         - Essa espera irá esperar o tempo passado, mas se um elemento for carregado antes desse tempo
         a espera é parada e o fluxo segue, caso não seja carregado nenhum elemento, o erro é mostrado

         - O getDriver() está configurado para esperar 30 segundos
         **/
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        dsl.write("novoCampo", "Deu certo!");

        // Finalizacao da espera implicita
        /** Fazemos isso para retornar o funcionamento normal da aplicacao pois outros testes serão feitos **/
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void interactsButtonExplicitDelayResponse() throws InterruptedException {
        dsl.clickButton("buttonDelay");

        /**
         * Espera explicita
         - Usamos o WebDriverWait, ele recebe o getDriver() e o tempo em segundos que irá esperar.
         - O wait está configurado para esperar 30 segundos
         **/

        WebDriverWait wait = new WebDriverWait(getDriver(), 30);

        /**
         * Espera explicita
         - Essa espera irá esperar o tempo passado, até que o elemento explicitado no método "until"
         seja carregado, se antes desse tempo ele for carregado,
         a espera é parada e o fluxo segue, caso não seja carregado esse elemento, o erro é mostrado
         **/
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));

        dsl.write("novoCampo", "Deu certo!");
    }


}
