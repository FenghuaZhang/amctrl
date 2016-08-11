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
                if (message.indexOf("address-pool-status") > -1) {
                    wrappedMessage.setObjectProperty("pool_state", message);
                } else if (message.indexOf("ama-register-to-controller") > -1){
                	wrappedMessage.setObjectProperty("register_confirm", message);
                } else if (message.indexOf("ama-report-address-got-to-controller-ipv4") > -1) {
                    wrappedMessage.setObjectProperty("address_block_ipv4_config_confirm", message);
                } else if (message.indexOf("ama-report-address-recycled-to-controller-ipv4") > -1) {
                    wrappedMessage.setObjectProperty("address_block_ipv4_recycle_confirm", message);
                } else if (message.indexOf("ama-report-address-got-to-controller-ipv6") > -1) {
                    wrappedMessage.setObjectProperty("address_block_ipv6_config_confirm", message);
                } else if (message.indexOf("ama-report-address-recycled-to-controller-ipv6") > -1) {
                    wrappedMessage.setObjectProperty("address_block_ipv6_recycle_confirm", message);
                } else if (message.indexOf("ama-report-heart-beat-to-controller") > -1) {
                    wrappedMessage.setObjectProperty("heart_beat", message);
                }
                return wrappedMessage;
            }
        });
    }

}
