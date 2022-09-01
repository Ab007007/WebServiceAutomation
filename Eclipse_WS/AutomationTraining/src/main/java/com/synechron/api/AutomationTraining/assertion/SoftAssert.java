package com.synechron.api.AutomationTraining.assertion;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class SoftAssert {

	
	@Test
	public void validateMultipleAssertions() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		given().
			param("key", GlobalVariables.KEY).
			param("token",GlobalVariables.TOKEN). 
		when().
			get("/1/boards/630c6e646174f70111e94e63").
		then().
			assertThat().statusCode(200).and().
			contentType(ContentType.JSON).and().
			body("id",equalTo("630c6e646174f70111e94e63")).and().
			body("prefs.backgroundImageScaled[0]",hasKey("width")).
			body("prefs.backgroundImageScaled[0]",hasKey("url")).
			body("prefs.backgroundImageScaled.size()",equalTo(10)).
			body("prefs.backgroundImageScaled.size()",lessThan(11)).
			body("prefs.backgroundImageScaled.size()",greaterThan(5));
			
	}
	
	@Test
	public void softAssertions() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		given().
			param("key", GlobalVariables.KEY).
			param("token",GlobalVariables.TOKEN). 
		when().
			get("/1/boards/630c6e646174f70111e94e63").
		then().
			assertThat().statusCode(200).and().
			contentType(ContentType.JSON).and().
			body("prefs.backgroundImageScaled[0]",hasKey("width"), 
					"prefs.backgroundImageScaled[0]",hasKey("url"),
					"prefs.backgroundImageScaled.size()",equalTo(20),
					"prefs.backgroundImageScaled.size()",lessThan(1),
					"prefs.backgroundImageScaled.size()",greaterThan(5));
	}
	
	
}
