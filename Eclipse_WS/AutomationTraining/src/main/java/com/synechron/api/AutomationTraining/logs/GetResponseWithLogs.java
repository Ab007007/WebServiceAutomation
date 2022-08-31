package com.synechron.api.AutomationTraining.logs;

import java.util.Random;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;

public class GetResponseWithLogs {

	
	@Test
	public void printAllResponseHeaders() {
		System.out.println("--------------LOG STATUS ON printAllResponseHeaders ------------------------");
		
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		RestAssured.
			given().
				param("key", GlobalVariables.KEY).
				param("token",GlobalVariables.TOKEN).
			when().
				get("/1/boards/630c6e646174f70111e94e63").
			then().log().headers().
				assertThat().statusCode(200).
				body("name", CoreMatchers.equalTo("MyAPI-290822"));
				
	
	}
	
	@Test
	public void printResponseBody() {	
		System.out.println("--------------LOG STATUS ON printResponseBody ------------------------");
	
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		RestAssured.
			given().
				param("key", GlobalVariables.KEY).
				param("token",GlobalVariables.TOKEN).
			when().
				get("/1/boards/630c6e646174f70111e94e63").
			then().log().body().
				assertThat().statusCode(200).
				body("name", CoreMatchers.equalTo("MyAPI-290822"));
				
	
	}
	
	
	
	@Test
	public void printStatus() {
		System.out.println("--------------LOG STATUS------------------------");
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		RestAssured.
			given().log().parameters().
				param("key", GlobalVariables.KEY).
				param("token",GlobalVariables.TOKEN).
			when().
				get("/1/boards/630c6e646174f70111e94e63").
			then().log().status().
				assertThat().statusCode(200).
				body("name", CoreMatchers.equalTo("MyAPI-290822"));
				
	
	}
	
	@Test
	public void logOnFailures() {
		System.out.println("--------------LOG STATUS ON FAILURE ------------------------");
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		RestAssured.
			given().log().parameters().
				param("key", GlobalVariables.KEY + 13123).
				param("token",GlobalVariables.TOKEN).
			when().
				get("/1/boards/630c6e646174f70111e94e63").
			then().log().ifStatusCodeIsEqualTo(401).
				assertThat().statusCode(200).
				body("name", CoreMatchers.equalTo("MyAPI-290822"));
				
	
	}
}
