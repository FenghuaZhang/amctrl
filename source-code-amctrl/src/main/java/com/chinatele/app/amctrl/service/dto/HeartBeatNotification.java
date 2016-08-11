package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notification", namespace = "urn:ietf:params:xml:ns:netconf:notification:1.0")
public class HeartBeatNotification extends BasicInfo{

    @XmlElement(name = "ama-report-heart-beat-to-controller")
    private HeartBeat hearBeat;

    public HeartBeat getHearBeat() {
        return hearBeat;
    }
}
