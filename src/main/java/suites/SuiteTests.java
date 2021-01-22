package suites;

import challenges.TesteBusinessRules;
import challenges.TesteDesafioForm;
import ddt.TesteRulesRegisterDDT;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.*;

import static core.DriverFactory.killDriver;

/**
 * Descomente caso queira que todos as classes listadas executem Classes que não seguem o padrão PageObject
 **/
//@RunWith(Suite.class)
//@Suite.SuiteClasses({
//        TestGoogle.class,
//        TesteWindows.class,
//        TesteFrames.class,
//        TesteAlert.class,
//        TesteCampoTreinamento.class,
//        TesteDesafioForm.class,
//        TesteBusinessRules.class,
//        TesteRulesRegisterDDT.class
//
//})

/**
 * Classes que seguem o padrão PageObject
 * <p>
 * Testando o não fechamento do driver após execução do teste,
 * para assim abrir apenas uma instancia do WebDriver e acelerar os testes.
 * <p>
 * OBS: NÃO RECOMENDADO, USADO APENAS PARA FINS DIDÁTICOS
 **/
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteDesafioForm.class,
        TesteBusinessRules.class,
        TesteRulesRegisterDDT.class

})
public class SuiteTests {

    /**
     * Como estamos usando essa abordagem de não fechar o WebDriver dos testes
     * que herdam de BaseTest, para assim acelerar a execução dos testes com apenas
     * uma instância do WebDriver.
     *
     * Precisamos fechar o testes descritos que nessa suite, isso é feito usando a
     * annotation @AfterClass
     *
     * O que ela faz?
     *
     * Após a execução de todos as classes de testes da suite (After)
     * ela irá realizar o método que está anotado com ela.
     * ou seja,
     * AfterClass -> depois da execução da classe.
     *
     * Assim estamos passando o KillDriver() do DriverFactory, dessa forma
     * fechamos o WebDriver após o término da suite
     *
     * OBS: O MÉTODO ANOTADO DEVE SER STATIC, POIS APÓS A EXECUÇÃO DA CLASSE (SuiteTests)
     * NÃO EXISTIRÁ UMA INSTANCIA DE CLASSE PARA ELE SER EXECUTADO, POR ISSO USA-SE O STATIC
     * POIS ELE SERA EXECUTADO SEM DEPENDER DE UMA INSTANCIA.
     **/
    @AfterClass
    public static void closeWebDriver() {
        killDriver();
    }


}
