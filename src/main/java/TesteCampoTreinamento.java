import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

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
    public void testeTextFieldNome() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
        driver.findElement(By.id("elementosForm:nome")).getAttribute("value");

        Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }

    @Test
    public void testeTextFieldSobrenome() {
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Teste de escrita");

        Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
    }

    @Test
    public void testeTextArea() {
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste de escrita");

        Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }

    @Test
    public void testeRadioButton() {
        driver.findElement(By.id("elementosForm:sexo:0")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    public void testeCheckBox() {
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
    }

    @Test
    public void testeComboBox() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);

//        combo.selectByIndex(2);
//        combo.selectByValue("superior");
        combo.selectByVisibleText("Superior");

        Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
    }

    @Test
    public void testeValoresDispComboBox() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);

        List<WebElement> listOptions = combo.getOptions();

        Assert.assertEquals(8, listOptions.size());

        boolean encontrou = false;

        for (WebElement options : listOptions) {
            if (options.getText().equals("Superior")) {
                encontrou = true;
                break;
            }
        }

        Assert.assertTrue(encontrou);
    }

    @Test
    public void testeValoresMultComboBox() {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> listSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, listSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");

        listSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, listSelectedOptions.size());
    }

    @Test
    public void testeButton() {
        driver.findElement(By.id("buttonSimple")).click();

        Assert.assertEquals("Obrigado!", driver.findElement(By.id("buttonSimple")).getAttribute("value"));
    }

    @Test
    public void testeLink() {
        driver.findElement(By.linkText("Voltar")).click();

        Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
    }

    @Test
    public void testeBuscarTexto() {
        driver.findElement(By.tagName("body")).getText();

        Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
    }


}
