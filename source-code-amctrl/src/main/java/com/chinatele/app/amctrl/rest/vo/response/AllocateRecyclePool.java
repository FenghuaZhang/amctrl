package com.chinatele.app.amctrl.rest.vo.response;

public class AllocateRecyclePool {

    /** 时间 */
    private int time;

    /** 域名 */
    private String domain_name;

    /** 协议类型(0:ipv6,1:ipv4) */
    private int protocol_type;

    /** 地址池唯一标识 */
    private int address_pool_id;

    /** 地址池名称 */
    private String address_pool_name;

    /** 地址块唯一标识 */
    private int address_block_id;

    /** 地址块名称 */
    private String address_block_name;

    /** ip地址前缀 */
    private String ip_prefix;

    /** ip地址前缀长度 */
    private int ip_prefix_length;

    /** 网关 */
    private String usergateway;

    /** 网关掩码长度 */
    private int gwnetmask;

    /** dns */
    private String dns_list;

    /** 存活时间 */
    private int leasing_time;

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

    public String getIp_prefix() {
        return ip_prefix;
    }

    public void setIp_prefix(String ip_prefix) {
        this.ip_prefix = ip_prefix;
    }

    public int getIp_prefix_length() {
        return ip_prefix_length;
    }

    public void setIp_prefix_length(int ip_prefix_length) {
        this.ip_prefix_length = ip_prefix_length;
    }

    public String getUsergateway() {
        return usergateway;
    }

    public void setUsergateway(String usergateway) {
        this.usergateway = usergateway;
    }

    public int getGwnetmask() {
        return gwnetmask;
    }

    public void setGwnetmask(int gwnetmask) {
        this.gwnetmask = gwnetmask;
    }

    public String getDns_list() {
        return dns_list;
    }

    public void setDns_list(String dns_list) {
        this.dns_list = dns_list;
    }

    public int getLeasing_time() {
        return leasing_time;
    }

    public void setLeasing_time(int leasing_time) {
        this.leasing_time = leasing_time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }
}
