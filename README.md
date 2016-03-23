#Test Platform for UX Design
<img src="https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform.svg?branch=master" alt="Build Status" />

##How to run your tests in local:
1. Clone this project:
    `git clone https://github.com/Pearson-Higher-Ed/ux-test-platform.git`

3. Go to ux-test-platform directory
4. In testng.xml set the below values, and leave the rest to default:
<pre>
    &lt;parameter name="runEnv" value="local"&gt;&lt;/parameter&gt;
    &lt;parameter name="desktop" value="on">&lt;/parameter&gt;
    &lt;parameter name="mobile" value="off"&gt;
        &lt;groups&gt;
            &lt;run&gt;
                &lt;include name="desktop"/&gt;
            &lt;/run&gt;
        &lt;/groups&gt;
</pre>








5. Run the command from the root directory:
<pre>
mvn test
</pre>

NOTE: Mobile tests runs only on Sauce Machine.

##How to run your tests in sauce:
1. Leave the default testng.xml settings. Make sure to have the below parameters always set to:
<pre>
  &lt;parameter name="runEnv" value="sauce"&gt;&lt;/parameter&gt;
</pre>
    - If you want to run desktop tests:
    <pre>
    &lt;parameter name="desktop" value="on"&gt;&lt;/parameter&gt;
    &lt;parameter name="mobile" value="off"&gt;&lt;/parameter&gt;
     &lt;groups&gt;
            &lt;run&gt;
                &lt;include name="desktop"/&gt;
            &lt;/run&gt;
        &lt;/groups&gt;
    </pre>
	
	
    - If you want to run mobile tests:
    <pre>
    &lt;parameter name="desktop" value="off"&gt;&lt;/parameter&gt;
    &lt;parameter name="mobile" value="on"&gt;&lt;/parameter&gt;
     &lt;groups&gt;
            &lt;run&gt;
                &lt;include name="mobile"/&gt;
            &lt;/run&gt;
        &lt;/groups&gt;
    </pre>
2. Make a codechange/commit to this repo
3. In Travis CI, ux-test-platform build is triggered automatically.
4. Monitor the status of the build by looking into the Travis CI logs
    https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform/builds

##How to set the correct platform config:
Go this link: https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/

    - API
        Desktop - Choose Selenium
        Mobile - Choose Appium
    - Device
        Desktop - Choose Desktop - PC(Windows) or Mac
        Mobile - Choose iOS or Android - Choose your desired device
    - Operating System
        Desktop - Choose your OS
        Mobile - Choose your iOS or Android OS
    - Appium Version
        Desktop - does not apply
        Mobile - Choose latest stable Appium version
    - Copy Code
        The Copy Code section is the right config platform. The same config should be used as Desired Capabilities in Test Code. Anything else would throw an error on Sauce and tests wouldn't run.
        Refer Base Class where desired Capabilities are set:                    
        https://github.com/Pearson-Higher-Ed/ux-test-platform/blob/master/src/test/java/elementsTests/BaseClass.java

##How to view the test results:
Go to Travis CI https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform/builds/<last_build_run_id&gt;

Look for TESTS section.
