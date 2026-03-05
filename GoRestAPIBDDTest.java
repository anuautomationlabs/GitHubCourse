package GETAPIWithBDD;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class GoRestAPIBDDTest {
	
	@Test
	public void getAllUsersTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		 given().log().all()
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
		.when().log().all()
			.get("/public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
				.statusLine("HTTP/1.1 200 OK");
	}
	
	
	@Test
	public void getSingleUsersTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		 given().log().all()
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
		.when().log().all()
			.get("/public/v2/users/8135702")
		.then().log().all()
			.assertThat()
				.statusCode(200)
				.and()
				.statusLine("HTTP/1.1 200 OK");
	}
	
	
	@Test
	public void authTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		 given().log().all()
			.header("Authorization", "Bearer ---naveen")
		.when().log().all()
			.get("/public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(401)
				.and()
				.statusLine("HTTP/1.1 401 Unauthorized");
	}
	
	
	
	

}
