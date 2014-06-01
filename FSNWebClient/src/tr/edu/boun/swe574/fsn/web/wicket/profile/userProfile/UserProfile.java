package tr.edu.boun.swe574.fsn.web.wicket.profile.userProfile;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.DynamicImageResource;

import tr.edu.boun.swe574.fsn.web.common.DateUtil;
import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.info.FoodGroup;
import tr.edu.boun.swe574.fsn.web.common.info.FoodForm;
import tr.edu.boun.swe574.fsn.web.common.util.Validator;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import edu.boun.swe574.fsn.common.client.network.BaseServiceResponse;
import edu.boun.swe574.fsn.common.client.network.FoodInfo;
import edu.boun.swe574.fsn.common.client.network.GetProfileResponse;

@AuthorizeInstantiation(value = {FsnRoles.USER})
public class UserProfile extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7990510282187042131L;

	private static Logger logger = Logger.getLogger(UserProfile.class);

	private final  List<FoodGroup> blackListCategorized;
	

	public UserProfile(final String email) {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Getting profile of the user with the email:" + email);
		}
		
		final GetProfileResponse profile = WSCaller.getNetworkService().getProfileOfOtherUser(FsnSession.getInstance().getUser().getToken(), email);
		List<FoodForm> blackList = convertToIngList(profile.getIngredientBlackList());
		blackListCategorized = FoodGroup.categorize(blackList);
		
		final boolean followed = profile.isFollowed();
		
		Label lblName = new Label("lblName", profile.getName() + " " + profile.getSurname());
		Label lblMessage = new Label("lblMessage", profile.getProfileMessage());
		Label lblLocation = new Label("lblLocation", profile.getLocation());
		
		AjaxLink<Object> lnkFollow = new AjaxLink<Object>("lnkFollow") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 6065483241723906271L;

			@Override
			public void onClick(AjaxRequestTarget arg0) {
				BaseServiceResponse response = WSCaller.getNetworkService().follow(FsnSession.getInstance().getUser().getToken(), email);
				
				System.out.println("follow service resultCode:" + response.getResultCode() + " errorCode:" + response.getErrorCode());
				
				setResponsePage(new UserProfile(email));
			}
		};
		add(lnkFollow);
		
		Label lblFollow = new Label("lblFollow", followed ? "Following":"Follow");
		lnkFollow.add(lblFollow);
		
		if(followed) {
			lnkFollow.setEnabled(false);
		}
		
		if(FsnSession.getInstance().getUser().getEmail().trim().toUpperCase().equalsIgnoreCase(email.trim())) {
			lnkFollow.setVisible(false);
		}
		
		XMLGregorianCalendar dateOfBirth = profile.getDateOfBirth();
		
		String dateString = "";
		if(dateOfBirth != null) {
			dateString = DateUtil.getDateString(dateOfBirth.toGregorianCalendar().getTime());
		}
		
		Label lblBirthDate = new Label("lblBirthDate", dateString);
		
		DynamicImageResource ds = new DynamicImageResource() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8788521037622651869L;

			@Override
			protected byte[] getImageData(Attributes arg0) {
				return profile.getImage();
			}
		};
		
		if(profile.getImage() != null) {
			Image img = new Image("search_icon", ds);
			add(img);
		} else {
			add(new Image("search_icon", new ContextRelativeResource("/images/icon-user-default.png")));
		}
		
		
		//blacklist loop
		Loop loop = new Loop("blackList", blackListCategorized.size()) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(LoopItem item) {
				FoodGroup group = blackListCategorized.get(item.getIndex());
				
				item.add(new Label("category", group.getCategoryName()));
				
				final List<FoodForm> ingredientList = group.getIngredientList();
				
				Loop loopIng = new Loop("ingredientsLoop", ingredientList.size()) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 9054877562387000796L;

					@Override
					protected void populateItem(LoopItem item2) {
						final FoodForm ingredientInfo = ingredientList.get(item2.getIndex());
						
				        item2.add(new Label("ingredient", ingredientInfo.getFoodName()));
					}
					
				};
				
				item.add(loopIng);
			}
			
		};
		
		add(loop);
		add(lblLocation);
		add(lblMessage);
		add(lblBirthDate);
		add(lblName);
	}
	
	private List<FoodForm> convertToIngList(List<FoodInfo> ingredientBlackList) {
		List<FoodForm> list = new ArrayList<FoodForm>();
		FoodForm info;
		
		if(ingredientBlackList != null) {
			for (FoodInfo iInfo : ingredientBlackList) {
				info = new FoodForm();
				info.setCategoryName(Validator.fixNullCategory(iInfo.getCategoryName()));
				info.setId(iInfo.getFoodId());
				info.setFoodName(iInfo.getFoodName());
				list.add(info);
			}
		}
		return list;
	}

}
