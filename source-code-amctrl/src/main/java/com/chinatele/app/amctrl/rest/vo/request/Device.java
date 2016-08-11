package com.chinatele.app.amctrl.rest.vo.request;

public class Device {

    /** 设备的唯一标识 */
    private int device_id;

    /** 设备类型 */
    private String device_type;

    /** 设备的ip地址 */
    private String device_ip;

    /** 设备的端口 */
    private int device_port;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 心跳时间间隔 */
    private int device_keep_alive_interval;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDevice_keep_alive_interval() {
        return device_keep_alive_interval;
    }

    public void setDevice_keep_alive_interval(int device_keep_alive_interval) {
        this.device_keep_alive_interval = device_keep_alive_interval;
    }
}
