package tr.edu.boun.swe574.fsn.web.wicket.profile.myProfile;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow.WindowClosedCallback;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.request.resource.ContextRelativeResource;
import org.apache.wicket.request.resource.DynamicImageResource;

import tr.edu.boun.swe574.fsn.web.common.DateUtil;
import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.info.FoodForm;
import tr.edu.boun.swe574.fsn.web.common.info.FoodGroup;
import tr.edu.boun.swe574.fsn.web.common.info.UserInfoForm;
import tr.edu.boun.swe574.fsn.web.common.util.Validator;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import tr.edu.boun.swe574.fsn.web.wicket.profile.addToBlacklist.AddToBL;
import tr.edu.boun.swe574.fsn.web.wicket.profile.deleteFromBlacklist.DeleteBlackList;
import tr.edu.boun.swe574.fsn.web.wicket.profile.deletePhoto.DeletePhoto;
import tr.edu.boun.swe574.fsn.web.wicket.profile.editProfile.EditProfile;
import tr.edu.boun.swe574.fsn.web.wicket.profile.updatePhoto.UpdatePhoto;
import edu.boun.swe574.fsn.common.client.network.FoodInfo;
import edu.boun.swe574.fsn.common.client.network.GetProfileResponse;

@AuthorizeInstantiation(value = {FsnRoles.USER})
public class MyProfile extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2524020622548319320L;
//	private static Logger logger = Logger.getLogger(MyProfile.class);
	
	private ModalWindow mwUpdateProfile = null;
	private ModalWindow mwDeleteFromBL = null;
	private ModalWindow mwDeletePhoto = null;
	private ModalWindow mwUpdatePhoto = null;
	private ModalWindow mwAddToBL = null;
	private AjaxLink<Object> lnUpdateProfile;
	private AjaxLink<Object> lnkAddToBL;
	private AjaxLink<Object> lnkDeletePhoto;
	private AjaxLink<Object> lnkUpdatePhoto;
	private final  List<FoodGroup> blackListCategorized;
	

	public MyProfile() {
		
		final GetProfileResponse profile = WSCaller.getNetworkService().getProfileOfSelf(FsnSession.getInstance().getUser().getToken());
		
		System.out.println("getProfileOfSelf service result code:" + profile.getResultCode() + " errorCode:" + profile.getErrorCode() + " location:" + profile.getLocation() + " image:" + profile.getImage());
		
		List<FoodForm> blackList = convertToIngList(profile.getIngredientBlackList());
		blackListCategorized = FoodGroup.categorize(blackList);
		
		Label lblMessage = new Label("lblMessage", profile.getProfileMessage());
		Label lblLocation = new Label("lblLocation", profile.getLocation());
		
		
		XMLGregorianCalendar dateOfBirth = profile.getDateOfBirth();
		
		String dateString = "";
		if(dateOfBirth != null) {
			dateString = DateUtil.getDateString(dateOfBirth.toGregorianCalendar().getTime());
		}
		
		Label lblBirthDate = new Label("lblBirthDate", dateString);
		
		Label lblName = new Label("lblName", profile.getName() + " " + profile.getSurname());
		add(lblName);
		
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
			NonCachingImage img = new NonCachingImage("search_icon", ds);
			add(img);
		} else {
			add(new NonCachingImage("search_icon", new ContextRelativeResource("/images/icon-user-default.png")));
		}
		
		mwUpdateProfile = new ModalWindow("mwUpdateProfile");
		mwUpdateProfile.setCookieName(null);
		mwUpdateProfile.setResizable(false);
		mwUpdateProfile.setInitialWidth(600);
		mwUpdateProfile.setInitialHeight(350);
		mwUpdateProfile.setCssClassName(ModalWindow.CSS_CLASS_GRAY);

        add(mwUpdateProfile);
        
        mwUpdateProfile.setWindowClosedCallback(new WindowClosedCallback() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -4640230189156707328L;

			@Override
            public void onClose(AjaxRequestTarget arg0) {
                setResponsePage(MyProfile.class);
            }

        });
        
		
		mwDeleteFromBL = new ModalWindow("mwDeleteFromBL");
		mwDeleteFromBL.setCookieName(null);
		mwDeleteFromBL.setResizable(false);
		mwDeleteFromBL.setInitialWidth(500);
		mwDeleteFromBL.setInitialHeight(200);
		mwDeleteFromBL.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
		
		add(mwDeleteFromBL);
        
		mwDeleteFromBL.setWindowClosedCallback(new WindowClosedCallback() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -4640230189156707328L;

			@Override
            public void onClose(AjaxRequestTarget arg0) {
                setResponsePage(MyProfile.class);
            }

        });
		
		mwDeletePhoto= new ModalWindow("mwDeletePhoto");
		mwDeletePhoto.setCookieName(null);
		mwDeletePhoto.setResizable(false);
		mwDeletePhoto.setInitialWidth(500);
		mwDeletePhoto.setInitialHeight(200);
		mwDeletePhoto.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
		
		add(mwDeletePhoto);
        
		mwDeletePhoto.setWindowClosedCallback(new WindowClosedCallback() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4128097268645131388L;

			@Override
            public void onClose(AjaxRequestTarget arg0) {
                setResponsePage(MyProfile.class);
            }

        });
		
		mwUpdatePhoto= new ModalWindow("mwUpdatePhoto");
		mwUpdatePhoto.setCookieName(null);
		mwUpdatePhoto.setResizable(false);
		mwUpdatePhoto.setInitialWidth(500);
		mwUpdatePhoto.setInitialHeight(200);
		mwUpdatePhoto.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
		
		add(mwUpdatePhoto);
        
		mwUpdatePhoto.setWindowClosedCallback(new WindowClosedCallback() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2831485330879574995L;

			@Override
            public void onClose(AjaxRequestTarget arg0) {
                setResponsePage(MyProfile.class);
            }

        });
		
		mwAddToBL= new ModalWindow("mwAddToBL");
		mwAddToBL.setCookieName(null);
		mwAddToBL.setResizable(false);
		mwAddToBL.setInitialWidth(600);
		mwAddToBL.setInitialHeight(300);
		mwAddToBL.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
		
		add(mwAddToBL);
        
		mwAddToBL.setWindowClosedCallback(new WindowClosedCallback() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2831485330879574995L;

			@Override
            public void onClose(AjaxRequestTarget arg0) {
                setResponsePage(MyProfile.class);
            }

        });
        
        
        lnUpdateProfile = new AjaxLink<Object>("lnUpdateProfile") {

            /**
			 * 
			 */
			private static final long serialVersionUID = 5838154829422314429L;

			@Override
            public void onClick(AjaxRequestTarget arg0) {
				UserInfoForm userInfo = convertToUserForm(profile);
            	EditProfile updateProfileWindow = new EditProfile(mwUpdateProfile.getContentId(), mwUpdateProfile, userInfo);
            	mwUpdateProfile.setContent(updateProfileWindow);
            	mwUpdateProfile.show(arg0);
            }
        };
        
		add(lnUpdateProfile);
		
        lnkAddToBL = new AjaxLink<Object>("lnkAddToBL") {

            /**
			 * 
			 */
			private static final long serialVersionUID = -8817979564953506859L;

			@Override
            public void onClick(AjaxRequestTarget arg0) {
            	AddToBL addToBLWindow = new AddToBL(mwAddToBL.getContentId(), mwAddToBL);
            	mwAddToBL.setContent(addToBLWindow);
            	mwAddToBL.show(arg0);
            }
        };
		add(lnkAddToBL);
		
        lnkDeletePhoto = new AjaxLink<Object>("lnkDeletePhoto") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2205050086599680915L;

			@Override
            public void onClick(AjaxRequestTarget arg0) {
				DeletePhoto deletePhotoWindow = new DeletePhoto(mwDeletePhoto.getContentId(), mwDeletePhoto);
				mwDeletePhoto.setContent(deletePhotoWindow);
				mwDeletePhoto.show(arg0);
            }
        };
		add(lnkDeletePhoto);
		
        lnkUpdatePhoto = new AjaxLink<Object>("lnkUpdatePhoto") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6750224937247896572L;

			@Override
            public void onClick(AjaxRequestTarget arg0) {
				UpdatePhoto updatePhotoWindow = new UpdatePhoto(mwUpdatePhoto.getContentId(), mwUpdatePhoto);
				mwUpdatePhoto.setContent(updatePhotoWindow);
				mwUpdatePhoto.show(arg0);
            }
        };
		add(lnkUpdatePhoto);
		
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
						
						AjaxLink<Object> lnDeleteBL = new AjaxLink<Object>("lnDeleteBL") {

				            /**
							 * 
							 */
							private static final long serialVersionUID = 5838154829422314429L;

							@Override
				            public void onClick(AjaxRequestTarget arg0) {
				            	DeleteBlackList deleteFromBLPanel = new DeleteBlackList(mwDeleteFromBL.getContentId(), mwDeleteFromBL, ingredientInfo);
				            	mwDeleteFromBL.setContent(deleteFromBLPanel);
				            	mwDeleteFromBL.show(arg0);
				            }
				        };
				        
				        lnDeleteBL.add(new Label("ingredient", ingredientInfo.getFoodName()));
						item2.add(lnDeleteBL);
					}
					
				};
				
				item.add(loopIng);
			}
			
		};
		
		add(loop);
		add(lblLocation);
		add(lblMessage);
		add(lblBirthDate);
	}
	
	private UserInfoForm convertToUserForm(GetProfileResponse profile) {
		UserInfoForm form = new UserInfoForm();
		if(profile.getDateOfBirth() != null) {
			form.setBirthdate(profile.getDateOfBirth().toGregorianCalendar().getTime());
		}
		form.setLocation(profile.getLocation());
		form.setProfileMessage(profile.getProfileMessage());
		
		return form;
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
