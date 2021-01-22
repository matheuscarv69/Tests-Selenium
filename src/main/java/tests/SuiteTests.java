package tests;

import challenges.TesteBusinessRules;
import challenges.TesteDesafioForm;
import ddt.TesteRulesRegisterDDT;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestGoogle.class,
        TesteWindows.class,
        TesteFrames.class,
        TesteAlert.class,
        TesteCampoTreinamento.class,
        TesteDesafioForm.class,
        TesteBusinessRules.class,
        TesteRulesRegisterDDT.class

})
public class SuiteTests {


}
