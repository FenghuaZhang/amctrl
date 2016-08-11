package com.chinatele.app.amctrl.rest.vo.request;

public class DeviceConfigInfo {

    /** 设备的唯一标识 */
    private int device_id;

    /** 设备类型 */
    private String device_type;

    /** 设备的ip地址 */
    private String device_ip;

    /** 设备的端口 */
    private int device_port;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;

    /** 心跳间隔(单位：秒) */
    private int heartbeat_interval;

    /** ip4地址池申请阈值 */
    private float ipv4_threshold;

    /** ipv6地址池申请阈值 */
    private float ipv6_threshold;

    /** device向控制器汇报状态更新时间间隔(单位：秒) */
    private int state_update_interval;

    /** 采样周期(单位：秒) */
    private int device_sampling_interval;

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_ip() {
        return device_ip;
    }

    public void setDevice_ip(String device_ip) {
        this.device_ip = device_ip;
    }

    public int getDevice_port() {
        return device_port;
    }

    public void setDevice_port(int device_port) {
        this.device_port = device_port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHeartbeat_interval() {
        return heartbeat_interval;
    }

    public void setHeartbeat_interval(int heartbeat_interval) {
        this.heartbeat_interval = heartbeat_interval;
    }

    public float getIpv4_threshold() {
        return ipv4_threshold;
    }

    public void setIpv4_threshold(float ipv4_threshold) {
        this.ipv4_threshold = ipv4_threshold;
    }

    public float getIpv6_threshold() {
        return ipv6_threshold;
    }

    public void setIpv6_threshold(float ipv6_threshold) {
        this.ipv6_threshold = ipv6_threshold;
    }

    public int getState_update_interval() {
        return state_update_interval;
    }

    public void setState_update_interval(int state_update_interval) {
        this.state_update_interval = state_update_interval;
    }

    public int getDevice_sampling_interval() {
        return device_sampling_interval;
    }

    public void setDevice_sampling_interval(int device_sampling_interval) {
        this.device_sampling_interval = device_sampling_interval;
    }
}
