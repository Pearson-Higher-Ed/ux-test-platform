package utilities;

import org.testng.SkipException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by udhadpa on 1/2/18.
 */
public class BrowserStackParam {

    public static final String BROWSERSTACK_USERNAME;
    public static final String BROWSERSTACK_ACCESS_KEY;
    public static final String BROWSERSTACK_LOCAL_IDENTIFIER;
    public static final String TEST_ROOT_DIR;
    private static final String PROP_FILE = "src/main/resources/BrowserStackParam.properties";
    private static Properties properties = new Properties();
    private static final boolean env = String.valueOf(System.getenv().get("USER")).equals("travis");

    static {

        FileInputStream in = null;
        BROWSERSTACK_LOCAL_IDENTIFIER = properties.getProperty("LocIdentifier");
        TEST_ROOT_DIR = System.getProperty("user.dir");
        try {
            in = new FileInputStream(TEST_ROOT_DIR + File.separator + PROP_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new SkipException(PROP_FILE + " -> Sauce Connect Config file not found, Please specify the correct config file");
        }

        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SkipException("Failure loading property file -> " + e.getMessage());
        }

        if (System.getenv("BROWSERSTACK_USERNAME") != null && !(System.getenv("BROWSERSTACK_USERNAME").equalsIgnoreCase(""))) {
            BROWSERSTACK_USERNAME = System.getenv("BROWSERSTACK_USERNAME");
        } else if (properties.getProperty("BSUser") != null
                && !(properties.getProperty("BSUser").equals("\"\"")) && !(properties.getProperty("BSUser").equalsIgnoreCase("dummy"))) {
            BROWSERSTACK_USERNAME = properties.getProperty("BSUser");
        } else {
            throw new SkipException("BSUser property not set, " + "it is mandate to define the BSUser property");
        }

        if (System.getenv("BROWSERSTACK_ACCESS_KEY") != null && !(System.getenv("BROWSERSTACK_ACCESS_KEY").equalsIgnoreCase(""))) {
            BROWSERSTACK_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
        } else if (properties.getProperty("BSKey") != null
                && !(properties.getProperty("BSKey").equalsIgnoreCase("\"\"")) && !(properties.getProperty("BSKey").equalsIgnoreCase("dummy"))) {
            BROWSERSTACK_ACCESS_KEY = properties.getProperty("BSKey");
        } else {
            throw new SkipException("BSKey property not set, " + "it is mandate to define the BSKey property");
        }
        if (!env && properties.getProperty("LocIdentifier") != null
                && !(properties.getProperty("LocIdentifier").equalsIgnoreCase("\"\"")) && !(properties.getProperty("LocIdentifier").equalsIgnoreCase("dummy"))) {
        } else {
            if (!env) {
                throw new SkipException("LocIdentifier property not set, " + "it is mandate to define the LocIdentifier property");
            }
        }

    }
}
