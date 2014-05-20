package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;

public class SearchUserForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2133449812593896155L;
	
	private String searchString;
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	
}
