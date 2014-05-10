package tr.edu.boun.swe574.fsn.web.common.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyReader {
	
	private static Logger logger = Logger.getLogger(PropertyReader.class);
	
	private static String authUrl = "ws.auth.url";
	private static String foodUrl = "ws.food.url";
	private static String networkUrl = "ws.network.url";
	private static String searchUrl = "ws.search.url";
	
	
	private PropertyReader() {
		
	}

	public static String getProperty(String key) {
		Properties properties = new Properties();
		try {
			properties.load(PropertyReader.class.getResourceAsStream("config.properties"));
			return properties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return null;
	}
	
	public static String getAuthenticationWsURL(){
		return getProperty(authUrl);
	}

	public static String getFoodsWsURL() {
		return getProperty(foodUrl);
	}

	public static String getSearchWsURL() {
		return getProperty(searchUrl);
	}
	
	public static String getNetworkWsURL()  {
		return getProperty(networkUrl);
	}
}
