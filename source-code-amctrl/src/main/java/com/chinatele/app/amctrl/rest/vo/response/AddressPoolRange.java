package com.chinatele.app.amctrl.rest.vo.response;

public class AddressPoolRange {

    /** 地址块唯一标识 */
    private int address_block_id;

    /** 地址块名称 */
    private String address_block_name;

    /** 地址块使用值 */
    private int average_address_usage_ratio;

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

    public int getAverage_address_usage_ratio() {
        return average_address_usage_ratio;
    }

    public void setAverage_address_usage_ratio(int average_address_usage_ratio) {
        this.average_address_usage_ratio = average_address_usage_ratio;
    }
}
