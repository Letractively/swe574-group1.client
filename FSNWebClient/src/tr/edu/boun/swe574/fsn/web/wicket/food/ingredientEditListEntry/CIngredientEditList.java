package tr.edu.boun.swe574.fsn.web.wicket.food.ingredientEditListEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.easywicket.EasyWicket;
import net.sourceforge.easywicket.EasyWicketUtil;
import net.sourceforge.easywicket.IEasyWicket;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

import tr.edu.boun.swe574.fsn.web.common.info.FoodForm;
import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import tr.edu.boun.swe574.fsn.web.common.util.Validator;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.event.IngredientRemovedEvent;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;
import edu.boun.swe574.fsn.common.client.food.GetIngredientsResponse;


public class CIngredientEditList extends BasePanel implements IEasyWicket {
	

	private final static Logger logger = Logger.getLogger(CIngredientEditList.class);

	
	private static final long serialVersionUID = 1L;
	
	EasyWicketUtil easyWicketUtil;
	
	Loop rangeList;

	private EasyWicket annot;
	
    TextField<String>     amount;
    TextField<String>     unit;
    DropDownChoice<FoodForm> tariffType;
    
    List<FoodForm> listOfFoods;
    
	public CIngredientEditList(String id) {
		super(id);
		
		easyWicketUtil = EasyWicketUtil.getInstance();
		
		setOutputMarkupId(true);
		
		listOfFoods = getFoodList();
		
		rangeList = new Loop("rangeList", new PropertyModel<Integer>(this, 
		"listCount")) {
	
			private static final long serialVersionUID = 1L;
			

			@Override
			protected void populateItem(LoopItem li) {
				List<IngredientForm> currentList = getMsisdnRangeList();
				final IngredientForm current = currentList.get(li.getIndex());
				
				amount = new TextField("amount", new PropertyModel(current, "amount"));
				li.add(amount);
				
				unit = new TextField("unit", new PropertyModel(current, "unit"));
				li.add(unit);
				unit.setOutputMarkupId(true);
				
				DropDownChoice<FoodForm> tariffType = new DropDownChoice<FoodForm>("tariffType",
                        new Model<FoodForm>(current.getFood()),
                        listOfFoods,
                        new ChoiceRenderer<FoodForm>("foodName",
                                                       "id"));
				
				li.add(tariffType);

				
				AjaxLink<IngredientForm> lnkRemove = new AjaxLink<IngredientForm>("lnkRemove") {
					
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget ajaxRequestTarget) {
						dispatchEvent(new IngredientRemovedEvent(CIngredientEditList.this, 
								ajaxRequestTarget, current));
					}
				};
				
				li.add(lnkRemove);
				
			}
			

		};
		
		add(rangeList);		
	}
	
	public List<FoodForm> getFoodList() {
		GetIngredientsResponse ingredients = WSCaller.getFoodService().getIngredients(FsnSession.getInstance().getUser().getToken(), "S");
		List<FoodForm> listOfIngredients = Validator.convertToFoodFormList(ingredients.getListOfIngredients());
		
		
		if ( logger.isInfoEnabled() ) {
			logger.info("Got foods list. ResultCode=" + ingredients.getResultCode() + " Number of foods:" + (listOfIngredients == null ? 0 : listOfIngredients.size()));
		}
		
		return listOfIngredients != null ? listOfIngredients : new ArrayList<FoodForm>();
	}

	@Override
	public void initComponent(EasyWicket annot, MarkupContainer parentWidget) {
		easyWicketUtil.initComponentWithAnnot(this, annot);
		
		this.annot = annot;
	}
	
	@Override
	protected void onConfigure() {
		super.onConfigure();
		
		easyWicketUtil.configureComponent(this, annot.visible(), null);
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
	
	
	
	public List<IngredientForm> getMsisdnRangeList() {
		return (List<IngredientForm>) getDefaultModelObject();
	}
	
	public int getListCount() {
		return getMsisdnRangeList().size();
	}


}
