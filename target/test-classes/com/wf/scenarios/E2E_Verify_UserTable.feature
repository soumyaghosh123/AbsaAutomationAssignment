@End2End @Chrome @validateUserTable
Feature: E2E automated scenario for validating user table
As a user
I want to validate that in User List Table new user with unique username is added successfully.

Background:
Given UserListTbale website is opened in targetbrowser and hompage is loaded successfully with loginURL
   

Scenario Outline: Verify functionality in userListTable
When page is loaded successfully "userListTable_xpath" is present
And user is able to add with "<Password>" and "<Customer>" and "<Role>" and "<Cell>" successfully with unique userid
Then verify that user is added successfully in UserListTable

Examples:
| Password      |  Customer        | Role  | Cell    |
| Password@1    |  Company BBB     | Admin | 9132651 |











	

