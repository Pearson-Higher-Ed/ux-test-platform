## Run mobile tests on sauce via local
__Pre-requisite__:</br> You should have a valid sauce account. If you don't have one, check with your manager and get one for your team:)

1. Go to {project.base.dir}/ux-test-platform/src/main/resources/SauceParam.properties
<pre>
    SauceUser=sauce_user_name
    SauceKey=sauce_key
    Tunnel=test_tunnel
</pre>

2. Download sauce connect plugin on your machine from https://wiki.saucelabs.com/display/DOCS/Sauce+Connect+Proxy

3. Move the downloaded jar to any directory, extract the zip file.
    * For windows, the directory name is something like: sc-4.4.9-win32
    * For Mac, the direcoty name is something like this: sc-4.4.6-osx

4. Run the below commands to start the sauce tunnel
    <details><summary>MAC: </summary>
    Go to {workspace}/sc-4.4.6-osx and run the below command
    <pre>  bin/sc -u sauce_user_name -k sauce_key -i test_tunnel </pre>
    </details>

    <details><summary>Windows: </summary>
    Go to {workspace}/sc-4.4.9-win32 and run the below command
    <pre>  bin/sc -u sauce_user_name -k sauce_key -i test_tunnel </pre>
    </details>
    Wait for start your tests command

5. Go to saucelabs.com and verify if the sauce tunnel "test_tunnel" is started under the 'Tunnels' section.

5. Now go to test_suites/<components.xml> and set the groups to mobile-regression.

6. Start the tests now. You would see the mobile tests starts running on Sauce.

7. All the logs are seen on your editor or terminal, depending on where you triggered your tests.