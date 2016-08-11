/* 
 * @(#)AddressPool.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "ietf-address-pool", revision: "2015-07-17".
 */

package gen.addressPool.ietfAddressPool.addressPools;

import com.tailf.jnc.Element;
import com.tailf.jnc.JNCException;
import com.tailf.jnc.Leaf;
import com.tailf.jnc.NodeSet;
import com.tailf.jnc.YangElement;
import com.tailf.jnc.YangString;
import com.tailf.jnc.YangUInt32;

import gen.addressPool.ietfAddressPool.AddrPool;
import gen.addressPool.ietfAddressPool.addressPools.addressPool.AddressPoolEntries;
import gen.addressPool.ietfInetTypes.Ipv4AddressNoZone;
import gen.addressPool.ietfInetTypes.Ipv6AddressNoZone;

/**
 * This class represents an element from 
 * the namespace urn:ietf:params:xml:ns:yang:ietf-address-pool
 * generated to "src/gen/addressPool/ietfAddressPool/addressPools/address-pool"
 * <p>
 * See line 70 in
 * ../yang/ietf-address-pool.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class AddressPool extends YangElement {

    private static final long serialVersionUID = 1L;

    /**
     * Field for child container "address-pool-entries".
     */
    public AddressPoolEntries addressPoolEntries = null;

    /**
     * Constructor for an empty AddressPool object.
     */
    public AddressPool() {
        super(AddrPool.NAMESPACE, "address-pool");
    }

    /**
     * Constructor for an initialized AddressPool object,
     * 
     * @param addressPoolNameValue Key argument of child.
     */
    public AddressPool(YangString addressPoolNameValue) throws JNCException {
        super(AddrPool.NAMESPACE, "address-pool");
        Leaf addressPoolName = new Leaf(AddrPool.NAMESPACE, "address-pool-name");
        addressPoolName.setValue(addressPoolNameValue);
        insertChild(addressPoolName, childrenNames());
    }

    /**
     * Constructor for an initialized AddressPool object,
     * with String keys.
     * @param addressPoolNameValue Key argument of child.
     */
    public AddressPool(String addressPoolNameValue) throws JNCException {
        super(AddrPool.NAMESPACE, "address-pool");
        Leaf addressPoolName = new Leaf(AddrPool.NAMESPACE, "address-pool-name");
        addressPoolName.setValue(new YangString(addressPoolNameValue));
        insertChild(addressPoolName, childrenNames());
    }

    /**
     * Clones this object, returning an exact copy.
     * @return A clone of the object.
     */
    public AddressPool clone() {
        AddressPool copy;
        try {
            copy = new AddressPool(getAddressPoolNameValue().toString());
        } catch (JNCException e) {
            copy = null;
        }
        return (AddressPool)cloneContent(copy);
    }

    /**
     * Clones this object, returning a shallow copy.
     * @return A clone of the object. Children are not included.
     */
    public AddressPool cloneShallow() {
        AddressPool copy;
        try {
            copy = new AddressPool(getAddressPoolNameValue().toString());
        } catch (JNCException e) {
            copy = null;
        }
        return (AddressPool)cloneShallowContent(copy);
    }

    /**
     * @return An array with the identifiers of any key children
     */
    public String[] keyNames() {
        return new String[] {
            "address-pool-name",
        };
    }

    /**
     * @return An array with the identifiers of any children, in order.
     */
    public String[] childrenNames() {
        return new String[] {
            "address-pool-name",
            "address-pool-id",
            "domain-name",
            "ipv4-usergateway",
            "ipv6-usergateway",
            "gwnetmask",
            "leasing-time",
            "primarydns",
            "secondarydns",
            "address-pool-entries",
        };
    }

    /* Access methods for leaf child: "address-pool-name". */

    /**
     * Gets the value for child leaf "address-pool-name".
     * @return The value of the leaf.
     */
    public YangString getAddressPoolNameValue() throws JNCException {
        return (YangString)getValue("address-pool-name");
    }

    /**
     * Sets the value for child leaf "address-pool-name",
     * using instance of generated typedef class.
     * @param addressPoolNameValue The value to set.
     * @param addressPoolNameValue used during instantiation.
     */
    public void setAddressPoolNameValue(YangString addressPoolNameValue)
            throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "address-pool-name",
            addressPoolNameValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "address-pool-name",
     * using a String value.
     * @param addressPoolNameValue used during instantiation.
     */
    public void setAddressPoolNameValue(String addressPoolNameValue)
            throws JNCException {
        setAddressPoolNameValue(new YangString(addressPoolNameValue));
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "address-pool-name" leaf will not have a value.
     */
    public void addAddressPoolName() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "address-pool-name",
            null,
            childrenNames());
    }

    /* Access methods for optional leaf child: "address-pool-id". */

    /**
     * Gets the value for child leaf "address-pool-id".
     * @return The value of the leaf.
     */
    public YangUInt32 getAddressPoolIdValue() throws JNCException {
        YangUInt32 addressPoolId = (YangUInt32)getValue("address-pool-id");
        if (addressPoolId == null) {
            addressPoolId = new YangUInt32("0");  // default
        }
        return addressPoolId;
    }

    /**
     * Sets the value for child leaf "address-pool-id",
     * using instance of generated typedef class.
     * @param addressPoolIdValue The value to set.
     * @param addressPoolIdValue used during instantiation.
     */
    public void setAddressPoolIdValue(YangUInt32 addressPoolIdValue)
            throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "address-pool-id",
            addressPoolIdValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "address-pool-id",
     * using Java primitive values.
     * @param addressPoolIdValue used during instantiation.
     */
    public void setAddressPoolIdValue(long addressPoolIdValue)
            throws JNCException {
        setAddressPoolIdValue(new YangUInt32(addressPoolIdValue));
    }

    /**
     * Sets the value for child leaf "address-pool-id",
     * using a String value.
     * @param addressPoolIdValue used during instantiation.
     */
    public void setAddressPoolIdValue(String addressPoolIdValue)
            throws JNCException {
        setAddressPoolIdValue(new YangUInt32(addressPoolIdValue));
    }

    /**
     * Unsets the value for child leaf "address-pool-id".
     */
    public void unsetAddressPoolIdValue() throws JNCException {
        delete("address-pool-id");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "address-pool-id" leaf will not have a value.
     */
    public void addAddressPoolId() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "address-pool-id",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "address-pool-id" with operation "replace".
     */
    public void markAddressPoolIdReplace() throws JNCException {
        markLeafReplace("addressPoolId");
    }

    /**
     * Marks the leaf "address-pool-id" with operation "merge".
     */
    public void markAddressPoolIdMerge() throws JNCException {
        markLeafMerge("addressPoolId");
    }

    /**
     * Marks the leaf "address-pool-id" with operation "create".
     */
    public void markAddressPoolIdCreate() throws JNCException {
        markLeafCreate("addressPoolId");
    }

    /**
     * Marks the leaf "address-pool-id" with operation "delete".
     */
    public void markAddressPoolIdDelete() throws JNCException {
        markLeafDelete("addressPoolId");
    }

    /* Access methods for optional leaf child: "domain-name". */

    /**
     * Gets the value for child leaf "domain-name".
     * @return The value of the leaf.
     */
    public YangString getDomainNameValue() throws JNCException {
        return (YangString)getValue("domain-name");
    }

    /**
     * Sets the value for child leaf "domain-name",
     * using instance of generated typedef class.
     * @param domainNameValue The value to set.
     * @param domainNameValue used during instantiation.
     */
    public void setDomainNameValue(YangString domainNameValue)
            throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "domain-name",
            domainNameValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "domain-name",
     * using a String value.
     * @param domainNameValue used during instantiation.
     */
    public void setDomainNameValue(String domainNameValue) throws JNCException {
        setDomainNameValue(new YangString(domainNameValue));
    }

    /**
     * Unsets the value for child leaf "domain-name".
     */
    public void unsetDomainNameValue() throws JNCException {
        delete("domain-name");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "domain-name" leaf will not have a value.
     */
    public void addDomainName() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "domain-name",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "domain-name" with operation "replace".
     */
    public void markDomainNameReplace() throws JNCException {
        markLeafReplace("domainName");
    }

    /**
     * Marks the leaf "domain-name" with operation "merge".
     */
    public void markDomainNameMerge() throws JNCException {
        markLeafMerge("domainName");
    }

    /**
     * Marks the leaf "domain-name" with operation "create".
     */
    public void markDomainNameCreate() throws JNCException {
        markLeafCreate("domainName");
    }

    /**
     * Marks the leaf "domain-name" with operation "delete".
     */
    public void markDomainNameDelete() throws JNCException {
        markLeafDelete("domainName");
    }

    /* Access methods for optional leaf child: "ipv4-usergateway". */

    /**
     * Gets the value for child leaf "ipv4-usergateway".
     * @return The value of the leaf.
     */
    public Ipv4AddressNoZone getIpv4UsergatewayValue() throws JNCException {
        return (Ipv4AddressNoZone)getValue("ipv4-usergateway");
    }

    /**
     * Sets the value for child leaf "ipv4-usergateway",
     * using a JNC type value.
     * @param ipv4UsergatewayValue The value to set.
     * @param ipv4UsergatewayValue used during instantiation.
     */
    public void setIpv4UsergatewayValue(Ipv4AddressNoZone ipv4UsergatewayValue)
            throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "ipv4-usergateway",
            ipv4UsergatewayValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "ipv4-usergateway",
     * using a String value.
     * @param ipv4UsergatewayValue used during instantiation.
     */
    public void setIpv4UsergatewayValue(String ipv4UsergatewayValue)
            throws JNCException {
        setIpv4UsergatewayValue(new Ipv4AddressNoZone(ipv4UsergatewayValue));
    }

    /**
     * Unsets the value for child leaf "ipv4-usergateway".
     */
    public void unsetIpv4UsergatewayValue() throws JNCException {
        delete("ipv4-usergateway");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "ipv4-usergateway" leaf will not have a value.
     */
    public void addIpv4Usergateway() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "ipv4-usergateway",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "ipv4-usergateway" with operation "replace".
     */
    public void markIpv4UsergatewayReplace() throws JNCException {
        markLeafReplace("ipv4Usergateway");
    }

    /**
     * Marks the leaf "ipv4-usergateway" with operation "merge".
     */
    public void markIpv4UsergatewayMerge() throws JNCException {
        markLeafMerge("ipv4Usergateway");
    }

    /**
     * Marks the leaf "ipv4-usergateway" with operation "create".
     */
    public void markIpv4UsergatewayCreate() throws JNCException {
        markLeafCreate("ipv4Usergateway");
    }

    /**
     * Marks the leaf "ipv4-usergateway" with operation "delete".
     */
    public void markIpv4UsergatewayDelete() throws JNCException {
        markLeafDelete("ipv4Usergateway");
    }

    /* Access methods for optional leaf child: "ipv6-usergateway". */

    /**
     * Gets the value for child leaf "ipv6-usergateway".
     * @return The value of the leaf.
     */
    public Ipv6AddressNoZone getIpv6UsergatewayValue() throws JNCException {
        return (Ipv6AddressNoZone)getValue("ipv6-usergateway");
    }

    /**
     * Sets the value for child leaf "ipv6-usergateway",
     * using a JNC type value.
     * @param ipv6UsergatewayValue The value to set.
     * @param ipv6UsergatewayValue used during instantiation.
     */
    public void setIpv6UsergatewayValue(Ipv6AddressNoZone ipv6UsergatewayValue)
            throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "ipv6-usergateway",
            ipv6UsergatewayValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "ipv6-usergateway",
     * using a String value.
     * @param ipv6UsergatewayValue used during instantiation.
     */
    public void setIpv6UsergatewayValue(String ipv6UsergatewayValue)
            throws JNCException {
        setIpv6UsergatewayValue(new Ipv6AddressNoZone(ipv6UsergatewayValue));
    }

    /**
     * Unsets the value for child leaf "ipv6-usergateway".
     */
    public void unsetIpv6UsergatewayValue() throws JNCException {
        delete("ipv6-usergateway");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "ipv6-usergateway" leaf will not have a value.
     */
    public void addIpv6Usergateway() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "ipv6-usergateway",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "ipv6-usergateway" with operation "replace".
     */
    public void markIpv6UsergatewayReplace() throws JNCException {
        markLeafReplace("ipv6Usergateway");
    }

    /**
     * Marks the leaf "ipv6-usergateway" with operation "merge".
     */
    public void markIpv6UsergatewayMerge() throws JNCException {
        markLeafMerge("ipv6Usergateway");
    }

    /**
     * Marks the leaf "ipv6-usergateway" with operation "create".
     */
    public void markIpv6UsergatewayCreate() throws JNCException {
        markLeafCreate("ipv6Usergateway");
    }

    /**
     * Marks the leaf "ipv6-usergateway" with operation "delete".
     */
    public void markIpv6UsergatewayDelete() throws JNCException {
        markLeafDelete("ipv6Usergateway");
    }

    /* Access methods for optional leaf child: "gwnetmask". */

    /**
     * Gets the value for child leaf "gwnetmask".
     * @return The value of the leaf.
     */
    public YangUInt32 getGwnetmaskValue() throws JNCException {
        return (YangUInt32)getValue("gwnetmask");
    }

    /**
     * Sets the value for child leaf "gwnetmask",
     * using instance of generated typedef class.
     * @param gwnetmaskValue The value to set.
     * @param gwnetmaskValue used during instantiation.
     */
    public void setGwnetmaskValue(YangUInt32 gwnetmaskValue)
            throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "gwnetmask",
            gwnetmaskValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "gwnetmask",
     * using Java primitive values.
     * @param gwnetmaskValue used during instantiation.
     */
    public void setGwnetmaskValue(long gwnetmaskValue) throws JNCException {
        setGwnetmaskValue(new YangUInt32(gwnetmaskValue));
    }

    /**
     * Sets the value for child leaf "gwnetmask",
     * using a String value.
     * @param gwnetmaskValue used during instantiation.
     */
    public void setGwnetmaskValue(String gwnetmaskValue) throws JNCException {
        setGwnetmaskValue(new YangUInt32(gwnetmaskValue));
    }

    /**
     * Unsets the value for child leaf "gwnetmask".
     */
    public void unsetGwnetmaskValue() throws JNCException {
        delete("gwnetmask");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "gwnetmask" leaf will not have a value.
     */
    public void addGwnetmask() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "gwnetmask",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "gwnetmask" with operation "replace".
     */
    public void markGwnetmaskReplace() throws JNCException {
        markLeafReplace("gwnetmask");
    }

    /**
     * Marks the leaf "gwnetmask" with operation "merge".
     */
    public void markGwnetmaskMerge() throws JNCException {
        markLeafMerge("gwnetmask");
    }

    /**
     * Marks the leaf "gwnetmask" with operation "create".
     */
    public void markGwnetmaskCreate() throws JNCException {
        markLeafCreate("gwnetmask");
    }

    /**
     * Marks the leaf "gwnetmask" with operation "delete".
     */
    public void markGwnetmaskDelete() throws JNCException {
        markLeafDelete("gwnetmask");
    }

    /* Access methods for optional leaf child: "leasing-time". */

    /**
     * Gets the value for child leaf "leasing-time".
     * @return The value of the leaf.
     */
    public YangUInt32 getLeasingTimeValue() throws JNCException {
        YangUInt32 leasingTime = (YangUInt32)getValue("leasing-time");
        if (leasingTime == null) {
            leasingTime = new YangUInt32("0");  // default
        }
        return leasingTime;
    }

    /**
     * Sets the value for child leaf "leasing-time",
     * using instance of generated typedef class.
     * @param leasingTimeValue The value to set.
     * @param leasingTimeValue used during instantiation.
     */
    public void setLeasingTimeValue(YangUInt32 leasingTimeValue)
            throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "leasing-time",
            leasingTimeValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "leasing-time",
     * using Java primitive values.
     * @param leasingTimeValue used during instantiation.
     */
    public void setLeasingTimeValue(long leasingTimeValue) throws JNCException {
        setLeasingTimeValue(new YangUInt32(leasingTimeValue));
    }

    /**
     * Sets the value for child leaf "leasing-time",
     * using a String value.
     * @param leasingTimeValue used during instantiation.
     */
    public void setLeasingTimeValue(String leasingTimeValue)
            throws JNCException {
        setLeasingTimeValue(new YangUInt32(leasingTimeValue));
    }

    /**
     * Unsets the value for child leaf "leasing-time".
     */
    public void unsetLeasingTimeValue() throws JNCException {
        delete("leasing-time");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "leasing-time" leaf will not have a value.
     */
    public void addLeasingTime() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "leasing-time",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "leasing-time" with operation "replace".
     */
    public void markLeasingTimeReplace() throws JNCException {
        markLeafReplace("leasingTime");
    }

    /**
     * Marks the leaf "leasing-time" with operation "merge".
     */
    public void markLeasingTimeMerge() throws JNCException {
        markLeafMerge("leasingTime");
    }

    /**
     * Marks the leaf "leasing-time" with operation "create".
     */
    public void markLeasingTimeCreate() throws JNCException {
        markLeafCreate("leasingTime");
    }

    /**
     * Marks the leaf "leasing-time" with operation "delete".
     */
    public void markLeasingTimeDelete() throws JNCException {
        markLeafDelete("leasingTime");
    }

    /* Access methods for optional leaf child: "primarydns". */

    /**
     * Gets the value for child leaf "primarydns".
     * @return The value of the leaf.
     */
    public YangString getPrimarydnsValue() throws JNCException {
        YangString primarydns = (YangString)getValue("primarydns");
        if (primarydns == null) {
            primarydns = new YangString("0.0.0.0");  // default
        }
        return primarydns;
    }

    /**
     * Sets the value for child leaf "primarydns",
     * using instance of generated typedef class.
     * @param primarydnsValue The value to set.
     * @param primarydnsValue used during instantiation.
     */
    public void setPrimarydnsValue(YangString primarydnsValue)
            throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "primarydns",
            primarydnsValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "primarydns",
     * using a String value.
     * @param primarydnsValue used during instantiation.
     */
    public void setPrimarydnsValue(String primarydnsValue) throws JNCException {
        setPrimarydnsValue(new YangString(primarydnsValue));
    }

    /**
     * Unsets the value for child leaf "primarydns".
     */
    public void unsetPrimarydnsValue() throws JNCException {
        delete("primarydns");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "primarydns" leaf will not have a value.
     */
    public void addPrimarydns() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "primarydns",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "primarydns" with operation "replace".
     */
    public void markPrimarydnsReplace() throws JNCException {
        markLeafReplace("primarydns");
    }

    /**
     * Marks the leaf "primarydns" with operation "merge".
     */
    public void markPrimarydnsMerge() throws JNCException {
        markLeafMerge("primarydns");
    }

    /**
     * Marks the leaf "primarydns" with operation "create".
     */
    public void markPrimarydnsCreate() throws JNCException {
        markLeafCreate("primarydns");
    }

    /**
     * Marks the leaf "primarydns" with operation "delete".
     */
    public void markPrimarydnsDelete() throws JNCException {
        markLeafDelete("primarydns");
    }

    /* Access methods for optional leaf child: "secondarydns". */

    /**
     * Gets the value for child leaf "secondarydns".
     * @return The value of the leaf.
     */
    public YangString getSecondarydnsValue() throws JNCException {
        YangString secondarydns = (YangString)getValue("secondarydns");
        if (secondarydns == null) {
            secondarydns = new YangString("0.0.0.0");  // default
        }
        return secondarydns;
    }

    /**
     * Sets the value for child leaf "secondarydns",
     * using instance of generated typedef class.
     * @param secondarydnsValue The value to set.
     * @param secondarydnsValue used during instantiation.
     */
    public void setSecondarydnsValue(YangString secondarydnsValue)
            throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "secondarydns",
            secondarydnsValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "secondarydns",
     * using a String value.
     * @param secondarydnsValue used during instantiation.
     */
    public void setSecondarydnsValue(String secondarydnsValue)
            throws JNCException {
        setSecondarydnsValue(new YangString(secondarydnsValue));
    }

    /**
     * Unsets the value for child leaf "secondarydns".
     */
    public void unsetSecondarydnsValue() throws JNCException {
        delete("secondarydns");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "secondarydns" leaf will not have a value.
     */
    public void addSecondarydns() throws JNCException {
        setLeafValue(AddrPool.NAMESPACE,
            "secondarydns",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "secondarydns" with operation "replace".
     */
    public void markSecondarydnsReplace() throws JNCException {
        markLeafReplace("secondarydns");
    }

    /**
     * Marks the leaf "secondarydns" with operation "merge".
     */
    public void markSecondarydnsMerge() throws JNCException {
        markLeafMerge("secondarydns");
    }

    /**
     * Marks the leaf "secondarydns" with operation "create".
     */
    public void markSecondarydnsCreate() throws JNCException {
        markLeafCreate("secondarydns");
    }

    /**
     * Marks the leaf "secondarydns" with operation "delete".
     */
    public void markSecondarydnsDelete() throws JNCException {
        markLeafDelete("secondarydns");
    }

    /* Access methods for container child: "address-pool-entries". */

    /**
     * Adds container entry "addressPoolEntries", using an existing object.
     * @param addressPoolEntries The object to add.
     * @return The added child.
     */
    public AddressPoolEntries addAddressPoolEntries(AddressPoolEntries addressPoolEntries)
            throws JNCException {
        this.addressPoolEntries = addressPoolEntries;
        insertChild(addressPoolEntries, childrenNames());
        return addressPoolEntries;
    }

    /**
     * Adds container entry "addressPoolEntries".
     * This method is used for creating subtree filters.
     * @return The added child.
     */
    public AddressPoolEntries addAddressPoolEntries() throws JNCException {
        AddressPoolEntries addressPoolEntries = new AddressPoolEntries();
        this.addressPoolEntries = addressPoolEntries;
        insertChild(addressPoolEntries, childrenNames());
        return addressPoolEntries;
    }

    /**
     * Deletes container entry "addressPoolEntries".
     * @return An array of the deleted element nodes.
     */
    public NodeSet deleteAddressPoolEntries() throws JNCException {
        this.addressPoolEntries = null;
        String path = "address-pool-entries";
        return delete(path);
    }

    /**
     * Support method for addChild.
     * Adds a child to this object.
     * 
     * @param child The child to add
     */
    public void addChild(Element child) {
        super.addChild(child);
        if (child instanceof AddressPoolEntries) addressPoolEntries = (AddressPoolEntries)child;
    }

}
