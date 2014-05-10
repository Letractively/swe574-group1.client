package tr.edu.boun.swe574.fsn.web.common.ws;

import tr.edu.boun.swe574.fsn.web.common.util.PropertyReader;
import edu.boun.swe574.fsn.common.client.auth.AuthService;
import edu.boun.swe574.fsn.common.client.food.FoodsService;
import edu.boun.swe574.fsn.common.client.network.NetworkService;
import edu.boun.swe574.fsn.common.client.search.SearchService;

public class WSCaller {
	
	public static AuthService getAuthService() {
    	String authenticationWsURL = PropertyReader.getAuthenticationWsURL();
    	return StubCache.getInstance().getAuthStub(authenticationWsURL);
	}
	
	public static NetworkService getNetworkService() {
    	String networkWsURL = PropertyReader.getNetworkWsURL();
    	return StubCache.getInstance().getNetworkStub(networkWsURL);
	}
	
	public static FoodsService getFoodService() {
		String foodsWsURL = PropertyReader.getFoodsWsURL();
		return StubCache.getInstance().getFoodStub(foodsWsURL);
	}
	
	public static SearchService getSearchService() {
		String searchWsURL = PropertyReader.getSearchWsURL();
		return StubCache.getInstance().getSearchStub(searchWsURL);
	}

}