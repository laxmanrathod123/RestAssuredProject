package Demo;

import org.testng.annotations.Test;

import file.ReUsableMethods;
import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test
	public void addBook() {
		
		// AddBook Dynamically from the method itself 
		// if we hard coded the value of book added then next time its fail cause its already present there
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().headers("Content-Type","application/json").body(payload.addBook("asdfs","6464")).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReUsableMethods.rawToJson(response);
		String id=js.get("Id");
		System.out.println(id   );
		
	}

}
