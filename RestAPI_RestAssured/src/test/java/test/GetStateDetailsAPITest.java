package test;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import responsePojo.GetStateDetail;
import responsePojo.GetStateDetailResponse;
import service.Service;

public class GetStateDetailsAPITest extends TestBase
{
	List<Object> list;
	List<Object> testData;
	
	@BeforeClass
	public void dataSetUp()
	{
		testData = new ArrayList<Object>();
		testData.add("Bihar");
		testData.add("UP");
		testData.add("karnataka");
		testData.add("Kerala");
		testData.add("tamil nadu");
		testData.add("MP");
	}
	
	@Test
	public void getstatedetailsAPITest() 
	{
		service = new Service();
		response = service.getStateDetailsAPI();
		System.out.println(response.asString());
		
		if(response.getStatusCode()==200)
		{
			Gson gson = new Gson();
			GetStateDetailResponse getStateDetailResponse = gson.fromJson(response.asString(), GetStateDetailResponse.class);
			List<GetStateDetail> stateData = getStateDetailResponse.getGetStateDetails();
			list = new ArrayList<Object>();
			for(int i =0 ; i<stateData.size();i++)
			{
				System.out.println(stateData.get(i).getName());
				list.add(stateData.get(i).getName());
			}
			Assert.assertEquals(list, testData);
		}
		else
		{
			Assert.assertTrue(false,response.asString());
		}
	}
}
