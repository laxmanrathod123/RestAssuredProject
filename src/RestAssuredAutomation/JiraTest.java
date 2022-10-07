package RestAssuredAutomation;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTest {

	public static void main(String[] args) {
// login scenario
		
		
		
		RestAssured.baseURI="http://localhost:8080";
		
		SessionFilter session= new SessionFilter();
		
		String response=given().header("Content-Tyep","application/json").
		body("{ \"username\": \"laxman\", \"password\": \"Rathod\" }").log().all().filter(session).
		when().post("/rest/auth/1/session").then().log().all().
		extract().response().asString();
		
		// commenting on issue
		
		
		  given().pathParam("key","10005").log().all().header("Content-Tyep","application/json").body("{\r\n" +
		  "    \"body\": \"I have commented through the rest automation \",\r\n" +
		  "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n" +
		  "        \"value\": \"Administrators\"\r\n" + "    }\r\n" +
		  "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").
		  then().log().all().assertThat().statusCode(201);
		  
		  
		  //Add Attachement using multiPart method
		  
		  given().header("X-Atlassian-Token","no check").filter(session).pathParam("key","10005").
		  header("Content-Type","multipart/form-data").
		  multiPart("file",new File("jira.txt")).when().post("rest/api/2/issue/{key}/attachments").
		  then().log().all().assertThat().statusCode(200);
		  
		  //get issue
		  
		 String issuedetails= given().filter(session).pathParam("key","10005").
				 queryParam("field", "comment").log().all().when().get("rest/api/2/issue/{key}").
		  then().log().all().extract().response().asString();
		  System.out.println("issuedetails:"+issuedetails);

	}
}
