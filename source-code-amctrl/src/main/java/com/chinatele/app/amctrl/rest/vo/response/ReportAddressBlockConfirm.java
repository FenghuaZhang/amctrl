package com.chinatele.app.amctrl.rest.vo.response;

public class ReportAddressBlockConfirm {

    /** 设备唯一标识 */
    private int device_id;

    /** 域名 */
    private String domain_name;

    /** 地址池唯一标识 */
    private int address_pool_id;

    /** 地址池名称 */
    private String address_pool_name;

    /** 地址块唯一标识 */
    private int address_block_id;

    /** 地址块名称 */
    private String address_block_name;

    /** 0代表分配成功,1代表回收成功,2代表分配失败,3代表回收失败 */
    private int result_status;

    /** 协议类型(0:ipv6,1:ipv4) */
    private int protocol_type;

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
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

    public int getResult_status() {
        return result_status;
    }

    public void setResult_status(int result_status) {
        this.result_status = result_status;
    }

    public int getProtocol_type() {
        return protocol_type;
    }

    public void setProtocol_type(int protocol_type) {
        this.protocol_type = protocol_type;
    }
}
