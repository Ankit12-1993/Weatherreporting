package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.SearchContext;

public class GUILocatorsConfig {

	public String ndtvMenu;
	public String ndtvWeatherLink;

	public String weatherInputSearchBox;
	public String weathercitycheckbox;
	public String weathersearchreloadbutton;
	public String weatherClickCity;

	private Properties properties;

	public void populateGUILocators() {
		try {
			properties = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/java/testData/locators.properties");
			properties.load(ip);

			// NDTV HOME PAGE
			ndtvMenu = properties.getProperty("ndtv.menu");
			ndtvWeatherLink = properties.getProperty("ndtv.weather.link");

			// NDTV WEATHER PAGE
			weatherInputSearchBox = properties.getProperty("weather.input.search.box");
			weathersearchreloadbutton = properties.getProperty("weather.search.reload.button");
			weathercitycheckbox = properties.getProperty("weather.city.checkbox");
			weatherClickCity = properties.getProperty("weather.click.city");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
