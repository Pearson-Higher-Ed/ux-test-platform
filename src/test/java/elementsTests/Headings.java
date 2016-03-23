package elementsTests;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Headings extends BaseClass{
	
	private final String url = "http://localhost:8000/src/main/java/elements/fixtures/icons.html";
	private String inputFilePath = "src/main/java/elements/fixtures/icons.html";
	private String localUrl = new File(inputFilePath).getAbsolutePath();
	private static String env;
	private static String mobileDevice;
	private static String setMobile;
	private static String browser;
	private static String mBrowser;
	String fetchCharacter;
	String content;
	String actualContent;
	String code;
	Boolean result;
	final static Logger log = Logger.getLogger(IconsTest.class.getName());
	
	@Parameters({"runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser"})
    @BeforeClass(alwaysRun = true)
    private void iconsTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {
        env = runEnv;
        mobileDevice = mobDeviceName;
        browser = sauceBrowser;
        mBrowser = mobBrowser;
        setMobile = mobile;    
    }
	
	  @DataProvider(name = "getHeadingsTestData")
	    private Object[][] getIconsTestData() {
	        return new Object[][]{{"Level 1","24","30","bold font-weight","2","#A6A8AB","2","#A6A8AB"}
	        	
	        };
	      }
	
	  @Test(testName = "Heading Test", dataProvider = "getIconsTestData", groups = {"desktop"})
	    private void iconsTest(String testIcon, String expectedContent) throws InterruptedException, UnsupportedEncodingException {
	        chooseEnv();
	        fetchCharacter = "return window.getComputedStyle(document.querySelector('.pe-icon--" + testIcon + "'), ':before').getPropertyValue('content')";
	        actualContent = getCode(fetchCharacter);
	        result = assertUnicode(actualContent, expectedContent, testIcon);
	        Assert.assertTrue(result);
	    }
	  
	  
	  
	  
	  
	  private void chooseEnv() throws InterruptedException {
	        if (env.equals("sauce")) {
	            commonUtils.getUrl(url);
	        } else {
	            commonUtils.getUrl("file:///" + localUrl);
	        }
	    }
}
