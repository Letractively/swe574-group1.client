
package edu.boun.swe574.fsn.common.client.food;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getIngredientsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getIngredientsResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.backend.fsn.swe574.boun.edu/}baseServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="listOfIngredients" type="{http://ws.backend.fsn.swe574.boun.edu/}ingredientInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getIngredientsResponse", propOrder = {
    "listOfIngredients"
})
public class GetIngredientsResponse
    extends BaseServiceResponse
{

    @XmlElement(nillable = true)
    protected List<IngredientInfo> listOfIngredients;

    /**
     * Gets the value of the listOfIngredients property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listOfIngredients property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfIngredients().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IngredientInfo }
     * 
     * 
     */
    public List<IngredientInfo> getListOfIngredients() {
        if (listOfIngredients == null) {
            listOfIngredients = new ArrayList<IngredientInfo>();
        }
        return this.listOfIngredients;
    }

}
