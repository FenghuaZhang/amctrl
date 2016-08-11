package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "notification", namespace = "urn:ietf:params:xml:ns:netconf:notification:1.0")
@XmlType
public class PoolStatusNotification extends BasicInfo {

    @XmlElement(name = "address-pool-status")
    private PoolStatus poolStatus;

    public PoolStatus getPoolStatus() {
        return poolStatus;
    }

}
