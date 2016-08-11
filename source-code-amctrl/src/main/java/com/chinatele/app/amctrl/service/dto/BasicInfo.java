package com.chinatele.app.amctrl.service.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

public class BasicInfo {

    @XmlElement(name = "eventTime")
    private Date eventTime;

    public Date getEventTime() {
        return eventTime;
    }
}
