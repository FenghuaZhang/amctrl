package com.chinatele.app.amctrl.service;

import javax.jms.Destination;

public interface AddressBlockMessageService {

    /**
     * @description 文本消息
     * @author zhoudr
     * @time 2015-11-26 下午3:55:33
     * @param destination
     * @param message
     */
    public void sendTextMessage(Destination destination, final String message);

    /**
     * @description 发送json对象信息
     * @author zhoudr
     * @time 2015-11-27 下午2:35:24
     * @param destination
     * @param message
     */
    public void sendJsonObjectMessage(Destination destination, final String message);

    /**
     * @description 发送xml对象
     * @author zhoudr
     * @time 2015-11-27 下午3:55:02
     * @param destination
     * @param message
     */
    public void sendXmlObjectMessage(Destination destination, final String message);
}
