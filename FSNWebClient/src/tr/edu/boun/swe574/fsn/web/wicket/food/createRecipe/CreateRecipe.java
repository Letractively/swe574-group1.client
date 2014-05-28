package tr.edu.boun.swe574.fsn.web.wicket.food.createRecipe;

import net.sourceforge.easywicket.EasyWicket;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;

import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import tr.edu.boun.swe574.fsn.web.common.info.RecipeForm;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.event.MsisdnRangeRemovedEvent;
import tr.edu.boun.swe574.fsn.web.event.MsisdnRangeSelectedEvent;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import tr.edu.boun.swe574.fsn.web.wicket.food.ingredientEntry.CIngredientEntry;
import tr.edu.boun.swe574.fsn.web.wicket.food.ingredientList.CIngredientList;
import tr.edu.boun.swe574.fsn.web.wicket.home.HomePage;

public class CreateRecipe extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2288702422196443554L;

	private final static Logger logger = Logger.getLogger(CreateRecipe.class);

	IngredientForm ingredientInfo;
//	List<IngredientForm> ingredientInfoList;
	String directionsStr;
	RecipeForm recipeForm;

	@EasyWicket(id = "form")
	Form<Void> form;

	@EasyWicket(id = "form.title", value = "recipeForm.title", required = true)
	TextField<String> idNo;

	@EasyWicket(id = "form.msisdnRangeEntry", value = "ingredientInfo")
	CIngredientEntry msisdnRangeEntry;

	@EasyWicket(id = "form.msisdnRangeList", value = "recipeForm.ingredientFormList", visible = "msisdnAvailable")
	CIngredientList msisdnRangeList;

	@EasyWicket(id = "form.directions", value = "recipeForm.directions", required = true)
	TextArea<String> profileMessage;

	@EasyWicket(id = "form.btnSend", action = "actionSend")
	Button btnSend;

	public CreateRecipe() {
		ingredientInfo = new IngredientForm();

//		ingredientInfoList = new ArrayList<IngredientForm>();
		recipeForm = new RecipeForm();
	}

	@Override
	public void pack() {
		super.pack();

		msisdnRangeEntry.addEventLink(MsisdnRangeSelectedEvent.class, this,
				"onMsisdnRangeAdded");

		msisdnRangeList.addEventLink(MsisdnRangeRemovedEvent.class, this,
				"onMsisdnRangeRemoved");
		
		btnSend.add(new AttributeModifier("onclick", "disableButton(this)"));

	}

	public boolean isMsisdnAvailable() {
//		return ingredientInfoList.size() > 0;
		return recipeForm.getIngredientFormList().size()>0;
	}

	@SuppressWarnings("unused")
	private void onMsisdnRangeAdded(MsisdnRangeSelectedEvent event) {
		if (logger.isInfoEnabled()) {
			logger.info("Food to be added=" + event.getTargetItem());
		}

		refreshErrorPanel(AjaxRequestTarget.get());

		IngredientForm range = event.getTargetItem();

		if (range.getUnit() == null) {
			error("Unit is empty!");
			return;
		}

		if (range.getFood() == null) {
			error("Food is empty!");
			return;
		}

//		ingredientInfoList.add(range);
		recipeForm.getIngredientFormList().add(range);

		event.getRequestTarget().add(msisdnRangeList);

		ingredientInfo = new IngredientForm();

	}

	@SuppressWarnings("unused")
	private void onMsisdnRangeRemoved(MsisdnRangeRemovedEvent event) {
		if (logger.isInfoEnabled()) {
			logger.info("Ingredient to be removed=" + event.getTargetItem());
		}

		IngredientForm range = event.getTargetItem();

//		ingredientInfoList.remove(range);
		recipeForm.getIngredientFormList().remove(range);

		event.getRequestTarget().add(msisdnRangeList);
	}
	
	 public void actionSend() {
		 //TODO validation
		 WSCaller.createRecipe(FsnSession.getInstance().getUser(), recipeForm);
		 setResponsePage(HomePage.class);
	 }

}
