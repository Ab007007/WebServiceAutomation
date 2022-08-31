package com.synechron.api.AutomationTraining.logs;

import java.util.Random;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;

public class GetRequestWithLogs {

	
	//@Test
	public void printAllHeaders() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		RestAssured.
			given().log().headers().
				param("key", GlobalVariables.KEY).
				param("token",GlobalVariables.TOKEN).
			when().
				get("/1/boards/630c6e646174f70111e94e63").
			then().
				assertThat().statusCode(200).
				body("name", CoreMatchers.equalTo("MyAPI-290822"));
				
	
	}
	
	//@Test
	public void printBody() {
		RestAssured.baseURI = GlobalVariables.git_baseURI;
		String projectName = "API_Demo_Project_" + (new Random().nextInt(9000));
		
		RestAssured. 
			given().log().headers().log().body().
				headers("Authorization" , GlobalVariables.barrierToken).
				headers("Content-Type" ,"application/json").
				body("{\r\n" + 
						"    \"name\" : \""+projectName+"\",\r\n" + 
						"    \"descriptionstring\" : \"CreatedFromAPI-Desc\",\r\n" + 
						"    \"homepage\" : \"CreatedFromAPI-HomePage\"\r\n" + 
						"}").
			when(). 
				post("/user/repos").
			then().
				assertThat().statusCode(201).and().
				body("name", CoreMatchers.equalTo(projectName));
				
	
	}
	
	
	@Test
	public void printParameters() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		RestAssured.
			given().log().parameters().
				param("key", GlobalVariables.KEY).
				param("token",GlobalVariables.TOKEN).
			when().
				get("/1/boards/630c6e646174f70111e94e63").
			then().
				assertThat().statusCode(200).
				body("name", CoreMatchers.equalTo("MyAPI-290822"));
				
	
	}
	
	//@Test
	public void printAllReqInfo() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		RestAssured.
			given().log().all().
				param("key", GlobalVariables.KEY).
				param("token",GlobalVariables.TOKEN).
			when().
				get("/1/boards/630c6e646174f70111e94e63").
			then().
				assertThat().statusCode(200).
				body("name", CoreMatchers.equalTo("MyAPI-290822"));
				
	
	}
}
