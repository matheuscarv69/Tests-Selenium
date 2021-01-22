package challenges;

import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.CampoTreinamentoPage;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TesteBusinessRules {

    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void initializerWebDriver() {
        getDriver().get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }

    @After
    public void finalizeWebDriver() {
        killDriver();
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
