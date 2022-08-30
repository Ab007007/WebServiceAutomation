package com.synechron.api.AutomationTraining.get;

import org.hamcrest.CoreMatchers;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;

public class GetAutomationDemo {

	
	public static void main(String[] args) {
		//
		
		RestAssured.
			given().
				param("key", GlobalVariables.KEY).
				param("token",GlobalVariables.TOKEN).
			when().
				get("https://api.trello.com/1/boards/630c6e646174f70111e94e63")
			.then()
				.assertThat().statusCode(200)
				.body("name", CoreMatchers.equalTo("MyAPI-290822"))
				.body("idOrganization", CoreMatchers.equalTo("6092c51ec5be6965c23bd18c"));
			
	System.out.println("API Executed Successfully");
	}
	//Validate GET List AND Get CARD
	//VAlidate httpbin GET Request
	
}
