package tr.edu.boun.swe574.fsn.web.wicket;

import org.apache.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import tr.edu.boun.swe574.fsn.web.common.Encrypter;
import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.constants.ResultCode;
import tr.edu.boun.swe574.fsn.web.common.info.WebUser;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import edu.boun.swe574.fsn.common.client.auth.LoginResponse;

public class FsnSession extends AuthenticatedWebSession {
	
	private static Logger logger = Logger.getLogger(FsnSession.class);
	
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
    	if(logger.isInfoEnabled()) {
    		logger.info( username + " is signing in...");
    	}
    	
    	LoginResponse login = WSCaller.getAuthService().login(username, Encrypter.generateMD5(password));
    	
    	if(logger.isDebugEnabled()) {
    		logger.debug("login response received from the WS for username:" + username + ". ResultCode=" +
    				login.getResultCode() + " ErrorCode=" + login.getErrorCode() + " Token=" + login.getToken());
    	}
    	
    	if(login.getResultCode().intValue() == ResultCode.FAILURE.getCode()) {
    		return false;
    	}
    	
        WebUser user = new WebUser();
        user.setEmail(username);
        user.setFirstName("Myra");
        user.setLastName("Hudson");
        user.setToken(login.getToken());
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
