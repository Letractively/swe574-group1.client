package tr.edu.boun.swe574.fsn.web.wicket.home;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;

@AuthorizeInstantiation(value = {FsnRoles.USER})
public class HomePage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6774351834417575020L;
	
	public HomePage() {
		
	}

}
