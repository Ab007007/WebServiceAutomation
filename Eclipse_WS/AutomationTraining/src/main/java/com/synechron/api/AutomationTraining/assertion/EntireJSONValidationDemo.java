package com.synechron.api.AutomationTraining.assertion;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class EntireJSONValidationDemo {

	
	@Test
	public void verifyCompleteResponse() throws IOException, JSONException {
		
		String expectedOutput = new String(Files.readAllBytes(Paths.get("jsonoutput/board.txt")));
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		Response response = given().
			param("key", GlobalVariables.KEY).
			param("token",GlobalVariables.TOKEN). 
		when().
			get("/1/boards/630c6e646174f70111e94e63");
		String actualOutput = response.asString();
		
		JSONAssert.assertEquals(expectedOutput, actualOutput, JSONCompareMode.LENIENT);
		
		
		
	}
	
	@Test
	public void verifyCompleteResponseWithLinent() throws IOException, JSONException {
		
		String expectedOutput = new String(Files.readAllBytes(Paths.get("jsonoutput/board2.txt")));
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		Response response = given().
			param("key", GlobalVariables.KEY).
			param("token",GlobalVariables.TOKEN). 
		when().
			get("/1/boards/630c6e646174f70111e94e63");
		String actualOutput = response.asString();
		
		JSONAssert.assertEquals(expectedOutput, actualOutput, true);
		
		
		
	}
}
