package com.chinatele.app.amctrl.rest.vo;

public class DeviceDown {

    /** 设备唯一标识 */
    private String device_id;

    /** 下线原由 */
    private String reason;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
