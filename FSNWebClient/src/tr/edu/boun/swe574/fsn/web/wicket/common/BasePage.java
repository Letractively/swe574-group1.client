package tr.edu.boun.swe574.fsn.web.wicket.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.home.HomePage;

public class BasePage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1254130356433351710L;
	
    private static final Logger logger = Logger.getLogger(BasePage.class);
    protected FeedbackPanel feedbackPanel;
    WebMarkupContainer profileTab;
    WebMarkupContainer recipesTab;
    WebMarkupContainer recommendationTab;
    Label lblEmail;
    final HashMap vars = new HashMap();
    
    public Map getVars()
    {
        return vars;
    }

    public String getFullName()
    {
        return (new StringBuilder(String.valueOf(FsnSession.getInstance().getUser().getFirstName()))).append(" ").append(FsnSession.getInstance().getUser().getLastName()).toString();
    }

    public BasePage()
    {
        feedbackPanel = new FeedbackPanel("feedBackPanel");
        feedbackPanel.setOutputMarkupId(true);
        lblEmail = new Label("lblEmail", getFullName());
        profileTab = new WebMarkupContainer("profileTab");
        recipesTab = new WebMarkupContainer("recipesTab");
        recommendationTab = new WebMarkupContainer("recommendationTab");
        
        Link lnkSignOut = new Link("lnkSignOut") {

            public void onClick()
            {
                String email = FsnSession.getInstance().getUser().getEmail();
                BasePage.logger.info((new StringBuilder(String.valueOf(email))).append(" clicked logout!").toString());
                getSession().invalidate();
                setResponsePage(HomePage.class);
            }

        };
        
        Link lnkMyProfile = new Link("lnkMyProfile") {

            public void onClick()
            {
                setResponsePage(HomePage.class);
            }

            private static final long serialVersionUID = 0x20aa8a6f78c094ccL;

        };
        
        Link lnkSearchForUsers = new Link("lnkSearchForUsers") {

            public void onClick()
            {
            	setResponsePage(HomePage.class);
            }

            private static final long serialVersionUID = 0x359a985dcf54ffc7L;
        };
        
        Link lnkFollowedUsers = new Link("lnkFollowedUsers") {

            public void onClick()
            {
            	setResponsePage(HomePage.class);
            }

            private static final long serialVersionUID = 0xe27d6824a3ab259eL;
        };
        
        Link lnkMyFollowers = new Link("lnkMyFollowers") {

            public void onClick()
            {
            	setResponsePage(HomePage.class);
            }

            private static final long serialVersionUID = 0xc775ea122dcf194dL;
        };
        
        Link lnkMyRecipes = new Link("lnkMyRecipes") {

            public void onClick()
            {
            	setResponsePage(HomePage.class);
            }

            private static final long serialVersionUID = 0x9bcc5d32c789f552L;
        };
        
        Link lnkCreateRecipe = new Link("lnkCreateRecipe") {

            public void onClick()
            {
            	setResponsePage(HomePage.class);
            }

            private static final long serialVersionUID = 0x6f9ac8930ea687afL;
        };
        
        Link lnkRecipesFeed = new Link("lnkRecipesFeed") {

            public void onClick()
            {
            	setResponsePage(HomePage.class);
            }

            private static final long serialVersionUID = 0xe8b5dd6ffd40ce2L;
        };
        
        Link lnkSearchForRecipes = new Link("lnkSearchForRecipes") {

            public void onClick()
            {
            	setResponsePage(HomePage.class);
            }

            private static final long serialVersionUID = 0x713963667c6fc27aL;
        };
        
        profileTab.add(new Component[] {
            lnkMyProfile
        });
        profileTab.add(new Component[] {
            lnkSearchForUsers
        });
        profileTab.add(new Component[] {
            lnkFollowedUsers
        });
        profileTab.add(new Component[] {
            lnkMyFollowers
        });
        recipesTab.add(new Component[] {
            lnkMyRecipes
        });
        recipesTab.add(new Component[] {
            lnkCreateRecipe
        });
        recipesTab.add(new Component[] {
            lnkRecipesFeed
        });
        recommendationTab.add(new Component[] {
            lnkSearchForRecipes
        });
        add(new Component[] {
            lblEmail
        });
        add(new Component[] {
            profileTab
        });
        add(new Component[] {
            recipesTab
        });
        add(new Component[] {
            recommendationTab
        });
        add(new Component[] {
            lnkSignOut
        });
        add(new Component[] {
            feedbackPanel
        });
        MetaDataRoleAuthorizationStrategy.authorize(profileTab, RENDER, "USER");
        MetaDataRoleAuthorizationStrategy.authorize(recipesTab, RENDER, "USER");
        MetaDataRoleAuthorizationStrategy.authorize(recommendationTab, RENDER, "USER");
    }

    protected boolean isBlank(Collection collection)
    {
        return collection == null || collection.size() == 0;
    }

    public void initValues()
    {
    }

    public void pack()
    {
    }

    public FeedbackPanel getFeedbackPanel()
    {
        return feedbackPanel;
    }

    public void setFeedbackPanel(FeedbackPanel feedbackPanel)
    {
        this.feedbackPanel = feedbackPanel;
    }

    protected void addComponentToAjaxTarget(Component component)
    {
        AjaxRequestTarget target = AjaxRequestTarget.get();
        if(target != null)
            target.add(new Component[] {
                component
            });
    }

    protected void addFeedbackPanelToAjaxTarget()
    {
        AjaxRequestTarget target = AjaxRequestTarget.get();
        if(target != null)
            target.add(new Component[] {
                feedbackPanel
            });
    }


}
