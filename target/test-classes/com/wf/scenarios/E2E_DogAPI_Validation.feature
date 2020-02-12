@End2End @Chrome @validateDogAPI

Feature: E2E automated scenario for validating different types of dogs in Dog API
As a user
I want to validate the results are displayed when I get response for specific breed from Dog API.



Scenario: Verify valid dog breed search results from API
Given "DogAPI" is launched successfully to verify all dog breeds
When  user verifies for specific breed "retriever"
Then  verify  the successfull response code with breedname as "retriever" along with sub-breed name
And   verify random image for the sub-breed "golden"















	

