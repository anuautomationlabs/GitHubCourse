package RequestResponseSpecificationConcept;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecTest {
	
	RequestSpecification requestSpec, requestQueryParamSpec, reqSpecInvalidAuth;
	ResponseSpecification responseSpec, responseSpec_401;
	
	
	@BeforeTest
	public void setup() {
		
		requestSpec = given().log().all()
						.baseUri("https://gorest.co.in")
						.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
						.header("Content-Type", "application/json");
		
		
		requestQueryParamSpec = given().log().all()
				.baseUri("https://gorest.co.in")
				.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
				.header("Content-Type", "application/json")
				.queryParam("name", "naveen")
				.queryParam("status", "active");
		
		
		reqSpecInvalidAuth = given().log().all()
				.baseUri("https://gorest.co.in")
				.header("Authorization", "Bearer invalid")
				.header("Content-Type", "application/json");
		
		responseSpec = expect()
						.statusCode(200)
						.header("Content-Type", "application/json; charset=utf-8")
						.header("Server", "cloudflare")
						.time(lessThan(5000L));
		
		responseSpec_401 = expect()
				.statusCode(401)
				.header("Content-Type", "application/json; charset=utf-8")
				.header("Server", "cloudflare")
				.time(lessThan(2000L))
				.body("message", equalTo("Invalid token"));
		
	}
	
	
	@Test
	public void getUsersTest() {
		requestSpec
				.get("/public/v2/users/")
					.then()
						.spec(responseSpec);
	}
	
	@Test
	public void getUsersWithQueryParamTest() {
		requestQueryParamSpec
				.get("/public/v2/users/")
					.then()
						.spec(responseSpec);
	}
	
	
	@Test
	public void authTest() {
		
		reqSpecInvalidAuth
						.get("/public/v2/users/")
							.then().log().all()
								.spec(responseSpec_401);
	}
	
	

}
