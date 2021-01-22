package tests;

import org.junit.After;
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

import java.util.concurrent.TimeUnit;

public class TestSincronismo {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
//      Local correto para se colocar a espera implicita
//      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finalizeWebDriver() {
        driver.quit();
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

         - O driver está configurado para esperar 30 segundos
         **/
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        dsl.write("novoCampo", "Deu certo!");

        // Finalizacao da espera implicita
        /** Fazemos isso para retornar o funcionamento normal da aplicacao pois outros testes serão feitos **/
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void interactsButtonExplicitDelayResponse() throws InterruptedException {
        dsl.clickButton("buttonDelay");

        /**
         * Espera explicita
         - Usamos o WebDriverWait, ele recebe o driver e o tempo em segundos que irá esperar.
         - O driver está configurado para esperar 30 segundos
         **/

        WebDriverWait wait = new WebDriverWait(driver, 30);

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
