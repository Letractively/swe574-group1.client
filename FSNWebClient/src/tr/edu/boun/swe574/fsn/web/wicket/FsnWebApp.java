package tr.edu.boun.swe574.fsn.web.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

import tr.edu.boun.swe574.fsn.web.wicket.home.HomePage;
import tr.edu.boun.swe574.fsn.web.wicket.login.FsnSignInPage;
import tr.edu.boun.swe574.fsn.web.wicket.register.Register;

public class FsnWebApp extends AuthenticatedWebApplication {

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return FsnSignInPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return FsnSession.class;
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return HomePage.class;
	}
	
    private void initMounts()
    {
        super.mountPage("/register", Register.class);
    }

    protected void init()
    {
        super.init();
        initMounts();
    }

}