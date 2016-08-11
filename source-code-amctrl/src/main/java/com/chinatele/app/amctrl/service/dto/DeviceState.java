package com.chinatele.app.amctrl.service.dto;

import java.util.Date;

public class DeviceState {

    /** 设备唯一标识 */
    private int deviceId;

    /** 状态时间戳 */
    private Date time;

    /** 状态(0:连接中, 1:活着, 2:死亡) */
    private int isAlive;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(int isAlive) {
        this.isAlive = isAlive;
    }
}
