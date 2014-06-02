package tr.edu.boun.swe574.fsn.web.wicket;

import java.util.Map;

import net.sourceforge.easywicket.EasyWicketComponentInitializer;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.AjaxRequestTarget.IJavaScriptResponse;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.IContextProvider;

import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import tr.edu.boun.swe574.fsn.web.wicket.login.FsnSignInPage;
import tr.edu.boun.swe574.fsn.web.wicket.profile.recipeFeeds.RecipeFeeds;
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
		return RecipeFeeds.class;
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
        initEasyWicket();
        initRequestListener();
    }
    
	private void initExceptionPages() {
//		getApplicationSettings().setInternalErrorPage(SysErr.class);
//		getExceptionSettings().setUnexpectedExceptionDisplay(
//				IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		getApplicationSettings().setPageExpiredErrorPage(getSignInPageClass());
//        getApplicationSettings().setAccessDeniedPage(AccessDenied.class);
	}
	
	private void initEasyWicket() {
		getComponentInitializationListeners().add(new EasyWicketComponentInitializer());

	}
	
	private void initRequestListener() {
//		getRequestCycleListeners().add(new DefaultRequestListener());

		final IContextProvider<AjaxRequestTarget, Page> old = getAjaxRequestTargetProvider();
		setAjaxRequestTargetProvider(new IContextProvider<AjaxRequestTarget, Page>() {
			@Override
			public AjaxRequestTarget get(Page page) {
				AjaxRequestTarget target = old.get(page);
				target.registerRespondListener(new AjaxRequestTarget.ITargetRespondListener() {
					
					@Override
					public void onTargetRespond(AjaxRequestTarget target) {
						Page page = target.getPage();
						if ( page instanceof BasePage) {
							((BasePage)page).onAfterAjaxRequest(target);
						}
					}
				});
				return target;
			}
		});
		
		getAjaxRequestTargetListeners().add(new AjaxRequestTarget.IListener() {
			
			
			@Override
			public void onBeforeRespond(Map<String, Component> map,
					AjaxRequestTarget target) {
				Page page = target.getPage();
				
				if ( page instanceof BasePage) {
					((BasePage)page).onBeforeAjaxRequest(target);
				}
				

			}
			
			@Override
			public void onAfterRespond(Map<String, Component> map,
					IJavaScriptResponse response) {
			}
		});
	}

}
