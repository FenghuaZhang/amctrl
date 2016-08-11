package com.chinatele.app.amctrl.rest.vo.request;

public class ReleaseAddrPoolInfo {
	
	/** 设备的唯一标识 */
    private int device_id;
    
    /** 地址池的唯一标识 */
    private int address_pool_id;
    
    /** 地址池名称 */
    private String address_pool_name;

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
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
    
}
