package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddressBlockIPv6 {

    @XmlElement(name = "ipv6-address-block-id")
    private int ipv6AddressBlockId;

    @XmlElement(name = "ipv6-address-block-name")
    private String ipv6AddressBlockName;

    @XmlElement(name = "average-address-usage-ratio")
    private int averageAddressUsageRatio;

    public int getIpv6AddressBlockId() {
        return ipv6AddressBlockId;
    }

    public String getIpv6AddressBlockName() {
        return ipv6AddressBlockName;
    }

    public int getAverageAddressUsageRatio() {
        return averageAddressUsageRatio;
    }


}
