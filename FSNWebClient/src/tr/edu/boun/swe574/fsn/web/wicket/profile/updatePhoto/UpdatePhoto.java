package tr.edu.boun.swe574.fsn.web.wicket.profile.updatePhoto;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.lang.Bytes;

import tr.edu.boun.swe574.fsn.web.common.util.FileUtility;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;


public class UpdatePhoto extends BasePanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -872875102063071478L;

	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(UpdatePhoto.class);

	private AjaxSubmitLink lnkDelete;
	private AjaxSubmitLink lnkCancel;
	private FeedbackPanel feedbackPanel = new FeedbackPanel("feedBackPanel");
	private Form<Void> form;
	
	private FileUploadField fileUploadField;
	private UploadProgressBar progressBar;
	
	public UpdatePhoto(String id, final ModalWindow mwUpdatePhoto) { 
		super(id);
		
		logger.info(FsnSession.getInstance().getUser().getEmail() + " is updating the profile photo");
		
		form = new Form<Void>("form");
		
		form.add(fileUploadField = new FileUploadField("fileInput"));

		progressBar = new UploadProgressBar("progress", form, fileUploadField);
		
		form.add(progressBar);

		lnkDelete = new AjaxSubmitLink("lnkDelete") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4091528668903964731L;
			
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
            	
				if (fileUploadField.getFileUploads() == null
						|| fileUploadField.getFileUploads().isEmpty()) {
					error("A file have to be chosen");
					return;
				} 
				
				String clientFileName = fileUploadField.getFileUploads().get(0).getClientFileName();
				if(!FileUtility.isImageFile(clientFileName)) {
					error("Please choose a valid image file");
					return;
				}
				
            	
				//TODO call WS to save photo
				
            	mwUpdatePhoto.close(target);
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
			private static final long serialVersionUID = -3998055611379419222L;

			@Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				mwUpdatePhoto.close(target);
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                if (logger.isInfoEnabled()) {
                    logger.info("An error has been occurred on cancel button");
                }
            }
		};
		form.setMultiPart(true);
		form.add(lnkDelete);
		form.add(lnkCancel);
		add(form);
		form.add(feedbackPanel);
		form.setMaxSize(Bytes.kilobytes(100));
	}
	
}
