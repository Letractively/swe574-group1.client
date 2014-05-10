
package edu.boun.swe574.fsn.common.client.food;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRevisionHistoryOfRecipeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRevisionHistoryOfRecipeResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.backend.fsn.swe574.boun.edu/}baseServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="listOfRevisions" type="{http://ws.backend.fsn.swe574.boun.edu/}revisionInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRevisionHistoryOfRecipeResponse", propOrder = {
    "listOfRevisions"
})
public class GetRevisionHistoryOfRecipeResponse
    extends BaseServiceResponse
{

    @XmlElement(nillable = true)
    protected List<RevisionInfo> listOfRevisions;

    /**
     * Gets the value of the listOfRevisions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listOfRevisions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfRevisions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RevisionInfo }
     * 
     * 
     */
    public List<RevisionInfo> getListOfRevisions() {
        if (listOfRevisions == null) {
            listOfRevisions = new ArrayList<RevisionInfo>();
        }
        return this.listOfRevisions;
    }

}
