package com.synechron.api.AutomationTraining.post;

import java.util.Random;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;

public class CreateRepositoryAsPayloadString {

	
	@Test
	public void createRepo() {
		
		
		RestAssured.baseURI = GlobalVariables.git_baseURI;
		
		String projectName = "API_Demo_Project_" + (new Random().nextInt(9000));
		String payload = "{\"name\":\"CreatedFromAPI-Postman1\",\"descriptionstring\":\"CreatedFromAPI-Desc\",\"homepage\":\"CreatedFromAPI-HomePage\"}";
		RestAssured. 
			given().
				headers("Authorization" , GlobalVariables.barrierToken).
				headers("Content-Type" ,"application/json").
				body(payload).
			when(). 
				post("/user/repos").
			then().
				assertThat().statusCode(201).and().
				body("name", CoreMatchers.equalTo(projectName));
		
		System.out.println("Done");
				
	}
}
