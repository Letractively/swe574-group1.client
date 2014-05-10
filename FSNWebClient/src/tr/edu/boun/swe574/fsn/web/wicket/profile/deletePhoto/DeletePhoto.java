package tr.edu.boun.swe574.fsn.web.wicket.profile.deletePhoto;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;


public class DeletePhoto extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5680758832419784439L;
	
	private static Logger logger = Logger.getLogger(DeletePhoto.class);

	private AjaxSubmitLink lnkDelete;
	private AjaxSubmitLink lnkCancel;
	private FeedbackPanel feedbackPanel = new FeedbackPanel("feedBackPanel");
	private Form<Void> form;
	
	public DeletePhoto(String id, final ModalWindow mwCreateBlog) { 
		super(id);
		
		logger.info(FsnSession.getInstance().getUser().getEmail() + " is deleting the profile photo");
		
		form = new Form<Void>("form");

		lnkDelete = new AjaxSubmitLink("lnkDelete") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4091528668903964731L;
			
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
            	//TODO Call web service to delete profile photo
            	
            	mwCreateBlog.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                if (logger.isInfoEnabled()) {
                    logger.info("An error has been occurred on DeletePhoto button");
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
				mwCreateBlog.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                if (logger.isInfoEnabled()) {
                    logger.info("An error has been occurred on DeletePhoto button");
                }
            }
		};
		
		form.add(lnkDelete);
		form.add(lnkCancel);
		add(form);
		add(feedbackPanel);

	}
	
}
