package challenges;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CampoTreinamentoPage;
import utils.DSL;

public class TesteBusinessRules {

    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void initializerWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(300, 300));
        driver.get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finalizeWebDriver() {
        driver.quit();
    }

    @Test
    public void testFieldName() {
        page.clickButtonCadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.getTextAlertAccept());
    }

    @Test
    public void testFieldSurname() {
        page.setName("Matheus");
        page.clickButtonCadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.getTextAlertAccept());
    }

    @Test
    public void testFieldSex() {
        page.setName("Matheus");
        page.setSurname("Carvalho");
        page.clickButtonCadastrar();
        Assert.assertEquals("Sexo eh obrigatorio", dsl.getTextAlertAccept());
    }

    @Test
    public void testFieldFavoriteFood() {
        page.setName("Matheus");
        page.setSurname("Carvalho");
        page.setSexMale();
        page.setFoodCarne();
        page.setFoodVegetarian();
        page.clickButtonCadastrar();

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.getTextAlertAccept());

        dsl.quitFrame();

        // limpando campos
        page.setFoodCarne();
        page.setFoodVegetarian();

        // novo teste
        page.setFoodFrango();
        page.setFoodVegetarian();

        page.clickButtonCadastrar();

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.getTextAlertAccept());
    }

    @Test
    public void testFieldSportsPracticed() {
        page.setName("Matheus");
        page.setSurname("Carvalho");
        page.setSexMale();
        page.setFoodCarne();

        page.setSport("Natacao", "O que eh esporte?");
        page.clickButtonCadastrar();

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.getTextAlertAccept());

        dsl.quitFrame();

        page.removeSport("Natacao");
        page.setSport("Futebol");
        page.clickButtonCadastrar();

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.getTextAlertAccept());

        dsl.quitFrame();

        page.removeSport("Futebol");
        page.setSport("Corrida");
        page.clickButtonCadastrar();

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.getTextAlertAccept());
        dsl.quitFrame();

        page.removeSport("Corrida");
        page.setSport("Karate");
        page.clickButtonCadastrar();

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.getTextAlertAccept());
        dsl.quitFrame();

        page.removeSport("Karate");
    }


}
