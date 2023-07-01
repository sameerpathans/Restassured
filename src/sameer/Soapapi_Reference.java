package sameer;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.path.xml.*;
public class Soapapi_Reference {

	public static void main(String[] args) {
	//Declare the baseUrl
	RestAssured.baseURI="https://www.dataaccess.com/";
	//Declare RequestBody
	String RequestBody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
			+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
			+ "  <soap:Body>\r\n"
			+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
			+ "      <ubiNum>1000</ubiNum>\r\n"
			+ "    </NumberToWords>\r\n"
			+ "  </soap:Body>\r\n"
			+ "</soap:Envelope>";
	//Extract ResponseBody
	String ResponseBody=given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).
			when().post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
	System.out.println(ResponseBody);
	//parse the ResponseBody
	XmlPath XmlResponse = new XmlPath(ResponseBody);
	String ResponseParameter = XmlResponse.getString("NumberToWordsResult");
	System.out.println(ResponseParameter);
	//Validate The ResponseBody
	Assert.assertEquals(ResponseParameter,"one thousand");
			
			
			
			
			
	
		

	}

}
