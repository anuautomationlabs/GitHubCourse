package GETAPIWithBDD;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetUserWithPathParam {

	@Test
	public void getSingleUsersTest() {
		
		
		//BDD + Non BDD
		RestAssured.baseURI = "https://gorest.co.in";
		
		//https://gorest.co.in/public/v2/users/8135702
		given().log().all()
			.header("Authorization", "Bearer d0bf1714ac04c10dd2982e009d2dffe694a8e0b53af518cb7370e41e046a72f6")
			.pathParam("userId", 8135702)
		.when()
			.get("/public/v2/users/{userId}")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	
	//https://reqres.in/:apiname/:resource?page=2
	//https://reqres.in/api/users?page=2
	
	@Test
	public void getAllUsersWithQueryAndPathParamsTest() {
				
		RestAssured.baseURI = "https://reqres.in";
		
		Map<String, String> pathParamMap = new HashMap<String, String>();
		pathParamMap.put("apiname", "api");
		pathParamMap.put("resource", "users");
		
		Map<String, Integer> queryParamMap = new HashMap<String, Integer>();
		queryParamMap.put("page", 2);

		given().log().all()
			.pathParams(pathParamMap)
			.queryParams(queryParamMap)
		.when()
			.get("/{apiname}/{resource}")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
}
