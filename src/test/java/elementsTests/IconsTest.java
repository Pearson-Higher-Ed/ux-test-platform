package elementsTests;

import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * Created by umahaea on 2/15/16.
 */
public class IconsTest extends BaseClass {

    private final String url = "http://localhost:8000/src/main/java/elements/fixtures/icons.html";
    private String inputFilePath = "src/main/java/elements/fixtures/icons.html";
    private String localUrl = new File(inputFilePath).getAbsolutePath();
    private static String env;
    private static String mobileDevice;
    private static String browser;
    String fetchCharacter;
    String content;

    @Parameters({"runEnv", "mobDeviceName","vmBrowser"})
    @BeforeClass(alwaysRun = true)
    private void iconsTestBeforeClass(String runEnv, String mobDeviceName,String vmBrowser) {
        env = runEnv;
        mobileDevice = mobDeviceName;
        browser=vmBrowser;
    }

    @DataProvider(name = "getIconsTestData")
    private Object[][] getIconsTestData() {
        return new Object[][]{
                {"check", "\\f00c"},
                {"chevron-down", "\\f078"},
                {"chevron-up", "\\f077"},
                {"chevron-right", "\\f058"},
                {"chevron-left", "\\f056"},
                {"cog", "\\f013"},
                {"envelope", "\\f0e0"},
                {"plus-circle", "\\f055"},
                {"search", "\\f002"},
                {"thumb-tack", "\\f08d"},
                {"times", "\\f00d"},
                {"times-circle", "\\f057"},
                {"trash-o", "\\f014"},
                {"users", "\\f0c0"},
                {"info-circle", "\\f05a"},
                {"user", "\\f007"},
                {"file-o", "\\f016"},
                {"calendar", "\\f073"}
        };
    }

    @Test(testName = "Icons Test", dataProvider = "getIconsTestData", groups = {"desktop"})
    private void iconsTest(String icon, String expectedContent) throws InterruptedException, UnsupportedEncodingException {
        chooseEnv();
        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + icon + "'), ':before').getPropertyValue('content')";
        String actualContent = getCode(fetchCharacter);
        //System.out.println(icon+" : "+actualContent);
        if(browser.equals("chrome")){
            //in sauce MAC Chrome, the query returns only \xyz. Tested this on local with same config and it works fine \fxyz
            actualContent =actualContent.replace("\\","\\f");
            //System.out.println(icon+" : "+actualContent);
        }
        System.out.println("actualContent: "+actualContent+" ---- " +"actualContent: "+expectedContent);
        Assert.assertEquals(actualContent, expectedContent, "The icon " + icon + " is not as per the SPEC");
    }

    private String getCode(String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        content = (String) js.executeScript(script);
        String t = StringEscapeUtils.escapeJava(content);
        return "\\" + t.substring(4, 8).toLowerCase();
    }

    private void chooseEnv() throws InterruptedException {
        if (env.equals("sauce")) {
            commonUtils.getUrl(url);
        } else {
            commonUtils.getUrl("file:///" + localUrl);
        }
    }
}