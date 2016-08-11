package com.chinatele.app.amctrl.rest.vo;


/**
 * @description app下发或回收地址块对象
 * @author zhoudr
 *
 */
public class AddressBlockIPv6 {

    /** 地址块唯一标识 */
    private int block_id;

    /** 地址块名称 */
    private String address_block_name;

    /** ip前缀 */
    private String ip_prefix;

    /** ip前缀长度 */
    private Integer ip_prefix_length;

    /** 地址块生命周期：回收时该值为0，其他该值非0 */
    private String lifetime;

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public String getAddress_block_name() {
        return address_block_name;
    }

    public void setAddress_block_name(String address_block_name) {
        this.address_block_name = address_block_name;
    }

    public String getIp_prefix() {
        return ip_prefix;
    }

    public void setIp_prefix(String ip_prefix) {
        this.ip_prefix = ip_prefix;
    }

    public Integer getIp_prefix_length() {
        return ip_prefix_length;
    }

    public void setIp_prefix_length(Integer ip_prefix_length) {
        this.ip_prefix_length = ip_prefix_length;
    }

    public String getLifetime() {
        return lifetime;
    }

    public void setLifetime(String lifetime) {
        this.lifetime = lifetime;
    }
}
