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
        for(String value : values){
            dsl.selectComboBoxOption("elementosForm:esportes", value);
        }
    }
    public void removeSport(String... values) {
        for(String value : values){
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

    public boolean getResultRegister() {
        return dsl.getTextComponent("resultado").startsWith("Cadastrado!");
    }

    public String getNameRegister() {
        return dsl.getTextComponent("descNome");
    }

    public String getSurNameRegister() {
        return dsl.getTextComponent("descSobrenome");
    }

    public String getSexRegister() {
        return dsl.getTextComponent("descSexo");
    }

    public String getFavFoodRegister() {
        return dsl.getTextComponent("descComida");
    }

    public String getSchoolingRegister() {
        return dsl.getTextComponent("descEscolaridade");
    }

    public String getSportsRegister() {
        return dsl.getTextComponent("descEsportes");
    }

    public String getSuggestionsRegister() {
        return dsl.getTextComponent("descSugestoes");
    }


}
