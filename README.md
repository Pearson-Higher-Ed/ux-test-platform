# Test Platform for UX Design [![Build Status](https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform.svg?branch=master)](https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform)

## How to run your tests in local:
* Clone this project:
    `git clone https://github.com/Pearson-Higher-Ed/ux-test-platform.git`

    <details>
    <summary> Pre-requisite to run tests on local: </summary>
	Have <a href="https://nodejs.org/en/download/">node </a> and <a href="http://maven.apache.org/install.html">maven</a> installed on your machine.<br>
	1. elements_sdk:	
	<details> 
      <summary>Install _elements sdk_ on your local machine and copy the _elements.css_ file to /ux-test-       platform/src/main/java/elements/css/ </summary>
        <pre>
        git clone https://github.com/Pearson-Higher-Ed/elements.git
        cd elements
        git checkout branch-name
        npm install
        npm run build
        cp elements/dist/css/elements.css /ux-test-platform/src/main/java/elements/css/</pre>
    </details>
	2. origami_v2:
    <details> 
      <summary>Install origami v2 component(eg. app-header) on your local machine and copy the dist.app-header.js file to /ux-test- platform/src/main/java/origamiV2/jsfiles/appHeader/
    </summary>
    <pre>
        git clone https://github.com/Pearson-Higher-Ed/app-header.git
        cd app-header
        git checkout branch-name
        npm install
        npm run build
        cp app-header/build/dist.app-header.js /ux-test-platform/src/main/java/origamiV2/jsfiles/appHeader/
        cp app-header/node_modules/pearson-elements/dist/css/elements.css /ux-test-platform/src/main/java/origamiV2/css/appHeader/
        cp -R app-header/node_modules/pearson-elements/dist/fonts /ux-test-platform/</pre>
    </details>
	3. compounds_sdk:
	<details>
		<summary>Install _compounds sdk_ on your local machine and copy the dist.compounds.js and dev.compounds.js file to /ux-test- platform/src/main/java/compounds/jsfiles/ </summary>
        <pre>
        git clone https://github.com/Pearson-Higher-Ed/compounds.git
        cd compounds
        git checkout branch-name
        npm install
        npm run build
        cp compounds/build/dist.compounds.js /ux-test-platform/src/main/java/compounds/jsfiles/
        cp compounds/build/dev.compounds.js /ux-test-platform/src/main/java/compounds/jsfiles/
        cp compounds/node_modules/pearson-elements/dist/css/elements.css /ux-test-platform/src/main/java/compounds/css/</pre>		
	</details>
    </details>

NOTE: Mobile tests runs only on Sauce Machine. But still it can be triggered locally to run on Sauce via Sauce Connect. Follow this <a href="https://neo.pearson.com/docs/DOC-617300">link</a> for detailed steps.

* Go to ux-test-platform directory
<pre>
    cd ux-test-platform
    git checkout rebrand
    npm install
    npm run copy-assets
    npm start</pre>
 * In test_suites/&lt;&lt;component&gt;&gt;.xml set the below values, and leave the rest to default:
    <details>
    <summary>For CI tests:</summary><pre>
    &lt;include name="desktop-ci"/&gt;</pre>
    </details>
    <details>
    <summary>For Regression tests:</summary>
    <pre>
    &lt;include name="desktop-regression"/&gt;</pre>
    </details>

* Run mode configuration:
	* Run it as a maven test:<pre>mvn test</pre>
	* Run it as a testng suite. test_suites/<component.xml>
	
## How to run your tests in sauce via Travis CI:
* Go to test_suites/<component.xml> and set below settings:
    <details>
    <summary>To run desktop tests:</summary>
    <pre>&lt;include name="desktop-ci"/&gt;</pre>
    </details>
    <details>
    <summary>To run mobile tests:</summary>
    <pre>&lt;include name="mobie-regression"/&gt;</pre>
    </details>

* Make a codechange/commit to this repo
* In Travis CI, ux-test-platform build is triggered automatically
* Monitor the status of the build by looking into the Travis CI <a href="https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform/builds">logs</a>
* To run elements_sdk:
    <details>
    <summary>Set .travis.yml: </summary>
    <pre>
    export component=elements_sdk
    export feature_branch=v1
    chmod 777 ./src/main/shell_scripts/components.sh
    ./src/main/shell_scripts/components.sh
    mvn -Dtest_suite_xml=elements_sdk.xml test</pre>
    </details>
* To run origami V2(eg. app-header):
    <details>
    <summary>Set .travis.yml: </summary>
    <pre>
    export component=app-header
    export feature_branch=master
    chmod 777 ./src/main/shell_scripts/components.sh
    ./src/main/shell_scripts/components.sh
    mvn -Dtest_suite_xml=app_header.xml test</pre>
    </details>
* To run compounds sdk:
    <details>
    <summary>Set .travis.yml: </summary>
    <pre>
    export component=compounds_sdk
    export feature_branch=v0
    chmod 777 ./src/main/shell_scripts/components.sh
    ./src/main/shell_scripts/components.sh
    mvn -Dtest_suite_xml=compounds_sdk.xml test</pre>
    </details>

### How to set browser config properties:
* Go to src/main/resources/environment.properties
* Replace the default values to new values. Make sure the config properites are case-sensitive as accepted by Sauce. Refer [How to set the correct platform config:](#How-to-set-the-correct-platform-config) section.

### Shell_Scripts for npm intall:
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

## More Details on how to contribute to this repo:
Look into this NEO page: https://neo.pearson.com/docs/DOC-607807
