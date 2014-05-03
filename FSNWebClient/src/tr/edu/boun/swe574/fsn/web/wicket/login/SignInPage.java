package tr.edu.boun.swe574.fsn.web.wicket.login;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SignInPage extends WebPage {
	
	/**
	 * 
	 */

	private SignInPanel signInPanel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2304825779988874521L;
	
    public SignInPage(PageParameters parameters) {
        signInPanel = new SignInPanel("signInPanel");
        add(signInPanel);
    }

}
