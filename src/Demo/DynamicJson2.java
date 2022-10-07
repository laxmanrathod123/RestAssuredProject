package Demo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import file.ReUsableMethods;
import file.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson2 {
	// parameterization of data
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		
		// AddBook Dynamically from the method itself 
		// if we hard coded the value of book added then next time its fail cause its already present there
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().headers("Content-Type","application/json").body(payload.addBook(isbn,aisle)).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReUsableMethods.rawToJson(response);
		String id=js.get("Id");
		System.out.println(id   );
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		// array => collection of element
		// multidiamentional Array => collection of Array
		return new Object[][] {{"fashdh","1242"},{"agfg","1313"},{"adgg","1325"}};
	}

}
