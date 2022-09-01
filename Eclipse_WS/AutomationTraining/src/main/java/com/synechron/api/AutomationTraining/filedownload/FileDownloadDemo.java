package com.synechron.api.AutomationTraining.filedownload;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class FileDownloadDemo {

	
	@Test
	public void fileDownload() {
		
		File f  = new File("download\\commons-io-2.11.0.jar");
		int expectedSize = (int) f.length();
		System.out.println("Expected length of the file " + expectedSize + " bytes" );
		
	byte[] actualValue = RestAssured.
		given().
		when().
			get("https://repo1.maven.org/maven2/commons-io/commons-io/2.11.0/commons-io-2.11.0.jar"). 
		then().
		extract().
		asByteArray();
			
	System.out.println("Actual length of the file " + actualValue.length + " bytes" );
	
		
		
		
	}
}
