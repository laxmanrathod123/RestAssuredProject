package Serialization;


import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

	public static void main(String[] args) {

		RestAssured.baseURI="https://rahulshettyacademy.com";
		AddPlace p = new AddPlace();
		Location l= new Location();
		l.setLat(-38.383494);
	    l.setLng(33.427362);
	    p.setLocation(l);
	    p.setAccuracy(50);
	    p.setName("Frontline house");
	    p.setPhone_number("(+91) 983 893 3937");
	    p.setAddress("29, side layout, cohen 09");
	    List<String> myList= new ArrayList<String>();
	    myList.add("shoe park");
	    myList.add("shop");
	    p.setTypes(myList);
	    
	    Response res=given().log().all().queryParam("key ", "qaclick123").body(p).when().
	    		post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();
	    		
	    		String responseString =res.asString();
	    		System.out.println(responseString);
		
		
	}

}
