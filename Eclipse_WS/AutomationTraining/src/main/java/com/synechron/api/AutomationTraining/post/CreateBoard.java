package com.synechron.api.AutomationTraining.post;

import org.hamcrest.CoreMatchers;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateBoard {

	
	public static void main(String[] args) {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		Response response = RestAssured.
			given().
			queryParam("key", GlobalVariables.KEY).
			queryParam("token",GlobalVariables.TOKEN).
			queryParam("name" , "BoardFromEclipse").
			header("Content-type", "application/json").
		when().
			post("/1/boards/");
		
		response.prettyPrint();
//		
//		then().
//			assertThat().statusCode(200).and()
//			.contentType(ContentType.JSON).and()
//			.body("name", CoreMatchers.equalTo("BoardFromEclipse"));
	}
}
