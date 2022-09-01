package com.synechron.api.AutomationTraining.time;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ResponseTime {

	@Test
	public void validateMultipleAssertionsWithRiitPath() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		long time = given().
						param("key", GlobalVariables.KEY).
						param("token",GlobalVariables.TOKEN). 
					when().
						get("/1/boards/630c6e646174f70111e94e63").
						time();
		System.out.println("Total time taken " + time);
			
	}
	
	
	@Test
	public void timeInSec() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		long time = given().
						param("key", GlobalVariables.KEY).
						param("token",GlobalVariables.TOKEN). 
					when().
						get("/1/boards/630c6e646174f70111e94e63").
						timeIn(TimeUnit.SECONDS);
		System.out.println("Total time taken in  Sec " + time);
			
	}
	
	@Test
	public void timeValidation() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		given().
			param("key", GlobalVariables.KEY).
			param("token",GlobalVariables.TOKEN). 
		when().
			get("/1/boards/630c6e646174f70111e94e63").
		then().
			time(Matchers.lessThan(2L),TimeUnit.SECONDS);

			
	}
}
