package com.chinatele.app.amctrl.rest.vo.request;

public class RecycleBlock {

    /** 设备的唯一标识 */
    private int device_id;

    /** 地址块唯一标识 */
    private int address_block_id;

    /** 地址块名称 */
    private String address_block_name;

    /** 协议类型(0:ipv6,1:ipv4) */
    private int protocol_type;

    /** 地址池唯一标识 */
    private int address_pool_id;

    /** 地址池名称 */
    private String address_pool_name;

    /** 地址块租用时间(0:表示回收) */
    private int leasing_time;

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getAddress_block_id() {
        return address_block_id;
    }

    public void setAddress_block_id(int address_block_id) {
        this.address_block_id = address_block_id;
    }

    public String getAddress_block_name() {
        return address_block_name;
    }

    public void setAddress_block_name(String address_block_name) {
        this.address_block_name = address_block_name;
    }

    public int getProtocol_type() {
        return protocol_type;
    }

    public void setProtocol_type(int protocol_type) {
        this.protocol_type = protocol_type;
    }

    public int getAddress_pool_id() {
        return address_pool_id;
    }

    public void setAddress_pool_id(int address_pool_id) {
        this.address_pool_id = address_pool_id;
    }

    public String getAddress_pool_name() {
        return address_pool_name;
    }

    public void setAddress_pool_name(String address_pool_name) {
        this.address_pool_name = address_pool_name;
    }

    public int getLeasing_time() {
        return leasing_time;
    }

    public void setLeasing_time(int leasing_time) {
        this.leasing_time = leasing_time;
    }
}
