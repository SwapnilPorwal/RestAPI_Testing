package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.jayway.restassured.response.Response;

import responsePojo.CreatePersonResponse;
import service.Service;

public class CreatePersonAPITest extends TestBase
{
	String name;
	String surname;
	String city;
	String landmark;
	String state;
	String zipcode;
	
	@BeforeClass
	public void DataSetup()
	{
		name ="Swapnil";
		surname = "Porwal";
		city = "Hyderabad";
		landmark = "Kondapur";
		state = "Telangana";
		zipcode = "500084";		
	}
	
	@Test
	public void createpersonAPITest()
	{
		Service service = new Service();
		Response data = service.createPersonAPI("Swapnil", "Porwal", "Hyderabad", "Kondapur", "Telangana", "500084");
		System.out.println(data.getStatusCode());
		System.out.println(data.asString());
		
		if(data.getStatusCode()==200)
		{
			Gson gson = new Gson();
			CreatePersonResponse createPersonResponse = gson.fromJson(data.asString(), CreatePersonResponse.class);
			System.out.println(createPersonResponse.getResponse().get(0).getName());
			System.out.println(createPersonResponse.getResponse().get(0).getSurname());
			System.out.println(createPersonResponse.getResponse().get(0).getAddress().getCity());
			System.out.println(createPersonResponse.getResponse().get(0).getAddress().getLandmark());
			System.out.println(createPersonResponse.getResponse().get(0).getAddress().getState());
			System.out.println(createPersonResponse.getResponse().get(0).getAddress().getZipcode());
		}
		
	}
}
