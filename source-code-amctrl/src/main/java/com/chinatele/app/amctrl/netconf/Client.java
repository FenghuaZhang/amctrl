package com.chinatele.app.amctrl.netconf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinatele.app.amctrl.service.AddressBlockMessageService;
import com.tailf.jnc.Device;
import com.tailf.jnc.DeviceUser;
import com.tailf.jnc.Element;
import com.tailf.jnc.JNCException;
import com.tailf.jnc.NetconfSession;
import com.tailf.jnc.NodeSet;

public class Client {
	
	private static  Logger logger = Logger.getLogger(Client.class);
	
    @Autowired
	private AddressBlockMessageService addressBlockMessageService;
    
    @Autowired
    private Destination queueDestination;
    
	// Hard coded host name, user name and password
    private String emsUserName = "bobby";
    private String junosUserName = "admin";
    private String pass = "admin";

	public static Map<String,Device> devices = new HashMap<String,Device>();
    
    public Client() {
        
    }


	public void init(String ip,int port,String userName,String password) {
		Device dev = null;
		DeviceUser duser = null;
		if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password)){
			duser = new DeviceUser(emsUserName, userName, password);
		}
		else{
			duser = new DeviceUser(emsUserName, junosUserName, pass);
		}
        try {
        	if (!devices.containsKey(ip)){
            	dev = new Device("dev-"+ip, duser, ip, port);
            	devices.put(ip, dev);
            	//dev.connect(emsUserName);
            }
            else{
            	dev = devices.get(ip);
            }
            
        	if (!dev.hasSession("cfg")){
        		dev.connect(emsUserName);
        		dev.newSession("cfg");
        	}
        } catch (IOException e) {
        	logger.error("Can't connect");
        } catch (JNCException e1) {
        	e1.printStackTrace();
        	logger.error("Can't authenticate: " + e1.getMessage());
        } catch(Exception e2){
        	e2.printStackTrace();
        }
    }
	
	public void initConn(String ip,int port,String userName,String password) {
		Device dev = null;
		DeviceUser duser = null;
		if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password)){
			duser = new DeviceUser(emsUserName, userName, password);
		}
		else{
			duser = new DeviceUser(emsUserName, junosUserName, pass);
		}
        try {
        	if (!devices.containsKey(ip)){
            	dev = new Device("dev-"+ip, duser, ip, port);
            	devices.put(ip, dev);
            }
            else{
            	dev = devices.get(ip);
            }
            
        	if (!dev.hasSession("msg")){
        		logger.info("Start connecting device " + ip + ":" + port + " with "+ emsUserName + "......");
        		dev.connect(emsUserName);
        		logger.info("End connecting device");
        		CertusnetIOSubscriber certusnetIOSubscriber = new CertusnetIOSubscriber("dev-"+ip,addressBlockMessageService,queueDestination);
        		logger.info("Start create new session with name msg");
        		dev.newSession(certusnetIOSubscriber,"msg");
        		logger.info("Start create new session with name cfg");
        		dev.newSession("cfg");
        		logger.info("Session creation finished.");
        	}
        } catch (IOException e) {
        	logger.error("Can't connect cause IOException: " + e.getMessage());
        } catch (JNCException e1) {
        	e1.printStackTrace();
        	logger.error("Can't authenticate: " + e1.getMessage());
        } catch(Exception e2){
        	e2.printStackTrace();
        	logger.error("Can't connect cause Exception: " + e2.getMessage());
        }
    }
    
    private NodeSet getConfig(Device d) throws IOException, JNCException {
        NetconfSession session = d.getSession("cfg");
        NodeSet reply = session.getConfig(NetconfSession.RUNNING);
        return reply;
    }

    /*public NodeSet getConfig() throws IOException, JNCException {
        return getConfig(dev);
    }
    
    public NetconfSession getSession(String sessionName){
    	return dev.getSession(sessionName);
    }*/
    public NetconfSession getSession(Device d,String sessionName){
		logger.info("Enter method Client::getSession for sessionName " + sessionName+ " and device "
				+ ReflectionToStringBuilder.toString(d,ToStringStyle.MULTI_LINE_STYLE));
    	return d.getSession(sessionName);
    }
    
    
    
    /**
     * Gets the first configuration element in configs with specified name.
     * 
     * @param configs Set of device configuration data.
     * @param name The identifier of the configuration to select
     * @return First configuration with matching name, or null if none present.
     */
    public static Element getConfig(NodeSet configs, String name) {
        Element config = configs.first();
        if (!config.name.equals(name)) {
            config = null;
            for (Element elem : configs) {
                if (elem.name.equals(name)) {
                    config = elem;
                }
            }
        }
        return config;
    }


    public void setAddressBlockMessageService(AddressBlockMessageService addressBlockMessageService) {
        this.addressBlockMessageService = addressBlockMessageService;
    }


    public void setQueueDestination(Destination queueDestination) {
        this.queueDestination = queueDestination;
    }

}
