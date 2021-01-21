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

    @Before
    public void initializerWebDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(300, 300));
        driver.get(System.getProperty("user.dir") + "/src/main/resources/componentes.html");
    }

    @After
    public void finalizeWebDriver() {
        driver.quit();
    }

    @Test
    public void testeForm() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        Assert.assertEquals("Matheus", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Carvalho");
        Assert.assertEquals("Carvalho", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));

        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertEquals("M", driver.findElement(By.id("elementosForm:sexo:0")).getAttribute("value"));

        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Assert.assertEquals("carne", driver.findElement(By.id("elementosForm:comidaFavorita:0")).getAttribute("value"));
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        Assert.assertEquals("frango", driver.findElement(By.id("elementosForm:comidaFavorita:1")).getAttribute("value"));
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assert.assertEquals("pizza", driver.findElement(By.id("elementosForm:comidaFavorita:2")).getAttribute("value"));

        WebElement schoolingLevel = driver.findElement(By.id("elementosForm:escolaridade"));
        Select level = new Select(schoolingLevel);
        level.selectByVisibleText("Superior");
        Assert.assertEquals("Superior", level.getFirstSelectedOption().getText());

        WebElement sports = driver.findElement(By.id("elementosForm:esportes"));
        Select sportsCombo = new Select(sports);

        sportsCombo.selectByVisibleText("Corrida");
        sportsCombo.selectByVisibleText("Natacao");

        List<WebElement> selectedsSports = sportsCombo.getAllSelectedOptions();
        Assert.assertTrue(selectedsSports.size() == 2);

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Precisa de mais RGB na vida!");
        Assert.assertEquals("Precisa de mais RGB na vida!", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

        driver.findElement(By.id("elementosForm:cadastrar")).click();


        Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));

        Assert.assertEquals("Nome: Matheus", driver.findElement(By.id("descNome")).getText());
        Assert.assertEquals("Sobrenome: Carvalho", driver.findElement(By.id("descSobrenome")).getText());
        Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
        Assert.assertEquals("Comida: Carne Frango Pizza", driver.findElement(By.id("descComida")).getText());
        Assert.assertEquals("Escolaridade: superior", driver.findElement(By.id("descEscolaridade")).getText());
        Assert.assertEquals("Esportes: Natacao Corrida", driver.findElement(By.id("descEsportes")).getText());
        Assert.assertEquals("Sugestoes: Precisa de mais RGB na vida!", driver.findElement(By.id("descSugestoes")).getText());
    }


}
