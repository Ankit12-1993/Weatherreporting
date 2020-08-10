package services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;

import com.jayway.restassured.response.Response;

import Base.TestBase;

public class BackendServices extends TestBase {

	Response response = null;

	public Response openWeatherMapApi(String lat, String lon, String appid, String unit) throws Exception {

		try {

			response = request().header("Cache-Control", "no-cache").header("Content-Type", "application/json")
					.post(propertiesConfig.apiURL + "?lat=" + lat + "&lon=" + lon + "&exclude=hourly,daily&appid="
							+ appid + "&units=" + unit + "");

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException iae) {
			Assert.fail("Auth key is not available, therefore, failing the test case");
		}
		return response;
	}

}
