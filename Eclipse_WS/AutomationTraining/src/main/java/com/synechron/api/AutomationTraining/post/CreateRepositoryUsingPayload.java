package com.synechron.api.AutomationTraining.post;

import java.util.Random;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;

public class CreateRepositoryUsingPayload {

	
	@Test
	public void createRepo() {
		
		
		RestAssured.baseURI = GlobalVariables.git_baseURI;
		
		String projectName = "API_Demo_Project_" + (new Random().nextInt(9000));
		GItPOJO gp = new GItPOJO();
		gp.setName(projectName);
		gp.setDesc(projectName + "_Description"); 
		gp.setHomePage(projectName + " Home");
		
		
		RestAssured. 
			given().
				headers("Authorization" , GlobalVariables.barrierToken).
				headers("Content-Type" ,"application/json").
				body(gp).
			when(). 
				post("/user/repos").
			then().
				assertThat().statusCode(201).and().
				body("name", CoreMatchers.equalTo(projectName));
		
		System.out.println("Done");
				
	}
}
