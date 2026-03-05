package CertificateHAndling;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;

public class SSLCertificateTest {
	
	@Test
	public void sslTest() {
		
		RestAssured.given().log().all()
						.relaxedHTTPSValidation()
					.when()	
						.get("https://untrusted-root.badssl.com/")
					.then().log().all()
						.statusCode(200);
		
	}
	
	
	@Test
	public void sslHandleWithConfigTest() {
		
		RestAssured.config = RestAssuredConfig.config()
												.sslConfig(SSLConfig.sslConfig()
															.relaxedHTTPSValidation());
		
		RestAssured.given().log().all()
					.when()	
						.get("https://untrusted-root.badssl.com/")
					.then().log().all()
						.statusCode(200);
		
	}
	
	
	
	

}
