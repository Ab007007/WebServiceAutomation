package com.synechron.api.AutomationTraining.response.jaway;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.synechron.api.AutomationTraining.global.GlobalVariables;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JayWayDemo {

	Response response = null;
	
	@BeforeTest
	public void getResponse() {
		RestAssured.baseURI = GlobalVariables.Trello_baseURI;
		
		response = given()
			.param("key", GlobalVariables.KEY)
			.param("token", GlobalVariables.TOKEN).
		when()
			.get("/1/boards/630c6e646174f70111e94e63");
		
		
	}
	
	@Test
	public void vailadateID() {
		String id = JsonPath.read(response.asString(), "$.id");
			System.out.println("ID : " + id);
		
	}
	
	@Test
	public void getFirstBackGroundImage() {
		Map<String, ?> firstElement = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled[0]");
		
		System.out.println("---------------First Element ---------------------");
		Set<String> keys = firstElement.keySet();
		for (String key : keys) {
			System.out.println(key + " : " + firstElement.get(key));
		}
//		System.out.println(firstElement);
	}
	
	@Test
	public void getFirstBackGroundImageUsingLambdaExp() {
		Map<String, ?> firstElement = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled[0]");
		
		System.out.println("---------------First Element Using lambda Expression  ---------------------");
		firstElement.forEach((key,value) -> System.out.println(key + " : " + value));
	}
	
	@Test
	public void getCompleteBackGroundImage() {
		List<Map<String, ?>> allElements = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled");
		
		System.out.println("---------------Complete List Elements ---------------------");
		for (int i = 0; i < allElements.size(); i++) {
			Map<String,?> ele = allElements.get(i);
			
			Set<String> keys = ele.keySet();
			for (String key : keys) {
				System.out.println("Element at " + i + " index " + key + " : " + ele.get(key));
			}
			System.out.println("------------------------------------------------------------------------------");
			
		}
		
//		System.out.println(firstElement);
	}
	
	
	@Test
	public void printAllUrlsinImages() {
		List<String> urls = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled[*].url");
		System.out.println("---- Printing all URLs -----");
		urls.forEach(url -> System.out.println(url));
	}
	
	@Test
	public void printWidthWithConditininImages() {
		List<Integer> allWidth = JsonPath.read(response.asString(), "$.prefs.backgroundImageScaled[*].[?(@.width<1000)].width");
		System.out.println("---- Printing Width less than 1000 -----");
		allWidth.forEach(width -> System.out.println(width));
	}
	
}
