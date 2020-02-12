# AbsaAutomationAssignment
This repo contains 2 Test-cases related to ABSA assignment.
Project Structure:This project is created as Maven Project using Cucumber BDD feature.
For input data purpose I have used as feature file stored in "src/test/resources/com/wf/scenarios."
Object properties and URL and API details are stored in project.properties file.
Also report location is stored in project.properties (path--src/test/resources) file under variable--reportPath.Please add path as per your system.Just change the drive name as per system driver.Folder will be created automatically.
Currently it is running in chrome browser but we have a province to run it on Mozilla and IE also.We can change targetbrowser under project.properties.Hence in future we can enhance it on Firefox and IE.
Reporting purpose ExtentReport is used and junit is used for testcase running.
For running bulk testcases proceed src/test/java under package no--"com.wf.Runner" right click on Runner.java and run as junit Test.
API based testcases results will be shown in console as well as in report for pass and fail.
For UI automation pass and fail report with screenshot will be present.
For Sample purpose one report folder attaching inside project.
