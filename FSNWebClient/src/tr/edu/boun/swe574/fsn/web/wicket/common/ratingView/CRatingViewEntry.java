package tr.edu.boun.swe574.fsn.web.wicket.common.ratingView;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.resource.ContextRelativeResource;

import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;

public class CRatingViewEntry extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8856240903922193372L;


	public CRatingViewEntry(String id, Integer rating) {
		super(id);
		
		boolean isRated = rating < 1 ? false : true;

		NonCachingImage img1 = new NonCachingImage("img1", new ContextRelativeResource(getImgURI(isRated, rating, 1)));
		add(img1);

		NonCachingImage img2 = new NonCachingImage("img2", new ContextRelativeResource(getImgURI(isRated, rating, 2)));
		add(img2);

		NonCachingImage img3 = new NonCachingImage("img3", new ContextRelativeResource(getImgURI(isRated, rating, 3)));
		add(img3);

		NonCachingImage img4 = new NonCachingImage("img4", new ContextRelativeResource(getImgURI(isRated, rating, 4)));
		add(img4);

		NonCachingImage img5 = new NonCachingImage("img5", new ContextRelativeResource(getImgURI(isRated, rating, 5)));
		add(img5);

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
