package tests;

import core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

public class TestePrimeFaces {


    private DSL dsl;

    @Before
    public void initializerWebDriver() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
        dsl = new DSL();
    }

    @After
    public void finalizeWebDriver() {
        killDriver();
    }

    @Test
    public void interactsButtonPrime() {
        // id do input do radio button = j_idt253:console:0

        // buscando o radio button pela classe
        dsl.clickRadioButton(By.xpath("//input[@id='j_idt253:console:0']/../../div/span[@class='ui-radiobutton-icon ui-icon ui-icon-blank ui-c']"));

        // buscando o unico span que existe dentro da div
        //        dsl.clickRadioButton(By.xpath("//input[@id='j_idt253:console:0']/../..//span"));
        Assert.assertTrue(dsl.isSelectedRadioButton("j_idt253:console:0"));

        dsl.clickRadioButton(By.xpath("//label[.='Option2']/..//span"));
        Assert.assertTrue(dsl.isSelectedRadioButton("j_idt253:console:1"));
    }

}
