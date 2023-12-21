# Test Automation Suite for SW API

## The project directory structure
The project uses Maven and follows the follosing directory structure :
```Gherkin
src
  + main
    +java                 various utility classes
      +starter
        +Utils            utility classes package
          PropertiesLoader.class   - loads the config.properties file
    +resources
      config.properties - the file which contains various parameters which can be customized at will (log verbosity,etc)
  ________________________________________________________________
  + test
    + java                        Test runners and test implementations
      + starter
        + api                       API wrapper package
          ApiLayer.class            
        + models                    Models package
          Film.class
          Films.class
          Person.class
        + testimplementations                    test implementations package
          testimplementations.class              
        + teestsuite                            test suite package
          SWAPITest.class                       test suite file
    + resources
      + testsuites                              TestNG testsuites package
        testsuite.xml                           TestNG testuite file
      logback.xml - the file which can be customized for various options related to contextual logging
  gitlab-ci.yml - the file for CI/CD integration with GitLab
  README.md - the documentation file (you are reading it now)
  pom.xml - dependency holder - used by Maven
  .gitignore - file which contains various folder and file formats which will be ignored for commits
  Javadoc - the folder holds various information about the Java methods in the project
  target - the folder is created after each build and test run. It contains the testing logs. 
```
## Detailed description about the directory structure and functionalities

### PropertiesLoader.class
Performs the loading of properties file. In this file will be found parameters related to the tests behaviour which can be customized.

### config.properties
In this file will be found parameters related to the tests behaviour which can be customized. 
These parameters can be used for debugging purposes, enabling verbosity for essential functionalities of the automation test project. 
```
logResponsesAsString=false
requestLogging=false
```
To enable the response logging:
```
logResponsesAsString=true
```
(false value is default). 

To enable the logging during and after an API request is performed:
```
requestLogging=true
```
To disable the logging during and after an API request, keep this parameter with false value (default).
Many other useful parameters can be added to this **config.properties** file in order to improve and enrich the basic test automation framework behaviour.

**Other details** can be noticed by reading **the project directory structure** section (above) and the JavaDoc which holds information about the classes and the project structure. 
The **Javadoc** documentation can be found in the corresponding folder named accordingly.  

## Test logs and console logging
This test automation project generates various types of logs during and after each test suite run. 
Surefire reports can be found in the target/surefire-reports folder which is generated after each test suite run. 
```
target
  + surefire-reports  - the folder containing the html format reports
    index.html - access this file for displaying the html format reports which will open in a chosen browser
```
Additionally, the test automation suite will generate a console log during and after the test execution. 

## Test scenarios
These can be found in the following folder:
```
src
  +test
    + resources
        + testsuite
```
The testsuite files contain the **test scenarios** for the current project setup. 
More other scenarios can be also added **here** for future development of this test automation framework.
Each scenario can be run individually from IDE by pressing the corresponding arrow which can be found near each scenario description. 
Multiple scenarios can be run by starting the tests from IDE by right click on each testsuite file or by right-clicking the testsuite.xml file.

## Important notice:
The IDE test run will not generate the html reports. Only console reports will be generated in a IDE test run. 
For a complete reporting please run the entire suite using command line in the terminal (in the root project folder) as follows:
```
> mvn clean verify
```
This will also clean the existing build and will generate the complete reports in target folder.

## Assignment requirements

**(mandatory):**

![img.png](img.png)1.Find the film with latest release date.

![img.png](img.png)2.Using previous response (1) find the tallest person among the characters that were part of that film.

![img.png](img.png)3.Find the tallest person ever played in any Star Wars film.

![img.png](img.png)4.Create contract (Json schema validation) test for /people API.

## GitLab URLs

Project main url:
* **[XM-SWAPI-Java-Restassured](https://gitlab.com/excalidor/xm-swapi-java-restassured)**

Pipelines in GitLab CI/CD:
* **[XM-SWAPI-Java-Restassured pipelines](https://gitlab.com/excalidor/xm-swapi-java-restassured/-/pipelines)**

## How to run this project
Prerequisites (for **Windows machines**):
(For **Linux machines**, please install Java and Maven. Installation will follow the specific way for Linux environments).

* Java (jdk version more or equal with 1.8)
* Maven (latest version works fine)
* (optional) Git for Windows (latest version, enabling access to various Git commands, if contributing to this project is intended)

- Java can be downloaded from here: **https://www.java.com/download/ie_manual.jsp**
- Newer versions can be found here: **https://www.oracle.com/java/technologies/downloads/**
- After Java installation is complete, the PATH variable should contain the location for the current Java installation bin folder. 
- In order to check the Java installation, the following command can be used (open a command prompt window and type):
```
> java -version
```
- A message displaying the current Java version should be displayed. If not, please refer to a Java instalation guide. 
- Maven should be installed on local machine. Please download it and install from * **[Apache Maven website](https://maven.apache.org/download.cgi)**
- After downloading Maven, open the archive and paste it in a folder on the local machine.
- Open the Maven folder and locate the bin folder. Add this folder to the Path environment variable. Additional information related to Maven installation process can be found on the Maven website. 
- Restart the computer/logout and login into the current user account in order to make the Path variable updates operational. 
- Test the Mavem installation by opening a console (cmd.exe) and typing:
```
> mvn -version
```
- The Maven installation is completed if a message containing information related to Maven version is displayed. 
- If such message is not displayed, then most probably the Maven bin folder was not added to the PATH variable correctly or the computer was not restarted to make the changes operational. 
- Clone the repository from GitLab in a local folder:
```
> git clone https://gitlab.com/excalidor/xm-swapi-java-restassured.git 
```
- After successfully cloning the repository, the project can be run from command line prompt opened in the root folder of the project.


**Running the tests:**

- To run the tests, a console in the project root folder must be opened. It can be opened via cmd.exe (System terminal) or from IDE terminal. 
- In the opened console, please type:
```
> mvn clean verify
```
- After the test session is ended, the logs can be found in their appropriate folders, as described above in the **Test logs** section. 


