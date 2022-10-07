package Demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import file.ReUsableMethods;
import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


public class updateAddress {

	public static void main(String[] args) {

        // add place
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
		System.out.println("*************");
		
		// update place 
		String newaddress="70 winter walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").
		body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newaddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").
		when().put("maps/api/place/update/json").
		then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		System.out.println("**********************");

     // get place
		
		String getplaceResponse=given().queryParam("key", "qaclick123").
		queryParam("place_id", placeid).
		when().get("maps/api/place/get/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();

		JsonPath js1=ReUsableMethods.rawToJson(getplaceResponse);
		//JsonPath js1=new JsonPath(getplaceResponse);
		String actualadd=js1.get("address");
		System.out.println(actualadd);
		Assert.assertEquals(actualadd, newaddress);





	}

}
