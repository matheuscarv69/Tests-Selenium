package core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class BaseTest {

    @Rule
    public TestName testName = new TestName();

    @After
    public void finalizeWebDriver() throws IOException {
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot" +
                File.separator + testName.getMethodName() + ".jpg"));

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
