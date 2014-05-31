package tr.edu.boun.swe574.fsn.web.wicket.food.revisionDiff;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.Loop;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.model.PropertyModel;

import tr.edu.boun.swe574.fsn.web.common.DateUtil;
import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.constants.DiffStatus;
import tr.edu.boun.swe574.fsn.web.common.info.DiffInfo;
import tr.edu.boun.swe574.fsn.web.common.info.DiffResult;
import tr.edu.boun.swe574.fsn.web.common.util.RevisionDiffFinder;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import edu.boun.swe574.fsn.common.client.food.GetRecipeResponse;
import edu.boun.swe574.fsn.common.client.food.RevisionInfo;

@AuthorizeInstantiation(value = {FsnRoles.USER})
public class RevisionDiff extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7761888071957256271L;
	
//	private static Logger logger = Logger.getLogger(RevisionHistory.class); 
	
	private List<DiffInfo> ingredientsDiff = new ArrayList<DiffInfo>(); 
	
	Loop rangeList;
	
	public RevisionDiff(String title, RevisionInfo revisionInfo) {
		
		GetRecipeResponse parentResp = WSCaller.getFoodService().getRecipe(FsnSession.getInstance().getUser().getToken(), revisionInfo.getParentRecipeId());
		GetRecipeResponse currentResp = WSCaller.getFoodService().getRecipe(FsnSession.getInstance().getUser().getToken(), revisionInfo.getCurrentRecipeId());

		DiffResult diffResult = RevisionDiffFinder.findDiff(currentResp.getRecipe(), parentResp.getRecipe());

		ingredientsDiff.addAll(diffResult.getIngredientsDiffList());
		ingredientsDiff.add(diffResult.getDirectionsDiff());
		
		Label lblParentCreateDate = new Label("lblParentCreateDate", DateUtil.getDateString(parentResp.getRecipe().getCreateDate(), DateUtil.DATE_FORMAT_MM_DD_YYYY));
		add(lblParentCreateDate);
		
		Label lblParentOwner = new Label("lblParentOwner", parentResp.getRecipe().getOwnerName() + " " + parentResp.getRecipe().getOwnerSurname());
		add(lblParentOwner);
		
		Label lblCurrentCreateDate = new Label("lblCurrentCreateDate", DateUtil.getDateString(currentResp.getRecipe().getCreateDate(), DateUtil.DATE_FORMAT_MM_DD_YYYY));
		add(lblCurrentCreateDate);
		
		Label lblCurrentOwner = new Label("lblCurrentOwner", currentResp.getRecipe().getOwnerName() + " " + currentResp.getRecipe().getOwnerSurname());
		add(lblCurrentOwner);
		
		Label lblRevNoteOwner = new Label("lblRevNoteOwner", currentResp.getRecipe().getOwnerName() + " " + currentResp.getRecipe().getOwnerSurname());
		add(lblRevNoteOwner);
		
		Label lblRevNote = new Label("lblRevNote", revisionInfo.getRevisionNote());
		add(lblRevNote);
		
		rangeList = new Loop("rangeList", new PropertyModel<Integer>(this,
				"listCount")) {

			private static final long serialVersionUID = -79079985811912615L;

			@Override
			protected void populateItem(LoopItem li) {
				DiffInfo diffInfo = ingredientsDiff.get(li.getIndex());


				if (diffInfo.getStatus().equals(DiffStatus.ADDED) || 
						diffInfo.getStatus().equals(DiffStatus.DELETED) || 
						diffInfo.getStatus().equals(DiffStatus.MODIFIED)) {
					
					Label lblCurrent = new Label("lblCurrent", getCurrentTD(diffInfo.getCurrentEntry()));
					lblCurrent.setEscapeModelStrings(false);
					li.add(lblCurrent);

					Label lblParent = new Label("lblParent", getParentTD(diffInfo.getParentEntry()));
					lblParent.setEscapeModelStrings(false);
					li.add(lblParent);

				} else if (diffInfo.getStatus().equals(DiffStatus.NO_CHANGE)) {
					Label lblCurrent = new Label("lblCurrent", getNoChangeTD(diffInfo.getCurrentEntry()));
					lblCurrent.setEscapeModelStrings(false);
					li.add(lblCurrent);

					Label lblParent = new Label("lblParent", getNoChangeTD(diffInfo.getParentEntry()));
					lblParent.setEscapeModelStrings(false);
					li.add(lblParent);
				}
				
			}
		};
		
		add(rangeList);
		
		Label lblTitle = new Label("lblTitle", title);
		add(lblTitle);
	}
	
	private String getCurrentTD(String entry) {
		StringBuilder sb = new StringBuilder();
		sb.append("<td style=\"border:solid 2px #0066FF; padding:5px\">").append(entry).append("</td>");
		return sb.toString();
	}
	
	private String getParentTD(String entry) {
		StringBuilder sb = new StringBuilder();
		sb.append("<td style=\"border:solid 2px #FF6600; padding:5px\">").append(entry).append("</td>");
		return sb.toString();
	}
	
	private String getNoChangeTD(String entry) {
		StringBuilder sb = new StringBuilder();
		sb.append("<td style=\"border:solid 2px #EAEAE0; padding:5px\">").append(entry).append("</td>");
		return sb.toString();
	}
	
	public int getListCount() {
		return ingredientsDiff.size();
	}

}
