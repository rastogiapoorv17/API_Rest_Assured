package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

@Before("@DeletePlace")
public void beforeScenario() throws IOException {
	//execute this code only when place_id is null
	//write a code to give place_id
	
	StepDefinition m= new StepDefinition();
	if(StepDefinition.place_id==null)
	{
	m.add_place_payload_with("Apoorv", "English", "12 Mg Street");
	m.user_calls_with_http_request("AddPlaceAPI", "POST");
	m.verify_created_maps_to_using("place_id", "Apoorv", "GetPlaceAPI");

	}
}
}
