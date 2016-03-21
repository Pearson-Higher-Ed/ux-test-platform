package elementsTests;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class HedingsTest {

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
	
	
	
	 @Parameters({"runEnv", "mobile", "mobDeviceName", "sauceBrowser", "mobBrowser"})
	 @BeforeClass(alwaysRun = true)
	 private void iconsTestBeforeClass(String runEnv, String mobile, String mobDeviceName, String sauceBrowser, String mobBrowser) {
	        env = runEnv;
	        mobileDevice = mobDeviceName;
	        browser = sauceBrowser;
	        mBrowser = mobBrowser;
	        setMobile = mobile;    
	    }
	
	
	
	
	
	
	
	
}
