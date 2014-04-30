package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;

public class UserInfoForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4156816740327381494L;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String passwordAgain;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordAgain() {
		return passwordAgain;
	}
	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

}
