package utilities;

/**
 * Created by vbalave on 6/8/16.
 */

import org.testng.SkipException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SauceParam {

    public static final String SAUCE_USERNAME;
    public static final String SAUCE_ACCESS_KEY;
    public static final String SAUCE_TUNNEL;
    public static final String TEST_ROOT_DIR;
    private static final String PROP_FILE = "src/main/resources/SauceParam.properties";
    private static Properties properties = new Properties();

    static {

        FileInputStream in = null;
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

        if (System.getenv("SAUCE_USERNAME") != null && !(System.getenv("SAUCE_USERNAME").equalsIgnoreCase(""))) {
            SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
        } else if (properties.getProperty("SauceUser") != null
                && !(properties.getProperty("SauceUser").equalsIgnoreCase(""))) {
            SAUCE_USERNAME = properties.getProperty("SauceUser");
        } else {
            throw new SkipException("SauceUser property not set, " + "it is mandate to define the SauceUser property");
        }

        if (System.getenv("SAUCE_ACCESS_KEY") != null && !(System.getenv("SAUCE_ACCESS_KEY").equalsIgnoreCase(""))) {
            SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
        } else if (properties.getProperty("SauceKey") != null
                && !(properties.getProperty("SauceKey").equalsIgnoreCase(""))) {
            SAUCE_ACCESS_KEY = properties.getProperty("SauceKey");
        } else {
            throw new SkipException("SauceKey property not set, " + "it is mandate to define the SauceKey property");
        }
        if (properties.getProperty("Tunnel") != null
                && !(properties.getProperty("Tunnel").equalsIgnoreCase(""))) {
            SAUCE_TUNNEL = properties.getProperty("Tunnel");
        } else {
            throw new SkipException("Tunnel property not set, " + "it is mandate to define the Tunnel property");
        }
    }
}
