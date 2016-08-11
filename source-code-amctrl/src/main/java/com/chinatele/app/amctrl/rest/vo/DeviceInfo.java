package com.chinatele.app.amctrl.rest.vo;

public class DeviceInfo {

    /** 设备ip */
    private String device_ip;

    /** 设备端口 */
    private int device_port;

    /** 设备唯一标识 */
    private String device_id;

    /** 经度 */
    private float position_longitude;

    /** 纬度 */
    private float position_latitude;

    /** 厂商信息 */
    private String vendor_name;

    /** 设备管理的用户所在的区域 */
    private String manage_area;

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

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public float getPosition_longitude() {
        return position_longitude;
    }

    public void setPosition_longitude(float position_longitude) {
        this.position_longitude = position_longitude;
    }

    public float getPosition_latitude() {
        return position_latitude;
    }

    public void setPosition_latitude(float position_latitude) {
        this.position_latitude = position_latitude;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getManage_area() {
        return manage_area;
    }

    public void setManage_area(String manage_area) {
        this.manage_area = manage_area;
    }
}
