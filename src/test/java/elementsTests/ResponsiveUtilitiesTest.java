package elementsTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class ResponsiveUtilitiesTest extends BaseClass {

	@Parameters({ "runEnv" })
	@Test(testName = "xtraSmallResponsiveTest")
	public void xtraSmallTest(String runEnv) {

		if (runEnv.equals("sauce")) {
			commonUtils.getUrl("http://localhost:8000/src/main/java/elements/fixtures/responsive.html");
		}
        else {
			String inputFilePath = "src/main/java/elements/fixtures/responsive.html";
			String url = new File(inputFilePath).getAbsolutePath();
			commonUtils.getUrl("file:///" + url);
		}
		commonUtils.setWindowSize(300, 800);
		String actual = commonUtils.getCSSValue(respPgObj.xtraSmall,"background-color");
		Assert.assertEquals(actual, "rgba(255, 0, 0, 1)");
	}

}