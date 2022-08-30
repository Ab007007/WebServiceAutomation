package com.synechron.api.AutomationTraining.delete;

import org.hamcrest.CoreMatchers;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteBoard {
	public static void main(String[] args) {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		RestAssured.
			given().
			queryParam("key", GlobalVariables.KEY).
			queryParam("token",GlobalVariables.TOKEN).
			header("Content-type", "application/json").
		when().
			delete("/1/boards/630dd6037aaa8500b5092b0c").
		then().
			assertThat().statusCode(200);
			
	}
}
