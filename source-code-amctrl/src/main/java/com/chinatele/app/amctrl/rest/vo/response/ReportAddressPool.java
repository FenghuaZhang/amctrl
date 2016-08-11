package com.chinatele.app.amctrl.rest.vo.response;

import java.util.List;

public class ReportAddressPool {

    /** 域名 */
    private String domain_name;

    /** 地址池唯一标识 */
    private int address_pool_id;

    /** 地址池名称 */
    private String address_pool_name;

    /** 地址块信息 */
    private List<AddressPoolRange> address_pool_ranges;

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

    public List<AddressPoolRange> getAddress_pool_ranges() {
        return address_pool_ranges;
    }

    public void setAddress_pool_ranges(List<AddressPoolRange> address_pool_ranges) {
        this.address_pool_ranges = address_pool_ranges;
    }
}
