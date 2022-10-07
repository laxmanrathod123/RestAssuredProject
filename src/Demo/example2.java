package Demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class example2 {

	public static void main(String[] args) {


		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.addpayload()).
				when().post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).body("scope", equalTo("APP")).
				header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

		System.out.println("Response :"+response);

		JsonPath js= new JsonPath(response);   // for parsing json
		String placeid=	js.getString("place_id");

		System.out.println("PlaceId :"+placeid);









	}

}
