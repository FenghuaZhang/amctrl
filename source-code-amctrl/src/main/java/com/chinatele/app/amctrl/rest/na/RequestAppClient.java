package com.chinatele.app.amctrl.rest.na;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.chinatele.app.amctrl.rest.vo.DeviceDown;
import com.chinatele.app.amctrl.rest.vo.ResponseVo;
import com.chinatele.app.amctrl.rest.vo.response.AllocateRecycleAddress;
import com.chinatele.app.amctrl.rest.vo.response.ReportAddressBlockConfirm;
import com.chinatele.app.amctrl.rest.vo.response.ReportPoolStatus;
import com.chinatele.app.amctrl.util.ConfigUtil;
import com.chinatele.app.amctrl.util.Constants;
import com.chinatele.app.amctrl.util.JsonUtil;

public class RequestAppClient {

    private static Logger log = LoggerFactory.getLogger(RequestAppClient.class);

    /**
     * @description 控制器向app上报设备信息
     * @author zhoudr
     * @time 2015-11-25 下午4:10:53
     * @param deviceInfo
     */
    public static void reportNewDevice(int deviceId) throws Exception{
        String appUrl = ConfigUtil.getValue("APP_URL");
        String uri = appUrl + "/controller_report_new_Device_to_controller_msg";
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient httpClient = builder.build();
        HttpPost post = new HttpPost(uri);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("device_id",deviceId);
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        post.setEntity(entity);
        log.info("report new device to app [{}]", jsonObject.toString());
        HttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            log.info("report new device to app, app return [{}]", EntityUtils.toString(response.getEntity()));
        } else {
            log.error("report new device to app, app return [null]");
        }
    }

    /**
     * 
     * Description: 上报无法连接的设备id到app
     * @author zhoudr
     * @time 2016-3-14 下午4:05:22
     * @param deviceId
     * @throws Exception
     */
    public static void reportFailedDevice(int deviceId) throws Exception{
        String appUrl = ConfigUtil.getValue("APP_URL");
        String uri = appUrl + "/controller_report_reg_Device_failed_to_app_msg";
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient httpClient = builder.build();
        HttpPost post = new HttpPost(uri);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("device_id",deviceId);
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        post.setEntity(entity);
        log.info("report connection failed device to app [{}]", jsonObject.toString());
        HttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            log.info("report connection failed device to app, app return [{}]", EntityUtils.toString(response.getEntity()));
        } else {
            log.error("report connection failed device to app, app return [null]");
        }
    }

    /**
     * @description 控制器向app上报地址池状态
     * @author zhoudr
     * @time 2015-11-28 上午11:31:33
     * @param reportPools
     * @return
     */
    public static ResponseVo reportPoolState(ReportPoolStatus reportPoolStatus) throws Exception{
        String appUrl = ConfigUtil.getValue("APP_URL");
        String uri = appUrl + "/controller_report_Device_status_to_app_msg";
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient httpClient = builder.build();
        HttpPost post = new HttpPost(uri);
        JSONObject jsonObject = JSONObject.fromObject(reportPoolStatus);
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        post.setEntity(entity);
        log.info("report pool status to app [{}]", jsonObject.toString());
        HttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseEntity = EntityUtils.toString(response.getEntity());
            log.info("report pool status to app, app return [{}]", responseEntity);
            ResponseVo vo = new ResponseVo();
            if (!StringUtils.isBlank(responseEntity)){
                if (!responseEntity.equalsIgnoreCase(Constants.RETURN_MESSAGE_SUCCESS)
                        && !responseEntity.equalsIgnoreCase(Constants.RETURN_MESSAGE_FAIL)
                        && !responseEntity.equalsIgnoreCase(Constants.QUTED_RETURN_MESSAGE_SUCCESS)
                        && !responseEntity.equalsIgnoreCase(Constants.QUTED_RETURN_MESSAGE_FAIL)) {
                    JSONObject returnJson = JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
                    // 上报通知后，app返回fail或者success后，控制器不做任何处理
                    AllocateRecycleAddress allocateRecycleAddress = JsonUtil.convertToJavaBean(returnJson.toString(), AllocateRecycleAddress.class);
                    vo.setResult(allocateRecycleAddress);
                }
            }
            return vo;
        } else {
            log.error("report pool status to app, app return [null]");
            return null;
        }
    }

    public static void reportAddressBlockConfirm(ReportAddressBlockConfirm reportAddressBlockConfirm) throws Exception{
        String appUrl = ConfigUtil.getValue("APP_URL");
        String uri = appUrl + "/controller_report_address_processing_result_msg";
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient httpClient = builder.build();
        HttpPost post = new HttpPost(uri);
        JSONObject jsonObject = JSONObject.fromObject(reportAddressBlockConfirm);
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        post.setEntity(entity);
        log.info("report address block confirm to app [{}]", jsonObject.toString());
        HttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            log.info("report address block confirm to app, app return [{}]", EntityUtils.toString(response.getEntity()));
        } else {
            log.error("report address block confirm to app, app return [null]");
        }
    }
    
    public static void reportDeviceDown(DeviceDown deviceDown) throws Exception {
        String appUrl = ConfigUtil.getValue("APP_URL");
        String uri = appUrl + "/controller-report-app-device-down";
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient httpClient = builder.build();
        HttpPost post = new HttpPost(uri);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("device_down", deviceDown);
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        post.setEntity(entity);
        log.info("report device down to app [{}]", jsonObject.toString());
        HttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            log.info("report device down to app, app return [{}]", EntityUtils.toString(response.getEntity()));
        } else {
            log.error("report device down to app, app return [null]");
        }
    }

	public static void reportBlockLacking(String xmlBlockLacking) throws Exception {
		String appUrl = ConfigUtil.getValue("APP_URL");
        String uri = appUrl + "/controller_report_address_lacking";
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient httpClient = builder.build();
        
        // xml to json handler
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSONObject jsonObj = (JSONObject)xmlSerializer.read(xmlBlockLacking);
        String jsonStr = StringUtils.EMPTY;
        if (jsonObj.has(Constants.Notification.NOTIFY_ID_OF_AMA_LACK_BLOCK)) {
        	JSONObject obj = (JSONObject) jsonObj.get(Constants.Notification.NOTIFY_ID_OF_AMA_LACK_BLOCK);
        	// remove xmlns property
        	if(obj.has("@xmlns")) {
        		obj.remove("@xmlns");	
        	}
        	jsonStr = obj.toString();        	
        	log.info("RequestAppClient::reportBlockLacking() JSON after converting from xml is: ", jsonStr);
        } else {
        	log.error("Node " + Constants.Notification.NOTIFY_ID_OF_AMA_LACK_BLOCK + " is not found, please check the received notification.");
        }
        
        HttpPost post = new HttpPost(uri);
        StringEntity entity = new StringEntity(jsonStr, "utf-8");
        entity.setContentType(MediaType.APPLICATION_JSON_VALUE);
        post.setEntity(entity);
        
        HttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			log.info(
					"RequestAppClient::reportBlockLacking() report address block lacking to app, app return [{}]",
					EntityUtils.toString(response.getEntity()));
        } else {
            log.error("RequestAppClient::reportBlockLacking() report address block lacking to app, app return [null]");
        }
	}
}
