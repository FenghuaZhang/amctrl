package com.chinatele.app.amctrl.rest.vo;

public class ConnectionConfigInfo {

	/** 设备Id */
    private int deviceId;

    /** 被管理的device Ip */
    private String manageIp;
    
    /** 被管理的device 端口 */
    private int port;

    /** 心跳间隔时间-秒 */
    private int heartbeatInterval;

    /** IPv4阈值 */
    private double ipv4Threshold;

    /** IPv6阈值 */
    private double ipv6Threshold;

    /** 状态上报间隔时间 -秒 */
    private int stateUpdateInterval;

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getManageIp() {
		return manageIp;
	}

	public void setManageIp(String manageIp) {
		this.manageIp = manageIp;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getHeartbeatInterval() {
		return heartbeatInterval;
	}

	public void setHeartbeatInterval(int heartbeatInterval) {
		this.heartbeatInterval = heartbeatInterval;
	}

	public double getIpv4Threshold() {
		return ipv4Threshold;
	}

	public void setIpv4Threshold(double ipv4Threshold) {
		this.ipv4Threshold = ipv4Threshold;
	}

	public double getIpv6Threshold() {
		return ipv6Threshold;
	}

	public void setIpv6Threshold(double ipv6Threshold) {
		this.ipv6Threshold = ipv6Threshold;
	}

	public int getStateUpdateInterval() {
		return stateUpdateInterval;
	}

	public void setStateUpdateInterval(int stateUpdateInterval) {
		this.stateUpdateInterval = stateUpdateInterval;
	}
}
