package origamiV2.origamiV2PageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by umahaea on 4/28/16.
 */
public class ContextualHelpPageObjects {

    public WebDriver driver;
    public AppiumDriver appium;

    public ContextualHelpPageObjects(WebDriver driver) {
        this.driver = driver;
    }
    public ContextualHelpPageObjects(AppiumDriver appium) {
        this.appium = appium;
    }

    public By contextualHelpDrawer = By.xpath("//div[@id='o-contextual-help-drawer']");
    public By contextualHelpHeader= By.xpath("//div[@class='o-contextual-help__header pe-label pe-label--large']");
    public By contextualHelpDrawerCloseButton=By.xpath("//i[@class='pe-icon--close']");
    public By contextualHelpDrawerHelpTopicDetailCloseButton=By.xpath("//div[@id='o-contextual-help-drawer']/div[2]/div/a[2]/i");
    public By contextualHelpDrawerOpen=By.xpath("//div[@class='o-drawer-right o-drawer-animated o-contextual-help__drawer o-drawer o-drawer-open']");
    public By contextualHelpDrawerClose=By.xpath("//div[@class='o-drawer-right o-drawer-animated o-contextual-help__drawer o-drawer']");
    public By helpTopicBackgroundColor=By.xpath("//div[@class='o-contextual-help__excerpt-list']/div[1]");

    public By helpTopics=By.xpath("//div[@class='o-contextual-help__excerpt-list']/div[1]/div");
    public String xpathForHelpTopics(String item, int x){
        return "//div[@class='o-contextual-help__excerpt-list']/div["+x+"]/div";
    }

    public By helpTopicTitle=By.xpath("//div[@class='o-contextual-help__excerpt-list']/div[1]/div/h4/span/a");
    public String xpathForHelpTopicsTitle(String item, int x){
        return "//div[@class='o-contextual-help__excerpt-list']/div["+x+"]/div/h4/span/a";
    }

    public By helpTopicExcerpt=By.xpath("//div[@class='o-contextual-help__excerpt-list']/div[1]/div/p");
    public String xpathForHelpTopicExcerpt(String item, int x){
        return "//div[@class='o-contextual-help__excerpt-list']/div["+x+"]/div/p";
    }

    public By helpContentTopicDetailVisible=By.xpath("//div[@class='o-drawer-right o-drawer-animated o-contextual-help__drawer o-drawer o-drawer-open o-contextual-help__detail--visible']");
    public By helpContentTopicDetailReopen=By.xpath("//div[@class='o-drawer-right o-drawer-animated o-contextual-help__drawer o-drawer o-contextual-help__detail--visible o-drawer-open']");
    public By helpContentTopicDetailHidden=By.xpath("//div[@class= 'o-drawer-right o-drawer-animated o-contextual-help__drawer o-drawer o-contextual-help__detail--visible']");
    public By helpTopicContentHeader=By.xpath("//div[@id='o-contextual-help-topic-content-target']/h4");
    public By helpTopicContent=By.xpath("//div[@id='o-contextual-help-topic-content-target']");
    public By backtoHelpTopicsLink=By.xpath("//div[@class='o-contextual-help__topic-content']/div/a");
    public By backToHelpTopicsIcon=By.xpath("//span[@class='back-to-help-icon']");
    public By helpContentTopicDetailTitle=By.xpath("//div[@id='o-contextual-help-topic-content-target']/h4");
    public By helpContentTopicDetailText=By.xpath("//div[@id='o-contextual-help-topic-content-target']/div");
    public By labelDemo=By.id("demo");
    public By openSpecificHelpTopic=By.id("openSpecificHelpTopic");
    public By openAndThenRemoveAllHelpTopics=By.id("openAndThenRemoveAllHelpTopics");
    public By openAndThenRemoveOneTopic= By.id("openAndThenRemoveOneTopic");
    public By openAndThenRemoveMoreThanOneTopic=By.id("openAndThenRemoveMoreThanOneTopic");
    public By removeAllTopicsAndSetLanguageAndAddtopicsAndOpen = By.id("removeAllTopicsAndSetLanguageAndAddtopicsAndOpen");

}
