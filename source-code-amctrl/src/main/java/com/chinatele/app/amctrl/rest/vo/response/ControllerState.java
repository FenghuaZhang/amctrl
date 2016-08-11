package com.chinatele.app.amctrl.rest.vo.response;

import java.util.List;


public class ControllerState {

    /** 控制器唯一标识 */
    private int controller_id;

    /** 是否存活('y':活着,'n':死亡) */
    private String is_alive;

    /** 设备列表 */
    private List<ReportDeviceState> device_list;

    public int getController_id() {
        return controller_id;
    }

    public void setController_id(int controller_id) {
        this.controller_id = controller_id;
    }

    public String getIs_alive() {
        return is_alive;
    }

    public void setIs_alive(String is_alive) {
        this.is_alive = is_alive;
    }

    public List<ReportDeviceState> getDevice_list() {
        return device_list;
    }

    public void setDevice_list(List<ReportDeviceState> device_list) {
        this.device_list = device_list;
    }

}
