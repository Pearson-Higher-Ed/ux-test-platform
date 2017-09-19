# How to contribute to this repo:
You can fork the repo or create a branch out of 'rebrand' and make your changes. Create a Pull Request for your changes to merge into this Original 'rebrand' branch.

## Basic Set Up
1. Clone this project:

    `git clone https://github.com/Pearson-Higher-Ed/ux-test-platform.git`

2. Create a feature branch:

    By default you would see 'rebrand' branch been checked out.
    * Create a new feature branch:
    <pre>git checkout -b feature_branch</pre>
3.  Make sure what component is been developed, it should be one of these:
    * elementsSDK
    * stand alone  
## To contribute elements-sdk(style and functional) Tests:
* :point_right: __Tests__:
    <details><summary>Styles: </summary>
    Write Styles tests into:
    <pre>{project.base.dir}/workspace/ux-test-platform/src/test/java/elementsSDKTests/stylesTests/</pre>
    </details>
    <details><summary>Functional: </summary>
    Write Functional tests into:
    <pre> {project.base.dir}/workspace/ux-test-platform/src/test/java/elementsSDKTests/functionalTests/</pre>
    </details>    
     
* :point_right: __Main__:
    <details><summary>Styles:</summary>
    <i>Page Objects:</i></br>
    Write the page objects and its properties goes into: </br>
    <pre>{project.base.dir}/workspace/ux-test-platform/src/main/java/elementsSDK/styles/stylesPageObjects/</pre>
    <i>Fixtures:</i></br>
    Write html for respective elements sdk into:
    <pre>{project.base.dir}/workspace/ux-test-platform/src/main/java/elementsSDK/styles/fixtures/</pre>
    </details>
    <details><summary>Functional:</summary>
    <i>Page Objects:</i></br> 
    Write the page objects and its properties into: </br>
    <pre>{project.base.dir}/workspace/ux-test-platform/src/main/java/elementsSDK/functional/functionalPageObjects/</pre>
    <i>Fixtures:</i></br> 
    Write the html for the component into:
    <pre>{project.base.dir}/ux-test-platform/src/main/java/elementsSDK/functional/fixtures/ </pre>
    <i>JSFiles:</i></br>Write all the js files into:
    <pre>{project.base.dir}/ux-test-platform/src/main/java/elementsSDK/functional/jsfiles/ </pre>
    </details>
NOTE: Follow one of the existing components standards to write the tests and supporting classes.

## To contribute stand-alone Tests:
* :point_right: __Tests__: </br>
    Write tests into:
    <pre>{project.base.dir}/workspace/ux-test-platform/src/test/java/standAloneTests/</pre>
* :point_right: __Main__: </br>
    <i>Page Objects:</i></br>
    Write the page objects and its properties into:
    <pre>{project.base.dir}/workspace/ux-test-platform/src/main/java/standAlone/standAlonePageObjects/</pre>
    <i>Fixtures:</i></br> 
    Write the html for respective stand-alone into:
    <pre>{project.base.dir}/workspace/ux-test-platform/src/main/java/standAlone/fixtures/</pre>
    <i>JSFiles:</i></br>
    Write the js files into:
    <pre>{project.base.dir}/workspace/ux-test-platform/src/main/java/standAlone/jsfiles/</pre>
    
NOTE: Follow one of the existing components standards to write the tests and supporting classes.

## Shell_Scripts for npm install:
If there is a component that you would want to npm install on this ux-test-platform, follow these <a href="https://github.com/Pearson-Higher-Ed/ux-test-platform/blob/rebrand/README.md#shell_scripts-for-npm-intall">steps</a>.

## Run across X platforms:
<a href="https://github.com/Pearson-Higher-Ed/ux-test-platform#how-to-set-browser-config-properties">Follow </a>this and run your tests across all Desktop and Mobile platforms. Follow this to run tests on 
<a href= "https://github.com/Pearson-Higher-Ed/ux-test-platform/blob/rebrand/README.md#how-to-run-your-tests-in-local">local</a> or to run tests on 
<a href="https://github.com/Pearson-Higher-Ed/ux-test-platform/blob/rebrand/README.md#how-to-run-your-tests-in-sauce-via-travis-ci">sauce</a>.

## How to view the test results:
<a href="https://github.com/Pearson-Higher-Ed/ux-test-platform/blob/rebrand/README.md#how-to-view-the-test-results">Refer this.</a>

## Create a PR:
Once all the tests are written and ran across X platforms, create a PR. Follow the PR steps in github. 

## PR Reviews:
Your PR will be reviewed primarily by PDA QAs. PDA QAs will provide feedback and once all the review comments are fixed, they would merge it.
