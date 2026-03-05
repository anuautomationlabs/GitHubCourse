package JaywayJsonPathConcept;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathFunctionsTest {
	
	
	@Test
	public void getProductsAPITest() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = RestAssured.given().when().get("/products");

		response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 200);

		String jsonResponse = response.getBody().asString();

		ReadContext ctx = JsonPath.parse(jsonResponse);
		
		double minPrice = ctx.read("min($[*].price)");
		System.out.println(minPrice);
		
		double maxPrice = ctx.read("max($[*].price)");
		System.out.println(maxPrice);
		
		double avgPrice = ctx.read("avg($[*].price)");
		System.out.println(avgPrice);
		
		int totalProducts = ctx.read("length($)");
		System.out.println(totalProducts);
		
		double totalPrice = ctx.read("sum($[*].price)");
		System.out.println(totalPrice);
		
		// index(-2).['id', 'price']
		// first($[*])
		//last
		

	}

}
