package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterConfirm {

    @XmlElement(name = "device-id")
    private int deviceId;

    public int getDeviceId() {
        return deviceId;
    }
}
