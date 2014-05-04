package tr.edu.boun.swe574.fsn.web.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.settings.IExceptionSettings;

import tr.edu.boun.swe574.fsn.web.wicket.exception.AccessDenied;
import tr.edu.boun.swe574.fsn.web.wicket.exception.PageExpired;
import tr.edu.boun.swe574.fsn.web.wicket.exception.SysErr;
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
        initExceptionPages();
    }
    
	private void initExceptionPages() {
//		getApplicationSettings().setInternalErrorPage(SysErr.class);
//		getExceptionSettings().setUnexpectedExceptionDisplay(
//				IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
//		getApplicationSettings().setPageExpiredErrorPage(PageExpired.class);
//        getApplicationSettings().setAccessDeniedPage(AccessDenied.class);
	}

}
