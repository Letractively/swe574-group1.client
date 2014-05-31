package tr.edu.boun.swe574.fsn.web.wicket.food.createRecipe;

import net.sourceforge.easywicket.EasyWicket;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;

import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import tr.edu.boun.swe574.fsn.web.common.info.RecipeForm;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.event.IngredientRemovedEvent;
import tr.edu.boun.swe574.fsn.web.event.IngredientSelectedEvent;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import tr.edu.boun.swe574.fsn.web.wicket.food.ingredientEntryAdd.CIngredientEntry;
import tr.edu.boun.swe574.fsn.web.wicket.food.ingredientListEntry.CIngredientList;
import tr.edu.boun.swe574.fsn.web.wicket.home.HomePage;

@AuthorizeInstantiation(value = {FsnRoles.USER})
public class CreateRecipe extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2288702422196443554L;

	private final static Logger logger = Logger.getLogger(CreateRecipe.class);

	IngredientForm ingredientInfo;
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

		msisdnRangeEntry.addEventLink(IngredientSelectedEvent.class, this,
				"onMsisdnRangeAdded");

		msisdnRangeList.addEventLink(IngredientRemovedEvent.class, this,
				"onMsisdnRangeRemoved");
		
		btnSend.add(new AttributeModifier("onclick", "disableButton(this)"));

	}

	public boolean isMsisdnAvailable() {
//		return ingredientInfoList.size() > 0;
		return recipeForm.getIngredientFormList().size()>0;
	}

	@SuppressWarnings("unused")
	private void onMsisdnRangeAdded(IngredientSelectedEvent event) {
		if (logger.isInfoEnabled()) {
			logger.info("Food to be added=" + event.getTargetItem());
		}
		System.out.println("Food to be added=" + event.getTargetItem());

		refreshErrorPanel(AjaxRequestTarget.get());

		IngredientForm ingredient = event.getTargetItem();

		if (ingredient.getUnit() == null) {
			error("Unit is empty!");
			return;
		}

		if (ingredient.getFood() == null) {
			error("Food is empty!");
			return;
		}
		
		//check whether the food already added
		recipeForm.getIngredientFormList();
		for (IngredientForm ing : recipeForm.getIngredientFormList()) {
			if (ing.getFood().getId() == ingredient.getFood().getId()) {
				error("This food has already been added to the recipe!");
				return;
			}
		}
		
		recipeForm.getIngredientFormList().add(ingredient);

		event.getRequestTarget().add(msisdnRangeList);

		ingredientInfo = new IngredientForm();

	}

	@SuppressWarnings("unused")
	private void onMsisdnRangeRemoved(IngredientRemovedEvent event) {
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
