package com.synechron.api.AutomationTraining.response.jsonpath;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class JsonPathUsingValidateResponse {

	
	ValidatableResponse response = null;
	
	@BeforeTest
	public void getResponse() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		 response = given()
			.param("key", GlobalVariables.KEY)
			.param("token", GlobalVariables.TOKEN).
		when()
			.get("/1/boards/630c6e646174f70111e94e63"). 
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).log().all();
			
		
		
	}
	
	@Test
	public void getID() {
		String id = response.extract().path("id");
		System.out.println("ID : " + id);
	}
	
	@Test
	public void getFirstWidth() {
		int width = response.extract().path("prefs.backgroundImageScaled[0].width");
		System.out.println("First Width : " + width);
	}
	
	@Test
	public void printAllWidth() {
		int size = response.extract().path("prefs.backgroundImageScaled.size()");
		
		for (int i = 0; i < size; i++) {
			int width = response.extract().path("prefs.backgroundImageScaled["+i+"].width");
			System.out.println("Width at index " + i + "is : " + width);
			
		}
	}
	
	@Test
	public void printAllUsingGroovyFunction() {
		System.out.println("------------All width ----------------");
		Object width = response.extract().path("prefs.backgroundImageScaled.findAll { it.width > 1 }");
		System.out.println(width);
	
	}
	
	@Test
	public void printAllUrlGroovyFunction() {
		System.out.println("------------All URL ----------------");
		List<String> url = response.extract().path("prefs.backgroundImageScaled.findAll { it.width > 1 }.url");
		System.out.println("Total urls " + url.size());
		url.forEach(u -> System.out.println(u));
	
	}
	
	
	@Test
	public void printMaxWidth() {
		System.out.println("------------Max Width ----------------");
		Map<String, ?> width = response.extract().path("prefs.backgroundImageScaled.max { it.width }");
		
		width.forEach((key,value) -> System.out.println("Key : " + key + " and value : " + value));
	
	}
	
	@Test
	public void printURLofMaxWidth() {
		System.out.println("------------Max Width ----------------");
		String url = response.extract().path("prefs.backgroundImageScaled.max { it.width }.url");

		System.out.println("URL corresponds to max width " + url);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
