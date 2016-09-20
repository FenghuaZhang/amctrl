package com.chinatele.app.amctrl.rest.vo.response;

import java.util.ArrayList;
import java.util.List;

import com.chinatele.app.amctrl.service.dto.AddressBlockIPv4;
import com.chinatele.app.amctrl.service.dto.AddressBlockIPv6;
import com.chinatele.app.amctrl.service.dto.AddressPool;
import com.chinatele.app.amctrl.service.dto.AddressPoolEntities;
import com.chinatele.app.amctrl.service.dto.PoolStatus;

public class ReportPoolStatus {

    /** 设备唯一标识 */
    private int device_id;

    /** 上报时间 */
    private double time;

    /** 所有地址池 */
    private List<ReportAddressPool> address_pool;

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public List<ReportAddressPool> getAddress_pool() {
        return address_pool;
    }

    public void setAddress_pool(List<ReportAddressPool> address_pool) {
        this.address_pool = address_pool;
    }

    public void convertFromDto(PoolStatus poolStatus) {
        this.device_id = poolStatus.getDeviceId();
        this.time = poolStatus.getTime();
        if (poolStatus.getAddressPools() != null) {
            List<ReportAddressPool> reportAddressPools = new ArrayList<ReportAddressPool>();
            for (AddressPool addressPool : poolStatus.getAddressPools()) {
            	if (addressPool.getAddressPoolEntities() == null) {
            		continue;
            	}
                ReportAddressPool reportAddressPool = new ReportAddressPool();
                reportAddressPool.setDomain_name(addressPool.getDomainName());
                reportAddressPool.setAddress_pool_name(addressPool.getAddressPoolName());
                reportAddressPool.setAddress_pool_id(addressPool.getAddressPoolId());
                AddressPoolEntities addressPoolEntities = addressPool.getAddressPoolEntities();
                List<AddressBlockIPv4> ipv4Blocks = addressPoolEntities.getIpv4AddressBlocks();
                List<AddressBlockIPv6> ipv6Blocks = addressPoolEntities.getIpv6AddressBlocks();
                if (ipv4Blocks != null) {
                    List<AddressPoolRange> addressPoolRanges = new ArrayList<AddressPoolRange>();
                    for (AddressBlockIPv4 ipv4Block : ipv4Blocks) {
                        AddressPoolRange addressPoolRange = new AddressPoolRange();
                        addressPoolRange.setAddress_block_id(ipv4Block.getIpv4AddressBlockId());
                        addressPoolRange.setAddress_block_name(ipv4Block.getIpv4AddressBlockName());
                        addressPoolRange.setAverage_address_usage_ratio(ipv4Block.getAverageAddressUsageRatio() / 100.0f);
                        addressPoolRanges.add(addressPoolRange);
                    }
                    reportAddressPool.setAddress_pool_ranges(addressPoolRanges);
                } else if (ipv6Blocks != null) {
                    List<AddressPoolRange> addressPoolRanges = new ArrayList<AddressPoolRange>();
                    for (AddressBlockIPv6 ipv6Block : ipv6Blocks) {
                        AddressPoolRange addressPoolRange = new AddressPoolRange();
                        addressPoolRange.setAddress_block_id(ipv6Block.getIpv6AddressBlockId());
                        addressPoolRange.setAddress_block_name(ipv6Block.getIpv6AddressBlockName());
                        addressPoolRange.setAverage_address_usage_ratio(ipv6Block.getAverageAddressUsageRatio() / 100.0f);
                        addressPoolRanges.add(addressPoolRange);
                    }
                    reportAddressPool.setAddress_pool_ranges(addressPoolRanges);
                }
                reportAddressPools.add(reportAddressPool);
            }
            this.address_pool = reportAddressPools;
        }
    }
}
