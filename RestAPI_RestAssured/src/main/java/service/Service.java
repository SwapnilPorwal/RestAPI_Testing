package service;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import requestPojo.Address;
import requestPojo.CreatePersonRequest;
import requestPojo.UpdatePersonRequest;
import responsePojo.CreatePersonResponse;
import service.ServiceURL;


public class Service
{
	List<JSONObject> list;
	public Response createPersonAPI(String name , String surname , String city , String landmark , String state , String zipcode)
	{
		CreatePersonRequest createPersonRequest = new CreatePersonRequest();
		createPersonRequest.setName(name);
		createPersonRequest.setSurname(surname);
		
		Address address = new Address();
		createPersonRequest.setAddress(address);
		
		address.setCity(city);
		address.setLandmark(landmark);
		address.setState(state);
		address.setZipcode(zipcode);
		
		JSONObject jsonObj = new JSONObject(createPersonRequest);
		list = new ArrayList<JSONObject>();
		list.add(jsonObj);
		System.out.println(list);
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType("application/json");
		requestSpecification.accept("application/json");
		requestSpecification.body(list.toString());
		Response response = requestSpecification.post(ServiceURL.createPersonURL);
		return response;
	}
	
	public Response getStateDetailsAPI()
	{
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType("application/json");
		requestSpecification.accept("application/json");
		System.out.println("End point URL : "+ServiceURL.getStateDetails);
		Response response = requestSpecification.get(ServiceURL.getStateDetails);
		return response;
	}
	
	/**
	 * This API will update person details
	 * @param name
	 * @param surname
	 * @param city
	 * @param landmark
	 * @param state
	 * @param zipcode
	 * @return
	 */
	public Response updatePersonDetail(String name,String surname, String city,String landmark, String state, String zipcode){
		UpdatePersonRequest updatePersonPojo = new UpdatePersonRequest();
		updatePersonPojo.setName(name);
		updatePersonPojo.setSurname(surname);
		
		Address address = new Address();
		updatePersonPojo.setAddress(address);
		address.setCity(city);
		address.setLandmark(landmark);
		address.setState(state);
		address.setZipcode(zipcode);
		updatePersonPojo.setAddress(address);
		
		JSONObject jsonObj = new JSONObject(updatePersonPojo);
		System.out.println("json payload..");
		list = new ArrayList<JSONObject>();
		list.add(jsonObj);
		System.out.println(list);
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType("application/json");
		requestSpecification.accept("application/json");
		requestSpecification.body(list.toString());
		System.out.println("end point url.."+ServiceURL.updatePersonDetails);
		Response response = requestSpecification.put(ServiceURL.updatePersonDetails);
		return response;
}
	
	public static void main (String[] args)
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
