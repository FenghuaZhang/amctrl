/* 
 * @(#)ControllerNetconfRegistration.java        1.0 18/02/16
 *
 * This file has been auto-generated by JNC, the
 * Java output format plug-in of pyang.
 * Origin: module "flexbng-device-info", revision: "2015-11-11".
 */

package gen.deviceInfo.flexbngDeviceInfo;

import com.tailf.jnc.Element;
import com.tailf.jnc.JNCException;
import com.tailf.jnc.YangElement;
import com.tailf.jnc.YangUInt32;

import gen.deviceInfo.flexbngDeviceInfo.FlexbngVbras;

/**
 * This class represents an element from 
 * the namespace urn:ietf:params:xml:ns:yang:ietf-address-pool:flexbng:vbras
 * generated to "src/gen/deviceInfo/flexbngDeviceInfo/controller-netconf-registration"
 * <p>
 * See line 49 in
 * ../yang/flexbng-device-info.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class ControllerNetconfRegistration extends YangElement {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for an empty ControllerNetconfRegistration object.
     */
    public ControllerNetconfRegistration() {
        super(FlexbngVbras.NAMESPACE, "controller-netconf-registration");
        setDefaultPrefix();
        setPrefix(FlexbngVbras.PREFIX);
    }

    /**
     * Clones this object, returning an exact copy.
     * @return A clone of the object.
     */
    public ControllerNetconfRegistration clone() {
        return (ControllerNetconfRegistration)cloneContent(new ControllerNetconfRegistration());
    }

    /**
     * Clones this object, returning a shallow copy.
     * @return A clone of the object. Children are not included.
     */
    public ControllerNetconfRegistration cloneShallow() {
        return (ControllerNetconfRegistration)cloneShallowContent(new ControllerNetconfRegistration());
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
