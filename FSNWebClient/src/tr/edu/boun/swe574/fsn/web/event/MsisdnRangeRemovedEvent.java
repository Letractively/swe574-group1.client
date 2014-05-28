package tr.edu.boun.swe574.fsn.web.event;

import net.sourceforge.easywicket.web.event.ItemSelectedForDeletionEvent;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

import tr.edu.boun.swe574.fsn.web.common.info.IngredientForm;

public class MsisdnRangeRemovedEvent extends ItemSelectedForDeletionEvent<IngredientForm> {

	public MsisdnRangeRemovedEvent(Component source,
			AjaxRequestTarget requestTarget, IngredientForm targetItem) {
		super(source, requestTarget, targetItem);
		
	}

}
