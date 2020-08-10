package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {

	public String URL;
	public String browser;
	public String apiURL;

	public Properties prop;

	public void populateConfiguration() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/java/config/config.properties");
			prop.load(ip);

			// web page URL
			URL = prop.getProperty("url");
			browser = prop.getProperty("browser");
			apiURL = prop.getProperty("apiURL");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}