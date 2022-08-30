package com.synechron.api.AutomationTraining.post;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateCard {

	
	public static void main(String[] args) {
			RestAssured.baseURI=GlobalVariables.Trello_baseURI;
		Response result = RestAssured.given()
			.queryParam("key", GlobalVariables.KEY)
			.queryParam("token", GlobalVariables.TOKEN)
			.queryParam("idList", "630de0564f6aba028552f3a5")
			.queryParam("name", "restCard")
			.contentType(ContentType.JSON)
			.when()
			.post("/1/cards");
		//	.then().assertThat().statusCode(200);
		
		result.prettyPrint();
			System.out.println("created");
	

	}
}
