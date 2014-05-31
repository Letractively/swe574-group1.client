package tr.edu.boun.swe574.fsn.web.common.util;

import java.util.ArrayList;
import java.util.List;

import tr.edu.boun.swe574.fsn.web.common.constants.DiffStatus;
import tr.edu.boun.swe574.fsn.web.common.info.DiffInfo;
import tr.edu.boun.swe574.fsn.web.common.info.DiffResult;
import edu.boun.swe574.fsn.common.client.food.IngredientInfo;
import edu.boun.swe574.fsn.common.client.food.RecipeInfo;

public class RevisionDiffFinder {
	
	private static final String PLACE_HOLDER = " ";

	public static DiffResult findDiff(RecipeInfo current, RecipeInfo parent) {
		DiffResult result = new DiffResult();
		
		//compare directions
		DiffInfo diffDirections = diffDirections(current.getDirections(), parent.getDirections());
		result.setDirectionsDiff(diffDirections);
		
		//compare ingredients
		List<IngredientInfo> currentIngredients = current.getIngredientList() == null ? new ArrayList<IngredientInfo>() : current.getIngredientList();
		List<IngredientInfo> parentIngredients = parent.getIngredientList() == null ? new ArrayList<IngredientInfo>() : parent.getIngredientList();
		
		for (IngredientInfo ingredientInfo : currentIngredients) {
			//contained in parent ingredients
			IngredientInfo ingInParent = findMatchingIngredient(ingredientInfo, parentIngredients);
			
			DiffInfo diff = new DiffInfo();
			
			if(ingInParent != null) {
				// compare ingredients
				if(ingredientsMatch(ingredientInfo, ingInParent)) {
					diff.setStatus(DiffStatus.NO_CHANGE);
				} else {
					//amount or unit modified
					diff.setStatus(DiffStatus.MODIFIED);
				}
				diff.setCurrentEntry(toIngredientString(ingredientInfo));
				diff.setParentEntry(toIngredientString(ingInParent));
			} else {
				//current added
				diff.setStatus(DiffStatus.ADDED);
				diff.setCurrentEntry(toIngredientString(ingredientInfo));
			}
			result.getIngredientsDiffList().add(diff);
		}
		
		for (IngredientInfo ingredientInfo : parentIngredients) {
			IngredientInfo ingInCurrent= findMatchingIngredient(ingredientInfo, currentIngredients);
			
			if(ingInCurrent == null) {
				//current deleted
				DiffInfo diff = new DiffInfo();
				diff.setStatus(DiffStatus.DELETED);
				diff.setParentEntry(toIngredientString(ingredientInfo));
				result.getIngredientsDiffList().add(diff);
			}
		}
		
		return result;
	}
	
	private static String toIngredientString(IngredientInfo info) {
		return new StringBuffer().append(info.getAmount()).append(PLACE_HOLDER)
				.append(info.getUnit()).append(PLACE_HOLDER)
				.append(info.getFood().getFoodName()).toString();
	}

	private static DiffInfo diffDirections(String current, String parent) {
		DiffInfo info = new DiffInfo();
		
		info.setCurrentEntry(fixNull(current));
		info.setParentEntry(fixNull(parent));

		// compare directions
		boolean currentDirectionBlank = Validator.isTextBlank(current);
		boolean parentDirectionBlank = Validator.isTextBlank(parent);
		
		if (currentDirectionBlank && parentDirectionBlank) {
			//identical-no change
			info.setStatus(DiffStatus.NO_CHANGE);
			
		} else if(currentDirectionBlank) {
			info.setStatus(DiffStatus.DELETED);
			
		} else if(parentDirectionBlank) {
			info.setStatus(DiffStatus.ADDED);
		} else if (current.trim().equals(parent.trim())) {
			info.setStatus(DiffStatus.NO_CHANGE);
		} else {
			info.setStatus(DiffStatus.MODIFIED);
		}

		return info;
	}
	
	private static String fixNull(String str) {
		return str == null ? "" : str;
	}
	
	private static IngredientInfo findMatchingIngredient(IngredientInfo ing, List<IngredientInfo> ingredients) {
		if(ingredients != null) {
			for (IngredientInfo ingredientInfo : ingredients) {
				if(ingredientInfo.getFood().getFoodId() == ing.getFood().getFoodId()) {
					return ingredientInfo;
				}
			}
		}
		return null;
	}
	
	private static boolean ingredientsMatch(IngredientInfo current, IngredientInfo parent) {
		if(current.getAmount() != parent.getAmount()) {
			return false;
		} else if(!current.getUnit().trim().toUpperCase().equals(parent.getUnit().trim().toUpperCase())) {
			return false;
		}
		return true;
	}

}
