package com.chinatele.app.amctrl.rest.vo;

import java.util.List;

/**
 * @description app回收地址块对象
 * @author zhoudr
 * 
 */
public class AddressPool {

    /** 地址池名称 */
    private String address_pool_name;

    /** 设备的唯一标识 */
    private String device_id;

    /** ipv4地址块信息 */
    private List<AddressBlockIPv4> ipv4_address_pool_entries;

    /** ipv6地址块信息 */
    private List<AddressBlockIPv6> ipv6_address_pool_entries;

    /** ipv4网关 */
    private String ipv4_usergateway;

    /** ipv6网关 */
    private String ipv6_usergateway;

    /** netmask for user gate */
    private String gwnetmask;

    /** access type of address pool */
    private String type;

    /** adress pool(备用) */
    private String lifetime;

    /** 首选dns */
    private String primary_dns;

    /** 备用dns */
    private String secondary_dns;

    private String domain;

    public String getAddress_pool_name() {
        return address_pool_name;
    }

    public void setAddress_pool_name(String address_pool_name) {
        this.address_pool_name = address_pool_name;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public List<AddressBlockIPv4> getIpv4_address_pool_entries() {
        return ipv4_address_pool_entries;
    }

    public void setIpv4_address_pool_entries(List<AddressBlockIPv4> ipv4_address_pool_entries) {
        this.ipv4_address_pool_entries = ipv4_address_pool_entries;
    }

    public List<AddressBlockIPv6> getIpv6_address_pool_entries() {
        return ipv6_address_pool_entries;
    }

    public void setIpv6_address_pool_entries(List<AddressBlockIPv6> ipv6_address_pool_entries) {
        this.ipv6_address_pool_entries = ipv6_address_pool_entries;
    }

    public String getIpv4_usergateway() {
        return ipv4_usergateway;
    }

    public void setIpv4_usergateway(String ipv4_usergateway) {
        this.ipv4_usergateway = ipv4_usergateway;
    }

    public String getIpv6_usergateway() {
        return ipv6_usergateway;
    }

    public void setIpv6_usergateway(String ipv6_usergateway) {
        this.ipv6_usergateway = ipv6_usergateway;
    }

    public String getGwnetmask() {
        return gwnetmask;
    }

    public void setGwnetmask(String gwnetmask) {
        this.gwnetmask = gwnetmask;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLifetime() {
        return lifetime;
    }

    public void setLifetime(String lifetime) {
        this.lifetime = lifetime;
    }

    public String getPrimary_dns() {
        return primary_dns;
    }

    public void setPrimary_dns(String primary_dns) {
        this.primary_dns = primary_dns;
    }

    public String getSecondary_dns() {
        return secondary_dns;
    }

    public void setSecondary_dns(String secondary_dns) {
        this.secondary_dns = secondary_dns;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
