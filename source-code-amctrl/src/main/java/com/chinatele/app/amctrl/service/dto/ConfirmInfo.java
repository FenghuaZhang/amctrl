package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ConfirmInfo {

    @XmlElement(name = "device-id")
    private int deviceId;

    @XmlElement(name = "domain-name")
    private String domainName;

    @XmlElement(name = "address-pool-id")
    private int addressPoolId;

    @XmlElement(name = "address-pool-name")
    private String addressPoolName;

    @XmlElement(name = "address-block-id")
    private int addressBlockId;

    @XmlElement(name = "address-block-name")
    private String addressBlockName;

    @XmlElement(name = "result")
    private String result;

    public int getDeviceId() {
        return deviceId;
    }

    public String getDomainName() {
        return domainName;
    }

    public int getAddressPoolId() {
        return addressPoolId;
    }

    public String getAddressPoolName() {
        return addressPoolName;
    }

    public int getAddressBlockId() {
        return addressBlockId;
    }

    public String getAddressBlockName() {
        return addressBlockName;
    }

    public String getResult() {
        return result;
    }

}
