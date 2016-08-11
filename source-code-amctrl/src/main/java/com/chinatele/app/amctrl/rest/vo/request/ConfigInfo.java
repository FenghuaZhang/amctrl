package com.chinatele.app.amctrl.rest.vo.request;

import java.util.List;


public class ConfigInfo {

    /** 控制器的唯一标识 */
    private int controller_id;

    /** device列表 */
    private List<Device> device_list;

    /** 心跳间隔(单位：秒) */
    private int device_keep_alive_interval;

    /** ip4地址池申请阈值 */
    private float ipv4_address_pool_usage_threshold;

    /** ipv6地址池申请阈值 */
    private float ipv6_address_pool_usage_threshold;

    /** device向控制器汇报状态更新时间间隔(单位：秒) */
    private int state_update_interval;

    /** 采样周期(单位：秒) */
    private int device_sampling_interval;

    public int getController_id() {
        return controller_id;
    }

    public void setController_id(int controller_id) {
        this.controller_id = controller_id;
    }

    public List<Device> getDevice_list() {
        return device_list;
    }

    public void setDevice_list(List<Device> device_list) {
        this.device_list = device_list;
    }

    public int getDevice_keep_alive_interval() {
        return device_keep_alive_interval;
    }

    public void setDevice_keep_alive_interval(int device_keep_alive_interval) {
        this.device_keep_alive_interval = device_keep_alive_interval;
    }

    public float getIpv4_address_pool_usage_threshold() {
        return ipv4_address_pool_usage_threshold;
    }

    public void setIpv4_address_pool_usage_threshold(float ipv4_address_pool_usage_threshold) {
        this.ipv4_address_pool_usage_threshold = ipv4_address_pool_usage_threshold;
    }

    public float getIpv6_address_pool_usage_threshold() {
        return ipv6_address_pool_usage_threshold;
    }

    public void setIpv6_address_pool_usage_threshold(float ipv6_address_pool_usage_threshold) {
        this.ipv6_address_pool_usage_threshold = ipv6_address_pool_usage_threshold;
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
