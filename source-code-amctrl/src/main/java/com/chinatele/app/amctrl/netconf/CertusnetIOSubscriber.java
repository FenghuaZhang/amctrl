package com.chinatele.app.amctrl.netconf;

import javax.jms.Destination;
import org.apache.log4j.Logger;
import com.chinatele.app.amctrl.service.AddressBlockMessageService;
import com.tailf.jnc.IOSubscriber;


/**
 * This is a default IO subscriber that can be used for tracing, auditing, and
 * logging of messages sent and recived by the transport of the session.
 * <p>
 * This class is provided as an example of how to write your own IO subscriber,
 * and can be used as it is.
 * 
 * @see IOSubscriber
 */
public class CertusnetIOSubscriber extends IOSubscriber {
	
	private static  Logger logger = Logger.getLogger(CertusnetIOSubscriber.class);
	
    private AddressBlockMessageService addressBlockMessageService;

    private Destination destination;

    public void setAddressBlockMessageService(
			AddressBlockMessageService addressBlockMessageService) {
		this.addressBlockMessageService = addressBlockMessageService;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	String devName;

    public String getDevName() {
		return devName;
	}
    
    public void setDevName(String devName) {
		this.devName = devName;
	}

	public CertusnetIOSubscriber(){
    	super(false);
    }

	/**
     * Constructor.
     * 
     * @param devName The name of the device.
     */
    public CertusnetIOSubscriber(String devName) {
        super(false); // rawmode = false
        this.devName = devName;
    }

    /**
     * Constructor.
     * 
     * @param devName The name of the device.
     * @param rawmode If true 'raw' text will appear instead of pretty
     *            formatted XML.
     */
    public CertusnetIOSubscriber(String devName, boolean rawmode) {
        super(rawmode);
        this.devName = devName;
    }
    
    public CertusnetIOSubscriber(String devName,AddressBlockMessageService addressBlockMessageService,Destination destination) {
        super(false); // rawmode = false
        this.devName = devName;
        this.addressBlockMessageService = addressBlockMessageService;
        this.destination = destination;
    }

    /**
     * Will get called as soon as we have input (data which is received).
     * 
     * @param s Text being received
     */
    @Override
    public void input(String s){
        logger.info("RECV " + devName);
        
        if (s.indexOf("<notification")>-1){
        	s = s.substring(s.indexOf("<notification"));
        	
        	String message = s.substring(0,s.indexOf("</notification>")) + "</notification>";
        	logger.info(message);
        	addressBlockMessageService.sendXmlObjectMessage(destination, message);
        	
        	input(s.substring(s.indexOf(message)+1));
        }
    }

    /**
     * Will get called as soon as we have output (data which is being sent).
     * 
     * @param s Text being sent
     */
    @Override
    public void output(String s) {
    	logger.info("SEND " + devName);
    	logger.info(s);
    }
    
}
