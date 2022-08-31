package com.synechron.api.AutomationTraining.staticimports;

import java.util.Random;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;
import com.synechron.api.AutomationTraining.post.GItPOJO;

import io.restassured.RestAssured;

import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.given;

public class GITRepositoryTests {
	String projectName = null;
	@BeforeClass
	public void init() {
		RestAssured.baseURI = GlobalVariables.git_baseURI;
		projectName = "API_Demo_Project_" + (new Random().nextInt(9000));
	}

	@Test (priority = 1)
	public void createRepository() {
		System.out.println("--Create Repository Started ---");

		GItPOJO gp = new GItPOJO();
		gp.setName(projectName);
		gp.setDesc(projectName + "_Description"); 
		gp.setHomePage(projectName + " Home");

		given()
			.headers("Authorization", GlobalVariables.barrierToken)
			.headers("Content-Type", "application/json")
			.body(gp)
		.when()
			.post("/user/repos")
		.then()
			.assertThat()
				.statusCode(201).and()
				.body("name", equalTo(projectName));

		System.out.println("--Create Repository Ended ---");

	}

//	@Test(priority = 2)
	public void getAllRepository() {
		given().
			headers("Authorization", GlobalVariables.barrierToken).
			headers("Content-Type", "application/json").
		when().
			get("/user/repos").
				then().log().all();
	}

	@Test(priority = 3)
	public void getARepository() {
		System.out.println("--getARepository Repository Started ---");

		given().
			headers("Authorization", GlobalVariables.barrierToken).
			headers("Content-Type", "application/json").
		when().
			get("/repos/Ab007007/" + projectName).
		then().
			log().all();
		System.out.println("--getARepository Repository Ended ---");

	}

	@Test(priority = 4)
	public void deleteRepository() {
		System.out.println("--deleteRepository Repository Started ---");
		given().
			headers("Authorization", GlobalVariables.barrierToken).
			headers("Content-Type", "application/json").
		when().
			delete("/repos/Ab007007/" + projectName).
		then().
			log().all().
		assertThat().
			statusCode(204);
		System.out.println("Deleted " + projectName + " Repo Successfully");
	}
}
