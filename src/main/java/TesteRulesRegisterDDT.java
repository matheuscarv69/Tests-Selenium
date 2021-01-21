import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class TesteRulesRegisterDDT {

    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    /********* DDT - Data Driven Testing ************/

    @Parameterized.Parameter
    public String name;
    @Parameterized.Parameter(value = 1)
    public String surname;
    @Parameterized.Parameter(value = 2)
    public String sex;
    @Parameterized.Parameter(value = 3)
    public List<String> favFoot = new ArrayList<>();
    @Parameterized.Parameter(value = 4)
    public String[] sports;
    @Parameterized.Parameter(value = 5)
    public String msg;

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

    @Parameterized.Parameters
    public static Collection<Object[]> getCollection() {
        return Arrays.asList(new Object[][]{
                {"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
                {"Matheus", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
                {"Matheus", "Carvalho", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
                {"Matheus", "Carvalho", "Male", Arrays.asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
                {"Matheus", "Carvalho", "Male", Arrays.asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
        });
    }

    @Test
    public void valideRules() {
        page.setName(name);
        page.setSurname(surname);

        if (sex.equals("Male")) {
            page.setSexMale();
        }

        if (sex.equals("Female")) {
            page.setSexFemale();
        }

        if (favFoot.contains("Carne")) {
            page.setFoodCarne();
        }
        if (favFoot.contains("Frango")) {
            page.setFoodFrango();
        }
        if (favFoot.contains("Pizza")) {
            page.setFoodPizza();
        }
        if (favFoot.contains("Vegetariano")) {
            page.setFoodVegetarian();
        }

        page.setSport(sports);

        page.clickButtonCadastrar();

        System.out.println("Mensagem: " + msg);

        Assert.assertEquals(msg, dsl.getTextAlertAccept());

    }

}
