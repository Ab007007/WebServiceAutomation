package com.synechron.api.AutomationTraining.post;

import java.util.Random;

import org.hamcrest.CoreMatchers;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;

public class CreateRepository {

	
	public static void main(String[] args) {
		
		
		RestAssured.baseURI = GlobalVariables.git_baseURI;
		
		String projectName = "API_Demo_Project_" + (new Random().nextInt(9000));
		
		RestAssured. 
			given().
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
		
		System.out.println("Done");
				
	}
}
