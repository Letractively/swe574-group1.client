
package edu.boun.swe574.fsn.common.client.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ingredientInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ingredientInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ingredientId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ingredientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ingredientInfo", propOrder = {
    "categoryName",
    "ingredientId",
    "ingredientName"
})
public class IngredientInfo {

    protected String categoryName;
    protected long ingredientId;
    protected String ingredientName;

    /**
     * Gets the value of the categoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the value of the categoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Gets the value of the ingredientId property.
     * 
     */
    public long getIngredientId() {
        return ingredientId;
    }

    /**
     * Sets the value of the ingredientId property.
     * 
     */
    public void setIngredientId(long value) {
        this.ingredientId = value;
    }

    /**
     * Gets the value of the ingredientName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Sets the value of the ingredientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIngredientName(String value) {
        this.ingredientName = value;
    }

}
