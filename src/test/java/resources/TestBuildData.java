package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestBuildData {

	public AddPlace addPayLoad(String name, String language, String address) {
		AddPlace add = new AddPlace();
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		add.setLocation(loc);
		add.setAccuracy(50);
		add.setName(name);
		add.setPhone_number("(+91) 983 893 3937");
		add.setAddress(address);
		add.setLanguage(language);
		add.setWebsite("http://google.com");
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		add.setTypes(list);
		return add;
	}
	public String deletePayLoad(String place_id) {
		return "{\r\n" + 
				"\"place_id\": \""+place_id+"\"\r\n" + 
				"}";
	}

}
