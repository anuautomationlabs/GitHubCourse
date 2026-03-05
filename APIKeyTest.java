package AuthAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APIKeyTest {
	
	@Test
	public void googleGeminiAIModalTest() {
		
		RestAssured.baseURI = "https://generativelanguage.googleapis.com";
		
		RestAssured.given()
						.header("X-goog-api-key", "AIzaSyCTJihMNUssEYSeikgA5cydA-dxo-FHOck")
						.contentType(ContentType.JSON)
						.body("{\n"
								+ "    \"contents\": [\n"
								+ "      {\n"
								+ "        \"parts\": [\n"
								+ "          {\n"
								+ "            \"text\": \"what is rest assured in API testing?\"\n"
								+ "          }\n"
								+ "        ]\n"
								+ "      }\n"
								+ "    ]\n"
								+ "  }")
						.when().urlEncodingEnabled(false)
							.post("/v1beta/models/gemini-2.5-flash:generateContent")
							.then().log().all()
								.assertThat()
									.statusCode(200);
		
	}
	
	

}
