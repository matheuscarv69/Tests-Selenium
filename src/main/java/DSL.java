import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    public void write(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public void write(String field_id, String text) {
        write(By.id(field_id), text);
    }

    public String getFieldValue(String field_id) {
        return driver.findElement(By.id(field_id)).getAttribute("value");
    }

    public void clickRadioButton(String field_id) {
        driver.findElement(By.id(field_id)).click();
    }

    public void clickButton(String field_id) {
        driver.findElement(By.id(field_id)).click();
    }

    public boolean isSelectedRadioButton(String field_id) {
        return driver.findElement(By.id(field_id)).isSelected();
    }

    public void selectComboBoxOption(String field_id, String value) {
        WebElement element = driver.findElement(By.id(field_id));
        Select combo = new Select(element);

//        combo.selectByIndex(2);
//        combo.selectByValue("superior");
        combo.selectByVisibleText(value);
    }

    public void deselectComboBoxOption(String field_id, String value) {
        WebElement element = driver.findElement(By.id(field_id));
        Select combo = new Select(element);

        combo.deselectByVisibleText(value);
    }

    public String getComboBoxOption(String field_id) {
        WebElement element = driver.findElement(By.id(field_id));
        Select combo = new Select(element);

        return combo.getFirstSelectedOption().getText();
    }

    public void clickLink(String field_id) {
        driver.findElement(By.linkText(field_id)).click();
    }

    public String getTextComponent(By by) {
        return driver.findElement(by).getText();
    }

    public String getTextComponent(String field_id) {
        return getTextComponent(By.id(field_id));
    }


}