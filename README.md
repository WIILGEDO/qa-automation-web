# qa-automation-web
QA WEB Java Test Automation Framework

# Table of contents
* [Prerequisites](#prerequisites)
* [Build and run tests](#build-and-run-tests)
    * [Notes](#notes)
    * [Active profiles](#active-profiles)
    * [How to run it](#how-to-run-it)
* [Development guidelines](#development-guidelines)
    * [Guidelines summary](#guidelines-summary)
* [GitLab CI](#gitlab-ci)

## Prerequisites

End-to-End tests are implemented on Java programming language using [Maven](https://maven.apache.org/) as a build tool and [Cucumber](https://cucumber.io/) as a tool for describing test cases. Interaction with a page is performed through [Selenium WebDriver](http://www.seleniumhq.org/projects/webdriver/) and [Page Objects](https://github.com/SeleniumHQ/selenium/wiki/PageObjects)

You can run tests against production on your machine. Using [Sauce labs](https://saucelabs.com/) is a bit tricky, because only Dubai's IPS is whitelisted for it.

## Build and run tests

This section describes how to build the solution and run tests locally.

### Notes

Environment information is described in **prop_name.properties**.

Test suite to run is described in maven a specific run profile: **name_run**.

For current profiles and properties see next section

### Active profiles:

- Property profiles:
    - **local** - use local.properties
    - **sc** - use sc.properties
    - **saucelabs** - use saucelabs.properties
    - **beta** - use beta.properties [ production tests ]
- Run profiles:
    - **smoke_run** - rum smoke test suite
    - **saucelabs_run** - run saucelabs suite [ run on saucelabs cloud ]

### How to run it

Basically you need to run maven with selected profiles:

`mvn clean install -Pprofile1,profile2`

#### Examples

1. Run smoke tests on docker container environment

    ```
    mvn clean install -Plocal,smoke_run 
    ```

2. Run smoke tests on production

    ```
    mvn clean install -Plocal,smoke_run
    ```

3. Or you can select tags to run your tests

    ```
    mvn clean install  -Dcucumber.options="--tags @categories --tags ~@ignore" -Plocal,smoke_run
    ```

## Development guidelines

  The Cucumber is used to describe tests in terms of steps to be performed and desired outcomes. Steps are implemented in *Step.java files and split logically by system functionality. Each step should be self-contained, that means it initializes PageObjects it uses as well as getting needed state and initializing other dependencies.

> Example:
>
> Put steps that are related to Login functionality to LoginSteps.java file, registration steps to RegistrationSteps.java and account related steps to AccountSteps.java, etc.
    
      
  The PageObject pattern is used to hide implementation details of particular page from consumer. It means, that a page should not expose its internals to the consumer, WebElements representing page controls should not be accessible from outside of the PageObject. Instead, there should be methods to access some part of page's functionality. For instance, in LoginPage instead of giving access to the login and password fields implement method `login(String userName, String password)`
  
  Let's consider you have a login page with user name and password fields and login button.
  
> Do not do:
>
>```
> loginPage.userName().sendKeys("userName");
> loginPage.password().sendKeys("password");
> loginPage.userName().sendKeys("userNmae);
>```
  
> Instead do:
>```
> loginPage.login("userName", "password");
>```
    
   Implement navigation to another PageObject inside of current PageObject and wait till it is loaded. To access the PageObject from step use `onPage(PageObject.class)` method that will return initialized object for PageObject class. Then you can work with it in Step method.
 
   Fot example, we have a scenario user lands to account page after login
   ```
   Scenario: user is on account page after login
     Given open consumer platform page
     When login with 'example@example.com' and password 'example'
     Then verify user is on account page
   ```
    
> The login function in LoginPage will look:
> ```
> public void login(String userName, String password) {
>     this.userNameField.sendKeys(userName);
>     this.passwordField.sendKeys(password);
>     this.loginButton.click();
>     (new AccountPage(getDriver())).waitPageLoaded();
> }
> ```

> The last step will look:
> ```
> @And("^verify user is on account page")
> public void verifyUserIsOnAccountPage() {
>     AccountPage page = onPage(AccountPage.class);
>     assert.assertIsTrue("Account page is not visible", page.isVisible());
> }
> ```
 
 ### Guidelines summary
 * Steps are self-contained, use `onPage` method to initialize PageObject
 * PageObject should hide implementation details for the page, no controls exposed to consumers
 * Do not introduce extra constants for css/xpath if it is not needed, place string inline:
   
   ```
   @FindBy(css = "input[name='login']")
   private WebElement userName;
   ```
 * Do not use extra waits where it is not needed. If there is no control it should fail not wait. Most probably wait is only needed for page loading
 * Verify steps should contain asserts. See example above

## GitLab CI

There is a CI setup for this project in [GitLab](https://about.gitlab.com/features/gitlab-ci-cd/). It consists of the following steps:
1. Update apt-get package sources
2. Install display server to be able to render applicaiton
3. Install Firefox browser
4. Clone qa-testservices source code into `/qa-testservices` folder
5. Build and install qa-testservices with `-Psmoke_run` profile
6. Build and run test project with `-Psmoke_run` profile