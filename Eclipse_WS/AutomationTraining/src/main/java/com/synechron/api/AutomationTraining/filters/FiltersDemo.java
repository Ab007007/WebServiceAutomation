package com.synechron.api.AutomationTraining.filters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;

import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FiltersDemo {
	public static StringWriter requestWriter;
	public static PrintStream requestCapture;
	public static StringWriter resWriter;
	public static PrintStream resCapture;
	
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
		
		requestWriter = new StringWriter();
		requestCapture = new PrintStream(new WriterOutputStream(requestWriter),true);
		
		resWriter = new StringWriter();
		resCapture = new PrintStream(new WriterOutputStream(resWriter),true);
		
		
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
			filter(new RequestLoggingFilter(requestCapture)).
			filter(new ResponseLoggingFilter(resCapture)).
			when().
			get("/1/boards/630c6e646174f70111e94e63").
		then().
			spec(resSpec).
			body("[0]",hasKey("width")).
			body("[0]",hasKey("url")).
			body("width",hasItems(480,960,1024));
		
		System.err.println(requestWriter.toString());
		System.err.println(resWriter.toString());
				
	}
	
	
	
}
