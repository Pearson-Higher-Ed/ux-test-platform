package standAloneTests;

import org.testng.annotations.Test;
import utilities.BaseClass;

/**
 * Created by umahaea on 10/20/17.
 */
public class InstanbulCoverage extends BaseClass {

    @Test(groups = "desktop-regression")
    public void afterSuite() throws InterruptedException {
        commonUtils.getUrl("http://localhost:3000/coverage");
        Thread.sleep(300000);
    }
}