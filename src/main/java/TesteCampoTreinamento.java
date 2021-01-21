import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class TesteCampoTreinamento {

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
    public void testeTextFieldNome() {
        dsl.write("elementosForm:nome", "Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:nome"));
    }

    @Test
    public void testeTextFieldSobrenome() {
        dsl.write("elementosForm:sobrenome", "Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:sobrenome"));
    }

    @Test
    public void testeTextArea() {
        dsl.write("elementosForm:sugestoes", "Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.getFieldValue("elementosForm:sugestoes"));
    }

    @Test
    public void testeRadioButton() {
        dsl.clickRadioButton("elementosForm:sexo:0");
        Assert.assertTrue(dsl.isSelectedRadioButton("elementosForm:sexo:0"));
    }

    @Test
    public void testeCheckBox() {
        dsl.clickRadioButton("elementosForm:comidaFavorita:0");
        Assert.assertTrue(dsl.isSelectedRadioButton("elementosForm:comidaFavorita:0"));
    }

    @Test
    public void testeComboBox() {
        dsl.selectComboBoxOption("elementosForm:escolaridade", "Superior");
        Assert.assertEquals("Superior", dsl.getComboBoxOption("elementosForm:escolaridade"));
    }

    @Test
    public void testeValoresDispComboBox() {
        Assert.assertEquals(8, dsl.getQuantityOptionsComboBox("elementosForm:escolaridade"));
        Assert.assertTrue(dsl.verifyOptionComboBox("elementosForm:escolaridade", "Mestrado"));
    }

    @Test
    public void testeValoresMultComboBox() {
        dsl.selectComboBoxOption("elementosForm:esportes", "Natacao");
        dsl.selectComboBoxOption("elementosForm:esportes", "Corrida");
        dsl.selectComboBoxOption("elementosForm:esportes", "O que eh esporte?");

        List<String> valuesComboBox = dsl.getValuesComboBox("elementosForm:esportes");
        Assert.assertEquals(3, valuesComboBox.size());

        dsl.deselectComboBoxOption("elementosForm:esportes", "Corrida");
        valuesComboBox = dsl.getValuesComboBox("elementosForm:esportes");

        Assert.assertEquals(2, valuesComboBox.size());
        Assert.assertTrue(valuesComboBox.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
    }

    @Test
    public void testeButton() {
        dsl.clickButton("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.getFieldValue("buttonSimple"));
    }

    @Test
    public void testeLink() {
        dsl.clickLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.getTextComponent("resultado"));
    }

    @Test
    public void testeBuscarTexto() {
        Assert.assertEquals("Campo de Treinamento", dsl.getTextComponent(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.getTextComponent(By.className("facilAchar")));
    }


}
