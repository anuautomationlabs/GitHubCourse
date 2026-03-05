package AuthAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class AuthAPIsTest {

	//basic - 1
	//digest - 2
	//preemptive - 3
	//api key
	//oauth2 - 0
	//JWT Token
	//Bearer Token
	
	@Test
	public void basicAuthAPITest() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		RestAssured.given()
						.auth()
							.basic("admin", "admin")
					.when()
						.get("/basic_auth")
							.then().log().all()
								.assertThat()
									.statusCode(200);
	}
	
	
	@Test
	public void digestAuthAPITest() {
		
		RestAssured.baseURI = "https://postman-echo.com";
		
		RestAssured.given()
						.auth()
							.digest("postman", "password")
					.when()
						.get("/digest-auth")
							.then().log().all()
								.assertThat()
									.statusCode(200);
	}
	
	
	@Test
	public void preemptiveAuthAPITest() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		RestAssured.given()
						.auth()
							.preemptive().basic("admin", "admin")
					.when()
						.get("/basic_auth")
							.then().log().all()
								.assertThat()
									.statusCode(200);
	}
	
	
	
	
	
	
	
	
}
