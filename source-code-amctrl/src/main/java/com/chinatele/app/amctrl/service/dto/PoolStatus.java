package com.chinatele.app.amctrl.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PoolStatus {

    @XmlElement(name="device-id")
    private int deviceId;

    @XmlElement(name="time")
    private double time;

    @XmlElement(name = "address-pool")
    private List<AddressPool> addressPools;

    public int getDeviceId() {
        return deviceId;
    }

    public double getTime() {
        return time;
    }

    public List<AddressPool> getAddressPools() {
        return addressPools;
    }
}
