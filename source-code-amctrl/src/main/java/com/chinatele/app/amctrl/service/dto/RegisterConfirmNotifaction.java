package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notification", namespace = "urn:ietf:params:xml:ns:netconf:notification:1.0")
public class RegisterConfirmNotifaction {

    @XmlElement(name = "ama-register-to-controller")
    private RegisterConfirm registerConfirm;

    public RegisterConfirm getRegisterConfirm() {
        return registerConfirm;
    }

}
