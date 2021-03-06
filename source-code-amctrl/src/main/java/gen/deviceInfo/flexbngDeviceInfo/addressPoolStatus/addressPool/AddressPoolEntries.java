/* 
 * @(#)AddressPoolEntries.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "flexbng-device-info", revision: "2015-11-11".
 */

package gen.deviceInfo.flexbngDeviceInfo.addressPoolStatus.addressPool;

import com.tailf.jnc.Element;
import com.tailf.jnc.ElementChildrenIterator;
import com.tailf.jnc.JNCException;
import com.tailf.jnc.YangElement;

import gen.deviceInfo.flexbngDeviceInfo.FlexbngVbras;
import gen.deviceInfo.flexbngDeviceInfo.addressPoolStatus.addressPool.addressPoolEntries.Ipv4AddressBlock;
import gen.deviceInfo.flexbngDeviceInfo.addressPoolStatus.addressPool.addressPoolEntries.Ipv6AddressBlock;

/**
 * This class represents an element from 
 * the namespace urn:ietf:params:xml:ns:yang:ietf-address-pool:flexbng:vbras
 * generated to "src/gen/deviceInfo/flexbngDeviceInfo/addressPoolStatus/addressPool/address-pool-entries"
 * <p>
 * See line 112 in
 * ../yang/flexbng-device-info.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class AddressPoolEntries extends YangElement {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for an empty AddressPoolEntries object.
     */
    public AddressPoolEntries() {
        super(FlexbngVbras.NAMESPACE, "address-pool-entries");
    }

    /**
     * Clones this object, returning an exact copy.
     * @return A clone of the object.
     */
    public AddressPoolEntries clone() {
        return (AddressPoolEntries)cloneContent(new AddressPoolEntries());
    }

    /**
     * Clones this object, returning a shallow copy.
     * @return A clone of the object. Children are not included.
     */
    public AddressPoolEntries cloneShallow() {
        return (AddressPoolEntries)cloneShallowContent(new AddressPoolEntries());
    }

    /**
     * @return An array with the identifiers of any key children
     */
    public String[] keyNames() {
        return null;
    }

    /**
     * @return An array with the identifiers of any children, in order.
     */
    public String[] childrenNames() {
        return new String[] {
            "ipv4-address-block",
            "ipv6-address-block",
        };
    }

    /* Access methods for list child: "ipv4-address-block". */

    /**
     * Gets list entry "ipv4AddressBlock", with specified keys.
     */
    public Ipv4AddressBlock getIpv4AddressBlock() throws JNCException {
        String path = "ipv4-address-block";
        return (Ipv4AddressBlock)searchOne(path);
    }

    /**
     * Iterator method for the list "ipv4-address-block".
     * @return An iterator for the list.
     */
    public ElementChildrenIterator ipv4AddressBlockIterator() {
        return new ElementChildrenIterator(children, "ipv4-address-block");
    }

    /**
     * Adds list entry "ipv4AddressBlock", using an existing object.
     * @param ipv4AddressBlock The object to add.
     * @return The added child.
     */
    public Ipv4AddressBlock addIpv4AddressBlock(Ipv4AddressBlock ipv4AddressBlock)
            throws JNCException {
        insertChild(ipv4AddressBlock, childrenNames());
        return ipv4AddressBlock;
    }

    /**
     * Adds list entry "ipv4AddressBlock".
     * This method is used for creating subtree filters.
     * @return The added child.
     */
    public Ipv4AddressBlock addIpv4AddressBlock() throws JNCException {
        Ipv4AddressBlock ipv4AddressBlock = new Ipv4AddressBlock();
        insertChild(ipv4AddressBlock, childrenNames());
        return ipv4AddressBlock;
    }

    /**
     * Deletes list entry "ipv4AddressBlock", with specified keys.
     */
    public void deleteIpv4AddressBlock() throws JNCException {
        String path = "ipv4-address-block";
        delete(path);
    }

    /* Access methods for list child: "ipv6-address-block". */

    /**
     * Gets list entry "ipv6AddressBlock", with specified keys.
     */
    public Ipv6AddressBlock getIpv6AddressBlock() throws JNCException {
        String path = "ipv6-address-block";
        return (Ipv6AddressBlock)searchOne(path);
    }

    /**
     * Iterator method for the list "ipv6-address-block".
     * @return An iterator for the list.
     */
    public ElementChildrenIterator ipv6AddressBlockIterator() {
        return new ElementChildrenIterator(children, "ipv6-address-block");
    }

    /**
     * Adds list entry "ipv6AddressBlock", using an existing object.
     * @param ipv6AddressBlock The object to add.
     * @return The added child.
     */
    public Ipv6AddressBlock addIpv6AddressBlock(Ipv6AddressBlock ipv6AddressBlock)
            throws JNCException {
        insertChild(ipv6AddressBlock, childrenNames());
        return ipv6AddressBlock;
    }

    /**
     * Adds list entry "ipv6AddressBlock".
     * This method is used for creating subtree filters.
     * @return The added child.
     */
    public Ipv6AddressBlock addIpv6AddressBlock() throws JNCException {
        Ipv6AddressBlock ipv6AddressBlock = new Ipv6AddressBlock();
        insertChild(ipv6AddressBlock, childrenNames());
        return ipv6AddressBlock;
    }

    /**
     * Deletes list entry "ipv6AddressBlock", with specified keys.
     */
    public void deleteIpv6AddressBlock() throws JNCException {
        String path = "ipv6-address-block";
        delete(path);
    }

    /**
     * Support method for addChild.
     * Adds a child to this object.
     * 
     * @param child The child to add
     */
    public void addChild(Element child) {
        super.addChild(child);
    }

}
