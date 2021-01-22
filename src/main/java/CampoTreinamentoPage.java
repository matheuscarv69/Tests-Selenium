import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {

    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver) {
        this.dsl = new DSL(driver);
    }

    public void setName(String name) {
        dsl.write("elementosForm:nome", name);
    }

    public void setSurname(String surname) {
        dsl.write("elementosForm:sobrenome", surname);
    }

    public void setSexMale() {
        dsl.clickRadioButton("elementosForm:sexo:0");
    }

    public void setSexFemale() {
        dsl.clickRadioButton("elementosForm:sexo:1");
    }

    public void setFoodCarne() {
        dsl.clickRadioButton("elementosForm:comidaFavorita:0");
    }

    public void setFoodFrango() {
        dsl.clickRadioButton("elementosForm:comidaFavorita:1");
    }

    public void setFoodPizza() {
        dsl.clickRadioButton("elementosForm:comidaFavorita:2");
    }

    public void setFoodVegetarian() {
        dsl.clickRadioButton("elementosForm:comidaFavorita:3");
    }

    public void setSchooling(String value) {
        dsl.selectComboBoxOption("elementosForm:escolaridade", value);
    }

    public void setSport(String... values) {
        for (String value : values) {
            dsl.selectComboBoxOption("elementosForm:esportes", value);
        }
    }

    public void removeSport(String... values) {
        for (String value : values) {
            dsl.deselectComboBoxOption("elementosForm:esportes", value);
        }
    }

    public int getQuantitySports() {
        return dsl.getValuesComboBox("elementosForm:esportes").size();
    }

    public void setSuggestions() {
        dsl.write("elementosForm:sugestoes", "Precisa de mais RGB na vida!");
    }

    public String getSuggestionsValue() {
        return dsl.getFieldValue("elementosForm:sugestoes");
    }

    public void clickButtonCadastrar() {
        dsl.clickButton("elementosForm:cadastrar");
    }

    public String getResultRegister() {
        return dsl.getTextComponent(By.xpath("//*[@id='resultado']/span"));
    }

    public String getNameRegister() {
        return dsl.getTextComponent(By.xpath("//*[@id='descNome']/span"));
    }

    public String getSurNameRegister() {
        return dsl.getTextComponent(By.xpath("//*[@id='descSobrenome']/span"));
    }

    public String getSexRegister() {
        return dsl.getTextComponent(By.xpath("//*[@id='descSexo']/span"));
    }

    public String getFavFoodRegister() {
        return dsl.getTextComponent(By.xpath("//*[@id='descComida']/span"));
    }

    public String getSchoolingRegister() {
        return dsl.getTextComponent(By.xpath("//*[@id='descEscolaridade']/span"));
    }

    public String getSportsRegister() {
        return dsl.getTextComponent(By.xpath("//*[@id='descEsportes']/span"));
    }

    public String getSuggestionsRegister() {
        return dsl.getTextComponent(By.xpath("//*[@id='descSugestoes']/span"));
    }


}
