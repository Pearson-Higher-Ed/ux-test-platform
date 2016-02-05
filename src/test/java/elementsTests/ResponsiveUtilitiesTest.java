package elementsTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class ResponsiveUtilitiesTest extends BaseClass {

	final String url="http://localhost:8000/src/main/java/elements/fixtures/responsive.html";
	String inputFilePath = "src/main/java/elements/fixtures/responsive.html";
	String localUrl = new File(inputFilePath).getAbsolutePath();
	String responsiveValue_1,responsiveValue_2,defaultValue_1,defaultValue_2;

	@Parameters({"runEnv"})
	@Test(testName= "LG Test", enabled = true)
	public void lgTest(String runEnv) throws InterruptedException {
		if(runEnv.equals("sauce")){
			commonUtils.getUrl(url);
		}
		else{
			commonUtils.getUrl("file:///"+localUrl);
		}
		commonUtils.setWindowSize(1024,800);
		responsiveValue_1 = commonUtils.getText(respPgObj.largeVisible);
		responsiveValue_2=commonUtils.getCSSValue(respPgObj.largeVisible,"color");
		Assert.assertEquals(responsiveValue_1, "lg-visible","Responsive Failed");
		Assert.assertEquals(responsiveValue_2, "rgba(139, 0, 139, 1)","Responsive Failed");

		commonUtils.setWindowSize(1279,800);
		responsiveValue_1 = commonUtils.getText(respPgObj.largeVisible);
		responsiveValue_2=commonUtils.getCSSValue(respPgObj.largeVisible,"color");
		Assert.assertEquals(responsiveValue_1, "lg-visible","Responsive Failed");
		Assert.assertEquals(responsiveValue_2, "rgba(139, 0, 139, 1)","Responsive Failed");

		Thread.sleep(1000);
		commonUtils.setWindowSize(1023,800);
		defaultValue_1 = commonUtils.getText(respPgObj.largeHidden);
		defaultValue_2=commonUtils.getCSSValue(respPgObj.largeHidden,"color");
		Assert.assertEquals(defaultValue_1, "lg-hidden","Responsive Failed");
		Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)","Responsive Failed");

		commonUtils.setWindowSize(1280,800);
		defaultValue_1 = commonUtils.getText(respPgObj.largeHidden);
		defaultValue_2=commonUtils.getCSSValue(respPgObj.largeHidden,"color");
		Assert.assertEquals(defaultValue_1, "lg-hidden","Responsive Failed");
		Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)","Responsive Failed");
	}

	@Parameters({"runEnv"})
	@Test(testName= "XL Test", enabled = false)
	public void xlTest(String runEnv){
		if(runEnv.equals("sauce")){
			commonUtils.getUrl(url);
		}
		else{
			commonUtils.getUrl("file:///"+localUrl);
		}
		commonUtils.setWindowSize(1280,800);
		responsiveValue_1 = commonUtils.getText(respPgObj.xtraLargeVisible);
		responsiveValue_2=commonUtils.getCSSValue(respPgObj.xtraLargeVisible,"color");
		Assert.assertEquals(responsiveValue_1, "xl-visible","Responsive Failed");
		Assert.assertEquals(responsiveValue_2, "rgba(173, 255, 47, 1)","Responsive Failed");

		commonUtils.setWindowSize(1279,800);
		defaultValue_1 = commonUtils.getText(respPgObj.xtraLargeHidden);
		defaultValue_2=commonUtils.getCSSValue(respPgObj.xtraLargeHidden,"color");
		Assert.assertEquals(defaultValue_1, "xl-hidden","Responsive Failed");
		Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)","Responsive Failed");
	}

	@Parameters({"runEnv"})
	@Test(testName = "XS Test",enabled = false)
	public void xsTest(String runEnv) {
		if (runEnv.equals("sauce")) {
			commonUtils.getUrl(url);
		}
        else {
			commonUtils.getUrl("file:///" + localUrl);
		}
		commonUtils.setWindowSize(479, 800);
		responsiveValue_1 = commonUtils.getText(respPgObj.xtraSmallVisible);
		responsiveValue_2=commonUtils.getCSSValue(respPgObj.xtraSmallVisible,"color");
		Assert.assertEquals(responsiveValue_1, "xs-visible","Responsive Failed");
		Assert.assertEquals(responsiveValue_2, "rgba(255, 0, 0, 1)","Responsive Failed");

		commonUtils.setWindowSize(480,800);
		defaultValue_1 = commonUtils.getText(respPgObj.xtraSmallHidden);
		defaultValue_2=commonUtils.getCSSValue(respPgObj.xtraSmallHidden,"color");
		Assert.assertEquals(defaultValue_1, "xs-hidden","Responsive Failed");
		Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)","Responsive Failed");
	}


	@Parameters({"runEnv"})
	@Test(testName = "SM Test", enabled = false)
	public void smTest(String runEnv) {
		if (runEnv.equals("sauce")) {
			commonUtils.getUrl(url);
		}
		else {
			commonUtils.getUrl("file:///" + localUrl);
		}
		commonUtils.setWindowSize(480, 800);
		responsiveValue_1 = commonUtils.getText(respPgObj.smallVisible);
		responsiveValue_2=commonUtils.getCSSValue(respPgObj.smallVisible,"color");
		Assert.assertEquals(responsiveValue_1, "sm-visible","Responsive Failed");
		Assert.assertEquals(responsiveValue_2, "rgba(0, 128, 0, 1)","Responsive Failed");

		commonUtils.setWindowSize(767,800);
		responsiveValue_1 = commonUtils.getText(respPgObj.smallVisible);
		responsiveValue_2=commonUtils.getCSSValue(respPgObj.smallVisible,"color");
		Assert.assertEquals(responsiveValue_1, "sm-visible","Responsive Failed");
		Assert.assertEquals(responsiveValue_2, "rgba(0, 128, 0, 1)","Responsive Failed");

		commonUtils.setWindowSize(479,800);
		defaultValue_1 = commonUtils.getText(respPgObj.smallHidden);
		defaultValue_2=commonUtils.getCSSValue(respPgObj.smallHidden,"color");
		Assert.assertEquals(defaultValue_1, "sm-hidden","Responsive Failed");
		Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)","Responsive Failed");

		commonUtils.setWindowSize(768,800);
		defaultValue_1 = commonUtils.getText(respPgObj.smallHidden);
		defaultValue_2=commonUtils.getCSSValue(respPgObj.smallHidden,"color");
		Assert.assertEquals(defaultValue_1, "sm-hidden","Responsive Failed");
		Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)","Responsive Failed");
	}


	@Parameters({"runEnv"})
	@Test(testName= "MD Test", enabled = false)
	public void mdTest(String runEnv){
		if(runEnv.equals("sauce")){
			commonUtils.getUrl(url);
		}
		else{
			commonUtils.getUrl("file:///"+localUrl);
		}
		commonUtils.setWindowSize(768,800);
		responsiveValue_1 = commonUtils.getText(respPgObj.mediumVisible);
		responsiveValue_2=commonUtils.getCSSValue(respPgObj.mediumVisible,"color");
		Assert.assertEquals(responsiveValue_1, "md-visible","Responsive Failed");
		Assert.assertEquals(responsiveValue_2, "rgba(0, 0, 255, 1)","Responsive Failed");

		commonUtils.setWindowSize(1023,800);
		responsiveValue_1 = commonUtils.getText(respPgObj.mediumVisible);
		responsiveValue_2=commonUtils.getCSSValue(respPgObj.mediumVisible,"color");
		Assert.assertEquals(responsiveValue_1, "md-visible","Responsive Failed");
		Assert.assertEquals(responsiveValue_2, "rgba(0, 0, 255, 1)","Responsive Failed");

		commonUtils.setWindowSize(767,800);
		defaultValue_1 = commonUtils.getText(respPgObj.mediumHidden);
		defaultValue_2=commonUtils.getCSSValue(respPgObj.mediumHidden,"color");
		Assert.assertEquals(defaultValue_1, "md-hidden","Responsive Failed");
		Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)","Responsive Failed");

		commonUtils.setWindowSize(1024,800);
		defaultValue_1 = commonUtils.getText(respPgObj.mediumHidden);
		defaultValue_2=commonUtils.getCSSValue(respPgObj.mediumHidden,"color");
		Assert.assertEquals(defaultValue_1, "md-hidden","Responsive Failed");
		Assert.assertEquals(defaultValue_2, "rgba(128, 128, 128, 1)","Responsive Failed");
	}
}