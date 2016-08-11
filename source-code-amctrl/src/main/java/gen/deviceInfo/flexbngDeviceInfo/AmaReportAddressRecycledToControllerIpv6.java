/* 
 * @(#)AmaReportAddressRecycledToControllerIpv6.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "flexbng-device-info", revision: "2015-11-11".
 */

package gen.deviceInfo.flexbngDeviceInfo;

import com.tailf.jnc.Element;
import com.tailf.jnc.JNCException;
import com.tailf.jnc.YangElement;
import com.tailf.jnc.YangString;
import com.tailf.jnc.YangUInt32;

import gen.deviceInfo.flexbngDeviceInfo.FlexbngVbras;
import gen.deviceInfo.flexbngDeviceInfo.ResultStatus;

/**
 * This class represents an element from 
 * the namespace urn:ietf:params:xml:ns:yang:ietf-address-pool:flexbng:vbras
 * generated to "src/gen/deviceInfo/flexbngDeviceInfo/ama-report-address-recycled-to-controller-ipv6"
 * <p>
 * See line 177 in
 * ../yang/flexbng-device-info.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class AmaReportAddressRecycledToControllerIpv6 extends YangElement {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for an empty AmaReportAddressRecycledToControllerIpv6 object.
     */
    public AmaReportAddressRecycledToControllerIpv6() {
        super(FlexbngVbras.NAMESPACE, "ama-report-address-recycled-to-controller-ipv6");
        setDefaultPrefix();
        setPrefix(FlexbngVbras.PREFIX);
    }

    /**
     * Clones this object, returning an exact copy.
     * @return A clone of the object.
     */
    public AmaReportAddressRecycledToControllerIpv6 clone() {
        return (AmaReportAddressRecycledToControllerIpv6)cloneContent(new AmaReportAddressRecycledToControllerIpv6());
    }

    /**
     * Clones this object, returning a shallow copy.
     * @return A clone of the object. Children are not included.
     */
    public AmaReportAddressRecycledToControllerIpv6 cloneShallow() {
        return (AmaReportAddressRecycledToControllerIpv6)cloneShallowContent(new AmaReportAddressRecycledToControllerIpv6());
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
            "domain-name",
            "address-pool-id",
            "address-pool-name",
            "address-block-id",
            "address-block-name",
            "result",
        };
    }

    /* Access methods for optional leaf child: "device-id". */

    /**
     * Gets the value for child leaf "device-id".
     * @return The value of the leaf.
     */
    public YangUInt32 getDeviceIdValue() throws JNCException {
        return (YangUInt32)getValue("device-id");
    }

    /**
     * Sets the value for child leaf "device-id",
     * using instance of generated typedef class.
     * @param deviceIdValue The value to set.
     * @param deviceIdValue used during instantiation.
     */
    public void setDeviceIdValue(YangUInt32 deviceIdValue) throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
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
        setLeafValue(FlexbngVbras.NAMESPACE,
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
        setLeafValue(FlexbngVbras.NAMESPACE,
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
        setLeafValue(FlexbngVbras.NAMESPACE,
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

    /* Access methods for optional leaf child: "address-pool-id". */

    /**
     * Gets the value for child leaf "address-pool-id".
     * @return The value of the leaf.
     */
    public YangUInt32 getAddressPoolIdValue() throws JNCException {
        return (YangUInt32)getValue("address-pool-id");
    }

    /**
     * Sets the value for child leaf "address-pool-id",
     * using instance of generated typedef class.
     * @param addressPoolIdValue The value to set.
     * @param addressPoolIdValue used during instantiation.
     */
    public void setAddressPoolIdValue(YangUInt32 addressPoolIdValue)
            throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
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
        setLeafValue(FlexbngVbras.NAMESPACE,
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

    /* Access methods for optional leaf child: "address-pool-name". */

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
        setLeafValue(FlexbngVbras.NAMESPACE,
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
     * Unsets the value for child leaf "address-pool-name".
     */
    public void unsetAddressPoolNameValue() throws JNCException {
        delete("address-pool-name");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "address-pool-name" leaf will not have a value.
     */
    public void addAddressPoolName() throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "address-pool-name",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "address-pool-name" with operation "replace".
     */
    public void markAddressPoolNameReplace() throws JNCException {
        markLeafReplace("addressPoolName");
    }

    /**
     * Marks the leaf "address-pool-name" with operation "merge".
     */
    public void markAddressPoolNameMerge() throws JNCException {
        markLeafMerge("addressPoolName");
    }

    /**
     * Marks the leaf "address-pool-name" with operation "create".
     */
    public void markAddressPoolNameCreate() throws JNCException {
        markLeafCreate("addressPoolName");
    }

    /**
     * Marks the leaf "address-pool-name" with operation "delete".
     */
    public void markAddressPoolNameDelete() throws JNCException {
        markLeafDelete("addressPoolName");
    }

    /* Access methods for optional leaf child: "address-block-id". */

    /**
     * Gets the value for child leaf "address-block-id".
     * @return The value of the leaf.
     */
    public YangUInt32 getAddressBlockIdValue() throws JNCException {
        return (YangUInt32)getValue("address-block-id");
    }

    /**
     * Sets the value for child leaf "address-block-id",
     * using instance of generated typedef class.
     * @param addressBlockIdValue The value to set.
     * @param addressBlockIdValue used during instantiation.
     */
    public void setAddressBlockIdValue(YangUInt32 addressBlockIdValue)
            throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "address-block-id",
            addressBlockIdValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "address-block-id",
     * using Java primitive values.
     * @param addressBlockIdValue used during instantiation.
     */
    public void setAddressBlockIdValue(long addressBlockIdValue)
            throws JNCException {
        setAddressBlockIdValue(new YangUInt32(addressBlockIdValue));
    }

    /**
     * Sets the value for child leaf "address-block-id",
     * using a String value.
     * @param addressBlockIdValue used during instantiation.
     */
    public void setAddressBlockIdValue(String addressBlockIdValue)
            throws JNCException {
        setAddressBlockIdValue(new YangUInt32(addressBlockIdValue));
    }

    /**
     * Unsets the value for child leaf "address-block-id".
     */
    public void unsetAddressBlockIdValue() throws JNCException {
        delete("address-block-id");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "address-block-id" leaf will not have a value.
     */
    public void addAddressBlockId() throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "address-block-id",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "address-block-id" with operation "replace".
     */
    public void markAddressBlockIdReplace() throws JNCException {
        markLeafReplace("addressBlockId");
    }

    /**
     * Marks the leaf "address-block-id" with operation "merge".
     */
    public void markAddressBlockIdMerge() throws JNCException {
        markLeafMerge("addressBlockId");
    }

    /**
     * Marks the leaf "address-block-id" with operation "create".
     */
    public void markAddressBlockIdCreate() throws JNCException {
        markLeafCreate("addressBlockId");
    }

    /**
     * Marks the leaf "address-block-id" with operation "delete".
     */
    public void markAddressBlockIdDelete() throws JNCException {
        markLeafDelete("addressBlockId");
    }

    /* Access methods for optional leaf child: "address-block-name". */

    /**
     * Gets the value for child leaf "address-block-name".
     * @return The value of the leaf.
     */
    public YangString getAddressBlockNameValue() throws JNCException {
        return (YangString)getValue("address-block-name");
    }

    /**
     * Sets the value for child leaf "address-block-name",
     * using instance of generated typedef class.
     * @param addressBlockNameValue The value to set.
     * @param addressBlockNameValue used during instantiation.
     */
    public void setAddressBlockNameValue(YangString addressBlockNameValue)
            throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "address-block-name",
            addressBlockNameValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "address-block-name",
     * using a String value.
     * @param addressBlockNameValue used during instantiation.
     */
    public void setAddressBlockNameValue(String addressBlockNameValue)
            throws JNCException {
        setAddressBlockNameValue(new YangString(addressBlockNameValue));
    }

    /**
     * Unsets the value for child leaf "address-block-name".
     */
    public void unsetAddressBlockNameValue() throws JNCException {
        delete("address-block-name");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "address-block-name" leaf will not have a value.
     */
    public void addAddressBlockName() throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "address-block-name",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "address-block-name" with operation "replace".
     */
    public void markAddressBlockNameReplace() throws JNCException {
        markLeafReplace("addressBlockName");
    }

    /**
     * Marks the leaf "address-block-name" with operation "merge".
     */
    public void markAddressBlockNameMerge() throws JNCException {
        markLeafMerge("addressBlockName");
    }

    /**
     * Marks the leaf "address-block-name" with operation "create".
     */
    public void markAddressBlockNameCreate() throws JNCException {
        markLeafCreate("addressBlockName");
    }

    /**
     * Marks the leaf "address-block-name" with operation "delete".
     */
    public void markAddressBlockNameDelete() throws JNCException {
        markLeafDelete("addressBlockName");
    }

    /* Access methods for optional leaf child: "result". */

    /**
     * Gets the value for child leaf "result".
     * @return The value of the leaf.
     */
    public ResultStatus getResultValue() throws JNCException {
        return (ResultStatus)getValue("result");
    }

    /**
     * Sets the value for child leaf "result",
     * using a JNC type value.
     * @param resultValue The value to set.
     * @param resultValue used during instantiation.
     */
    public void setResultValue(ResultStatus resultValue) throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "result",
            resultValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "result",
     * using a String value.
     * @param resultValue used during instantiation.
     */
    public void setResultValue(String resultValue) throws JNCException {
        setResultValue(new ResultStatus(resultValue));
    }

    /**
     * Unsets the value for child leaf "result".
     */
    public void unsetResultValue() throws JNCException {
        delete("result");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "result" leaf will not have a value.
     */
    public void addResult() throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "result",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "result" with operation "replace".
     */
    public void markResultReplace() throws JNCException {
        markLeafReplace("result");
    }

    /**
     * Marks the leaf "result" with operation "merge".
     */
    public void markResultMerge() throws JNCException {
        markLeafMerge("result");
    }

    /**
     * Marks the leaf "result" with operation "create".
     */
    public void markResultCreate() throws JNCException {
        markLeafCreate("result");
    }

    /**
     * Marks the leaf "result" with operation "delete".
     */
    public void markResultDelete() throws JNCException {
        markLeafDelete("result");
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
