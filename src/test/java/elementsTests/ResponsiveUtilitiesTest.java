package elementsTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class ResponsiveUtilitiesTest extends BaseClass {

	final String url="http://localhost:8000/src/main/java/elements/fixtures/responsive.html";

	@Parameters({ "runEnv" })
	@Test(testName = "xtraSmallResponsiveTest")
	public void xtraSmallTest(String runEnv) {

		if (runEnv.equals("sauce")) {
			commonUtils.getUrl(url);
		}
        else {
			String inputFilePath = "src/main/java/elements/fixtures/responsive.html";
			String localUrl = new File(inputFilePath).getAbsolutePath();
			commonUtils.getUrl("file:///" + localUrl);
		}
		commonUtils.setWindowSize(479, 800);
		String respValue = commonUtils.getCSSValue(respPgObj.xtraSmall,"background-color");
		Assert.assertEquals(respValue, "rgba(255, 0, 0, 1)");

		commonUtils.setWindowSize(480,800);
		String defaultValue = commonUtils.getCSSValue(respPgObj.xtraSmall,"background-color");
		Assert.assertEquals(defaultValue, "rgba(255, 0, 0, 1)");
	}

}