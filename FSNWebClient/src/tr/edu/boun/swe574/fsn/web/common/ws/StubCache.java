package tr.edu.boun.swe574.fsn.web.common.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import edu.boun.swe574.fsn.common.client.auth.AuthService;
import edu.boun.swe574.fsn.common.client.auth.AuthService_Service;
import edu.boun.swe574.fsn.common.client.food.FoodsService;
import edu.boun.swe574.fsn.common.client.food.FoodsService_Service;
import edu.boun.swe574.fsn.common.client.network.NetworkService;
import edu.boun.swe574.fsn.common.client.network.NetworkService_Service;
import edu.boun.swe574.fsn.common.client.search.SearchService;
import edu.boun.swe574.fsn.common.client.search.SearchService_Service;

public class StubCache {
	
	private static Logger logger = Logger.getLogger(StubCache.class);
	
	private static StubCache instance = new StubCache();
	
	private static final Hashtable<String, AuthService> authStub = new Hashtable<String, AuthService>();
	private static final Hashtable<String, FoodsService> foodStub = new Hashtable<String, FoodsService>();
	private static final Hashtable<String, NetworkService> networkStub = new Hashtable<String, NetworkService>();
	private static final Hashtable<String, SearchService> searchStub = new Hashtable<String, SearchService>();
	
	private StubCache() {
		
	}
	
	public static StubCache getInstance() {
		return instance;
	}

	public AuthService getAuthStub(String url) {
		if(url == null) {
			return null;
		}
		if(!authStub.containsKey(url)) {
			URL uri;
			try {
				uri = new URL(url);
				AuthService_Service service = new AuthService_Service(uri, new QName("http://ws.backend.fsn.swe574.boun.edu/", "AuthService"));
				AuthService authServicePort = service.getAuthServicePort();
				authStub.put(url, authServicePort);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return authStub.get(url);
	}
	
	public FoodsService getFoodStub(String url) {
		if(url == null) {
			return null;
		}
		if(!foodStub.containsKey(url)) {
			URL uri;
			try {
				uri = new URL(url);
				FoodsService_Service service = new FoodsService_Service(uri, new QName("http://ws.backend.fsn.swe574.boun.edu/", "FoodsService"));
				FoodsService foodsServicePort = service.getFoodsServicePort();
				foodStub.put(url, foodsServicePort);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return foodStub.get(url);
	}
	
	public NetworkService getNetworkStub(String url) {
		if(url == null) {
			return null;
		}
		if(!networkStub.containsKey(url)) {
			URL uri;
			try {
				uri = new URL(url);
				NetworkService_Service service = new NetworkService_Service(uri, new QName("http://ws.backend.fsn.swe574.boun.edu/", "NetworkService"));
				NetworkService networkServicePort = service.getNetworkServicePort();
				networkStub.put(url, networkServicePort);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return networkStub.get(url);
	}
	
	public SearchService getSearchStub(String url) {
		if(url == null) {
			return null;
		}
		if(!searchStub.containsKey(url)) {
			URL uri;
			try {
				uri = new URL(url);
				SearchService_Service service = new SearchService_Service(uri, new QName("http://ws.backend.fsn.swe574.boun.edu/", "FoodsServicePort"));
				SearchService searchServicePort = service.getSearchServicePort();
				searchStub.put(url, searchServicePort);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
				logger.error("", e);
			}
		}
		return searchStub.get(url);
	}
}
