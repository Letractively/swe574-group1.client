package tr.edu.boun.swe574.fsn.web.wicket.common;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;

public class Message extends BasePage {
	
    private Label lblMessage;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9002822276770607121L;
	
    public Message(String msg)
    {
        lblMessage = new Label("lblMessage", msg != null ? msg : "");
        add(new Component[] {
            lblMessage
        });
    }


}
