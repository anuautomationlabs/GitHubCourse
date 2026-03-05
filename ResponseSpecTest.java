package RequestResponseSpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecTest {
	
	
	@Test
	public void getUsersTest() {
		
		ResponseSpecification resSpec = RestAssured.expect()
					.statusCode(200)
					.header("Content-Type", "application/json; charset=utf-8")
					.header("Server", "cloudflare");
					
		
		RestAssured.given()
						.baseUri("https://gorest.co.in")
						.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
					.when()
						.get("/public/v2/users/")
					.then()
						.spec(resSpec);
		
		
		RestAssured.given()
					.baseUri("https://gorest.co.in")
					.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
				.when()
					.get("/public/v2/users/8188549")
				.then()
					.spec(resSpec);
							
		
	}
	
	

}
