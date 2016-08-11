package com.chinatele.app.amctrl.service;

import com.chinatele.app.amctrl.rest.exception.AmCtrlException;
import com.chinatele.app.amctrl.rest.vo.ResponseVo;
import com.chinatele.app.amctrl.rest.vo.request.AllocateBlock;
import com.chinatele.app.amctrl.rest.vo.request.ConfigInfo;
import com.chinatele.app.amctrl.rest.vo.request.RecycleBlock;
import com.chinatele.app.amctrl.rest.vo.request.ReleaseAddrPoolInfo;

/**
 * 
 * @description app要求控制器回收指定的地址块
 * @author zhoudr
 * @time 2015-11-16 15:09:40
 *
 */
public interface ControllerService {

    /**
     * @description 与device建立连接并下发配置信息
     * @author zhoudr
     * @time 2015-11-25 下午4:59:56
     * @param configInfo
     * @return
     * @throws AmCtrlException
     */
    public ResponseVo config(ConfigInfo configInfo) throws AmCtrlException;

    /**
     * @description 回收指定地址块
     * @author zhoudr
     * @time 2015-12-2 下午4:21:45
     * @param recycleBlock
     * @return
     */
    public ResponseVo recycleAddress(RecycleBlock recycleBlock);

    /**
     * @description 控制器连接设备的存活状态
     * @author zhoudr
     * @time 2015-12-1 下午7:45:49
     * @param controllerId
     * @return
     */
    public ResponseVo getDeviceAliveState(int controllerId);

    /**
     * @description 分配指定地址块 
     * @author zhoudr
     * @time 2015-12-29 下午4:26:37
     * @param allocateBlock
     * @return
     */
    public ResponseVo allocateAddress(AllocateBlock allocateBlock);

    /**
     * 
     * @description 强制设备下线
     * @author zhoudr
     * @time 2015-12-1 下午5:09:50
     * @param deviceId
     * @return
     */
    public ResponseVo forceDeviceDown(int deviceId);

    public void clean();
    
    /**
     * @description 释放地址池 
     * @author xiey
     * @param allocateBlock
     * @return
     */
    public ResponseVo deleteAddressPool(ReleaseAddrPoolInfo releaseAddrPoolInfo);
}
