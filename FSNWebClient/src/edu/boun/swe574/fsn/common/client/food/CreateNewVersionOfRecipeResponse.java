
package edu.boun.swe574.fsn.common.client.food;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createNewVersionOfRecipeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createNewVersionOfRecipeResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.backend.fsn.swe574.boun.edu/}baseServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="idOfRecipeCreated" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createNewVersionOfRecipeResponse", propOrder = {
    "idOfRecipeCreated"
})
public class CreateNewVersionOfRecipeResponse
    extends BaseServiceResponse
{

    protected Long idOfRecipeCreated;

    /**
     * Gets the value of the idOfRecipeCreated property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdOfRecipeCreated() {
        return idOfRecipeCreated;
    }

    /**
     * Sets the value of the idOfRecipeCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdOfRecipeCreated(Long value) {
        this.idOfRecipeCreated = value;
    }

}
