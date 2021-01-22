package core;

import org.junit.After;

import static core.DriverFactory.killDriver;

public class BaseTest {


    @After
    public void finalizeWebDriver() {
        /**
         * Verificação da propriedade -> Properties.class
         *
         * Para não fechar os WebDriver dos testes que herdam esta Classe (BaseTest)
         * para assim abrir apenas uma instancia do WebDriver e acelerar os testes.
         *
         * OBS: NÃO RECOMENDADO, USADO APENAS PARA FINS DIDÁTICOS
         **/
        if (Properties.CLOSE_BROWSER) {
            killDriver();
        }
    }


}
