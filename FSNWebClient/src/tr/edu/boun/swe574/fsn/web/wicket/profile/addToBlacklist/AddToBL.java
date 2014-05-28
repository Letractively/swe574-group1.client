package tr.edu.boun.swe574.fsn.web.wicket.profile.addToBlacklist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.BookmarkablePageRequestHandler;
import org.apache.wicket.request.handler.PageProvider;
import org.apache.wicket.util.string.Strings;

import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;
import tr.edu.boun.swe574.fsn.web.wicket.home.HomePage;
import edu.boun.swe574.fsn.common.client.food.FoodInfo;
import edu.boun.swe574.fsn.common.client.food.GetIngredientsResponse;


public class AddToBL extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5680758832419784439L;
	
	private static Logger logger = Logger.getLogger(AddToBL.class);

	private Form<Void> form;
	
	private AjaxSubmitLink lnkDelete;
	private AjaxSubmitLink lnkCancel;
	private FeedbackPanel feedbackPanel = new FeedbackPanel("feedBackPanel");
	
	private final List<FoodInfo> ingredients = new ArrayList<FoodInfo>();
	private final HashMap<String, FoodInfo> ingredientsHash = new HashMap<String, FoodInfo>();
	private final List<FoodInfo> ingredientsSelected = new ArrayList<FoodInfo>();
	
	public AddToBL(String id, final ModalWindow mwDeleteFromBL) { 
		super(id);
		
		//get all ingredients from foods service
		GetIngredientsResponse ingredientsResponse = WSCaller.getFoodService().getIngredients(FsnSession.getInstance().getUser().getToken(), "");
		
		if(ingredientsResponse.getListOfIngredients() != null && !ingredientsResponse.getListOfIngredients().isEmpty()) {
			ingredients.addAll(ingredientsResponse.getListOfIngredients());
			//put to hash
			for (FoodInfo info : ingredients) {
				ingredientsHash.put(info.getFoodName(), info);
			}
		}
		
		form = new Form<Void>("form");
		

		
		final AutoCompleteTextField<String> field = new AutoCompleteTextField<String>("ac",
	            new Model<String>(""))
	        {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 944804902516000771L;

				@Override
	            protected Iterator<String> getChoices(String input)
	            {
	                if (Strings.isEmpty(input))
	                {
	                    List<String> emptyList = Collections.emptyList();
	                    return emptyList.iterator();
	                }

	                List<String> choices = new ArrayList<String>(10);

	                for (final FoodInfo locale : ingredients)
	                {
	                    final String country = locale.getFoodName();

	                    if (country.toUpperCase().startsWith(input.toUpperCase()))
	                    {
	                        choices.add(country);
	                        if (choices.size() == 10)
	                        {
	                            break;
	                        }
	                    }
	                }

	                return choices.iterator();
	            }
	        };
	     form.add(field);
	     
	    final Label label = new Label("selectedValue", field.getDefaultModel());
	    label.setOutputMarkupId(true);
	    form.add(label);
	     
	    field.add(new AjaxFormSubmitBehavior(form, "onchange") {
	            /**
				 * 
				 */
				private static final long serialVersionUID = -3388144940793863601L;

				@Override
	            protected void onSubmit(AjaxRequestTarget target) {
	                BookmarkablePageRequestHandler bookmarkablePageRequestHandler = new BookmarkablePageRequestHandler(
	                        new PageProvider(HomePage.class));
	                
	                    RequestCycle requestCycle = RequestCycle.get();
	                    CharSequence urlFor = requestCycle.urlFor(bookmarkablePageRequestHandler);
	                    String selectedIng = field.getModelObject();
	                    
	                    FoodInfo ingredientInfo = ingredientsHash.get(selectedIng);
	                    if(ingredientInfo != null)  {
	                    	if(!ingredientsSelected.contains(ingredientInfo)) {
	                    		ingredientsSelected.add(ingredientInfo);
//	                    		System.out.println("---------------ingredient added:" + selectedIng + " " + ingredientsSelected.size());
	                    	}
	                    }
	                    field.setDefaultModelObject(getIngredientString());
	                    target.add(label);
	            }

	            @Override
	            protected void onError(AjaxRequestTarget target) {
	            }
	   });
	     
	     
		
		
		
		
		
		

		lnkDelete = new AjaxSubmitLink("lnkDelete") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4091528668903964731L;
			
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
            	//Call delete service
            	
            	mwDeleteFromBL.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                if (logger.isInfoEnabled()) {
                    logger.info("An error has been occurred on AddToBL delete button");
                }
            }
		};
		
		lnkCancel = new AjaxSubmitLink("lnkCancel") {
			
            /**
			 * 
			 */
			private static final long serialVersionUID = 3908411552647518543L;

			@Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				mwDeleteFromBL.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                if (logger.isInfoEnabled()) {
                    logger.info("An error has been occurred on AddToBL cancel button");
                }
            }
		};
		
		form.add(lnkDelete);
		form.add(lnkCancel);
		add(form);
		add(feedbackPanel);

	}
	
	private String getIngredientString() {
		StringBuilder sb = new StringBuilder();
		for (FoodInfo info : ingredientsSelected) {
			sb.append(info.getFoodName()).append(",");
		}
		return sb.toString();
	}
	
}
