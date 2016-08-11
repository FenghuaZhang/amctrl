package com.chinatele.app.amctrl.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddressPoolEntities {

    @XmlElement(name = "ipv4-address-block")
    private List<AddressBlockIPv4> ipv4AddressBlocks;

    @XmlElement(name = "ipv6-address-block")
    private List<AddressBlockIPv6> ipv6AddressBlocks;

    public List<AddressBlockIPv4> getIpv4AddressBlocks() {
        return ipv4AddressBlocks;
    }

    public List<AddressBlockIPv6> getIpv6AddressBlocks() {
        return ipv6AddressBlocks;
    }

}
