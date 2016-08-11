package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddressBlockIPv4 {

    @XmlElement(name = "ipv4-address-block-id")
    private int ipv4AddressBlockId;

    @XmlElement(name = "ipv4-address-block-name")
    private String ipv4AddressBlockName;

    @XmlElement(name = "average-address-usage-ratio")
    private int averageAddressUsageRatio;

    public int getIpv4AddressBlockId() {
        return ipv4AddressBlockId;
    }

    public String getIpv4AddressBlockName() {
        return ipv4AddressBlockName;
    }

    public int getAverageAddressUsageRatio() {
        return averageAddressUsageRatio;
    }

}
