package com.synechron.api.AutomationTraining.response.jsonpath;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathDemo {

	
	Response response = null;
	
	@BeforeTest
	public void getResponse() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		response = given()
			.param("key", GlobalVariables.KEY)
			.param("token", GlobalVariables.TOKEN).
		when()
			.get("/1/boards/630c6e646174f70111e94e63");
		
		
	}
	
	@Test
	public void getID() {
		JsonPath responseBody = new JsonPath(response.asString());
		System.out.println("ID : " + responseBody.get("id"));
	}
	
	@Test
	public void getWidthofFirstElement() {
		JsonPath responseBody = new JsonPath(response.asString());
		System.out.println("FirstWidth : " + responseBody.get("obj.prefs.backgroundImageScaled[0].width"));
	}
	
	@Test
	public void getAllWidth() {
		JsonPath responseBody = new JsonPath(response.asString());
		List<Map<String,?>> ele = responseBody.get("obj.prefs.backgroundImageScaled.size()");
		
		System.out.println("Total Element in Collection : " + ele.size());
		//System.out.println("FirstWidth : " + responseBody.get("obj.prefs.backgroundImageScaled[0].width"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
