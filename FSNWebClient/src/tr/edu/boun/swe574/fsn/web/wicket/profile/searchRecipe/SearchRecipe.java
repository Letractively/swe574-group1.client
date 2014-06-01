package tr.edu.boun.swe574.fsn.web.wicket.profile.searchRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
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
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.BookmarkablePageRequestHandler;
import org.apache.wicket.request.handler.PageProvider;
import org.apache.wicket.util.string.Strings;

import tr.edu.boun.swe574.fsn.web.common.DateUtil;
import tr.edu.boun.swe574.fsn.web.common.FsnRoles;
import tr.edu.boun.swe574.fsn.web.common.constants.Constants;
import tr.edu.boun.swe574.fsn.web.common.ws.WSCaller;
import tr.edu.boun.swe574.fsn.web.wicket.FsnSession;
import tr.edu.boun.swe574.fsn.web.wicket.common.BasePage;
import tr.edu.boun.swe574.fsn.web.wicket.common.ratingView.CRatingViewEntry;
import tr.edu.boun.swe574.fsn.web.wicket.food.viewRecipe.ViewRecipe;
import tr.edu.boun.swe574.fsn.web.wicket.home.HomePage;
import edu.boun.swe574.fsn.common.client.food.FoodInfo;
import edu.boun.swe574.fsn.common.client.food.GetIngredientsResponse;
import edu.boun.swe574.fsn.common.client.search.GetRecipeFeedsResponse;

@AuthorizeInstantiation(value = { FsnRoles.USER })
public class SearchRecipe extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7761888071957256271L;

	private static Logger logger = Logger.getLogger(SearchRecipe.class);

	// private final List<FoodInfo> ingredients = new ArrayList<FoodInfo>();
	private final HashMap<String, FoodInfo> ingredientsHash = new HashMap<String, FoodInfo>();
	private final List<FoodInfo> ingredientsSelected = new ArrayList<FoodInfo>();
	
	private AjaxSubmitLink lnkSearch;
	
	private Form<Void> form;
	
	private List<edu.boun.swe574.fsn.common.client.search.RecipeInfo> recipeList;
	private DataTable<edu.boun.swe574.fsn.common.client.search.RecipeInfo> dataTable;

	public SearchRecipe() {

		form = new Form<Void>("form");
		
		recipeList = new ArrayList<edu.boun.swe574.fsn.common.client.search.RecipeInfo>();
		
		dataTable = getDataTable();
		dataTable.setOutputMarkupId(true);
		dataTable.setVisible(false);
		form.add(dataTable);

		final AutoCompleteTextField<String> field = new AutoCompleteTextField<String>(
				"ac", new Model<String>("")) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 944804902516000771L;

			@Override
			protected Iterator<String> getChoices(String input) {
				if (Strings.isEmpty(input)) {
					List<String> emptyList = Collections.emptyList();
					return emptyList.iterator();
				}

				List<String> choices = new ArrayList<String>(10);

				// get all ingredients from foods service
				GetIngredientsResponse ingredientsResponse = WSCaller
						.getFoodService().getIngredients(
								FsnSession.getInstance().getUser().getToken(),
								input);

				System.out.println("input is:" + input
						+ " getIngredients response result:"
						+ ingredientsResponse.getResultCode() + " errorCode:"
						+ ingredientsResponse.getErrorCode() + " "
						+ ingredientsResponse.getListOfIngredients());

				List<FoodInfo> ingredients = new ArrayList<FoodInfo>();

				if (ingredientsResponse.getListOfIngredients() != null
						&& !ingredientsResponse.getListOfIngredients()
								.isEmpty()) {
					ingredients.addAll(ingredientsResponse
							.getListOfIngredients());
					// put to hash
					for (FoodInfo info : ingredients) {
						ingredientsHash.put(info.getFoodName(), info);
					}
				}

				for (final FoodInfo locale : ingredients) {
					final String country = locale.getFoodName();

					if (country.toUpperCase().startsWith(input.toUpperCase())) {
						choices.add(country);
						if (choices.size() == 10) {
							break;
						}
					}
				}
//				ingredientsHash.clear();
				return choices.iterator();
			}
		};
		form.add(field);

		final Label label = new Label("selectedValue", field.getDefaultModel());
		label.setOutputMarkupId(true);
		form.add(label);

		field.add(new AjaxFormSubmitBehavior(form, "onchange") {
			/**
				 * 
				 */
			private static final long serialVersionUID = -3388144940793863601L;

			@Override
			protected void onSubmit(AjaxRequestTarget target) {
				BookmarkablePageRequestHandler bookmarkablePageRequestHandler = new BookmarkablePageRequestHandler(
						new PageProvider(HomePage.class));

				RequestCycle requestCycle = RequestCycle.get();
				CharSequence urlFor = requestCycle
						.urlFor(bookmarkablePageRequestHandler);
				String selectedIng = field.getModelObject();

				FoodInfo ingredientInfo = ingredientsHash.get(selectedIng);
				if (ingredientInfo != null) {
					if (!ingredientsSelected.contains(ingredientInfo)) {
						System.out.println("Ingredient selected...." + ingredientInfo.getFoodId());
						ingredientsSelected.add(ingredientInfo);
					}
				}
				field.setDefaultModelObject(getIngredientString());
				target.add(label);
			}

			@Override
			protected void onError(AjaxRequestTarget target) {
			}
		});

		lnkSearch = new AjaxSubmitLink("lnkSearch") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 4091528668903964731L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				// Call delete service
				if (!ingredientsSelected.isEmpty()) {
					System.out.println("Ingredient number to be sent to service:"+ingredientsSelected.size());
					
					GetRecipeFeedsResponse searchRecipes = WSCaller.searchRecipes(FsnSession.getInstance().getUser().getToken(), ingredientsSelected);
					List<edu.boun.swe574.fsn.common.client.search.RecipeInfo> searchResult = searchRecipes.getRecipeList();
					
					if(searchResult != null && !searchResult.isEmpty()) {
						recipeList.addAll(searchResult);
					}
					
					
					dataTable.setVisible(true);
					ingredientsSelected.clear();
					field.setDefaultModelObject("");
					target.add(label);
					target.add(field);
				}

			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				if (logger.isInfoEnabled()) {
					logger.info("An error has been occurred on AddToBL delete button");
				}
			}
		};

		form.add(lnkSearch);
		add(form);
		add(feedbackPanel);

	}

	private String getIngredientString() {
		StringBuilder sb = new StringBuilder();
		for (FoodInfo info : ingredientsSelected) {
			sb.append(info.getFoodName()).append(",");
		}
		return sb.toString();
	}
	
	private DataTable<edu.boun.swe574.fsn.common.client.search.RecipeInfo> getDataTable() {

		List<IColumn<edu.boun.swe574.fsn.common.client.search.RecipeInfo>> columns = new ArrayList<IColumn<edu.boun.swe574.fsn.common.client.search.RecipeInfo>>();

		columns.add(new PropertyColumn<edu.boun.swe574.fsn.common.client.search.RecipeInfo>(new Model<String>("Recipe Feeds"),
				"name") {

			/**
             * 
             */
			private static final long serialVersionUID = 5220247060301534590L;

			@Override
			public void populateItem(Item<ICellPopulator<edu.boun.swe574.fsn.common.client.search.RecipeInfo>> item,
					String componentId, IModel<edu.boun.swe574.fsn.common.client.search.RecipeInfo> rowModel) {

				Fragment frgUser = new Fragment(componentId, "frgRecipe", SearchRecipe.this, rowModel);
				item.add(frgUser);

				final edu.boun.swe574.fsn.common.client.search.RecipeInfo recipe = rowModel.getObject();
				SubmitLink lnkUser = new SubmitLink("lnkRecipe") {

					/**
					 * 
					 */
					private static final long serialVersionUID = -8580276547405546079L;

					public void onSubmit() {
						
						setResponsePage(new ViewRecipe(null, recipe.getRecipeId()));
					};
				};
				
				Label lblRecipeTitle = new Label("lblRecipeTitle", recipe.getRecipeName());
				lnkUser.add(lblRecipeTitle);
				
				Label lblCreateDate = new Label("lblCreateDate", DateUtil.getDateString(recipe.getCreateDate(), DateUtil.DATE_FORMAT_MM_DD_YYYY));
				frgUser.add(lblCreateDate);

				Label lblOwner = new Label("lblOwner", recipe.getOwnerName() + " " + recipe.getOwnerSurname());
				frgUser.add(lblOwner);
				
				frgUser.add(lnkUser);
				
				CRatingViewEntry ratingViewEntery = new CRatingViewEntry("ratingViewEntery", recipe.getRating());
				frgUser.add(ratingViewEntery);
			}

		});

		SortableDataProvider<edu.boun.swe574.fsn.common.client.search.RecipeInfo> provider = new SortableDataProvider<edu.boun.swe574.fsn.common.client.search.RecipeInfo>() {

			/**
             * 
             */
			private static final long serialVersionUID = 1L;

			public Iterator<edu.boun.swe574.fsn.common.client.search.RecipeInfo> iterator(int first, int count) {
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
			public IModel<edu.boun.swe574.fsn.common.client.search.RecipeInfo> model(final edu.boun.swe574.fsn.common.client.search.RecipeInfo user) {
				return new LoadableDetachableModel<edu.boun.swe574.fsn.common.client.search.RecipeInfo>() {

					/**
                     * 
                     */
					private static final long serialVersionUID = -7659348043571352653L;

					@Override
					protected edu.boun.swe574.fsn.common.client.search.RecipeInfo load() {
						return user;
					}

				};
			}
		};

		DataTable<edu.boun.swe574.fsn.common.client.search.RecipeInfo> dataTable = new DataTable<edu.boun.swe574.fsn.common.client.search.RecipeInfo>("resultTable",
				columns, provider, Constants.GUI_PAGE_SIZE) {

			/**
                     * 
                     */
			private static final long serialVersionUID = -8189804759594690615L;

			protected Item<edu.boun.swe574.fsn.common.client.search.RecipeInfo> newRowItem(String id, int index,
					IModel<edu.boun.swe574.fsn.common.client.search.RecipeInfo> model) {
				return new OddEvenItem<edu.boun.swe574.fsn.common.client.search.RecipeInfo>(id, index, model);
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
