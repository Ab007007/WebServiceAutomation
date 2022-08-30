package com.synechron.api.AutomationTraining.get;

import org.hamcrest.CoreMatchers;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetUsingRequestSpec {

	
	
	public static void main(String[] args) {
		
		RequestSpecification rSpec = RestAssured.given();
		rSpec.param("key", GlobalVariables.KEY);
		rSpec.param("token",GlobalVariables.TOKEN);
		
		Response response = rSpec.get("https://api.trello.com/1/boards/630c6e646174f70111e94e63");
		
		response.prettyPrint();
		
		ValidatableResponse validateRes = response.then();
		
		validateRes.statusCode(200);
		validateRes.body("name", CoreMatchers.equalTo("MyAPI-290822"));		
	}
}
