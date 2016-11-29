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
    public By contextualHelpHeader= By.xpath("//div[@class='o-contextual-help__header']/h2");
    public By contextualHelpDrawerHeader = By.xpath("//div[@class='o-contextual-help__header']");
    public By contextualHelpDrawerCloseIcon=By.xpath("//button[@class='close-help']/*[local-name() = 'svg']");
    public By contextualHelpDrawerCloseButton=By.xpath("//button[@class='close-help']");
    public By contextualHelpDrawerHelpTopicDetailCloseButton=By.xpath("//div[@id='o-contextual-help-drawer']/div[2]/div/button/*[local-name() = 'svg']");
    public By contextualHelpDrawerOpen=By.xpath("//div[@class='o-drawer-right o-drawer-animated o-contextual-help__drawer exists-o-header o-drawer o-drawer-open']");
    public By contextualHelpDrawerOpenWithoutAppHeader=By.xpath("//div[@class='o-drawer-right o-drawer-animated o-contextual-help__drawer o-drawer o-drawer-open']");
    public By contextualHelpDrawerClose=By.xpath("//div[@class='o-drawer-right o-drawer-animated o-contextual-help__drawer exists-o-header o-drawer']");
    public By helpTopicBackgroundColor=By.xpath("//div[@class='o-contextual-help__excerpt-list']/div[1]");

    public By helpTopicsSection=By.xpath("//div[@class='o-contextual-help__topics']");
    public By helpTopicsSectionList = By.xpath("//div[@class='o-contextual-help__excerpt-list']");
    public By helpTopicLayout=By.xpath("//div[@class='o-contextual-help__topic']");
    public By helpTopics=By.xpath("//div[@class='o-contextual-help__excerpt-list']/div[1]/div");
    public By helpTopicHeading = By.xpath("//div[@class='o-contextual-help__topic o-contextual-help__excerpt']/h3");
    public String xpathForHelpTopics(String item, int x){
        return "//div[@class='o-contextual-help__excerpt-list']/div["+x+"]/div";
    }

    public By helpTopicTitle=By.xpath("//div[@class='o-contextual-help__excerpt-list']/div[1]/div/h3/a");
    public String xpathForHelpTopicsTitle(String item, int x){
        return "//div[@class='o-contextual-help__excerpt-list']/div["+x+"]/div/h3/a";
    }

    public By helpTopicExcerpt=By.xpath("//div[@class='o-contextual-help__excerpt-list']/div[1]/div/p");
    public String xpathForHelpTopicExcerpt(String item, int x){
        return "//div[@class='o-contextual-help__excerpt-list']/div["+x+"]/div/p";
    }

    public By helpContentTopicDetailVisible=By.xpath("//div[@class='o-drawer-right o-drawer-animated o-contextual-help__drawer exists-o-header o-drawer o-drawer-open o-contextual-help__content--visible']");
    public By helpContentTopicDetailReopen=By.xpath("//div[@class='o-drawer-right o-drawer-animated o-contextual-help__drawer exists-o-header o-drawer o-contextual-help__content--visible o-drawer-open']");
    public By helpContentTopicDetailHidden=By.xpath("//div[@class= 'o-drawer-right o-drawer-animated o-contextual-help__drawer exists-o-header o-drawer o-contextual-help__content--visible']");
    public By helpTopicContentHeader=By.xpath("//div[@id='o-contextual-help-topic-content-target']/h4");
    public By helpTopicContent=By.xpath("//div[@id='o-contextual-help-topic-content-target']");
    public By backtoHelpTopicsLink=By.xpath("//button[@class='pe-label pe-label--large back-to-help']");
    public By backToHelpTopicsButton = By.xpath("//button[@class='back-to-help']");
    public By backToHelpTopicsIcon=By.xpath("//*[@class='back-to-help']/*[local-name() = 'svg']");
    public By backToHelpTopicsSpan=By.xpath("//button[@class='back-to-help']/span");
    public By helpContentTopicDetailTitle=By.xpath("//*[@id='o-contextual-help-topic-content-target']/h2");
    public By helpContentTopicDetailText=By.xpath("//*[@id='o-contextual-help-topic-content-target']/div/p");
    public By labelDemo=By.id("demo");
    public By openSpecificHelpTopic=By.id("openSpecificHelpTopic");
    public By openAndThenRemoveAllHelpTopics=By.id("openAndThenRemoveAllHelpTopics");
    public By openAndThenRemoveOneTopic= By.id("openAndThenRemoveOneTopic");
    public By openAndThenRemoveMoreThanOneTopic=By.id("openAndThenRemoveMoreThanOneTopic");
    public By removeAllTopicsAndSetLanguageAndAddtopicsAndOpen = By.id("removeAllTopicsAndSetLanguageAndAddtopicsAndOpen");
    public By toggleHelpDrawerButton=By.id("toggleHelpDrawer");
    public By testAccordianContentLink = By.id("testAccordianContent");

    public String xpathForAccordionItemButton(String item, int x){
        return "//div[@id='o-contextual-help-topic-content-target']/div/div/h3["+x+"]/button";
    }

    public String xpathForItemIcon(String item, int x){
        return "//div[@id='o-contextual-help-topic-content-target']/div/div/h3["+x+"]/button/*[local-name() = 'svg']";
    }

    public String xpathForItemPanel(String item, int x){
        return "//div[@id='o-contextual-help-topic-content-target']/div/div/div["+x+"]";
    }
}