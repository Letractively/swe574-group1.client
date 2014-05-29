package tr.edu.boun.swe574.fsn.web.wicket.food.ingredientEntry;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.easywicket.EasyWicket;
import net.sourceforge.easywicket.EasyWicketUtil;
import net.sourceforge.easywicket.IEasyWicket;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.edu.boun.swe574.fsn.web.common.info.FoodForm;
import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import tr.edu.boun.swe574.fsn.web.common.util.Validator;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.event.IngredientSelectedEvent;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;
import edu.boun.swe574.fsn.common.client.food.GetIngredientsResponse;


public class CIngredientEntry extends BasePanel implements IEasyWicket {

	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = LoggerFactory.getLogger(CIngredientEntry.class);
	
	IngredientForm ingredientForm;
	
	@EasyWicket(id="form")
	Form<Void> form;
	
	@EasyWicket(id="form.tariffType", value="ingredientForm.food", 
			list="tariffTypeList", idProperty="id", displayProperty="foodName")
	DropDownChoice<FoodForm> tariffType;
	
    @EasyWicket(id = "form.amount", value = "ingredientForm.amount")
    TextField<String>     amount;
    
    @EasyWicket(id = "form.unit", value = "ingredientForm.unit")
    TextField<String>     unit;
    
	
	@EasyWicket(id="form.btnAdd", action="actionAdd")
	AjaxSubmitLink btnAdd;
	
	private boolean msisdnAddDisabled = false;
	
	EasyWicketUtil easyWicketUtil;

	
	public CIngredientEntry(String id) {
		super(id);
		
		easyWicketUtil = EasyWicketUtil.getInstance();
		ingredientForm = new IngredientForm();
		
		setOutputMarkupId(true);
	}
	
	@Override
	public void pack() {		
		tariffType.setNullValid(true);
		tariffType.setLabel(new Model<String>("Food"));
		
	}
	
	@Override
	protected void onConfigure() {
		super.onConfigure();
		
		btnAdd.setEnabled(!msisdnAddDisabled);
		tariffType.setEnabled(!msisdnAddDisabled);
	}
	

	@Override
	public void initComponent(EasyWicket annot, MarkupContainer component) {
		easyWicketUtil.initComponentWithAnnot(this, annot);
	}
	
	public List<FoodForm> getTariffTypeList() {
		GetIngredientsResponse ingredients = WSCaller.getFoodService().getIngredients(FsnSession.getInstance().getUser().getToken(), "S");
		List<FoodForm> listOfIngredients = Validator.convertToFoodFormList(ingredients.getListOfIngredients());
		
		
		if ( logger.isInfoEnabled() ) {
			logger.info("Got foods list. ResultCode=" + ingredients.getResultCode() + " Number of foods:" + (listOfIngredients == null ? 0 : listOfIngredients.size()));
		}
		
		return listOfIngredients != null ? listOfIngredients : new ArrayList<FoodForm>();
	}
	
	private FoodForm getDefaultTariffType() {
		List<FoodForm> ttList = getTariffTypeList();
		
		if  ( ttList.size() > 0 ) {
			return ttList.get(0);
		}else {
			return null;
		}
	}
	
	
	public void actionAdd() {
		if ( logger.isInfoEnabled() ) {
			logger.info("Add button pressed");
		}

		if(ingredientForm.getFood() == null){
			
			error("Food must be selected");
			
			return;
		}
		
		dispatchEvent(new IngredientSelectedEvent(this, AjaxRequestTarget.get(),
				ingredientForm));
		
		
		ingredientForm = new IngredientForm();
		

		AjaxRequestTarget ajaxRequestTarget = AjaxRequestTarget.get();
		if ( ajaxRequestTarget != null ) {
			ajaxRequestTarget.add(this);
		}
		
	}


	public void setMsisdnAddDisabled(boolean disabled) {
		msisdnAddDisabled = disabled;
	}
	
	@SuppressWarnings("unused")
	private IngredientForm getMsisdnRange() {
		return (IngredientForm) getDefaultModelObject();
	}

}