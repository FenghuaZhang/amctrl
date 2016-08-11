/* 
 * @(#)AddressPools.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "ietf-address-pool", revision: "2015-07-17".
 */

package gen.addressPool.ietfAddressPool;

import com.tailf.jnc.Element;
import com.tailf.jnc.ElementChildrenIterator;
import com.tailf.jnc.JNCException;
import com.tailf.jnc.YangElement;
import com.tailf.jnc.YangString;
import com.tailf.jnc.YangUInt32;

import gen.addressPool.ietfAddressPool.AddrPool;
import gen.addressPool.ietfAddressPool.addressPools.AddressPool;

/**
 * This class represents an element from 
 * the namespace urn:ietf:params:xml:ns:yang:ietf-address-pool
 * generated to "src/gen/addressPool/ietfAddressPool/address-pools"
 * <p>
 * See line 59 in
 * ../yang/ietf-address-pool.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class AddressPools extends YangElement {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for an empty AddressPools object.
     */
    public AddressPools() {
        super(AddrPool.NAMESPACE, "address-pools");
        setDefaultPrefix();
        setPrefix(AddrPool.PREFIX);
    }

    /**
     * Clones this object, returning an exact copy.
     * @return A clone of the object.
     */
    public AddressPools clone() {
        return (AddressPools)cloneContent(new AddressPools());
    }

    /**
     * Clones this object, returning a shallow copy.
     * @return A clone of the object. Children are not included.
     */
    public AddressPools cloneShallow() {
        return (AddressPools)cloneShallowContent(new AddressPools());
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
            "device-id",
            "address-pool",
        };
    }

    /* Access methods for optional leaf child: "device-id". */

    /**
     * Gets the value for child leaf "device-id".
     * @return The value of the leaf.
     */
    public YangUInt32 getDeviceIdValue() throws JNCException {
        YangUInt32 deviceId = (YangUInt32)getValue("device-id");
        if (deviceId == null) {
            deviceId = new YangUInt32("0");  // default
        }
        return deviceId;
    }

    /**
     * Sets the value for child leaf "device-id",
     * using instance of generated typedef class.
     * @param deviceIdValue The value to set.
     * @param deviceIdValue used during instantiation.
     */
    public void setDeviceIdValue(YangUInt32 deviceIdValue) throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "device-id",
            deviceIdValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "device-id",
     * using Java primitive values.
     * @param deviceIdValue used during instantiation.
     */
    public void setDeviceIdValue(long deviceIdValue) throws JNCException {
        setDeviceIdValue(new YangUInt32(deviceIdValue));
    }

    /**
     * Sets the value for child leaf "device-id",
     * using a String value.
     * @param deviceIdValue used during instantiation.
     */
    public void setDeviceIdValue(String deviceIdValue) throws JNCException {
        setDeviceIdValue(new YangUInt32(deviceIdValue));
    }

    /**
     * Unsets the value for child leaf "device-id".
     */
    public void unsetDeviceIdValue() throws JNCException {
        delete("device-id");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "device-id" leaf will not have a value.
     */
    public void addDeviceId() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "device-id",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "device-id" with operation "replace".
     */
    public void markDeviceIdReplace() throws JNCException {
        markLeafReplace("deviceId");
    }

    /**
     * Marks the leaf "device-id" with operation "merge".
     */
    public void markDeviceIdMerge() throws JNCException {
        markLeafMerge("deviceId");
    }

    /**
     * Marks the leaf "device-id" with operation "create".
     */
    public void markDeviceIdCreate() throws JNCException {
        markLeafCreate("deviceId");
    }

    /**
     * Marks the leaf "device-id" with operation "delete".
     */
    public void markDeviceIdDelete() throws JNCException {
        markLeafDelete("deviceId");
    }

    /* Access methods for list child: "address-pool". */

    /**
     * Gets list entry "addressPool", with specified keys.
     * @param addressPoolNameValue Key argument of child.
     */
    public AddressPool getAddressPool(YangString addressPoolNameValue)
            throws JNCException {
        String path = "address-pool[addressPoolName='" + addressPoolNameValue + "']";
        return (AddressPool)searchOne(path);
    }

    /**
     * Gets list entry "addressPool", with specified keys.
     * The keys are specified as strings.
     * @param addressPoolNameValue Key argument of child.
     */
    public AddressPool getAddressPool(String addressPoolNameValue)
            throws JNCException {
        String path = "address-pool[addressPoolName='" + addressPoolNameValue + "']";
        return (AddressPool)searchOne(path);
    }

    /**
     * Iterator method for the list "address-pool".
     * @return An iterator for the list.
     */
    public ElementChildrenIterator addressPoolIterator() {
        return new ElementChildrenIterator(children, "address-pool");
    }

    /**
     * Adds list entry "addressPool", using an existing object.
     * @param addressPool The object to add.
     * @return The added child.
     */
    public AddressPool addAddressPool(AddressPool addressPool)
            throws JNCException {
        insertChild(addressPool, childrenNames());
        return addressPool;
    }

    /**
     * Adds list entry "addressPool", with specified keys.
     * @param addressPoolNameValue Key argument of child.
     * @return The added child.
     */
    public AddressPool addAddressPool(YangString addressPoolNameValue)
            throws JNCException {
        AddressPool addressPool = new AddressPool(addressPoolNameValue);
        return addAddressPool(addressPool);
    }

    /**
     * Adds list entry "addressPool", with specified keys.
     * The keys are specified as strings.
     * @param addressPoolNameValue Key argument of child.
     * @return The added child.
     */
    public AddressPool addAddressPool(String addressPoolNameValue)
            throws JNCException {
        AddressPool addressPool = new AddressPool(addressPoolNameValue);
        return addAddressPool(addressPool);
    }

    /**
     * Adds list entry "addressPool".
     * This method is used for creating subtree filters.
     * @return The added child.
     */
    public AddressPool addAddressPool() throws JNCException {
        AddressPool addressPool = new AddressPool();
        insertChild(addressPool, childrenNames());
        return addressPool;
    }

    /**
     * Deletes list entry "addressPool", with specified keys.
     * @param addressPoolNameValue Key argument of child.
     */
    public void deleteAddressPool(YangString addressPoolNameValue)
            throws JNCException {
        String path = "address-pool[addressPoolName='" + addressPoolNameValue + "']";
        delete(path);
    }

    /**
     * Deletes list entry "addressPool", with specified keys.
     * The keys are specified as strings.
     * @param addressPoolNameValue Key argument of child.
     */
    public void deleteAddressPool(String addressPoolNameValue)
            throws JNCException {
        String path = "address-pool[addressPoolName='" + addressPoolNameValue + "']";
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
