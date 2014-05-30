package tr.edu.boun.swe574.fsn.web.wicket.food.revisionHistory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.OddEvenItem;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import tr.edu.boun.swe574.fsn.web.common.DateUtil;
import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.constants.Constants;
import tr.edu.boun.swe574.fsn.web.common.constants.ResultCode;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import edu.boun.swe574.fsn.common.client.food.GetRevisionHistoryOfRecipeResponse;
import edu.boun.swe574.fsn.common.client.food.RevisionInfo;

@AuthorizeInstantiation(value = {FsnRoles.USER})
public class RevisionHistory extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7761888071957256271L;
	
//	private static Logger logger = Logger.getLogger(RevisionHistory.class); 
	
	private Form<Void> form;
	private DataTable<RevisionInfo>  dataTable;
	private List<RevisionInfo> listOfRevisions;
	
	public RevisionHistory(long recipeId, String title) {
		
		
		GetRevisionHistoryOfRecipeResponse response = WSCaller.getFoodService().getRevisionHistoryOfRecipe(FsnSession.getInstance().getUser().getToken(), recipeId);
		
		if(response.getResultCode() == ResultCode.SUCCESS.getCode()) {
			listOfRevisions = response.getListOfRevisions();
		} else {
			listOfRevisions = new ArrayList<RevisionInfo>();
		}
		
		//http://www.wicket-library.com/wicket-examples/repeater/wicket/bookmarkable/org.apache.wicket.examples.repeater.GridViewPage;jsessionid=FFA39F59CF71FA7AB7E0FD09F2AAFC57?0
		//see GridView Example - demonstrates a grid view
		//http://www.wicket-library.com/wicket-examples/repeater/
		dataTable = getTable();
		dataTable.setOutputMarkupId(true);
		
		form = new Form<Void>("form");
		
		Label lblTitle = new Label("lblTitle", title);
		form.add(lblTitle);
		form.add(dataTable);
		
		
		add(form);
	}
	
	private DataTable<RevisionInfo> getTable() {
    	
        List<IColumn<RevisionInfo>> columns = new ArrayList<IColumn<RevisionInfo>>();
        

        columns.add(new PropertyColumn<RevisionInfo>(
                new Model<String>("currentRecipeId"),"currentRecipeId") {

            /**
             * 
             */
            private static final long serialVersionUID = 5220247060301534590L;
            
            @Override
            public void populateItem(Item<ICellPopulator<RevisionInfo>> item,
                                     String componentId,
                                     IModel<RevisionInfo> rowModel) {

                Fragment frgUser = new Fragment(componentId,
                                                     "frgHeaderless",
                                                     RevisionHistory.this,
                                                     rowModel);
                item.add(frgUser);

                
                Label lblUserName = new Label("lblHeaderless", rowModel.getObject().getCurrentRecipeId() + " " + rowModel.getObject().getParentRecipeId());
                
                
                frgUser.add(lblUserName);
                
            }
            
            
        });
        
        columns.add(new PropertyColumn<RevisionInfo>(
                new Model<String>("Date"),"revisionDate") {
        	
        	/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

			@Override
            public void populateItem(Item<ICellPopulator<RevisionInfo>> item,
                                     String componentId,
                                     IModel<RevisionInfo> rowModel) {
				Fragment frgUser = new Fragment(componentId, "frgDate",
						RevisionHistory.this, rowModel);
				
				Label lblDate = new Label("lblDate", DateUtil.getDateString(rowModel.getObject().getRevisionDate(), DateUtil.DATE_FORMAT_MM_DD_YYYY));
				frgUser.add(lblDate);
				
				item.add(frgUser);
			}
        });
        
        columns.add(new PropertyColumn<RevisionInfo>(
                new Model<String>("User"),"user") {
        	
        	/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

			@Override
            public void populateItem(Item<ICellPopulator<RevisionInfo>> item,
                                     String componentId,
                                     IModel<RevisionInfo> rowModel) {
				Fragment frgUser = new Fragment(componentId, "frgUser",
						RevisionHistory.this, rowModel);
				
				Label lblDate = new Label("lblUser", rowModel.getObject().getUser().getName() + " " + rowModel.getObject().getUser().getSurname());
				frgUser.add(lblDate);
				
				item.add(frgUser);
			}
        });
        
        columns.add(new PropertyColumn<RevisionInfo>(
                new Model<String>("Note"),"revisionNote") {
        	
        	/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

        });


        SortableDataProvider<RevisionInfo> provider = new SortableDataProvider<RevisionInfo>() {

            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            public Iterator<RevisionInfo> iterator(int first, int count) {
                if (getSort() != null) {
                    Collections.sort(listOfRevisions, new Comparator<Object>() {

                        public int compare(Object o1, Object o2) {
                            PropertyModel<Comparable<Object>> model1 = new PropertyModel<Comparable<Object>>(o1,
                                                                                                             getSort().getProperty());
                            PropertyModel<Comparable<Object>> model2 = new PropertyModel<Comparable<Object>>(o2,
                                                                                                             getSort().getProperty());

                            int result;
                            if (model1.getObject() == null) {
                                result = 1;
                            } else if(model2.getObject() == null){
                                result = -1;
                            }
                            else {
                                result = model1.getObject().compareTo(model2.getObject());
                            }
                            
                            if (!getSort().isAscending()) {
                                result = -result;
                            }

                            return result;
                        }
                    });
                }
                return listOfRevisions.subList(first, first + count).iterator();
            }

            public int size() {
                return listOfRevisions.size();
            }

            @Override
            public IModel<RevisionInfo> model(final RevisionInfo user) {
                return new LoadableDetachableModel<RevisionInfo>() {

                    /**
                     * 
                     */
                    private static final long serialVersionUID = -7659348043571352653L;

                    @Override
                    protected RevisionInfo load() {
                        return user;
                    }

                };
            }
        };

        DataTable<RevisionInfo> dataTable = new DataTable<RevisionInfo>(
                "resultTable", columns, provider, Constants.GUI_PAGE_SIZE) {

            /**
                     * 
                     */
            private static final long serialVersionUID = -8189804759594690615L;

            protected Item<RevisionInfo> newRowItem(String id, int index,
                                                                   IModel<RevisionInfo> model) {
                return new OddEvenItem<RevisionInfo>(id, index, model);
            }

        };

        NavigationToolbar navigationToolbar = new NavigationToolbar(dataTable) {

            /**
             * 
             */
            private static final long serialVersionUID = -5313020381701491013L;

            protected void onConfigure() {
                super.onConfigure();
                setVisible(true);
            }
        };
        dataTable.addBottomToolbar(navigationToolbar);

        dataTable.addTopToolbar(new HeadersToolbar(dataTable, provider));
        dataTable.setOutputMarkupId(true);
        return dataTable;
    }

}
