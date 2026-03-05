package SchemaValidation;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;

public class SchemaValidationTest {
	
	@Test
	public void getUsersAPISchemaTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		RestAssured.given().log().all()
						.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
					.when()
						.get("/public/v2/users")
					.then().log().all()
						.assertThat()
							.statusCode(200)
							.body(matchesJsonSchemaInClasspath("getusersschema.json"));
				
	}
	
	
	@Test
	public void cretaAUserAPISchemaTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		RestAssured.given().log().all()
							.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
							.body(new File("./src/test/resources/jsons/newuser.json"))
							.contentType(ContentType.JSON)
						.when()
							.post("/public/v2/users")
					.then().log().all()
						.assertThat()
							.statusCode(201)
							.body(matchesJsonSchemaInClasspath("createuserschema.json"));
				
	}
	
}
