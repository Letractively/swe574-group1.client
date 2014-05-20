package tr.edu.boun.swe574.fsn.web.wicket.register;

import org.apache.log4j.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import tr.edu.boun.swe574.fsn.web.common.Encrypter;
import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.constants.ResultCode;
import tr.edu.boun.swe574.fsn.web.common.constants.ServiceErrorCode;
import tr.edu.boun.swe574.fsn.web.common.info.UserInfoForm;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.home.HomePage;
import tr.edu.boun.swe574.fsn.web.wicket.login.FsnSignInPage;
import edu.boun.swe574.fsn.common.client.auth.BaseServiceResponse;
import edu.boun.swe574.fsn.common.client.food.GetIngredientsResponse;
import edu.boun.swe574.fsn.common.client.search.GetRecipeFeedsResponse;
import edu.boun.swe574.fsn.common.client.search.LongArray;

@AuthorizeInstantiation(value = {FsnRoles.UNKNOWN})
public class Register extends WebPage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7624915525801556539L;
	
    private static final Logger logger = Logger.getLogger(Register.class);
    
	private FeedbackPanel   feedbackPanel     = new FeedbackPanel("feedBackPanel");
	private Form<Void> form;
	private TextField<String> txtEmail;
	private TextField<String> txtFirstName;
	private TextField<String> txtLastName;
	private PasswordTextField txtPassword;
	private PasswordTextField txtPasswordAgain;
	private UserInfoForm formInfo = new UserInfoForm();
	private SubmitLink lnkCreate;
	private AjaxSubmitLink lnkReset;
	Link<Object> lnkHome;
	
    private void init(String msg)
    {
        if(msg != null)
            info(msg);
        form = new Form("form");
        txtEmail = new TextField("txtEmail", new PropertyModel(formInfo, "email"));
        txtEmail.setRequired(true);
        txtEmail.add(EmailAddressValidator.getInstance());
        txtFirstName = new TextField("txtFirstName", new PropertyModel(formInfo, "firstName"));
        txtFirstName.setRequired(true);
        txtLastName = new TextField("txtLastName", new PropertyModel(formInfo, "lastName"));
        txtLastName.setRequired(true);
        txtPassword = new PasswordTextField("txtPassword", new PropertyModel(formInfo, "password"));
        txtPassword.setRequired(true);
        txtPassword.add(StringValidator.lengthBetween(6, 10));
        txtPasswordAgain = new PasswordTextField("txtPasswordAgain", new PropertyModel(formInfo, "passwordAgain"));
        txtPasswordAgain.setRequired(true);
        txtPasswordAgain.add(StringValidator.lengthBetween(6, 10));
        lnkHome = new Link("lnkHome") {

            public void onClick()
            {
                setResponsePage(HomePage.class);
            }

            private static final long serialVersionUID = 0x20aa8a6f78c094ccL;
        };
        
        form.add(new Component[] {
            lnkHome
        });
        
        lnkCreate = new SubmitLink("lnkCreate") {

            public void onSubmit()
            {
                String email = (String)txtEmail.getModelObject();
                String firstName = (String)txtFirstName.getModelObject();
                String lastName = (String)txtLastName.getModelObject();
                String password = (String)txtPassword.getModelObject();
                String passwordAgain = (String)txtPasswordAgain.getModelObject();
                
                if(!password.equals(passwordAgain)) {
                    error("Password values typed must be the same");
                    return;
                } else {
                	
                	if(logger.isDebugEnabled()) {
                		logger.debug("A user is registering. Registration info: Email=" + email + ", Name=" + firstName + ", Lastname=" + lastName);
                	}
                	
                	BaseServiceResponse register = WSCaller.getAuthService().register(email, firstName, lastName, Encrypter.generateMD5(password));
                	
                	
                	if(logger.isDebugEnabled()) {
                		logger.debug("Registration response received for user:"  + email + ". ResultCode=" + register.getResultCode() + ", ErrorCode=" + register.getErrorCode());
                	}
                	
                	String msg;
                	if(register.getResultCode().intValue() == ResultCode.SUCCESS.getCode()) {
                		msg = (new StringBuilder("User [")).append(email).append("] created successfully.").toString();
                	} else {
                		msg = "Registration can not be fulfilled. " + ServiceErrorCode.valueOf(register.getErrorCode()).getDescription();
                	}
                    
                    Register.logger.debug(msg);
                    setResponsePage(new Register(msg));
                    return;
                }
            }

            private static final long serialVersionUID = 0xf6be7ac11a5a3460L;

        };
        
        lnkReset = new AjaxSubmitLink("lnkReset") {

            public void onSubmit(AjaxRequestTarget target, Form<?> form)
            {
                setResponsePage(new Register(null));
            }

            protected void onError(AjaxRequestTarget ajaxrequesttarget, Form<?> form1)
            {
            }

            private static final long serialVersionUID = 0xcd42a099ad246560L;
        };
        
        
        lnkReset.setVisible(false);
        Form<Void> cuForm = new Form<Void>("cuForm");
        SubmitLink lnkCreateUser = new SubmitLink("lnkLogin") {

            public void onSubmit()
            {
                setResponsePage(FsnSignInPage.class);
            }
        };
        
        cuForm.add(new Component[] {
            lnkCreateUser
        });
        add(new Component[] {
            cuForm
        });
        form.add(new Component[] {
            txtEmail
        });
        form.add(new Component[] {
            txtFirstName
        });
        form.add(new Component[] {
            txtLastName
        });
        form.add(new Component[] {
            txtPassword
        });
        form.add(new Component[] {
            txtPasswordAgain
        });
        form.add(new Component[] {
            lnkCreate
        });
        form.add(new Component[] {
            lnkReset
        });
        add(new Component[] {
            form
        });
        add(new Component[] {
            feedbackPanel
        });
    }

    public Register(String msg)
    {
        feedbackPanel = new FeedbackPanel("feedBackPanel");
        formInfo = new UserInfoForm();
        init(msg);
    }

    public Register()
    {
        feedbackPanel = new FeedbackPanel("feedBackPanel");
        formInfo = new UserInfoForm();
        init(null);
    }


}
