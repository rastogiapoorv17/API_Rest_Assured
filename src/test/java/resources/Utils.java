package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification reqspec;    //By Making this variable static, we have declared it globally means on second execution its value will not be null

	public RequestSpecification requestSpecification() throws IOException {
		
		if(reqspec==null)       //using if condition, logging.txt file will not overwrite and will not be created again so that all tc's will be logged
		{
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqspec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseurl")).setRelaxedHTTPSValidation()
				.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
		return reqspec;
		}
		return reqspec;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.propeties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String keyvalue)
	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(keyvalue).toString();
		
	}

}
