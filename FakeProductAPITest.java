package JaywayJsonPathConcept;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FakeProductAPITest {

	@Test
	public void getProductsAPITest() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = RestAssured.given().when().get("/products");

		response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 200);

		String jsonResponse = response.getBody().asString();

		ReadContext ctx = JsonPath.parse(jsonResponse);

		List<Number> prices = ctx.read("$[?(@.price > 50)].price");
		System.out.println("total prices > 50 ::" + prices.size());
		System.out.println(prices);

		System.out.println("---------------");

		List<Integer> ids = ctx.read("$[?(@.price > 50)].id");
		System.out.println("total ids ::" + ids.size());
		System.out.println(ids);

		System.out.println("---------------");

		List<Double> rates = ctx.read("$[?(@.price > 50)].rating.rate");
		System.out.println("total rates ::" + rates.size());
		System.out.println(rates);

		System.out.println("---------------");

		// contains: /.*value.*/i
		List<Integer> backpackIDs = ctx.read("$[?(@.title =~ /.*Backpack.*/i)].id");
		System.out.println("total backpackIDs ::" + backpackIDs.size());
		System.out.println(backpackIDs);

		System.out.println("---------------");

		// startswith: /.^value.*/i
		List<Integer> startsWithID = ctx.read("$[?(@.title =~ /^Fjallraven.*/i)].id");
		System.out.println("total startsWithIDs ::" + startsWithID.size());
		System.out.println(startsWithID);

		System.out.println("---------------");

		// EndsWith: /.*value$/i
		List<Integer> endsWithID = ctx.read("$[?(@.title =~ /.*Laptops$/i)].id");
		System.out.println("total endsWithIDs ::" + endsWithID.size());
		System.out.println(endsWithID);

	}

	@Test
	public void getProductsAPITest_WithJsonPathQuery() {
		RestAssured.baseURI = "https://fakestoreapi.com";

		Response response = RestAssured.given().when().get("/products");

		response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 200);

		String jsonResponse = response.getBody().asString();

		ReadContext ctx = JsonPath.parse(jsonResponse);

		System.out.println("----------------");
		// single attribute:
		// fetch all the ids:
		List<Integer> allIds = ctx.read("$[*].id");
		System.out.println(allIds.size());
		System.out.println(allIds);

		System.out.println("----------------");

		// two attributes:
		// fetch all the ids and titles:
		List<Map<String, Object>> twoAttrList = ctx.read("$[*].['id', 'title']");
		System.out.println(twoAttrList);
		System.out.println(twoAttrList.size());

		for (Map<String, Object> e : twoAttrList) {
			int id = (Integer) e.get("id");
			String title = (String) e.get("title");
			System.out.println("ID: " + id);
			System.out.println("Title : " + title);
			System.out.println("-------------");
		}

		System.out.println("----------------");

		// three attributes:
		// fetch all the ids and titles:
		List<Map<String, Object>> threeAttrList = ctx.read("$[*].['id', 'title', 'category']");
		System.out.println(threeAttrList);
		System.out.println(threeAttrList.size());

		for (Map<String, Object> e : threeAttrList) {
			int id = (Integer) e.get("id");
			String title = (String) e.get("title");
			String category = (String) e.get("category");

			System.out.println("ID: " + id);
			System.out.println("Title : " + title);
			System.out.println("Category : " + category);

			System.out.println("-------------");
		}

		System.out.println("----------------");

		List<Double> rates = ctx.read("$[*].rating.rate");
		System.out.println(rates);

		System.out.println("----------------");

		// conditional based jsonpath:
		// fetch the id where category == 'jewelery'
		List<Integer> jeweleryIDs = ctx.read("$[?(@.category === 'jewelery')].id");
		System.out.println(jeweleryIDs);

		System.out.println("----------------");
		// fetch the id and title where category == 'jewelery'
		List<Map<String, Object>> jeweleryIDTitles = ctx.read("$[?(@.category === 'jewelery')].['id', 'title']");
		System.out.println(jeweleryIDTitles);
		
		System.out.println("----------------");

		//Multiple Conditions 
		//fetch the id where category == 'jewelery' and price < 10
		
		// $[?( (@.category === 'jewelery') || (@.category === 'electronics') ) ]
		// $[?( (@.category === 'jewelery') && (@.price > 50)  || (@.price < 10) ) ]
		// $[?( (@.category === 'jewelery') && (@.price > 50) ) ]
		

	}

}
