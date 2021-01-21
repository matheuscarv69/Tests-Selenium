import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
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
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
        alert.accept();
    }

    @Test
    public void testFieldSurname() {
        dsl.write("elementosForm:nome", "Matheus");
        dsl.clickButton("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
        alert.accept();
    }

    @Test
    public void testFieldSex() {
        dsl.write("elementosForm:nome", "Matheus");
        dsl.write("elementosForm:sobrenome", "Carvalho");
        dsl.clickButton("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
        alert.accept();
    }

    @Test
    public void testFieldFavoriteFood() {
        dsl.write("elementosForm:nome", "Matheus");
        dsl.write("elementosForm:sobrenome", "Carvalho");
        dsl.clickRadioButton("elementosForm:sexo:0");

        dsl.clickRadioButton("elementosForm:comidaFavorita:0");
        dsl.clickRadioButton("elementosForm:comidaFavorita:3");

        dsl.clickButton("elementosForm:cadastrar");

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();
        // limpando campos
        dsl.clickRadioButton("elementosForm:comidaFavorita:0");
        dsl.clickRadioButton("elementosForm:comidaFavorita:3");

        // novo teste
        dsl.clickRadioButton("elementosForm:comidaFavorita:1");
        dsl.clickRadioButton("elementosForm:comidaFavorita:3");

        dsl.clickButton("elementosForm:cadastrar");

        alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        alert.accept();
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

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();

        dsl.deselectComboBoxOption("elementosForm:esportes", "Natacao");

        dsl.selectComboBoxOption("elementosForm:esportes", "Futebol");
        dsl.clickButton("elementosForm:cadastrar");

        alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();

        dsl.deselectComboBoxOption("elementosForm:esportes", "Futebol");

        dsl.selectComboBoxOption("elementosForm:esportes", "Corrida");
        dsl.clickButton("elementosForm:cadastrar");

        alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();

        dsl.deselectComboBoxOption("elementosForm:esportes", "Corrida");

        dsl.selectComboBoxOption("elementosForm:esportes", "Karate");
        dsl.clickButton("elementosForm:cadastrar");

        alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();

        dsl.deselectComboBoxOption("elementosForm:esportes", "Karate");
    }


}
