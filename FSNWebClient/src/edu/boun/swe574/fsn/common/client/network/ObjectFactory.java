
package edu.boun.swe574.fsn.common.client.network;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.boun.swe574.fsn.common.client.network package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.boun.swe574.fsn.common.client.network
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BaseServiceResponse }
     * 
     */
    public BaseServiceResponse createBaseServiceResponse() {
        return new BaseServiceResponse();
    }

    /**
     * Create an instance of {@link UserInfo }
     * 
     */
    public UserInfo createUserInfo() {
        return new UserInfo();
    }

    /**
     * Create an instance of {@link FoodList }
     * 
     */
    public FoodList createFoodList() {
        return new FoodList();
    }

    /**
     * Create an instance of {@link FoodInfo }
     * 
     */
    public FoodInfo createFoodInfo() {
        return new FoodInfo();
    }

    /**
     * Create an instance of {@link GetProfileResponse }
     * 
     */
    public GetProfileResponse createGetProfileResponse() {
        return new GetProfileResponse();
    }

    /**
     * Create an instance of {@link SearchForUsersResponse }
     * 
     */
    public SearchForUsersResponse createSearchForUsersResponse() {
        return new SearchForUsersResponse();
    }

    /**
     * Create an instance of {@link RecipeInfo }
     * 
     */
    public RecipeInfo createRecipeInfo() {
        return new RecipeInfo();
    }

    /**
     * Create an instance of {@link IngredientInfo }
     * 
     */
    public IngredientInfo createIngredientInfo() {
        return new IngredientInfo();
    }

    /**
     * Create an instance of {@link GetRecipeFeedsResponse }
     * 
     */
    public GetRecipeFeedsResponse createGetRecipeFeedsResponse() {
        return new GetRecipeFeedsResponse();
    }

}
