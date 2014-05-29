package tr.edu.boun.swe574.fsn.web.wicket.food.recipeEntry;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.easywicket.EasyWicket;
import net.sourceforge.easywicket.IEasyWicket;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;

import tr.edu.boun.swe574.fsn.web.common.DateUtil;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;
import tr.edu.boun.swe574.fsn.web.wicket.food.changeRecipe.ChangeRecipe;
import edu.boun.swe574.fsn.common.client.network.IngredientInfo;
import edu.boun.swe574.fsn.common.client.network.RecipeInfo;

public class CRecipeEntry extends BasePanel implements IEasyWicket {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4101392788243795520L;
	
//	private static Logger logger = Logger.getLogger(CRecipeEntry.class);
	
	List<IngredientInfo> ingredientList;


	public CRecipeEntry(String id, final RecipeInfo recipeInfo) {
		super(id);
		
		Label lblTitle = new Label("lblTitle", recipeInfo.getRecipeName());
		add(lblTitle);
		
		Label lblOwner = new Label("lblOwner", recipeInfo.getOwnerName() + " " + recipeInfo.getOwnerSurname());
		add(lblOwner);
		
		Label lblCreateDate = new Label("lblCreateDate", DateUtil.getDateString(recipeInfo.getCreateDate(), DateUtil.DATE_FORMAT_MM_DD_YYYY));
		add(lblCreateDate);
		
		ingredientList = new ArrayList<IngredientInfo>();
		
		if(recipeInfo.getIngredientList() != null && !recipeInfo.getIngredientList().isEmpty()) {
			ingredientList.addAll(recipeInfo.getIngredientList());
		}
		
		Loop loop = new Loop("ingredients", ingredientList.size()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6061042651381535005L;

			@Override
			protected void populateItem(LoopItem item) {
				IngredientInfo ingredientInfo = ingredientList.get(item.getIndex());
				Label lblIngredient = new Label("lblIngredient", getIngredient(ingredientInfo));
				item.add(lblIngredient);
			}
		};
		add(loop);
		
		Label lblDirections = new Label("lblDirections", recipeInfo.getDirections());
		add(lblDirections);
		
		AjaxLink<Object> lnkChangeRecipe = new AjaxLink<Object>("lnkChangeRecipe") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2463068626554321901L;

			@Override
            public void onClick(AjaxRequestTarget arg0) {
				setResponsePage(new ChangeRecipe(recipeInfo));
            }
        };
        add(lnkChangeRecipe);
        
		AjaxLink<Object> lnkViewHistory = new AjaxLink<Object>("lnkViewHistory") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void onClick(AjaxRequestTarget arg0) {
				//TODO set response page
            }
        };
        add(lnkViewHistory);
	}
	
	private String getIngredient(IngredientInfo ingredient) {
		StringBuilder sb = new StringBuilder();
		sb.append(ingredient.getAmount()).append(" ");
		sb.append(ingredient.getUnit()).append(" ");
		sb.append(ingredient.getFood().getFoodName());
		return sb.toString();
	}

	@Override
	public void pack() {

	}

	@Override
	public void initComponent(EasyWicket arg0, MarkupContainer arg1) {

	}


}