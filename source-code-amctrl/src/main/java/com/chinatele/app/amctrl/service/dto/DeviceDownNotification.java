package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notification", namespace = "urn:ietf:params:xml:ns:netconf:notification:1.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDownNotification extends BasicInfo{

    @XmlElement(name = "ama-report-offline-to-controller-msg")
    private DeviceDownMessage deviceDownMessage;

    public DeviceDownMessage getDeviceDownMessage() {
        return deviceDownMessage;
    }
}
