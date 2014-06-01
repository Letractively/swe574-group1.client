
package edu.boun.swe574.fsn.common.client.food;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createRecipeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createRecipeResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.backend.fsn.swe574.boun.edu/}baseServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="createdRecipeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createRecipeResponse", propOrder = {
    "createdRecipeId"
})
public class CreateRecipeResponse
    extends BaseServiceResponse
{

    protected long createdRecipeId;

    /**
     * Gets the value of the createdRecipeId property.
     * 
     */
    public long getCreatedRecipeId() {
        return createdRecipeId;
    }

    /**
     * Sets the value of the createdRecipeId property.
     * 
     */
    public void setCreatedRecipeId(long value) {
        this.createdRecipeId = value;
    }

}
