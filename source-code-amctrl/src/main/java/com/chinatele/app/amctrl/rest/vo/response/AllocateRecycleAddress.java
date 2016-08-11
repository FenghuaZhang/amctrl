package com.chinatele.app.amctrl.rest.vo.response;

import java.util.List;

public class AllocateRecycleAddress {

    /** 设备唯一标识 */
    private int device_id;

    /** 地址池信息 */
    private List<AllocateRecyclePool> address_pools;

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public List<AllocateRecyclePool> getAddress_pools() {
        return address_pools;
    }

    public void setAddress_pools(List<AllocateRecyclePool> address_pools) {
        this.address_pools = address_pools;
    }
}
