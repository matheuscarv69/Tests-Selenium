import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

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
    public void testeAlertSimples() {
        driver.findElement(By.id("alert")).click();

        Alert alert = driver.switchTo().alert();

        String messageAlert = alert.getText();

        Assert.assertEquals("Alert Simples", messageAlert);
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(messageAlert);
    }

    @Test
    public void testeAlertConfirm() {
        driver.findElement(By.id("confirm")).click();
        Alert alertConfirm = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alertConfirm.getText());
        alertConfirm.accept();

        Alert alertConfirm2 = driver.switchTo().alert();
        Assert.assertEquals("Confirmado", alertConfirm2.getText());
        alertConfirm2.accept();

        // Cancelar Alert
        driver.findElement(By.id("confirm")).click();
        alertConfirm = driver.switchTo().alert();
        Assert.assertEquals("Confirm Simples", alertConfirm.getText());
        alertConfirm.dismiss();

        alertConfirm2 = driver.switchTo().alert();
        Assert.assertEquals("Negado", alertConfirm2.getText());
        alertConfirm2.accept();
    }

    @Test
    public void testeAlertPrompt() {
        driver.findElement(By.id("prompt")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alert.getText());
        alert.sendKeys("12");
        alert.accept();

        Assert.assertEquals("Era 12?", alert.getText());
        alert.accept();
        Assert.assertEquals(":D", alert.getText());
        alert.accept();

        driver.findElement(By.id("prompt")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Digite um numero", alert.getText());
        alert.dismiss();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Era null?", alert.getText());
        alert.dismiss();

        alert = driver.switchTo().alert();
        Assert.assertEquals(":(", alert.getText());
        alert.dismiss();
    }


}