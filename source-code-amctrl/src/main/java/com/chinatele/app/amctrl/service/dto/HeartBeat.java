package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class HeartBeat {

    @XmlElement(name = "ama-id")
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }
}
