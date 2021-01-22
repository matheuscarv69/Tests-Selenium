package challenges;

import core.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.CampoTreinamentoPage;

import static core.DriverFactory.getDriver;

public class TesteDesafioForm extends BaseTest {

    private CampoTreinamentoPage page;

    @Before
    public void initializerWebDriver() {
        getDriver().get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        page = new CampoTreinamentoPage();
    }
    

    @Test
    public void testeForm() {
        page.setName("Matheus");
        page.setSurname("Carvalho");
        page.setSexMale();
        page.setFoodCarne();
        page.setFoodFrango();
        page.setFoodPizza();
        page.setSchooling("Superior");
        page.setSport("Corrida");
        page.setSport("Natacao");
        page.setSuggestions();

        page.clickButtonCadastrar();

        Assert.assertEquals("Cadastrado!", page.getResultRegister());
        Assert.assertEquals("Matheus", page.getNameRegister());
        Assert.assertEquals("Carvalho", page.getSurNameRegister());
        Assert.assertEquals("Masculino", page.getSexRegister());
        Assert.assertEquals("Carne Frango Pizza", page.getFavFoodRegister());
        Assert.assertEquals("superior", page.getSchoolingRegister());
        Assert.assertEquals("Natacao Corrida", page.getSportsRegister());
        Assert.assertEquals("Precisa de mais RGB na vida!", page.getSuggestionsRegister());
    }


}
