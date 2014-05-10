
package edu.boun.swe574.fsn.common.client.network;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchForUsersResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchForUsersResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.backend.fsn.swe574.boun.edu/}baseServiceResponse">
 *       &lt;sequence>
 *         &lt;element name="userList" type="{http://ws.backend.fsn.swe574.boun.edu/}userInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchForUsersResponse", propOrder = {
    "userList"
})
public class SearchForUsersResponse
    extends BaseServiceResponse
{

    @XmlElement(nillable = true)
    protected List<UserInfo> userList;

    /**
     * Gets the value of the userList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserInfo }
     * 
     * 
     */
    public List<UserInfo> getUserList() {
        if (userList == null) {
            userList = new ArrayList<UserInfo>();
        }
        return this.userList;
    }

}
