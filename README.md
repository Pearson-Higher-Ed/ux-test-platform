# Test Platform for UX Design [![Build Status](https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform.svg?branch=master)](https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform)

## How to run your tests in local:
1. Clone this project:
    `git clone https://github.com/Pearson-Higher-Ed/ux-test-platform.git`

    <details>
    <summary>:hand: Pre-requisite to run tests on local: </summary>
	Have <a href="https://nodejs.org/en/download/">node </a> and <a href="http://maven.apache.org/install.html">maven</a> installed on your machine.<br>
    <details><summary>:point_right: elementsSDK:</summary>	 
      Choose a different working directory, Install elementsSDK on your local machine. Follow the below steps:
        <pre>
        git clone https://github.com/Pearson-Higher-Ed/elements-sdk.git
        cd elements-sdk
        git checkout branch-name
        npm install
        npm run build
        cp build/dist.elements-sdk.js /ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/
        cp build/eventInstantiator.elements-sdk.js /ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/
        cp build/css/elements.css /ux-test-platform/src/main/java/elementsSDK/css/</pre>
    </details>
	<details><summary>:point_right: standAlone:</summary>
     Choose a different working directory, Install a stand alone component(eg. app-header) on your local machine. Follow the below steps:
    <pre>
        git clone https://github.com/Pearson-Higher-Ed/app-header.git
        cd app-header
        git checkout branch-name
        npm install
        npm run build
        cp build/dist.app-header.js /ux-test-platform/src/main/java/standAlone/jsfiles/appHeader/
        cp node_modules/elementsSDK/build/css/elements.css /ux-test-platform/src/main/java/standAlone/css/appHeader/</pre>
    </details>
    </details>

2. Go to ux-test-platform directory
<pre>
    cd ux-test-platform
    git checkout rebrand
    npm install
    npm run copy-assets
    python -m SimpleHTTPServer
</pre>
3. In test_suites/&lt;&lt;component&gt;&gt;.xml set the below values, and leave the rest to default:
    <details>
    <summary>For CI tests:</summary><pre>
    &lt;include name="desktop-ci"/&gt;</pre>
    </details>
    <details>
    <summary>For Regression tests:</summary>
    <pre>
    &lt;include name="desktop-regression"/&gt;</pre>
    </details>

4. Run mode configuration:
	* Run it as a maven test from your Terminal/Cmd prompt(for eg. elements_sdk.xml):<pre>cd /workspace/ux-test-platform 
	mvn -Dtest_suite_xml=elements_sdk.xml</pre>
	* Run it as a testng suite. Right click on test_suites/<component.xml> and run as a testng suite.
	
NOTE: Mobile tests runs can also be run Sauce via your local machine. Follow this <a href="https://github.com/Pearson-Higher-Ed/ux-test-platform/blob/rebrand/docs/run-mobile-tests-on-sauce-via-local.md"><b>DOCS</b></a> 
	
## How to run your tests in sauce via Travis CI:
1. Go to .travis.yml and set the below settins:    
    <details>
    <summary>:runner:To run elementsSDK:</summary>
    <pre>
    export component=elementsSDK
    export feature_branch=v1
    chmod 777 ./src/main/shell_scripts/components.sh
    ./src/main/shell_scripts/components.sh
    mvn -Dtest_suite_xml=elements_sdk.xml test</pre>
    </details>
    <details>
    <summary>:runner:To run stand alone(eg. app-header):</summary>
    <pre>
    export component=app-header
    export feature_branch=master
    chmod 777 ./src/main/shell_scripts/components.sh
    ./src/main/shell_scripts/components.sh
    mvn -Dtest_suite_xml=app_header.xml test</pre>
    </details>
2. Go to test_suites/<component.xml> and set below settings:
    <details>
    <summary>:runner:To run desktop tests:</summary>
    <pre>&lt;include name="desktop-regression"/&gt;</pre>
    </details>
    <details>
    <summary>:runner:To run mobile tests:</summary>
    <pre>&lt;include name="mobie-regression"/&gt;</pre>
    </details>   

3. Make a codechange/commit to this repo
4. In Travis CI, ux-test-platform build is triggered automatically
5. Monitor the status of the build by looking into the Travis CI <a href="https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform/builds">logs</a>

### How to set browser config properties:
* Go to src/main/resources/environment.properties
* Replace the default values to new values. Make sure the config properites are case-sensitive as accepted by Sauce. Refer [How to set the correct platform config](#how-to-set-the-correct-platform-config) section.

### Shell_Scripts for npm install:
* If there is a component that you would want to npm install on this ux-test-platform
    * Go to src/main/shell_scripts/components.sh file.
    * Write a function with the component_name. for ex. install_component()
    * Add the npm install scripts and parametrize the function to checkout appropriate feature_branch name. Follow one of the existing     functions.
    <details>
    <summary>Set .travis.yml: </summary>
      <pre>script:
            export component=component_name
            export feature_branch=feature_branch_name
            mvn -Dtest_suite_xml=component_name.xml test</pre>
      </details>

## When you fork the repo
Tests would not run as the Sauce Connect secured encrypted user name and auth_key wouldn't work.The problem is due to encrypted environment variables SAUCE_USERNAME and SAUCE_ACCESS_KEY. Each repository needs its own encrypted variables, and they are not passed from the original repository to the fork. Regenerate encrypted environment variables SAUCE_USERNAME and SAUCE_ACCESS_KEY for the forked repo.
    <details>
    <summary>Encrypt sauce variables:</summary>
    <pre>
    cd ux-test-platform
    travis encrypt SAUCE_USERNAME=p_PDAauto   //This generates a new encrypted value. Simply replace the first 'secure' value in .travis.yml to this newly generated value
    travis encrypt SAUCE_ACCESS_KEY=xxx-xxx-xxx //This generates a new encrypted value. Simply replace the second 'secure' value in .travis.yml to this newly generated value</pre>
    </details>
* Now commit this and push to your forked repo. It will work as expected.

## How to set the correct platform config
Go this link: https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
* The Copy Code section is the right config platform. The same config should be used as Desired Capabilities in Test Code. Anything else would throw an error on Sauce and tests wouldn't run.
        Refer <a href="https://github.com/Pearson-Higher-Ed/ux-test-platform/blob/rebrand/src/main/java/utilities/BaseClass.java">Base Class </a> where desired Capabilities are set.

## How to view the test results:
Go to Travis CI https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform/builds/<last_build_run_id&gt;

Look for TESTS section.

## How to contribute to this repo:
Look into this <a href="https://github.com/Pearson-Higher-Ed/ux-test-platform/tree/rebrand/docs"><b>DOCS</b></a> 
