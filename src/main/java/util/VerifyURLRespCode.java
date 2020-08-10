package util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;


public class VerifyURLRespCode{

	private static ArrayList<String> hrefList; 	  
	private static ArrayList<String> okUrlList;
	private static ArrayList<String> brokenUrlList;
	private static ArrayList<String> exceptionList;
	
	public static HashMap<String, List<String>> verifiedUrlMap;
	
	public static HashMap<String, List<String>> validateAvailableLinks(WebDriver driver)
	{
		hrefList 	  = new ArrayList<String>();
		okUrlList	  = new ArrayList<String>();
		brokenUrlList = new ArrayList<String>();
		exceptionList = new ArrayList<String>();
		verifiedUrlMap= new HashMap<String , List<String>>();
		
		List<WebElement> list=driver.findElements(By.xpath("//*[@href or @src]"));

		for(WebElement e : list)
		{
			if(!(e.getAttribute("href")==null))
			{
				hrefList.add(e.getAttribute("href"));
			}
		}		

		verifiedUrlMap.put("Available_Links", hrefList);

		for(String url : hrefList)
		{	
					try
					{
						URL link = new URL(url);
						HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
							
						httpConn.setConnectTimeout(2000);
						httpConn.connect();
							
						if(httpConn.getResponseCode()==200 || httpConn.getResponseCode()==204 || httpConn.getResponseCode()==301|| httpConn.getResponseCode()==302) 
							okUrlList.add(url+" :: Reponse Code :: "+ httpConn.getResponseCode());
						else 
							brokenUrlList.add(url+" :: Reponse Code :: "+ httpConn.getResponseCode());
					} catch (Exception e) 
			        {
							exceptionList.add(url +" :: Exception Occured while conneting.");
			        } 
		}

		verifiedUrlMap.put("Valid_Links", okUrlList);
		verifiedUrlMap.put("Broken_Links", brokenUrlList);
		verifiedUrlMap.put("Others", exceptionList);
		
		return verifiedUrlMap;
	}
	
	public static HashMap<String, List<String>> validateAvailableLinksForSession(WebDriver driver)
	{
		hrefList 	  = new ArrayList<String>();
		okUrlList	  = new ArrayList<String>();
		brokenUrlList = new ArrayList<String>();
		exceptionList = new ArrayList<String>();
		verifiedUrlMap= new HashMap<String , List<String>>();
		
		List<WebElement> list=driver.findElements(By.xpath("//*[@href or @src]"));

		for(WebElement e : list)
		{     
			try {
			if(!(e.getAttribute("href")==null))
			{
				hrefList.add(e.getAttribute("href"));
				System.out.println("*********"+e.getAttribute("href"));
			}
			}
			catch(Exception E)
			{
				E.fillInStackTrace();
			}
		}		

		verifiedUrlMap.put("Available_Links", hrefList);

		for(String url : hrefList)
		{	
					try
					{
						URL link = new URL(url);
						HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();
							
						httpConn.setConnectTimeout(2000);
						httpConn.connect();
							
						if(httpConn.getResponseCode()==200 || httpConn.getResponseCode()==204 || httpConn.getResponseCode()==301|| httpConn.getResponseCode()==302) 
							okUrlList.add(url+" :: Reponse Code :: "+ httpConn.getResponseCode());
						else 
							brokenUrlList.add(url+" :: Reponse Code :: "+ httpConn.getResponseCode());
					} catch (Exception e) 
			        {
							exceptionList.add(url +" :: Exception Occured while conneting.");
			        } 
		}

		verifiedUrlMap.put("Valid_Links", okUrlList);
		verifiedUrlMap.put("Broken_Links", brokenUrlList);
		verifiedUrlMap.put("Others", exceptionList);
		
		return verifiedUrlMap;
	}

	
	public static Map<String, String> tracking(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		Map<String, String> networkRequestData = new HashMap<String, String>();
		for (LogEntry entry : driver.manage().logs().get(LogType.PERFORMANCE).getAll()) {
			JSONObject jsonObject = new JSONObject(entry.getMessage().toString());

			if (jsonObject.getJSONObject("message").get("method").equals("Network.requestWillBeSent")
					|| jsonObject.getJSONObject("message").get("method").equals("Network.dataReceived")
					|| jsonObject.getJSONObject("message").get("method").equals("Network.responseReceived")
					|| jsonObject.getJSONObject("message").get("method").equals("Network.responseReceivedExtraInfo")) {
				try {
					jsonObject = jsonObject.getJSONObject("message").getJSONObject("params").getJSONObject("request");
					String URL = (jsonObject.get("url")).toString();
					System.out.println("Requests*******" + URL);
					System.out.println();

				}

				catch (JSONException e) {
					try

					{
						jsonObject = jsonObject.getJSONObject("message").getJSONObject("params")
								.getJSONObject("response");
						String responseStatus = (jsonObject.get("status")).toString();
						String URL = (jsonObject.get("url")).toString();
						networkRequestData.put(URL, responseStatus);
						System.out.println("URL with status code***************" + URL
								+ "***************ResponseStatusCode: " + responseStatus);
						String remoteIpAddress = (jsonObject.get("remoteIPAddress")).toString();
						System.out.println("RemoteIpAddress for Above Request:---> " + remoteIpAddress);
						System.out.println();
					} catch (Exception E) {
						E.fillInStackTrace();
					}
				}

			}

		}

		return networkRequestData;

	}
	
	
}
