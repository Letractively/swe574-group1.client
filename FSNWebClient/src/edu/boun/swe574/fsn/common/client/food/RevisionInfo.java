
package edu.boun.swe574.fsn.common.client.food;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for revisionInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="revisionInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currentRecipeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="parentRecipeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="revisionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="revisionNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="user" type="{http://ws.backend.fsn.swe574.boun.edu/}userInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "revisionInfo", propOrder = {
    "currentRecipeId",
    "parentRecipeId",
    "revisionDate",
    "revisionNote",
    "user"
})
public class RevisionInfo {

    protected long currentRecipeId;
    protected long parentRecipeId;
    protected XMLGregorianCalendar revisionDate;
    protected String revisionNote;
    protected UserInfo user;

    /**
     * Gets the value of the currentRecipeId property.
     * 
     */
    public long getCurrentRecipeId() {
        return currentRecipeId;
    }

    /**
     * Sets the value of the currentRecipeId property.
     * 
     */
    public void setCurrentRecipeId(long value) {
        this.currentRecipeId = value;
    }

    /**
     * Gets the value of the parentRecipeId property.
     * 
     */
    public long getParentRecipeId() {
        return parentRecipeId;
    }

    /**
     * Sets the value of the parentRecipeId property.
     * 
     */
    public void setParentRecipeId(long value) {
        this.parentRecipeId = value;
    }

    /**
     * Gets the value of the revisionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRevisionDate() {
        return revisionDate;
    }

    /**
     * Sets the value of the revisionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRevisionDate(XMLGregorianCalendar value) {
        this.revisionDate = value;
    }

    /**
     * Gets the value of the revisionNote property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevisionNote() {
        return revisionNote;
    }

    /**
     * Sets the value of the revisionNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevisionNote(String value) {
        this.revisionNote = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link UserInfo }
     *     
     */
    public UserInfo getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserInfo }
     *     
     */
    public void setUser(UserInfo value) {
        this.user = value;
    }

}
