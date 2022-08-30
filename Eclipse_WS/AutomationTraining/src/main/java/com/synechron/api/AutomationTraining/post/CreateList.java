package com.synechron.api.AutomationTraining.post;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateList {
	public static void main(String[] args) {
			RestAssured.baseURI = GlobalVariables.Trello_baseURI;
			Response result = RestAssured.
			given().
				queryParam("idBoard", "630ddf26f2da3616a99d4fd1").
				queryParam("key", GlobalVariables.KEY).
				queryParam("token", GlobalVariables.TOKEN).
				queryParam("name", "restListNew").
				header("content-type", "application/json").
			when().
				post("/1/lists");
			result.prettyPeek();
			System.out.println("done");
		}
	
}

