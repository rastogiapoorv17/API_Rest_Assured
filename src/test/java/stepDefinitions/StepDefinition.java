package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResource;
import resources.TestBuildData;
import resources.Utils;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {
	RequestSpecification req;
	ResponseSpecification respspec;
	Response res;
	TestBuildData data = new TestBuildData();
	static String place_id;
	@Given("AddPlace Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		// Write code here that turns the phrase above into concrete actions
		req = given().spec(requestSpecification()).body(data.addPayLoad(name, language, address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions
		APIResource resAPI = APIResource.valueOf(resource); // Calling from ENum File
		respspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("Post"))
			res = req.when().post(resAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			res = req.when().get(resAPI.getResource());

	}

	@Then("the API call got success woth status code {int}")
	public void the_api_call_got_success_woth_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(res.statusCode(), 200);

	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(getJsonPath(res, keyValue), expectedValue);
	}

	@And("verify {string} created maps to {string} using {string}")
	public void verify_created_maps_to_using(String keyvalue, String expectedname, String resource) throws IOException {
		// Write code here that turns the phrase above into concrete actions
		place_id = getJsonPath(res, keyvalue);
		req = given().spec(requestSpecification()).queryParam(keyvalue, place_id);
		user_calls_with_http_request(resource, "GET");
		String name_value = getJsonPath(res, "name");
		System.out.println(name_value);
		assertEquals(name_value, expectedname);
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		req=given().spec(requestSpecification()).body(data.deletePayLoad(place_id));
	}


}
