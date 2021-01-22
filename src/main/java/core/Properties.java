package core;

public class Properties {

    /** Propriedade usada para não fechar a execução do WebDriver -> BaseTest.class**/
    /** OBS: NÃO RECOMENDADO, USADO APENAS FINS DIDÁTICOS **/
    public static boolean CLOSE_BROWSER = false;

    /** Informe o Browser que deseja executar os testes **/
    public static Browsers browsers = Browsers.CHROME;

    public enum Browsers{
        CHROME,
        FIREFOX
    }


}
