package elementsTests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class ResponsiveUtilitiesTest extends BaseClass {

	final String url="http://localhost:8000/src/main/java/elements/fixtures/responsive.html";
	String inputFilePath = "src/main/java/elements/fixtures/responsive.html";
	String localUrl = new File(inputFilePath).getAbsolutePath();
	static String env="";

	@Parameters({"runEnv"})
	@BeforeClass
	public void setEnv(String runEnv){
		env=runEnv;
	}

	@Test(testName = "XS Test")
	public void xsTest(String runEnv) {
		String responsiveValue,defaultValue;
		if (env.equals("sauce")) {
			commonUtils.getUrl(url);
		}
        else {
			commonUtils.getUrl("file:///" + localUrl);
		}
		commonUtils.setWindowSize(479, 800);
		responsiveValue = commonUtils.getCSSValue(respPgObj.xtraSmall,"display");
		Assert.assertEquals(responsiveValue, "block","Responsive Failed");

		commonUtils.setWindowSize(480,800);
		defaultValue = commonUtils.getCSSValue(respPgObj.xtraSmall,"display");
		Assert.assertEquals(defaultValue, "none","Responsive Failed");
	}

	@Test(testName = "SM Test")
	public void smTest(String runEnv) {
		String responsiveValue,defaultValue;

		if (env.equals("sauce")) {
			commonUtils.getUrl(url);
		}
		else {
			commonUtils.getUrl("file:///" + localUrl);
		}
		commonUtils.setWindowSize(480, 800);
		responsiveValue = commonUtils.getCSSValue(respPgObj.small,"display");
		Assert.assertEquals(responsiveValue, "block","Responsive Failed");

		commonUtils.setWindowSize(767,800);
		responsiveValue = commonUtils.getCSSValue(respPgObj.small,"display");
		Assert.assertEquals(responsiveValue, "block","Responsive Failed");

		commonUtils.setWindowSize(479,800);
		defaultValue = commonUtils.getCSSValue(respPgObj.small,"display");
		Assert.assertEquals(defaultValue, "none","Responsive Failed");

		commonUtils.setWindowSize(768,800);
		defaultValue = commonUtils.getCSSValue(respPgObj.small,"display");
		Assert.assertEquals(defaultValue, "none","Responsive Failed");
	}

	@Test(testName= "MD Test")
	public void mdTest(String runEnv){
		String responsiveValue,defaultValue;

		if(env.equals("sauce")){
			commonUtils.getUrl(url);
		}
		else{
			commonUtils.getUrl("file:///"+localUrl);
		}
		commonUtils.setWindowSize(768,800);
		responsiveValue=commonUtils.getCSSValue(respPgObj.medium,"display");
		Assert.assertEquals(responsiveValue,"block","Responsive Failed");

		commonUtils.setWindowSize(1023,800);
		defaultValue=commonUtils.getCSSValue(respPgObj.medium,"display");
		Assert.assertEquals(defaultValue,"block","Responsive Failed");

		commonUtils.setWindowSize(767,800);
		defaultValue = commonUtils.getCSSValue(respPgObj.medium,"display");
		Assert.assertEquals(defaultValue, "none","Responsive Failed");

		commonUtils.setWindowSize(1024,800);
		defaultValue = commonUtils.getCSSValue(respPgObj.medium,"display");
		Assert.assertEquals(defaultValue, "none","Responsive Failed");
	}

	@Test(testName= "LG Test")
	public void lgTest(String runEnv){
		String responsiveValue,defaultValue;

		if(runEnv.equals("sauce")){
			commonUtils.getUrl(url);
		}
		else{
			commonUtils.getUrl("file:///"+localUrl);
		}
		commonUtils.setWindowSize(1024,800);
		responsiveValue=commonUtils.getCSSValue(respPgObj.large,"display");
		Assert.assertEquals(responsiveValue,"block","Responsive Failed");

		commonUtils.setWindowSize(1279,800);
		defaultValue=commonUtils.getCSSValue(respPgObj.large,"display");
		Assert.assertEquals(defaultValue,"block","Responsive Failed");

		commonUtils.setWindowSize(1023,800);
		defaultValue = commonUtils.getCSSValue(respPgObj.large,"display");
		Assert.assertEquals(defaultValue, "none","Responsive Failed");

		commonUtils.setWindowSize(1280,800);
		defaultValue = commonUtils.getCSSValue(respPgObj.large,"display");
		Assert.assertEquals(defaultValue, "none","Responsive Failed");
	}

	@Test(testName= "XL Test")
	public void xlTest(String runEnv){
		String responsiveValue,defaultValue;

		if(env.equals("sauce")){
			commonUtils.getUrl(url);
		}
		else{
			commonUtils.getUrl("file:///"+localUrl);
		}
		commonUtils.setWindowSize(1280,800);
		responsiveValue=commonUtils.getCSSValue(respPgObj.xtraLarge,"display");
		Assert.assertEquals(responsiveValue,"block","Responsive Failed");

		commonUtils.setWindowSize(1279,800);
		defaultValue = commonUtils.getCSSValue(respPgObj.xtraLarge,"display");
		Assert.assertEquals(defaultValue, "none","Responsive Failed");
	}

}