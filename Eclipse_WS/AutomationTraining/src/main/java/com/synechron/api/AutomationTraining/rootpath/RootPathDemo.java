package com.synechron.api.AutomationTraining.rootpath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RootPathDemo {
	@Test
	public void validateKeyPresent() {
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
			body("prefs.backgroundImageScaled[0]",hasKey("width"));
	//460 980 1024
	}
	
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
	public void validateMultipleAssertionsWithRiitPath() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		given().
			param("key", GlobalVariables.KEY).
			param("token",GlobalVariables.TOKEN). 
		when().
			get("/1/boards/630c6e646174f70111e94e63").
		then().
			root("prefs.backgroundImageScaled").
			assertThat().statusCode(200).and().
			contentType(ContentType.JSON).and().
			body("[0]",hasKey("width")).
			body("[0]",hasKey("url")).
			body("width",hasItems(480,960,1024));
			
	}
}
