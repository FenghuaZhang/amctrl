package com.chinatele.app.amctrl.rest.vo;

import java.util.List;

/**
 * @description app回收地址块对象
 * @author zhoudr
 * 
 */
public class PoolInfo {

    /** 地址池信息 */
    private List<AddressPool> address_pools;

    public List<AddressPool> getAddress_pools() {
        return address_pools;
    }

    public void setAddress_pools(List<AddressPool> address_pools) {
        this.address_pools = address_pools;
    }
}
