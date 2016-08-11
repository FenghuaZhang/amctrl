package com.chinatele.app.amctrl.rest.vo.response;

public class ReportDeviceState {

    /** 设备唯一标识 */
    private int device_id;

    /** 存活状态('y':活着,'n':死亡) */
    private String is_alive;

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getIs_alive() {
        return is_alive;
    }

    public void setIs_alive(String is_alive) {
        this.is_alive = is_alive;
    }
}
