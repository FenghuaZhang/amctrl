package com.chinatele.app.amctrl.service;

import java.util.Map;

import net.sf.json.JSONObject;

import com.chinatele.app.amctrl.rest.vo.request.AllocateBlock;
import com.chinatele.app.amctrl.rest.vo.request.Device;
import com.chinatele.app.amctrl.rest.vo.request.DeviceConfigInfo;
import com.chinatele.app.amctrl.rest.vo.request.RecycleBlock;
import com.chinatele.app.amctrl.rest.vo.request.ReleaseAddrPoolInfo;


public interface NetconfDeviceService {
	
	boolean openConnection(DeviceConfigInfo connCfgInfo);
	
    void disconnectDevice(String ip);

    boolean validateSession(int deviceId, String deviceIp);

    JSONObject allocateAddress(AllocateBlock allocateBlock, Map<Integer,Device> map) throws Exception;
    
    JSONObject recycleAddress(RecycleBlock recycleBlock,Map<Integer,Device> map) throws Exception;
    
    JSONObject releaseAddressPool(ReleaseAddrPoolInfo releaseAddrPoolInfo,Map<Integer,Device> map);
}
