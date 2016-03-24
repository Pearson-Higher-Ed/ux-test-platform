package moleculesTests;

import utilities.BaseClass;
import org.testng.annotations.Test;

/**
 * Created by umahaea on 3/22/16.
 */
public class AppHeaderTest extends BaseClass{
    private final String url = "http://localhost:8000/src/main/java/molecules/fixtures/app-header.html";

    @Test(groups = {"desktop"})
    public void appHeaderCourseModeTest() throws InterruptedException {
        commonUtils.getUrl(url);
        Thread.sleep(5000);
    }
}