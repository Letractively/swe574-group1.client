
package edu.boun.swe574.fsn.common.client.food;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRecipeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRecipeResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.backend.fsn.swe574.boun.edu/}baseServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="recipe" type="{http://ws.backend.fsn.swe574.boun.edu/}recipeInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRecipeResponse", propOrder = {
    "recipe"
})
public class GetRecipeResponse
    extends BaseServiceResponse
{

    protected RecipeInfo recipe;

    /**
     * Gets the value of the recipe property.
     * 
     * @return
     *     possible object is
     *     {@link RecipeInfo }
     *     
     */
    public RecipeInfo getRecipe() {
        return recipe;
    }

    /**
     * Sets the value of the recipe property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipeInfo }
     *     
     */
    public void setRecipe(RecipeInfo value) {
        this.recipe = value;
    }

}
