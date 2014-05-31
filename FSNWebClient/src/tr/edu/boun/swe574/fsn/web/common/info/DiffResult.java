package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DiffResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3869052883202985347L;

	private List<DiffInfo> ingredientsDiffList;
	private DiffInfo directionsDiff;

	public List<DiffInfo> getIngredientsDiffList() {
		if (ingredientsDiffList == null) {
			ingredientsDiffList = new ArrayList<DiffInfo>();
		}
		return ingredientsDiffList;
	}

	public void setIngredientsDiffList(List<DiffInfo> ingredientsDiffList) {
		this.ingredientsDiffList = ingredientsDiffList;
	}

	public DiffInfo getDirectionsDiff() {
		return directionsDiff;
	}

	public void setDirectionsDiff(DiffInfo directionsDiff) {
		this.directionsDiff = directionsDiff;
	}

}
