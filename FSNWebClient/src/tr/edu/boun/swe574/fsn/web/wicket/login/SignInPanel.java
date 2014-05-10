package tr.edu.boun.swe574.fsn.web.wicket.login;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import tr.edu.boun.swe574.fsn.web.wicket.register.Register;

public class SignInPanel extends Panel {

	private static final long serialVersionUID = 1L;
    protected FeedbackPanel   feedbackPanel     = new FeedbackPanel("feedBackPanel");

    /** True if the panel should display a remember-me checkbox */
    private boolean           includeRememberMe = true;

    /** Field for password. */
    private PasswordTextField password;

    /** True if the user should be remembered via form persistence (cookies) */
    private boolean           rememberMe        = false;

    /** Field for user name. */
    private TextField<String> username;

    /** Field for sign in form . */
    private SignInForm        signInForm;

	public final class SignInForm extends StatelessForm<Void> {

		public final void onSubmit() {
		}

		private static final long serialVersionUID = 1L;
		private final ValueMap properties = new ValueMap();

		public SignInForm(String id) {
			super(id);
			username = new TextField<String>("username",
					new PropertyModel<String>(properties, "username"));
			username.setModelObject("myra@hodson.com");
			
			add(new Component[] { username });
			username.setRequired(true);
			password = new PasswordTextField("password",
					new PropertyModel<String>(properties, "password"));
			password.setRequired(true);
			
			password.setModelObject("111111");
			
			add(new Component[] { password });
			username.setType(String.class);
			password.setType(String.class);
			username.setOutputMarkupId(true);
			password.setOutputMarkupId(true);
		}
	}

	public SignInPanel(String id) {
		this(id, true);
	}

	public SignInPanel(String id, boolean includeRememberMe) {
		super(id);
		
		feedbackPanel = new FeedbackPanel("feedBackPanel");
		signInForm = new SignInForm("signInForm");
		
		AjaxSubmitLink loginLink = new AjaxSubmitLink("loginLink") {

			public void onSubmit(AjaxRequestTarget target, Form form) {
				target.addComponent(new Component[] { feedbackPanel });
				if (signIn(getUsername(), getPassword())) {
					onSignInSucceeded();
				} else {
					error("Username or password is wrong!");
					onSignInFailed();
				}
			}

			@Override
			protected void onError(AjaxRequestTarget arg0, Form<?> arg1) {
				// TODO Auto-generated method stub
				
			}

			private static final long serialVersionUID = 0xcd42a099ad246560L;
		};
		
		signInForm.add(loginLink);
		
		AjaxSubmitLink resetLink = new AjaxSubmitLink("resetLink") {

			public void onSubmit(AjaxRequestTarget target, Form form) {
				target.addComponent(new Component[] { username });
				target.addComponent(new Component[] { password });
				username.setDefaultModelObject("");
				password.setDefaultModelObject("");
			}

			@Override
			protected void onError(AjaxRequestTarget arg0, Form<?> arg1) {
				// TODO Auto-generated method stub
				
			}

			private static final long serialVersionUID = 0xcd42a099ad246560L;
		};
		
		
		signInForm.add(resetLink);
		
		Form<Void> cuForm = new Form<Void>("cuForm");
		
		SubmitLink lnkCreateUser = new SubmitLink("lnkCreateUser") {

			public void onSubmit() {
				setResponsePage(new Register(null));
			}

			private static final long serialVersionUID = 0xdbba679a1554f865L;
		};
		
		cuForm.add(lnkCreateUser);
		add(cuForm);
		feedbackPanel.setVisible(true);
		feedbackPanel.setOutputMarkupId(true);
		add(feedbackPanel);
		add(signInForm);
	}

	public final void forgetMe() {
	}

	public String getPassword() {
		return password.getInput();
	}

	public String getUsername() {
		return username.getDefaultModelObjectAsString();
	}

	public void setPersistent(boolean flag) {
	}

	public boolean signIn(String username, String password) {
		return AuthenticatedWebSession.get().signIn(username, password);
	}

	protected void onSignInFailed() {
		error(getLocalizer().getString("signInFailed", this, "Sign in failed"));
	}

	protected void onSignInSucceeded() {
		setResponsePage(getApplication().getHomePage());
	}

	protected void updateSignInForm() {
		AjaxRequestTarget target = AjaxRequestTarget.get();
		if (target != null)
			target.addComponent(new Component[] { signInForm });
	}

}
