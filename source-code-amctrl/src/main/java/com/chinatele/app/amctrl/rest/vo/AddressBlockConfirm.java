package com.chinatele.app.amctrl.rest.vo;

public class AddressBlockConfirm {

    /** 设备唯一标识 */
    private String device_id;

    /** 操作类型(0:下发地址块,1：回收地址块) */
    private int operate_type;

    /** 地址池名称 */
    private String pool_name;

    /** 地址块唯一标识 */
    private int block_id;

    /** 地址块名称 */
    private String ip_block_name;

    /** 结果状态(0:success,1:failed) */
    private int result_status;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public int getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(int operate_type) {
        this.operate_type = operate_type;
    }

    public String getPool_name() {
        return pool_name;
    }

    public void setPool_name(String pool_name) {
        this.pool_name = pool_name;
    }

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public String getIp_block_name() {
        return ip_block_name;
    }

    public void setIp_block_name(String ip_block_name) {
        this.ip_block_name = ip_block_name;
    }

    public int getResult_status() {
        return result_status;
    }

    public void setResult_status(int result_status) {
        this.result_status = result_status;
    }
}
