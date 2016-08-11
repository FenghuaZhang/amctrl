/* 
 * @(#)ControllerSendConfigurationToAma.java        1.0 18/02/16
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
import gen.deviceInfo.tailfXsdTypes.JDouble;

/**
 * This class represents an element from 
 * the namespace urn:ietf:params:xml:ns:yang:ietf-address-pool:flexbng:vbras
 * generated to "src/gen/deviceInfo/flexbngDeviceInfo/controller-send-configuration-to-ama"
 * <p>
 * See line 64 in
 * ../yang/flexbng-device-info.yang
 *
 * @version 1.0 2016-02-18
 * @author Auto Generated
 */
public class ControllerSendConfigurationToAma extends YangElement {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for an empty ControllerSendConfigurationToAma object.
     */
    public ControllerSendConfigurationToAma() {
        super(FlexbngVbras.NAMESPACE, "controller-send-configuration-to-ama");
        setDefaultPrefix();
        setPrefix(FlexbngVbras.PREFIX);
    }

    /**
     * Clones this object, returning an exact copy.
     * @return A clone of the object.
     */
    public ControllerSendConfigurationToAma clone() {
        return (ControllerSendConfigurationToAma)cloneContent(new ControllerSendConfigurationToAma());
    }

    /**
     * Clones this object, returning a shallow copy.
     * @return A clone of the object. Children are not included.
     */
    public ControllerSendConfigurationToAma cloneShallow() {
        return (ControllerSendConfigurationToAma)cloneShallowContent(new ControllerSendConfigurationToAma());
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
            "device-keep-alive-interval",
            "ipv4-address-pool-usage-threshold",
            "ipv6-address-pool-usage-threshold",
            "state-update-interval",
            "device-sampling-interval",
        };
    }

    /* Access methods for optional leaf child: "device-keep-alive-interval". */

    /**
     * Gets the value for child leaf "device-keep-alive-interval".
     * @return The value of the leaf.
     */
    public YangUInt32 getDeviceKeepAliveIntervalValue() throws JNCException {
        YangUInt32 deviceKeepAliveInterval = (YangUInt32)getValue("device-keep-alive-interval");
        if (deviceKeepAliveInterval == null) {
            deviceKeepAliveInterval = new YangUInt32("0");  // default
        }
        return deviceKeepAliveInterval;
    }

    /**
     * Sets the value for child leaf "device-keep-alive-interval",
     * using instance of generated typedef class.
     * @param deviceKeepAliveIntervalValue The value to set.
     * @param deviceKeepAliveIntervalValue used during instantiation.
     */
    public void setDeviceKeepAliveIntervalValue(YangUInt32 deviceKeepAliveIntervalValue)
            throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "device-keep-alive-interval",
            deviceKeepAliveIntervalValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "device-keep-alive-interval",
     * using Java primitive values.
     * @param deviceKeepAliveIntervalValue used during instantiation.
     */
    public void setDeviceKeepAliveIntervalValue(long deviceKeepAliveIntervalValue)
            throws JNCException {
        setDeviceKeepAliveIntervalValue(new YangUInt32(deviceKeepAliveIntervalValue));
    }

    /**
     * Sets the value for child leaf "device-keep-alive-interval",
     * using a String value.
     * @param deviceKeepAliveIntervalValue used during instantiation.
     */
    public void setDeviceKeepAliveIntervalValue(String deviceKeepAliveIntervalValue)
            throws JNCException {
        setDeviceKeepAliveIntervalValue(new YangUInt32(deviceKeepAliveIntervalValue));
    }

    /**
     * Unsets the value for child leaf "device-keep-alive-interval".
     */
    public void unsetDeviceKeepAliveIntervalValue() throws JNCException {
        delete("device-keep-alive-interval");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "device-keep-alive-interval" leaf will not have a value.
     */
    public void addDeviceKeepAliveInterval() throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "device-keep-alive-interval",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "device-keep-alive-interval" with operation "replace".
     */
    public void markDeviceKeepAliveIntervalReplace() throws JNCException {
        markLeafReplace("deviceKeepAliveInterval");
    }

    /**
     * Marks the leaf "device-keep-alive-interval" with operation "merge".
     */
    public void markDeviceKeepAliveIntervalMerge() throws JNCException {
        markLeafMerge("deviceKeepAliveInterval");
    }

    /**
     * Marks the leaf "device-keep-alive-interval" with operation "create".
     */
    public void markDeviceKeepAliveIntervalCreate() throws JNCException {
        markLeafCreate("deviceKeepAliveInterval");
    }

    /**
     * Marks the leaf "device-keep-alive-interval" with operation "delete".
     */
    public void markDeviceKeepAliveIntervalDelete() throws JNCException {
        markLeafDelete("deviceKeepAliveInterval");
    }

    /* Access methods for optional leaf child: "ipv4-address-pool-usage-threshold". */

    /**
     * Gets the value for child leaf "ipv4-address-pool-usage-threshold".
     * @return The value of the leaf.
     */
    public JDouble getIpv4AddressPoolUsageThresholdValue() throws JNCException {
        JDouble ipv4AddressPoolUsageThreshold = (JDouble)getValue("ipv4-address-pool-usage-threshold");
        if (ipv4AddressPoolUsageThreshold == null) {
            ipv4AddressPoolUsageThreshold = new JDouble("0");  // default
        }
        return ipv4AddressPoolUsageThreshold;
    }

    /**
     * Sets the value for child leaf "ipv4-address-pool-usage-threshold",
     * using a JNC type value.
     * @param ipv4AddressPoolUsageThresholdValue The value to set.
     * @param ipv4AddressPoolUsageThresholdValue used during instantiation.
     */
    public void setIpv4AddressPoolUsageThresholdValue(JDouble ipv4AddressPoolUsageThresholdValue)
            throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "ipv4-address-pool-usage-threshold",
            ipv4AddressPoolUsageThresholdValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "ipv4-address-pool-usage-threshold",
     * using a String value.
     * @param ipv4AddressPoolUsageThresholdValue used during instantiation.
     */
    public void setIpv4AddressPoolUsageThresholdValue(String ipv4AddressPoolUsageThresholdValue)
            throws JNCException {
        setIpv4AddressPoolUsageThresholdValue(new JDouble(ipv4AddressPoolUsageThresholdValue));
    }

    /**
     * Unsets the value for child leaf "ipv4-address-pool-usage-threshold".
     */
    public void unsetIpv4AddressPoolUsageThresholdValue() throws JNCException {
        delete("ipv4-address-pool-usage-threshold");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "ipv4-address-pool-usage-threshold" leaf will not have a value.
     */
    public void addIpv4AddressPoolUsageThreshold() throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "ipv4-address-pool-usage-threshold",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "ipv4-address-pool-usage-threshold" with operation "replace".
     */
    public void markIpv4AddressPoolUsageThresholdReplace() throws JNCException {
        markLeafReplace("ipv4AddressPoolUsageThreshold");
    }

    /**
     * Marks the leaf "ipv4-address-pool-usage-threshold" with operation "merge".
     */
    public void markIpv4AddressPoolUsageThresholdMerge() throws JNCException {
        markLeafMerge("ipv4AddressPoolUsageThreshold");
    }

    /**
     * Marks the leaf "ipv4-address-pool-usage-threshold" with operation "create".
     */
    public void markIpv4AddressPoolUsageThresholdCreate() throws JNCException {
        markLeafCreate("ipv4AddressPoolUsageThreshold");
    }

    /**
     * Marks the leaf "ipv4-address-pool-usage-threshold" with operation "delete".
     */
    public void markIpv4AddressPoolUsageThresholdDelete() throws JNCException {
        markLeafDelete("ipv4AddressPoolUsageThreshold");
    }

    /* Access methods for optional leaf child: "ipv6-address-pool-usage-threshold". */

    /**
     * Gets the value for child leaf "ipv6-address-pool-usage-threshold".
     * @return The value of the leaf.
     */
    public JDouble getIpv6AddressPoolUsageThresholdValue() throws JNCException {
        JDouble ipv6AddressPoolUsageThreshold = (JDouble)getValue("ipv6-address-pool-usage-threshold");
        if (ipv6AddressPoolUsageThreshold == null) {
            ipv6AddressPoolUsageThreshold = new JDouble("0");  // default
        }
        return ipv6AddressPoolUsageThreshold;
    }

    /**
     * Sets the value for child leaf "ipv6-address-pool-usage-threshold",
     * using a JNC type value.
     * @param ipv6AddressPoolUsageThresholdValue The value to set.
     * @param ipv6AddressPoolUsageThresholdValue used during instantiation.
     */
    public void setIpv6AddressPoolUsageThresholdValue(JDouble ipv6AddressPoolUsageThresholdValue)
            throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "ipv6-address-pool-usage-threshold",
            ipv6AddressPoolUsageThresholdValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "ipv6-address-pool-usage-threshold",
     * using a String value.
     * @param ipv6AddressPoolUsageThresholdValue used during instantiation.
     */
    public void setIpv6AddressPoolUsageThresholdValue(String ipv6AddressPoolUsageThresholdValue)
            throws JNCException {
        setIpv6AddressPoolUsageThresholdValue(new JDouble(ipv6AddressPoolUsageThresholdValue));
    }

    /**
     * Unsets the value for child leaf "ipv6-address-pool-usage-threshold".
     */
    public void unsetIpv6AddressPoolUsageThresholdValue() throws JNCException {
        delete("ipv6-address-pool-usage-threshold");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "ipv6-address-pool-usage-threshold" leaf will not have a value.
     */
    public void addIpv6AddressPoolUsageThreshold() throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "ipv6-address-pool-usage-threshold",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "ipv6-address-pool-usage-threshold" with operation "replace".
     */
    public void markIpv6AddressPoolUsageThresholdReplace() throws JNCException {
        markLeafReplace("ipv6AddressPoolUsageThreshold");
    }

    /**
     * Marks the leaf "ipv6-address-pool-usage-threshold" with operation "merge".
     */
    public void markIpv6AddressPoolUsageThresholdMerge() throws JNCException {
        markLeafMerge("ipv6AddressPoolUsageThreshold");
    }

    /**
     * Marks the leaf "ipv6-address-pool-usage-threshold" with operation "create".
     */
    public void markIpv6AddressPoolUsageThresholdCreate() throws JNCException {
        markLeafCreate("ipv6AddressPoolUsageThreshold");
    }

    /**
     * Marks the leaf "ipv6-address-pool-usage-threshold" with operation "delete".
     */
    public void markIpv6AddressPoolUsageThresholdDelete() throws JNCException {
        markLeafDelete("ipv6AddressPoolUsageThreshold");
    }

    /* Access methods for optional leaf child: "state-update-interval". */

    /**
     * Gets the value for child leaf "state-update-interval".
     * @return The value of the leaf.
     */
    public YangUInt32 getStateUpdateIntervalValue() throws JNCException {
        YangUInt32 stateUpdateInterval = (YangUInt32)getValue("state-update-interval");
        if (stateUpdateInterval == null) {
            stateUpdateInterval = new YangUInt32("0");  // default
        }
        return stateUpdateInterval;
    }

    /**
     * Sets the value for child leaf "state-update-interval",
     * using instance of generated typedef class.
     * @param stateUpdateIntervalValue The value to set.
     * @param stateUpdateIntervalValue used during instantiation.
     */
    public void setStateUpdateIntervalValue(YangUInt32 stateUpdateIntervalValue)
            throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "state-update-interval",
            stateUpdateIntervalValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "state-update-interval",
     * using Java primitive values.
     * @param stateUpdateIntervalValue used during instantiation.
     */
    public void setStateUpdateIntervalValue(long stateUpdateIntervalValue)
            throws JNCException {
        setStateUpdateIntervalValue(new YangUInt32(stateUpdateIntervalValue));
    }

    /**
     * Sets the value for child leaf "state-update-interval",
     * using a String value.
     * @param stateUpdateIntervalValue used during instantiation.
     */
    public void setStateUpdateIntervalValue(String stateUpdateIntervalValue)
            throws JNCException {
        setStateUpdateIntervalValue(new YangUInt32(stateUpdateIntervalValue));
    }

    /**
     * Unsets the value for child leaf "state-update-interval".
     */
    public void unsetStateUpdateIntervalValue() throws JNCException {
        delete("state-update-interval");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "state-update-interval" leaf will not have a value.
     */
    public void addStateUpdateInterval() throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "state-update-interval",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "state-update-interval" with operation "replace".
     */
    public void markStateUpdateIntervalReplace() throws JNCException {
        markLeafReplace("stateUpdateInterval");
    }

    /**
     * Marks the leaf "state-update-interval" with operation "merge".
     */
    public void markStateUpdateIntervalMerge() throws JNCException {
        markLeafMerge("stateUpdateInterval");
    }

    /**
     * Marks the leaf "state-update-interval" with operation "create".
     */
    public void markStateUpdateIntervalCreate() throws JNCException {
        markLeafCreate("stateUpdateInterval");
    }

    /**
     * Marks the leaf "state-update-interval" with operation "delete".
     */
    public void markStateUpdateIntervalDelete() throws JNCException {
        markLeafDelete("stateUpdateInterval");
    }

    /* Access methods for optional leaf child: "device-sampling-interval". */

    /**
     * Gets the value for child leaf "device-sampling-interval".
     * @return The value of the leaf.
     */
    public YangUInt32 getDeviceSamplingIntervalValue() throws JNCException {
        YangUInt32 deviceSamplingInterval = (YangUInt32)getValue("device-sampling-interval");
        if (deviceSamplingInterval == null) {
            deviceSamplingInterval = new YangUInt32("0");  // default
        }
        return deviceSamplingInterval;
    }

    /**
     * Sets the value for child leaf "device-sampling-interval",
     * using instance of generated typedef class.
     * @param deviceSamplingIntervalValue The value to set.
     * @param deviceSamplingIntervalValue used during instantiation.
     */
    public void setDeviceSamplingIntervalValue(YangUInt32 deviceSamplingIntervalValue)
            throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "device-sampling-interval",
            deviceSamplingIntervalValue,
            childrenNames());
    }

    /**
     * Sets the value for child leaf "device-sampling-interval",
     * using Java primitive values.
     * @param deviceSamplingIntervalValue used during instantiation.
     */
    public void setDeviceSamplingIntervalValue(long deviceSamplingIntervalValue)
            throws JNCException {
        setDeviceSamplingIntervalValue(new YangUInt32(deviceSamplingIntervalValue));
    }

    /**
     * Sets the value for child leaf "device-sampling-interval",
     * using a String value.
     * @param deviceSamplingIntervalValue used during instantiation.
     */
    public void setDeviceSamplingIntervalValue(String deviceSamplingIntervalValue)
            throws JNCException {
        setDeviceSamplingIntervalValue(new YangUInt32(deviceSamplingIntervalValue));
    }

    /**
     * Unsets the value for child leaf "device-sampling-interval".
     */
    public void unsetDeviceSamplingIntervalValue() throws JNCException {
        delete("device-sampling-interval");
    }

    /**
     * This method is used for creating a subtree filter.
     * The added "device-sampling-interval" leaf will not have a value.
     */
    public void addDeviceSamplingInterval() throws JNCException {
        setLeafValue(FlexbngVbras.NAMESPACE,
            "device-sampling-interval",
            null,
            childrenNames());
    }

    /**
     * Marks the leaf "device-sampling-interval" with operation "replace".
     */
    public void markDeviceSamplingIntervalReplace() throws JNCException {
        markLeafReplace("deviceSamplingInterval");
    }

    /**
     * Marks the leaf "device-sampling-interval" with operation "merge".
     */
    public void markDeviceSamplingIntervalMerge() throws JNCException {
        markLeafMerge("deviceSamplingInterval");
    }

    /**
     * Marks the leaf "device-sampling-interval" with operation "create".
     */
    public void markDeviceSamplingIntervalCreate() throws JNCException {
        markLeafCreate("deviceSamplingInterval");
    }

    /**
     * Marks the leaf "device-sampling-interval" with operation "delete".
     */
    public void markDeviceSamplingIntervalDelete() throws JNCException {
        markLeafDelete("deviceSamplingInterval");
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
