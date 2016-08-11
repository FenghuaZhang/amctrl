package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceDownMessage {

    @XmlElement(name = "ama-report-offline-to-controller")
    private DeviceDown deviceDown;

    public DeviceDown getDeviceDown() {
        return deviceDown;
    }
}
