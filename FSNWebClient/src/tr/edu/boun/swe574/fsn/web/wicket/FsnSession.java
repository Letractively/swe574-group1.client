package tr.edu.boun.swe574.fsn.web.wicket;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.info.WebUser;

public class FsnSession extends AuthenticatedWebSession {
	
	private WebUser user;

	public FsnSession(Request request) {
		super(request);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1489755374990019412L;

	@Override
	public Roles getRoles() {
        if(!isSignedIn())
            return new Roles(FsnRoles.UNKNOWN);
        else
            return new Roles(FsnRoles.USER);
	}
	
    public static FsnSession getInstance() {
        return (FsnSession)WebSession.get();
    }
    
    public boolean authenticate(String username, String password) {
        WebUser user = new WebUser();
        user.setEmail(username);
        user.setFirstName("Myra");
        user.setLastName("Hudson");
        this.user = user;
        return true;
    }

	public WebUser getUser() {
		return user;
	}

	public void setUser(WebUser user) {
		this.user = user;
	}
	
	

}
