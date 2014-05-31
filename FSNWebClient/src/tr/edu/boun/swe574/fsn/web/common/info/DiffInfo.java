package tr.edu.boun.swe574.fsn.web.common.info;

import java.io.Serializable;

import tr.edu.boun.swe574.fsn.web.common.constants.DiffStatus;

public class DiffInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6018182543700908830L;
	
	private String parentEntry = "";
	private String currentEntry = "";
	private DiffStatus status;
	
	public String getParentEntry() {
		return parentEntry;
	}
	public void setParentEntry(String parentEntry) {
		this.parentEntry = parentEntry;
	}
	public String getCurrentEntry() {
		return currentEntry;
	}
	public void setCurrentEntry(String currentEntry) {
		this.currentEntry = currentEntry;
	}
	public DiffStatus getStatus() {
		return status;
	}
	public void setStatus(DiffStatus status) {
		this.status = status;
	}
	
}
