package core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

import static core.DriverFactory.getDriver;

public class DSL {

    /********* Write TextField e TextArea ************/

    public void write(By by, String text) {
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(text);
    }

    public void write(String field_id, String text) {
        write(By.id(field_id), text);
    }

    public String getFieldValue(String field_id) {
        return getDriver().findElement(By.id(field_id)).getAttribute("value");
    }

    /********* RadioButton e Check ************/

    public void clickRadioButton(By by) {
        getDriver().findElement(by).click();
    }

    public void clickRadioButton(String field_id) {
        clickRadioButton(By.id(field_id));
    }

    public boolean isSelectedRadioButton(String field_id) {
        return getDriver().findElement(By.id(field_id)).isSelected();
    }

    /********* ComboBox ************/

    public void selectComboBoxOption(String field_id, String value) {
        WebElement element = getDriver().findElement(By.id(field_id));
        Select combo = new Select(element);

//        combo.selectByIndex(2);
//        combo.selectByValue("superior");
        combo.selectByVisibleText(value);
    }

    public void deselectComboBoxOption(String field_id, String value) {
        WebElement element = getDriver().findElement(By.id(field_id));
        Select combo = new Select(element);

        combo.deselectByVisibleText(value);
    }

    public String getComboBoxOption(String field_id) {
        WebElement element = getDriver().findElement(By.id(field_id));
        Select combo = new Select(element);

        return combo.getFirstSelectedOption().getText();
    }

    public List<String> getValuesComboBox(String field_id) {
        WebElement element = getDriver().findElement(By.id(field_id));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> values = allSelectedOptions.stream().map(opt -> opt.getText()).collect(Collectors.toList());

        return values;
    }

    public int getQuantityOptionsComboBox(String field_id) {
        WebElement element = getDriver().findElement(By.id(field_id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }

    public boolean verifyOptionComboBox(String field_id, String optionValue) {
        WebElement element = getDriver().findElement(By.id(field_id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        for (WebElement option : options) {
            if (option.getText().equals(optionValue)) {
                return true;
            }
        }
        return false;
    }

    public void selectComboPrimeFace(String field_id, String target) {
        clickRadioButton(By.xpath("//*[@id='" + field_id + "']//span"));
        clickRadioButton(By.xpath("//*[@id='" + field_id + "_items']//li[.='" + target + "']"));
    }


    /********* Botao ************/

    public void clickButton(String field_id) {
        getDriver().findElement(By.id(field_id)).click();
    }

    /********* Link ************/

    public void clickLink(String field_id) {
        getDriver().findElement(By.linkText(field_id)).click();
    }

    /********* Text ************/

    public String getTextComponent(By by) {
        return getDriver().findElement(by).getText();
    }

    public String getTextComponent(String field_id) {
        return getTextComponent(By.id(field_id));
    }

    /********* Alerts ************/

    public String getTextAlert() {
        Alert alert = getDriver().switchTo().alert();
        return alert.getText();
    }

    public String getTextAlertAccept() {
        Alert alert = getDriver().switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public String getTextAlertDismiss() {
        Alert alert = getDriver().switchTo().alert();
        String text = alert.getText();
        alert.dismiss();
        return text;
    }

    public void writeTextAlertAccept(String text) {
        Alert alert = getDriver().switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
    }


    /********* Frames and Windows ************/

    public void enterFrame(String frame_id) {
        getDriver().switchTo().frame(frame_id);
    }

    public void quitFrame() {
        getDriver().switchTo().defaultContent();
    }

    public void enterWindow(String window_id) {
        getDriver().switchTo().window(window_id);
    }

    /********* Javascript ************/

    public Object executeJs(String cmd, Object... param) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript(cmd, param);
    }

    /********* Tables ************/

    public void clickButtonTable(String colunaBusca, String valor, String colunaBotao, String idTabela) {
        //procurar coluna do registro
        WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
        int idColuna = obterIndiceColuna(colunaBusca, tabela);

        // encontrar linha do registro
        int idLinha = obterIndiceLinha(valor, tabela, idColuna);

        // procurar coluna do botao
        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

        // clicar no botao da celula encontrada
        WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]//td[" + idColunaBotao + "]"));
        celula.findElement(By.xpath(".//input")).click();

    }

    private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
        int idLinha = -1;
        for (int i = 0; i < linhas.size(); i++) {
            if (linhas.get(i).getText().equals(valor)) {
                idLinha = i + 1;
                break;
            }
        }
        return idLinha;
    }

    private int obterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));

        int idColuna = -1;
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equals(coluna)) {
                idColuna = i + 1;
                break;
            }
        }
        return idColuna;
    }


}