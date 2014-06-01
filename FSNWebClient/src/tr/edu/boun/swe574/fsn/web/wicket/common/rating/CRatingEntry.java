package tr.edu.boun.swe574.fsn.web.wicket.common.rating;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.resource.ContextRelativeResource;

import tr.edu.boun.swe574.fsn.web.common.constants.ResultCode;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;
import tr.edu.boun.swe574.fsn.web.wicket.food.viewRecipe.ViewRecipe;
import edu.boun.swe574.fsn.common.client.food.BaseServiceResponse;

public class CRatingEntry extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8856240903922193372L;


	public CRatingEntry(String id, final Long recipeId, Integer rating, Integer ownRating) {
		super(id);
		
		boolean isRated = rating < 1 ? false : true;
		
		Label ratingValue = new Label("ratingValue", String.valueOf(rating));
		add(ratingValue);
		
		AjaxLink<Object> star1 = new AjaxLink<Object>("star1") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 8833407412480806745L;

			@Override
			public void onClick(AjaxRequestTarget ajaxRequestTarget) {
				rate(recipeId, 1);
				
				setResponsePage(new ViewRecipe(null, recipeId));
			}
		};
		NonCachingImage img1 = new NonCachingImage("img1", new ContextRelativeResource(getImgURI(isRated, rating, 1)));
		star1.add(img1);
		
		
		
		AjaxLink<Object> star2 = new AjaxLink<Object>("star2") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 7008336395511696427L;

			@Override
			public void onClick(AjaxRequestTarget ajaxRequestTarget) {
				rate(recipeId, 2);
				setResponsePage(new ViewRecipe(null, recipeId));
			}
		};
		NonCachingImage img2 = new NonCachingImage("img2", new ContextRelativeResource(getImgURI(isRated, rating, 2)));
		star2.add(img2);
		
		
		
		AjaxLink<Object> star3 = new AjaxLink<Object>("star3") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 2808128286050187267L;

			@Override
			public void onClick(AjaxRequestTarget ajaxRequestTarget) {
				rate(recipeId, 3);
				setResponsePage(new ViewRecipe(null, recipeId));
			}
		};
		NonCachingImage img3 = new NonCachingImage("img3", new ContextRelativeResource(getImgURI(isRated, rating, 3)));
		star3.add(img3);
		
		
		
		AjaxLink<Object> star4 = new AjaxLink<Object>("star4") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 7794418562571962981L;

			@Override
			public void onClick(AjaxRequestTarget ajaxRequestTarget) {
				rate(recipeId, 4);
				setResponsePage(new ViewRecipe(null, recipeId));
			}
		};
		NonCachingImage img4 = new NonCachingImage("img4", new ContextRelativeResource(getImgURI(isRated, rating, 4)));
		star4.add(img4);
		
		
		AjaxLink<Object> star5 = new AjaxLink<Object>("star5") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -8354910337282254651L;

			@Override
			public void onClick(AjaxRequestTarget ajaxRequestTarget) {
				rate(recipeId, 5);
				setResponsePage(new ViewRecipe(null, recipeId));
			}
		};
		NonCachingImage img5 = new NonCachingImage("img5", new ContextRelativeResource(getImgURI(isRated, rating, 5)));
		star5.add(img5);
		
		if(isRated) {
			star1.setEnabled(false);
			star2.setEnabled(false);
			star3.setEnabled(false);
			star4.setEnabled(false);
			star5.setEnabled(false);
		}
		
		add(star1);
		add(star2);
		add(star3);
		add(star4);
		add(star5);
	}
	
	private boolean rate(long recipeId, int rateValue) {
		BaseServiceResponse recipe = WSCaller.getFoodService().rateRecipe(FsnSession.getInstance().getUser().getToken(), recipeId, rateValue);
		System.out.println("rateRecipe response code:" + recipe.getResultCode() + " errorCode:" + recipe.getErrorCode());
		if(recipe.getResultCode() == ResultCode.SUCCESS.getCode()) {
			return true;
		} else {
			return false;
		}
	}

	private String getImgURI(Boolean isRated, Integer rating, int index) {
		if(isRated.booleanValue()) {
			if(rating.intValue() == index || rating.intValue()>index) {
				return "/images/star_yellow.gif";
			} else {
				return "/images/star_grey.gif";
			}
		} else {
			return "/images/star_grey.gif";
		}
	}
	
	
	@Override
	protected void onConfigure() {
		super.onConfigure();
		
	}
	
	

    public Map<String, Object> getVars() {
        Map<String, Object> valMap = new HashMap<String, Object>();
        
        Object [] params = new Object []{
        		20
        };
        valMap.put("msg", new StringResourceModel("error.larger.than.allowed.size",
        		this, null, params).getString());
        valMap.put("size", 20);
        return valMap;
    }
	
}
