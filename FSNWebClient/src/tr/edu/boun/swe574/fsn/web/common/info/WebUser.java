package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;

public class WebUser implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8219751026985002460L;
	private String email;
    private String firstName;
    private String lastName;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    

}
