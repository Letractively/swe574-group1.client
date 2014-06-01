package tr.edu.boun.swe574.fsn.web.wicket.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.easywicket.web.common.EasyPage;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.food.createRecipe.CreateRecipe;
import tr.edu.boun.swe574.fsn.web.wicket.home.HomePage;
import tr.edu.boun.swe574.fsn.web.wicket.profile.followingUsers.FollowedUsers;
import tr.edu.boun.swe574.fsn.web.wicket.profile.myProfile.MyProfile;
import tr.edu.boun.swe574.fsn.web.wicket.profile.recipeFeeds.RecipeFeeds;
import tr.edu.boun.swe574.fsn.web.wicket.profile.searchRecipe.SearchRecipe;
import tr.edu.boun.swe574.fsn.web.wicket.profile.searchUsers.SearchUsers;

public class BasePage extends EasyPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1254130356433351710L;
	
    private static final Logger logger = Logger.getLogger(BasePage.class);
    protected FeedbackPanel feedbackPanel;
    WebMarkupContainer profileTab;
    WebMarkupContainer recipesTab;
    WebMarkupContainer recommendationTab;
    WebMarkupContainer logoutTab;
    
    Label lblEmail;
    
    
    final HashMap<String, Object> vars = new HashMap<String, Object>();
    
    public Map<String, Object> getVars()
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
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        
        lblEmail = new Label("lblEmail", getFullName());
        profileTab = new WebMarkupContainer("profileTab");
        recipesTab = new WebMarkupContainer("recipesTab");
        recommendationTab = new WebMarkupContainer("recommendationTab");
        logoutTab = new WebMarkupContainer("logoutTab");
        
        Link<Object> lnkSignOut = new Link<Object>("lnkSignOut") {

            /**
			 * 
			 */
			private static final long serialVersionUID = -7315514533212418318L;

			public void onClick()
            {
                String email = FsnSession.getInstance().getUser().getEmail();
                BasePage.logger.info((new StringBuilder(String.valueOf(email))).append(" clicked logout!").toString());
                getSession().invalidate();
                setResponsePage(HomePage.class);
            }

        };
        
        Link<Object> lnkMyProfile = new Link<Object>("lnkMyProfile") {

            public void onClick()
            {
                setResponsePage(MyProfile.class);
            }

            private static final long serialVersionUID = 0x20aa8a6f78c094ccL;

        };
        
        Link<Object> lnkSearchForUsers = new Link<Object>("lnkSearchForUsers") {

            public void onClick()
            {
            	setResponsePage(SearchUsers.class);
            }

            private static final long serialVersionUID = 0x359a985dcf54ffc7L;
        };
        
        Link lnkFollowedUsers = new Link("lnkFollowedUsers") {

            public void onClick()
            {
            	setResponsePage(FollowedUsers.class);
            }

            private static final long serialVersionUID = 0xe27d6824a3ab259eL;
        };
        
//        Link lnkMyFollowers = new Link("lnkMyFollowers") {
//
//            public void onClick()
//            {
//            	setResponsePage(HomePage.class);
//            }
//
//            private static final long serialVersionUID = 0xc775ea122dcf194dL;
//        };
//        lnkMyFollowers.setVisible(false);
        
//        Link lnkMyRecipes = new Link("lnkMyRecipes") {
//
//            public void onClick()
//            {
//            	setResponsePage(HomePage.class);
//            }
//
//            private static final long serialVersionUID = 0x9bcc5d32c789f552L;
//        };
//        lnkMyRecipes.setVisible(false);
//        
        Link lnkCreateRecipe = new Link("lnkCreateRecipe") {

            public void onClick()
            {
            	setResponsePage(CreateRecipe.class);
            }

            private static final long serialVersionUID = 0x6f9ac8930ea687afL;
        };
        
        Link lnkRecipesFeed = new Link("lnkRecipesFeed") {

            public void onClick()
            {
            	setResponsePage(RecipeFeeds.class);
            }

            private static final long serialVersionUID = 0xe8b5dd6ffd40ce2L;
        };
        
        Link lnkSearchForRecipes = new Link("lnkSearchForRecipes") {

            public void onClick()
            {
            	setResponsePage(SearchRecipe.class);
            }

            private static final long serialVersionUID = 0x713963667c6fc27aL;
        };
        
        logoutTab.add(lnkSignOut);
        
        profileTab.add(new Component[] {
            lnkMyProfile
        });
        profileTab.add(new Component[] {
            lnkSearchForUsers
        });
        profileTab.add(new Component[] {
            lnkFollowedUsers
        });
//        profileTab.add(new Component[] {
//            lnkMyFollowers
//        });
//        recipesTab.add(new Component[] {
//            lnkMyRecipes
//        });
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
            feedbackPanel
        });
        
        add(logoutTab);
        
        MetaDataRoleAuthorizationStrategy.authorize(profileTab, RENDER, "USER");
        MetaDataRoleAuthorizationStrategy.authorize(recipesTab, RENDER, "USER");
        MetaDataRoleAuthorizationStrategy.authorize(recommendationTab, RENDER, "USER");
        MetaDataRoleAuthorizationStrategy.authorize(logoutTab, RENDER, "USER");
    }
    
	protected void refreshErrorPanel(AjaxRequestTarget ajaxRequestTarget) {
		if ( ajaxRequestTarget != null ) {
			if ( feedbackPanel.anyMessage()) {
				ajaxRequestTarget.add(feedbackPanel);
			}
		}
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
