package Demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import file.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourse() {
		
		JsonPath js= new JsonPath(payload.CoursePrice());
		
		int count =js.getInt("courses.size()");
		System.out.println("count :"+count);
		int sum=0;
		for(int i=0; i<count; i++) {
			String tittleOfCourese=js.get("courses["+i+"].title");
			System.out.println(tittleOfCourese);
			int price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount=price*copies;
			System.out.println("amount :"+amount);
			sum=sum+amount;
		}
		System.out.println("sum :"+sum);
		
		int purchaseAmout=js.getInt("dashboard.purchaseAmount");
		System.out.println("purchaseAmout :"+purchaseAmout);
		
		Assert.assertEquals(purchaseAmout, sum);
	}
}
