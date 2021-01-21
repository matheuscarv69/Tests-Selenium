import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteBusinessRules {

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
    public void testFieldName() {
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
        alert.accept();
    }

    @Test
    public void testFieldSurname() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
        alert.accept();

    }

    @Test
    public void testFieldSex() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Carvalho");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
        alert.accept();

    }

    @Test
    public void testFieldFavoriteFood() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Carvalho");
        driver.findElement(By.id("elementosForm:sexo:0")).click();

        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();
        // limpando campos
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        // novo teste
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        alert.accept();

    }


    @Test
    public void testFieldSportsPracticed() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Carvalho");
        driver.findElement(By.id("elementosForm:sexo:0")).click();

        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();

        WebElement elements = driver.findElement(By.id("elementosForm:esportes"));
        Select selectOptions = new Select(elements);

        selectOptions.selectByVisibleText("Natacao");
        selectOptions.selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();

        selectOptions.deselectByVisibleText("Natacao");

        selectOptions.selectByVisibleText("Futebol");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();

        selectOptions.deselectByVisibleText("Futebol");

        selectOptions.selectByVisibleText("Corrida");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();

        selectOptions.deselectByVisibleText("Corrida");

        selectOptions.selectByVisibleText("Karate");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();

        selectOptions.deselectByVisibleText("Karate");

    }


}
