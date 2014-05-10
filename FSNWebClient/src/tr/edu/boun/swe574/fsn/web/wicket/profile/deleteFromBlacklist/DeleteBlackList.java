package tr.edu.boun.swe574.fsn.web.wicket.profile.deleteFromBlacklist;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import tr.edu.boun.swe574.fsn.web.common.info.IngredientInfoForm;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;


public class DeleteBlackList extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5680758832419784439L;
	
	private static Logger logger = Logger.getLogger(DeleteBlackList.class);

	private Form<Void> form;
	
	private AjaxSubmitLink lnkDelete;
	private AjaxSubmitLink lnkCancel;
	private FeedbackPanel feedbackPanel = new FeedbackPanel("feedBackPanel");
	
	public DeleteBlackList(String id, final ModalWindow mwDeleteFromBL, final IngredientInfoForm ingredientInfo) { 
		super(id);
		
		logger.info(FsnSession.getInstance().getUser().getEmail() + " is deleting " + ingredientInfo.getIngredientName() + " from the BL");
		
		
		form = new Form<Void>("form");
		
		Label ingredientName = new Label("lblIngredientName", ingredientInfo.getIngredientName());
		form.add(ingredientName);
		

		lnkDelete = new AjaxSubmitLink("lnkDelete") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4091528668903964731L;
			
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
            	//Call delete service
            	
            	mwDeleteFromBL.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                if (logger.isInfoEnabled()) {
                    logger.info("An error has been occurred on Fraud group comment button");
                }
            }
		};
		
		lnkCancel = new AjaxSubmitLink("lnkCancel") {
			
            /**
			 * 
			 */
			private static final long serialVersionUID = 3908411552647518543L;

			@Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				mwDeleteFromBL.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                if (logger.isInfoEnabled()) {
                    logger.info("An error has been occurred on cancel button");
                }
            }
		};
		
		form.add(lnkDelete);
		form.add(lnkCancel);
		add(form);
		add(feedbackPanel);

	}
	
}
