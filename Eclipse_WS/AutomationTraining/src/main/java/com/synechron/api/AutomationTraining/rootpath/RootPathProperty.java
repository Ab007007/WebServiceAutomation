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

public class RootPathProperty {
	
	@Test
	public void validateMultipleAssertionsWithRiitPath() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		RestAssured.rootPath = "prefs.backgroundImageScaled";
		
		given().
			param("key", GlobalVariables.KEY).
			param("token",GlobalVariables.TOKEN). 
		when().
			get("/1/boards/630c6e646174f70111e94e63").
		then().
			assertThat().statusCode(200).and().
			contentType(ContentType.JSON).and().
			body("[0]",hasKey("width")).
			body("[0]",hasKey("url")).
			body("width",hasItems(480,960,1024));
		
		RestAssured.reset();
			
	}
}
