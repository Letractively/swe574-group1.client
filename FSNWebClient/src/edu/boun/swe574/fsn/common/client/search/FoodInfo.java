
package edu.boun.swe574.fsn.common.client.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for foodInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="foodInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="foodId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="foodName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "foodInfo", propOrder = {
    "foodId",
    "foodName"
})
public class FoodInfo {

    protected long foodId;
    protected String foodName;

    /**
     * Gets the value of the foodId property.
     * 
     */
    public long getFoodId() {
        return foodId;
    }

    /**
     * Sets the value of the foodId property.
     * 
     */
    public void setFoodId(long value) {
        this.foodId = value;
    }

    /**
     * Gets the value of the foodName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * Sets the value of the foodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFoodName(String value) {
        this.foodName = value;
    }

}
