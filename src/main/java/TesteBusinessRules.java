import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteBusinessRules {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(300, 300));
        driver.get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finalizeWebDriver() {
        driver.quit();
    }

    @Test
    public void testFieldName() {
        dsl.clickButton("elementosForm:cadastrar");
        Assert.assertEquals("Nome eh obrigatorio", dsl.getTextAlertAccept());
    }

    @Test
    public void testFieldSurname() {
        dsl.write("elementosForm:nome", "Matheus");
        dsl.clickButton("elementosForm:cadastrar");
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.getTextAlertAccept());
    }

    @Test
    public void testFieldSex() {
        dsl.write("elementosForm:nome", "Matheus");
        dsl.write("elementosForm:sobrenome", "Carvalho");
        dsl.clickButton("elementosForm:cadastrar");
        Assert.assertEquals("Sexo eh obrigatorio", dsl.getTextAlertAccept());
    }

    @Test
    public void testFieldFavoriteFood() {
        dsl.write("elementosForm:nome", "Matheus");
        dsl.write("elementosForm:sobrenome", "Carvalho");
        dsl.clickRadioButton("elementosForm:sexo:0");

        dsl.clickRadioButton("elementosForm:comidaFavorita:0");
        dsl.clickRadioButton("elementosForm:comidaFavorita:3");

        dsl.clickButton("elementosForm:cadastrar");

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.getTextAlertAccept());

        dsl.quitFrame();

        // limpando campos
        dsl.clickRadioButton("elementosForm:comidaFavorita:0");
        dsl.clickRadioButton("elementosForm:comidaFavorita:3");

        // novo teste
        dsl.clickRadioButton("elementosForm:comidaFavorita:1");
        dsl.clickRadioButton("elementosForm:comidaFavorita:3");

        dsl.clickButton("elementosForm:cadastrar");

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.getTextAlertAccept());
    }

    @Test
    public void testFieldSportsPracticed() {
        dsl.write("elementosForm:nome", "Matheus");
        dsl.write("elementosForm:sobrenome", "Carvalho");
        dsl.clickRadioButton("elementosForm:sexo:0");

        dsl.clickRadioButton("elementosForm:comidaFavorita:1");

        dsl.selectComboBoxOption("elementosForm:esportes", "Natacao");
        dsl.selectComboBoxOption("elementosForm:esportes", "O que eh esporte?");

        dsl.clickButton("elementosForm:cadastrar");

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.getTextAlertAccept());

        dsl.quitFrame();

        dsl.deselectComboBoxOption("elementosForm:esportes", "Natacao");

        dsl.selectComboBoxOption("elementosForm:esportes", "Futebol");
        dsl.clickButton("elementosForm:cadastrar");

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.getTextAlertAccept());

        dsl.quitFrame();

        dsl.deselectComboBoxOption("elementosForm:esportes", "Futebol");

        dsl.selectComboBoxOption("elementosForm:esportes", "Corrida");
        dsl.clickButton("elementosForm:cadastrar");

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.getTextAlertAccept());
        dsl.quitFrame();

        dsl.deselectComboBoxOption("elementosForm:esportes", "Corrida");

        dsl.selectComboBoxOption("elementosForm:esportes", "Karate");
        dsl.clickButton("elementosForm:cadastrar");

        Assert.assertEquals("Voce faz esporte ou nao?", dsl.getTextAlertAccept());
        dsl.quitFrame();

        dsl.deselectComboBoxOption("elementosForm:esportes", "Karate");
    }


}
