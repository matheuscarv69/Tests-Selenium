import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteDesafioForm {

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
    public void testeForm() {
        dsl.write("elementosForm:nome", "Matheus");
        Assert.assertEquals("Matheus", dsl.getFieldValue("elementosForm:nome"));

        dsl.write("elementosForm:sobrenome", "Carvalho");
        Assert.assertEquals("Carvalho", dsl.getFieldValue("elementosForm:sobrenome"));

        dsl.clickRadioButton("elementosForm:sexo:0");
        Assert.assertEquals("M", dsl.getFieldValue("elementosForm:sexo:0"));

        dsl.clickRadioButton("elementosForm:comidaFavorita:0");
        Assert.assertEquals("carne", dsl.getFieldValue("elementosForm:comidaFavorita:0"));
        dsl.clickRadioButton("elementosForm:comidaFavorita:1");
        Assert.assertEquals("frango", dsl.getFieldValue("elementosForm:comidaFavorita:1"));
        dsl.clickRadioButton("elementosForm:comidaFavorita:2");
        Assert.assertEquals("pizza", dsl.getFieldValue("elementosForm:comidaFavorita:2"));

        dsl.selectComboBoxOption("elementosForm:escolaridade", "Superior");
        Assert.assertEquals("Superior", dsl.getComboBoxOption("elementosForm:escolaridade"));

        WebElement sports = driver.findElement(By.id("elementosForm:esportes"));
        Select sportsCombo = new Select(sports);

        dsl.selectComboBoxOption("elementosForm:esportes", "Corrida");
        dsl.selectComboBoxOption("elementosForm:esportes", "Natacao");

        List<WebElement> selectedsSports = sportsCombo.getAllSelectedOptions();
        Assert.assertTrue(selectedsSports.size() == 2);

        dsl.write("elementosForm:sugestoes", "Precisa de mais RGB na vida!");
        Assert.assertEquals("Precisa de mais RGB na vida!", dsl.getFieldValue("elementosForm:sugestoes"));

        dsl.clickButton("elementosForm:cadastrar");

        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));

        Assert.assertEquals("Nome: Matheus", dsl.getTextComponent("descNome"));
        Assert.assertEquals("Sobrenome: Carvalho",dsl.getTextComponent("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino", dsl.getTextComponent("descSexo"));
        Assert.assertEquals("Comida: Carne Frango Pizza", dsl.getTextComponent("descComida"));
        Assert.assertEquals("Escolaridade: superior", dsl.getTextComponent("descEscolaridade"));
        Assert.assertEquals("Esportes: Natacao Corrida", dsl.getTextComponent("descEsportes"));
        Assert.assertEquals("Sugestoes: Precisa de mais RGB na vida!", dsl.getTextComponent("descSugestoes"));
    }


}
