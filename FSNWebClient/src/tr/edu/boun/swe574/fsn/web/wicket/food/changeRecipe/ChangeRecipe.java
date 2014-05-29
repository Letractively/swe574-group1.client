package tr.edu.boun.swe574.fsn.web.wicket.food.changeRecipe;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.easywicket.EasyWicket;

import org.apache.log4j.Logger;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;

import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import tr.edu.boun.swe574.fsn.web.common.info.RecipeForm;
import tr.edu.boun.swe574.fsn.web.common.info.WebUser;
import tr.edu.boun.swe574.fsn.web.common.util.Validator;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.event.IngredientRemovedEvent;
import tr.edu.boun.swe574.fsn.web.event.IngredientSelectedEvent;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import tr.edu.boun.swe574.fsn.web.wicket.food.ingredientEditListEntry.CIngredientEditList;
import tr.edu.boun.swe574.fsn.web.wicket.food.ingredientEntryAdd.CIngredientEntry;
import tr.edu.boun.swe574.fsn.web.wicket.food.ingredientListEntry.CIngredientList;
import tr.edu.boun.swe574.fsn.web.wicket.food.viewRecipe.ViewRecipe;
import tr.edu.boun.swe574.fsn.web.wicket.home.HomePage;
import edu.boun.swe574.fsn.common.client.food.BaseServiceResponse;
import edu.boun.swe574.fsn.common.client.network.RecipeInfo;

@AuthorizeInstantiation(value = {FsnRoles.USER})
public class ChangeRecipe extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2288702422196443554L;

	private final static Logger logger = Logger.getLogger(ChangeRecipe.class);

	IngredientForm ingredientInfo;
	String directionsStr;
	RecipeForm recipeForm;
	
	List<IngredientForm> ingredientListForNewIngredients;
	
	private final RecipeInfo recipeInfo;

	@EasyWicket(id = "form")
	Form<Void> form;

	@EasyWicket(id = "form.title", value = "recipeForm.title")
	Label title;

	@EasyWicket(id = "form.msisdnRangeEntry", value = "ingredientInfo")
	CIngredientEntry msisdnRangeEntry;

	@EasyWicket(id = "form.msisdnRangeEditList", value = "recipeForm.ingredientFormList", visible = "msisdnAvailable")
	CIngredientEditList msisdnRangeEditList;
	
	@EasyWicket(id = "form.msisdnRangeList", value = "ingredientListForNewIngredients", visible = "newIngredientsAvailable")
	CIngredientList msisdnRangeList;

	@EasyWicket(id = "form.directions", value = "recipeForm.directions", required = true)
	TextArea<String> profileMessage;

	@EasyWicket(id = "form.btnSend", action = "actionSend")
	Button btnSend;
	
	@EasyWicket(id = "form.lnkViewRecipe" , action = "onClick")
	AjaxLink<Object> lnkViewRecipe;
	
	@EasyWicket(id = "form.lnkViewHistory" , action = "onViewHistoryClick")
	AjaxLink<Object> lnkViewHistory;
	
	@EasyWicket(id = "form.revisionNote", value = "recipeForm.revisionNote", required = true)
	TextField<String> revisionNote;

	public ChangeRecipe(final RecipeInfo recipeInfo) {
		ingredientInfo = new IngredientForm();
		
		ingredientListForNewIngredients = new ArrayList<IngredientForm>();

		recipeForm = new RecipeForm();
		recipeForm.setDirections(recipeInfo.getDirections());
		recipeForm.setTitle(recipeInfo.getRecipeName());
		recipeForm.setIngredientFormList(Validator.convertToIngredientFormArr(recipeInfo.getIngredientList()));
		
		this.recipeInfo = recipeInfo;
	}

	@Override
	public void pack() {
		super.pack();

		msisdnRangeEntry.addEventLink(IngredientSelectedEvent.class, this,
				"onMsisdnRangeAdded");

		msisdnRangeEditList.addEventLink(IngredientRemovedEvent.class, this,
				"onMsisdnRangeRemoved");
		
		msisdnRangeList.addEventLink(IngredientRemovedEvent.class, this,
				"onNewIngredientRemoved");
		
		btnSend.add(new AttributeModifier("onclick", "disableButton(this)"));
	}
	
	public void onClick() {
		setResponsePage(new ViewRecipe(recipeInfo));
    }
	
	public void onViewHistoryClick() {
		//TODO set response page
//		setResponsePage(new ViewRecipe(recipeInfo));
    }

	public boolean isMsisdnAvailable() {
		return recipeForm.getIngredientFormList().size()>0;
	}
	
	public boolean isNewIngredientsAvailable() {
		return ingredientListForNewIngredients.size()>0;
	}

	@SuppressWarnings("unused")
	private void onMsisdnRangeAdded(IngredientSelectedEvent event) {
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

		ingredientListForNewIngredients.add(range);
//		recipeForm.getIngredientFormList().add(range);

		event.getRequestTarget().add(msisdnRangeList);

		ingredientInfo = new IngredientForm();

	}

	@SuppressWarnings("unused")
	private void onMsisdnRangeRemoved(IngredientRemovedEvent event) {
		if (logger.isInfoEnabled()) {
			logger.info("Ingredient to be removed=" + event.getTargetItem());
		}

		IngredientForm range = event.getTargetItem();

		recipeForm.getIngredientFormList().remove(range);

		event.getRequestTarget().add(msisdnRangeEditList);
	}
	
	@SuppressWarnings("unused")
	private void onNewIngredientRemoved(IngredientRemovedEvent event) {
		if (logger.isInfoEnabled()) {
			logger.info("Ingredient to be removed=" + event.getTargetItem());
		}

		IngredientForm ingredient = event.getTargetItem();
		ingredientListForNewIngredients.remove(ingredient);
		event.getRequestTarget().add(msisdnRangeList);
	}
	
	
	 public void actionSend() {
		 //TODO validation
		 
		 System.out.println("--recipe form is----");
		 
		 List<IngredientForm> ingredientFormList = recipeForm.getIngredientFormList();
		 for (IngredientForm ingredientForm : ingredientFormList) {
			System.out.println("--Ingredient amount:" + ingredientForm.getAmount() + " unit:" +ingredientForm.getUnit() + " foodId:" + ingredientForm.getFood().getId());
		}
		 System.out.println("-- and also:");
		 for (IngredientForm ingredientForm : ingredientListForNewIngredients) {
			 System.out.println("--Ingredient amount:" + ingredientForm.getAmount() + " unit:" +ingredientForm.getUnit() + " foodId:" + ingredientForm.getFood().getId());
		 }
		 
		 BaseServiceResponse response = WSCaller.createNewVersionOfRecipe(FsnSession.getInstance().getUser(), recipeForm, recipeInfo.getRecipeId());
		 
		 
		 
		 setResponsePage(HomePage.class);
	 }

}
