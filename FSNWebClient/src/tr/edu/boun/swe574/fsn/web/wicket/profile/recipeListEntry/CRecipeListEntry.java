package tr.edu.boun.swe574.fsn.web.wicket.profile.recipeListEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.easywicket.EasyWicket;
import net.sourceforge.easywicket.IEasyWicket;

import org.apache.log4j.Logger;
import org.apache.wicket.MarkupContainer;
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
import tr.edu.boun.swe574.fsn.web.common.constants.Constants;
import tr.edu.boun.swe574.fsn.web.common.constants.ResultCode;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePanel;
import tr.edu.boun.swe574.fsn.web.wicket.food.viewRecipe.ViewRecipe;
import edu.boun.swe574.fsn.common.client.network.GetRecipeFeedsResponse;
import edu.boun.swe574.fsn.common.client.network.RecipeInfo;

public class CRecipeListEntry extends BasePanel implements IEasyWicket {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4101392788243795520L;
	
	private static Logger logger = Logger.getLogger(CRecipeListEntry.class); 

	private List<RecipeInfo> recipeList;
	private DataTable<RecipeInfo> dataTable;
	
	private Form<Void> form;

	public CRecipeListEntry(String id) {
		super(id);

		recipeList = new ArrayList<RecipeInfo>();
		
		form = new Form<Void>("form");

		//get recipe feeds
		GetRecipeFeedsResponse response = WSCaller.getNetworkService().getRecipeFeeds(FsnSession.getInstance().getUser().getToken(),1, 100000);
		
		System.out.println("getRecipeFeeds service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode() + " " + response.getRecipeList());
		
		if(logger.isDebugEnabled()) {
			logger.debug("getRecipeFeeds service result code:" + response.getResultCode() + " errorCode:" + response.getErrorCode() + " " + response.getRecipeList());
		}

		if (response.getResultCode().intValue() == ResultCode.SUCCESS.getCode()) {
			recipeList.addAll(response.getRecipeList());
		}
		
		dataTable = getDataTable();
		dataTable.setOutputMarkupId(true);
		form.add(dataTable);
		add(form);
	}

	@Override
	public void pack() {

	}

	@Override
	public void initComponent(EasyWicket arg0, MarkupContainer arg1) {

	}

	private DataTable<RecipeInfo> getDataTable() {

		List<IColumn<RecipeInfo>> columns = new ArrayList<IColumn<RecipeInfo>>();

		columns.add(new PropertyColumn<RecipeInfo>(new Model<String>("Recipe Feeds"),
				"name") {

			/**
             * 
             */
			private static final long serialVersionUID = 5220247060301534590L;

			@Override
			public void populateItem(Item<ICellPopulator<RecipeInfo>> item,
					String componentId, IModel<RecipeInfo> rowModel) {

				Fragment frgUser = new Fragment(componentId, "frgRecipe", CRecipeListEntry.this, rowModel);
				item.add(frgUser);

				final RecipeInfo recipe = rowModel.getObject();
				SubmitLink lnkUser = new SubmitLink("lnkRecipe") {

					/**
					 * 
					 */
					private static final long serialVersionUID = -8580276547405546079L;

					public void onSubmit() {
						setResponsePage(new ViewRecipe(recipe));
					};
				};
				
				Label lblRecipeTitle = new Label("lblRecipeTitle", recipe.getRecipeName());
				lnkUser.add(lblRecipeTitle);
				
				Label lblCreateDate = new Label("lblCreateDate", DateUtil.getDateString(recipe.getCreateDate(), DateUtil.DATE_FORMAT_MM_DD_YYYY));
				frgUser.add(lblCreateDate);

				Label lblOwner = new Label("lblOwner", recipe.getOwnerName() + " " + recipe.getOwnerSurname());
				frgUser.add(lblOwner);
				
				frgUser.add(lnkUser);
			}

		});

		SortableDataProvider<RecipeInfo> provider = new SortableDataProvider<RecipeInfo>() {

			/**
             * 
             */
			private static final long serialVersionUID = 1L;

			public Iterator<RecipeInfo> iterator(int first, int count) {
				if (getSort() != null) {
					Collections.sort(recipeList, new Comparator<Object>() {

						public int compare(Object o1, Object o2) {
							PropertyModel<Comparable<Object>> model1 = new PropertyModel<Comparable<Object>>(
									o1, getSort().getProperty());
							PropertyModel<Comparable<Object>> model2 = new PropertyModel<Comparable<Object>>(
									o2, getSort().getProperty());

							int result;
							if (model1.getObject() == null) {
								result = 1;
							} else if (model2.getObject() == null) {
								result = -1;
							} else {
								result = model1.getObject().compareTo(
										model2.getObject());
							}

							if (!getSort().isAscending()) {
								result = -result;
							}

							return result;
						}
					});
				}
				return recipeList.subList(first, first + count).iterator();
			}

			public int size() {
				return recipeList.size();
			}

			@Override
			public IModel<RecipeInfo> model(final RecipeInfo user) {
				return new LoadableDetachableModel<RecipeInfo>() {

					/**
                     * 
                     */
					private static final long serialVersionUID = -7659348043571352653L;

					@Override
					protected RecipeInfo load() {
						return user;
					}

				};
			}
		};

		DataTable<RecipeInfo> dataTable = new DataTable<RecipeInfo>("resultTable",
				columns, provider, Constants.GUI_PAGE_SIZE) {

			/**
                     * 
                     */
			private static final long serialVersionUID = -8189804759594690615L;

			protected Item<RecipeInfo> newRowItem(String id, int index,
					IModel<RecipeInfo> model) {
				return new OddEvenItem<RecipeInfo>(id, index, model);
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