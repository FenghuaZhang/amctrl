package com.chinatele.app.amctrl.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BlockLacking {
	
	@XmlElement(name = "device-id")
    private int deviceId;

	@XmlElement(name = "domain-name")
    private String domainName;
	
	@XmlElement(name = "protocol-type")
    private int protocolType;

	public int getDeviceId() {
		return deviceId;
	}

	public String getDomainName() {
		return domainName;
	}

	public int getProtocolType() {
		return protocolType;
	}
	
}
