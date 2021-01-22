import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteDesafioForm {

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
    public void testeForm() {
        page.setName("Matheus");
        Assert.assertEquals("Matheus", dsl.getFieldValue("elementosForm:nome"));

        page.setSurname("Carvalho");
        Assert.assertEquals("Carvalho", dsl.getFieldValue("elementosForm:sobrenome"));

        page.setSexMale();
        Assert.assertEquals("M", dsl.getFieldValue("elementosForm:sexo:0"));

        page.setFoodCarne();
        Assert.assertEquals("carne", dsl.getFieldValue("elementosForm:comidaFavorita:0"));
        page.setFoodFrango();
        Assert.assertEquals("frango", dsl.getFieldValue("elementosForm:comidaFavorita:1"));
        page.setFoodPizza();
        Assert.assertEquals("pizza", dsl.getFieldValue("elementosForm:comidaFavorita:2"));

        page.setSchooling("Superior");
        Assert.assertEquals("Superior", dsl.getComboBoxOption("elementosForm:escolaridade"));

        page.setSport("Corrida");
        page.setSport("Natacao");

        Assert.assertTrue(page.getQuantitySports() == 2);


        page.setSuggestions();
        Assert.assertEquals("Precisa de mais RGB na vida!", page.getSuggestionsValue());

        page.clickButtonCadastrar();

        Assert.assertEquals("Cadastrado!",page.getResultRegister());
        Assert.assertEquals("Matheus", page.getNameRegister());
        Assert.assertEquals("Carvalho", page.getSurNameRegister());
        Assert.assertEquals("Masculino", page.getSexRegister());
        Assert.assertEquals("Carne Frango Pizza", page.getFavFoodRegister());
        Assert.assertEquals("superior", page.getSchoolingRegister());
        Assert.assertEquals("Natacao Corrida", page.getSportsRegister());
        Assert.assertEquals("Precisa de mais RGB na vida!", page.getSuggestionsRegister());
    }


}
