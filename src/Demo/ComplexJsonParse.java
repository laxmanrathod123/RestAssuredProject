package Demo;

import file.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
// Number of courses which is return by api
		JsonPath js= new JsonPath(payload.CoursePrice());
		int count=js.getInt("courese.size()");
		System.out.println("count :"+count);
		
		// print the purchase amount
		int purchaseAmout=js.getInt("dashboard.purchaseAmount");
		System.out.println("purchaseAmout :"+purchaseAmout);
		
		// print the title of the first course
		
		String titleOfFirstCourses=js.get("courese[0].title");
		System.out.println("titleOfFirstCourses: "+titleOfFirstCourses);
		String titleOfSecondCourses=js.get("courese[1].title");
		System.out.println("titleOfSecondCourses: "+titleOfSecondCourses);
		String titleOfThirdCourses=js.get("courese[2].title");
		System.out.println("titleOfThirdCourses: "+titleOfThirdCourses);
		
		// print all courses tittle and there respective prices
		System.out.println("** using for loop******");
		for(int i=0 ; i<count; i++) {
			
			String courseTitle=js.get("courese["+i+"].title");
		
			int priceofcouse=js.get("courese["+i+"].price");
		   System.out.println(js.get("courese ["+i+"].price").toString());
			System.out.println(courseTitle);
			//System.out.println(priceofcouse);
		}
		// no of copies sold by RPA
		
        for(int i=0 ; i<count; i++) {
			
			String courseTitle=js.get("courese["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA")) {
			int copies=js.get("courese["+i+"].copies");
			System.out.println("copies:"+copies);
			break;
			}
			
		}
		
		
		
		
		
	}

}
