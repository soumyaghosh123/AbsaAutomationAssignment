$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/com/wf/scenarios/E2E_DogAPI_Validation.feature");
formatter.feature({
  "name": "E2E automated scenario for validating different types of dogs in Dog API",
  "description": "As a user\nI want to validate the results are displayed when I get response for specific breed from Dog API.",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@End2End"
    },
    {
      "name": "@Chrome"
    },
    {
      "name": "@validateDogAPI"
    }
  ]
});
formatter.scenario({
  "name": "Verify valid dog breed search results from API",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@End2End"
    },
    {
      "name": "@Chrome"
    },
    {
      "name": "@validateDogAPI"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "\"DogAPI\" is launched successfully to verify all dog breeds",
  "keyword": "Given "
});
formatter.match({
  "location": "E2E_DogAPI_Validation.is_launched_successfully_to_verify_all_dog_breeds(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user verifies for specific breed \"retriever\"",
  "keyword": "When "
});
formatter.match({
  "location": "E2E_DogAPI_Validation.user_verifies_for_specific_breed(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "verify  the successfull response code with breedname as \"retriever\" along with sub-breed name",
  "keyword": "Then "
});
formatter.match({
  "location": "E2E_DogAPI_Validation.verify_the_successfull_response_code_with_breedname_as_along_with_sub_breed_name(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "verify random image for the sub-breed \"golden\"",
  "keyword": "And "
});
formatter.match({
  "location": "E2E_DogAPI_Validation.verify_random_image_for_the_sub_breed(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.uri("src/test/resources/com/wf/scenarios/E2E_Verify_UserTable.feature");
formatter.feature({
  "name": "E2E automated scenario for validating user table",
  "description": "As a user\nI want to validate that in User List Table new user with unique username is added successfully.",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@End2End"
    },
    {
      "name": "@Chrome"
    },
    {
      "name": "@validateUserTable"
    }
  ]
});
formatter.scenarioOutline({
  "name": "Verify functionality in userListTable",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "page is loaded successfully \"userListTable_xpath\" is present",
  "keyword": "When "
});
formatter.step({
  "name": "user is able to add with \"\u003cPassword\u003e\" and \"\u003cCustomer\u003e\" and \"\u003cRole\u003e\" and \"\u003cCell\u003e\" successfully with unique userid",
  "keyword": "And "
});
formatter.step({
  "name": "verify that user is added successfully in UserListTable",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "Password",
        "Customer",
        "Role",
        "Cell"
      ]
    },
    {
      "cells": [
        "Password@1",
        "Company BBB",
        "Admin",
        "9132651"
      ]
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "UserListTbale website is opened in targetbrowser and hompage is loaded successfully with loginURL",
  "keyword": "Given "
});
formatter.match({
  "location": "E2E_UserListTable_Validation.userlisttbale_website_is_opened_in_targetbrowser_and_hompage_is_loaded_successfully_with_loginURL(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify functionality in userListTable",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@End2End"
    },
    {
      "name": "@Chrome"
    },
    {
      "name": "@validateUserTable"
    }
  ]
});
formatter.step({
  "name": "page is loaded successfully \"userListTable_xpath\" is present",
  "keyword": "When "
});
formatter.match({
  "location": "E2E_UserListTable_Validation.page_is_loaded_successfully_is_present(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user is able to add with \"Password@1\" and \"Company BBB\" and \"Admin\" and \"9132651\" successfully with unique userid",
  "keyword": "And "
});
formatter.match({
  "location": "E2E_UserListTable_Validation.user_is_able_to_add_with_and_and_and_successfully_with_unique_userid(String,String,String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "verify that user is added successfully in UserListTable",
  "keyword": "Then "
});
formatter.match({
  "location": "E2E_UserListTable_Validation.verify_that_user_is_added_successfully_in_UserListTable()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});