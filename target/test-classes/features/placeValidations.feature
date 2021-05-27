Feature: Validating Place API's'
@AddPlace
Scenario Outline: Validate if Place is being successfully added using ADDPLACE API
Given AddPlace Payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "POST" http request
Then the API call got success woth status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify "place_id" created maps to "<name>" using "GetPlaceAPI"

Examples: 
|name     |language    |address           |
|AAHouse  |English     |World Cross Center|
#|BbHouse  |Spanish     |SeaCross Centre   |

@DeletePlace
Scenario: Validate if Delete Place functionality
Given DeletePlace Payload 
When User calls "DeletePlaceAPI" with "POST" http request
Then the API call got success woth status code 200
And "status" in response body is "OK"