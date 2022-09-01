package com.synechron.api.AutomationTraining.spec;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestSpecDemo {
	RequestSpecification rspec ;
	RequestSpecBuilder rBuilder ;
	
	ResponseSpecification resSpec;
	ResponseSpecBuilder resBuilder ; 
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		RestAssured.rootPath = "prefs.backgroundImageScaled";
 
		rBuilder = new RequestSpecBuilder();
		rBuilder.addParam("key", GlobalVariables.KEY);
		rBuilder.addParam("token", GlobalVariables.TOKEN);
		rBuilder.addHeader("Content-type", "application/json");
		
		rspec = rBuilder.build();
	
		resBuilder = new ResponseSpecBuilder();
		resBuilder.expectStatusCode(200);
		resBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
		resBuilder.expectHeader("Server", "globaledge-envoy");
		
		resSpec = resBuilder.build();
		
		
	}
	
	@Test
	public void testWithoutSpec() {
		
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
	@Test
	public void reqSpecDemo() {
			
		given().
			spec(rspec).
		when().
			get("/1/boards/630c6e646174f70111e94e63").
		then().
			spec(resSpec).
			body("[0]",hasKey("width")).
			body("[0]",hasKey("url")).
			body("width",hasItems(480,960,1024));
				
	}
	
	
	
}
