package tr.edu.boun.swe574.fsn.web.wicket.common;

import net.sourceforge.easywicket.web.common.EasyPanel;

import org.apache.wicket.model.IModel;

public abstract class BasePanel extends EasyPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 6745129873547570177L;

    public BasePanel(String id, IModel<?> model) {
        super(id, model);
    }

    public BasePanel(String id) {
        super(id);

    }
    

}
