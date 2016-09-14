package com.chinatele.app.amctrl.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.chinatele.app.amctrl.service.AddressBlockMessageService;
import com.chinatele.app.amctrl.util.Constants;

public class AddressBlockMessageServiceImpl implements AddressBlockMessageService {

    private static Logger log = LoggerFactory.getLogger(AddressBlockMessageServiceImpl.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void sendTextMessage(Destination destination, final String message) {
        log.info("发送一条文本消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    @Override
    public void sendJsonObjectMessage(Destination destination, final String message) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message wrappedMessage = session.createObjectMessage();
                wrappedMessage.setObjectProperty("device_info", message);
                log.info("发送一条json字符串消息：" + wrappedMessage);
                return wrappedMessage;
            }
        });
    }

    @Override
    public void sendXmlObjectMessage(Destination destination, final String message) {
        log.info("发送一条消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message wrappedMessage = session.createObjectMessage();
                if (message.indexOf(Constants.Notification.NOTIFY_ID_OF_AMA_REPORT_ADDRESS_POOL_STATUS) > -1) {
                    wrappedMessage.setObjectProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_POOL_STATUS, message);
                } else if (message.indexOf(Constants.Notification.NOTIFY_ID_OF_AMA_REGISTER_TO_CONTROLLER) > -1){
                	wrappedMessage.setObjectProperty(Constants.Notification.MSG_ID_OF_AMA_REGISTER_TO_CONTROLLER, message);
                } else if (message.indexOf(Constants.Notification.NOTIFY_ID_OF_AMA_REPORT_ADDRESS_GOT_V4) > -1) {
                    wrappedMessage.setObjectProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_GOT_V4, message);
                } else if (message.indexOf(Constants.Notification.NOTIFY_ID_OF_AMA_REPORT_ADDRESS_RECYCLED_V4) > -1) {
                    wrappedMessage.setObjectProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_RECYCLED_V4, message);
                } else if (message.indexOf(Constants.Notification.NOTIFY_ID_OF_AMA_REPORT_ADDRESS_GOT_V6) > -1) {
                    wrappedMessage.setObjectProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_GOT_V6, message);
                } else if (message.indexOf(Constants.Notification.NOTIFY_ID_OF_AMA_REPORT_ADDRESS_RECYCLED_V6) > -1) {
                    wrappedMessage.setObjectProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_ADDRESS_RECYCLED_V6, message);
                } else if (message.indexOf(Constants.Notification.NOTIFY_ID_OF_AMA_REPORT_HEARTBEAT) > -1) {
                    wrappedMessage.setObjectProperty(Constants.Notification.MSG_ID_OF_AMA_REPORT_HEARTBEAT, message);
                } else if (message.indexOf(Constants.Notification.NOTIFY_ID_OF_AMA_LACK_BLOCK) > -1) {
                	wrappedMessage.setObjectProperty(Constants.Notification.MSG_ID_OF_AMA_LACK_BLOCK, message);
                }
                return wrappedMessage;
            }
        });
    }

}
