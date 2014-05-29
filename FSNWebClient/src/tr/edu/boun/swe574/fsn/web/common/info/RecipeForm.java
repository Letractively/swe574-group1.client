package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4355076084442993927L;
	private List<IngredientForm> ingredientFormList = new ArrayList<IngredientForm>();
	private String title;
	private String directions;
	private String revisionNote;

	public List<IngredientForm> getIngredientFormList() {
		return ingredientFormList;
	}

	public void setIngredientFormList(List<IngredientForm> ingredientFormList) {
		this.ingredientFormList = ingredientFormList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public String getRevisionNote() {
		return revisionNote;
	}

	public void setRevisionNote(String revisionNote) {
		this.revisionNote = revisionNote;
	}

}
