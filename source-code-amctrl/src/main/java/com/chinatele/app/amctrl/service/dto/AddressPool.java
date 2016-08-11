package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AddressPool {

    @XmlElement(name = "domain-name")
    private String domainName;

    @XmlElement(name = "address-pool-name")
    private String addressPoolName;

    @XmlElement(name = "address-pool-id")
    private int addressPoolId;

    @XmlElement(name = "address-pool-entries")
    private AddressPoolEntities addressPoolEntities;

    public String getDomainName() {
        return domainName;
    }

    public String getAddressPoolName() {
        return addressPoolName;
    }

    public int getAddressPoolId() {
        return addressPoolId;
    }

    public AddressPoolEntities getAddressPoolEntities() {
        return addressPoolEntities;
    }
}
