#Test Platform for UX Design [![Build Status](https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform.svg?branch=master)](https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform)

##How to run your tests in local:
* Clone this project:
    `git clone https://github.com/Pearson-Higher-Ed/ux-test-platform.git`

#####Pre-requisite to run tests on local:
  * __elements sdk__:
Install _elements sdk_ on your local machine and copy the _elements.css_ file to /ux-test-platform/src/main/java/elements/css/
    <pre>
    git clone https://github.com/Pearson-Higher-Ed/elements.git
    cd elements
    npm install
    npm run build
    cp elements/dist/css/elements.css /ux-test-platform/src/main/java/elements/css/
    </pre>

* __origami_v2__:
    *  Run python -m SimpleHTTPServer or npm install and npm start from the root directory of ux-test-platform. This will start localhost:8000
    *  Install origami v2 component(eg. app-header) on your local machine and copy the dist.app-header.js file to /ux-test-platform/src/main/java/origamiV2/jsfiles/appHeader/
    *  <pre>
    git clone https://github.com/Pearson-Higher-Ed/app-header.git
    cd app-header
    npm install
    npm run build
    cp app-header/build/dist.app-header.js /ux-test-platform/src/main/java/origamiV2/jsfiles/appHeader/
    cp app-header/node_modules/pearson-elements/dist/css/elements.css /ux-test-platform/src/main/java/elements/css/
    cp -R app-header/node_modules/pearson-elements/dist/fonts /ux-test-platform/
    </pre>

NOTE: Mobile tests runs only on Sauce Machine. But still it can be triggered locally to run on Sauce via Sauce Connect. Follow this <a href="https://neo.pearson.com/docs/DOC-617300">link</a> for detailed steps.

* Go to ux-test-platform directory
    * In test_suites/&lt;&lt;component&gt;&gt;.xml set the below values, and leave the rest to default:
    * For CI tests -> set name="desktop-ci"
    * For Regression tests -> set name="desktop-regression"
<pre>
    &lt;parameter name="runEnv" value="local"&gt;&lt;/parameter&gt;
    &lt;parameter name="desktop" value="on">&lt;/parameter&gt;
    &lt;parameter name="mobile" value="off"&gt;
        &lt;groups&gt;
            &lt;run&gt;
                &lt;include name="desktop-ci"/&gt;
            &lt;/run&gt;
        &lt;/groups&gt;
</pre>

* Run the command from the root directory:
<pre>
mvn test
</pre>

##How to run your tests in sauce via Travis CI:
Leave the default test_suites/<component>.xml settings. Make sure to have the below parameters always set to:
<pre>
&lt;parameter name="runEnv" value="sauce"&gt;&lt;/parameter&gt;
</pre>
* If you want to run desktop tests:
<pre>
&lt;parameter name="desktop" value="on">&lt;/parameter&gt;
    &lt;parameter name="mobile" value="off"&gt;
        &lt;groups&gt;
            &lt;run&gt;
                &lt;include name="desktop-ci"/&gt;
            &lt;/run&gt;
        &lt;/groups&gt;
</pre>
* If you want to run mobile tests:
<pre>
&lt;parameter name="desktop" value="off">&lt;/parameter&gt;
    &lt;parameter name="mobile" value="on"&gt;
        &lt;groups&gt;
            &lt;run&gt;
                &lt;include name="mobie-regression"/&gt;
            &lt;/run&gt;
        &lt;/groups&gt;
</pre>
* Make a codechange/commit to this repo
* In Travis CI, ux-test-platform build is triggered automatically
* Monitor the status of the build by looking into the Travis CI logs https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform/builds
* To run elements_sdk:
    * In .travis.yml file set
    <pre>
    export component=elements_sdk
    export feature_branch=v0
    chmod 777 ./src/main/shell_scripts/components.sh
    ./src/main/shell_scripts/components.sh
    mvn -Dtest_suite_xml=elements_sdk.xml test
</pre>
* To run origami V2(eg. app-header):
    * In .travis.yml file set
    <pre>
    export component=app-header
    export feature_branch=master
    chmod 777 ./src/main/shell_scripts/components.sh
    ./src/main/shell_scripts/components.sh
    mvn -Dtest_suite_xml=app_header.xml test
</pre>
* To run compounds sdk:
    * In .travis.yml file set
    <pre>
    export component=compounds_sdk
    export feature_branch=v0
    chmod 777 ./src/main/shell_scripts/components.sh
    ./src/main/shell_scripts/components.sh
    mvn -Dtest_suite_xml=compounds_sdk.xml test
</pre>

###Shell_Scripts for npm intall:
* If there is a component that you would want to npm install on this ux-test-platform
    * Go to src/main/shell_scripts/components.sh file.
    * Write a function with the component_name. for ex. install_component()
    * Add the npm install scripts and parametrize the function to checkout appropriate feature_branch name. Follow one of the existing     functions.
    * In .travis.yml file make sure, you set the below values
      * <pre>script:
            export component=component_name
            export feature_branch=feature_branch_name
            mvn -Dtest_suite_xml=component_name.xml test
      </pre>

##When you fork the repo
Tests would not run as the Sauce Connect secured encrypted user name and auth_key wouldn't work.The problem is due to encrypted environment variables SAUCE_USERNAME and SAUCE_ACCESS_KEY. Each repository needs its own encrypted variables, and they are not passed from the original repository to the fork.Regenerate encrypted environment variables SAUCE_USERNAME and SAUCE_ACCESS_KEY:

Go to your forked repo directory (cd ux-test-platform) and run below two commands
    <pre>travis encrypt SAUCE_USERNAME=p_PDAauto   //This generates a new encrypted value. Simply replace the first 'secure' value in .travis.yml to this newly generated value
travis encrypt SAUCE_ACCESS_KEY=xxx-xxx-xxx //This generates a new encrypted value. Simply replace the second 'secure' value in .travis.yml to this newly generated value</pre>

Now commit this and push to your forked repo. It will work as expected.

##How to set the correct platform config:
Go this link: https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/
* The Copy Code section is the right config platform. The same config should be used as Desired Capabilities in Test Code. Anything else would throw an error on Sauce and tests wouldn't run.
        Refer Base Class where desired Capabilities are set:                    
        https://github.com/Pearson-Higher-Ed/ux-test-platform/blob/master/src/main/java/utilities/BaseClass.java

##How to view the test results:
Go to Travis CI https://travis-ci.org/Pearson-Higher-Ed/ux-test-platform/builds/<last_build_run_id&gt;

Look for TESTS section.

##More Details on how to contribute to this repo:
Look into this NEO page: https://neo.pearson.com/docs/DOC-607807
