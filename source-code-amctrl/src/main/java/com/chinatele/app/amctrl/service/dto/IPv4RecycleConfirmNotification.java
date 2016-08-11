package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notification", namespace = "urn:ietf:params:xml:ns:netconf:notification:1.0")
@XmlAccessorType(XmlAccessType.FIELD)
public class IPv4RecycleConfirmNotification extends BasicInfo{

    @XmlElement(name = "ama-report-address-recycled-to-controller-ipv4")
    private ConfirmInfo confirmInfo;

    public ConfirmInfo getConfirmInfo() {
        return confirmInfo;
    }
}
