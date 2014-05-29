package tr.edu.boun.swe574.fsn.web.wicket.food.ingredientListEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.easywicket.EasyWicket;
import net.sourceforge.easywicket.EasyWicketUtil;
import net.sourceforge.easywicket.IEasyWicket;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.StringResourceModel;

import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;
import tr.edu.boun.swe574.fsn.web.event.IngredientRemovedEvent;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;


public class CIngredientList extends BasePanel implements IEasyWicket {
	

//	private final static Logger logger = LoggerFactory.getLogger(CIngredientList.class);

	
	private static final long serialVersionUID = 1L;
	
	EasyWicketUtil easyWicketUtil;
	
	Loop rangeList;

	private EasyWicket annot;


	public CIngredientList(String id) {
		super(id);
	
		easyWicketUtil = EasyWicketUtil.getInstance();
		
		rangeList = new Loop("rangeList", new PropertyModel<Integer>(this, 
		"listCount")) {
	
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(LoopItem li) {
				List<IngredientForm> currentList = getMsisdnRangeList();
				final IngredientForm current = currentList.get(li.getIndex());
				
				Label lblStart = new Label("amount", String.valueOf(current.getAmount()));
				li.add(lblStart);
				
				Label lblLast = new Label("unit", current.getUnit());
				li.add(lblLast);
				
				Label lblFood = new Label("food", current.getFood().getFoodName());
				li.add(lblFood);
				
				AjaxLink<IngredientForm> lnkRemove = new AjaxLink<IngredientForm>("lnkRemove") {
					
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget ajaxRequestTarget) {
						dispatchEvent(new IngredientRemovedEvent(CIngredientList.this, 
								ajaxRequestTarget, current));
					}
				};
				
				li.add(lnkRemove);
				
			}
		};

		add(rangeList);		
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
