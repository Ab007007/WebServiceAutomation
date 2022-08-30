package com.synechron.api.AutomationTraining.get;

import org.hamcrest.CoreMatchers;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;

public class GivenExtract {

	
	public static void main(String[] args) {
		RestAssured.baseURI = "https://api.trello.com";
		
		RestAssured.
		given().
			param("key", GlobalVariables.KEY).
			param("token",GlobalVariables.TOKEN).
		expect().
			statusCode(200).
			and().
			body("name", CoreMatchers.equalTo("MyAPI-290822")).
		when().get("/1/boards/630c6e646174f70111e94e63");
	}
}
