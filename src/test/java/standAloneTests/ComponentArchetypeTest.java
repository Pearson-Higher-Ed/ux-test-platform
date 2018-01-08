package standAloneTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import standAlone.standAlonePageObjects.ComponentArchetypePageObjects;
import utilities.BaseClass;

import java.lang.reflect.Method;

/**
 * Created by umahaea on 6/9/16.
 */
public class ComponentArchetypeTest extends BaseClass {

    private final String demoUrl = "http://bs-local.com:8000/src/main/java/standAlone/fixtures/componentArchetype/component-archetype.html";
    private String placeHolderText = "", translatedText = "";
    private boolean isPlaceHolderText = false, isTranslatedText = false;
    ComponentArchetypePageObjects compArchtypePgObj = new ComponentArchetypePageObjects();

    /****************
     * Desktop Tests
     ****************/
    @DataProvider(name = "Component Archetype Demo Test Data")
    public Object[][] getDefaultButtonTestData() {
        return new Object[][]{
                {"demoTarget1", compArchtypePgObj.demoTarget1, compArchtypePgObj.demoTarget1Input, "placeholder", "Hello world!"},
                {"demoTarget2", compArchtypePgObj.demoTarget2, compArchtypePgObj.demoTarget2Input, "espace réservé", "Bonjour le monde!"},
        };
    }

    @Test(testName = "Component Archetype Demo Test", dataProvider = "Component Archetype Demo Test Data", groups = {"desktop-regression", "standAlone","mobile-regression"})
    private void componentArchetypeDemoTest(String target, By element, By inputElement, String expPlaceHolder, String expTranslation) throws Exception {
        commonUtils.getUrl(demoUrl);
        placeHolderText = commonUtils.getAttributeValue(inputElement, "placeholder");
        System.out.println(placeHolderText);
        isPlaceHolderText = commonUtils.assertValue(placeHolderText, expPlaceHolder, "Place Holder for " + target + " is not as per the Spec");
        /*commonUtils.click(element);
        Thread.sleep(2000);
        translatedText = commonUtils.getAttributeValue(inputElement,"type");
        isTranslatedText = commonUtils.assertValue(translatedText,expTranslation,"Translated Text for "+target+" is not as per the Spec");*/
        Assert.assertTrue(isPlaceHolderText);
    }

    /****************
     * Mobile Tests
     ****************/

   /* @Test(testName = "Mobile: Component Archetype Demo Test", dataProvider = "Component Archetype Demo Test Data", groups = {"mobile-regression", "standAlone"})
    private void componentArchetypeDemoMobileTest(String target, By element, By inputElement, String expPlaceHolder, String expTranslation) throws Exception {
        commonUtils.getUrl(demoUrl);
        placeHolderText = commonUtils.getAttributeValue(inputElement, "placeholder");
        System.out.println(placeHolderText);
        isPlaceHolderText = commonUtils.assertValue(placeHolderText, expPlaceHolder, "Place Holder for " + target + " is not as per the Spec");
        *//*commonUtils.click(element,);
        Thread.sleep(2000);
        translatedText = commonUtils.getAttributeValue(inputElement,"type","mobile");
        isTranslatedText = commonUtils.assertValue(translatedText,expTranslation,"Translated Text for "+target+" is not as per the Spec");*//*
        Assert.assertTrue(isPlaceHolderText);
    }*/

    @BeforeMethod(alwaysRun = true)
    private void beforeMethod(Method method) throws Exception {
        System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    private void afterMethod() {
        System.out.println("_________________________________________________");
    }
}