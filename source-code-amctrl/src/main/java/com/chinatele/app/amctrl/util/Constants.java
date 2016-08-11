package com.chinatele.app.amctrl.util;

public class Constants {

    /** 返回码(0:正常) */
    public static final int RETURN_CODE_OK = 0;
    /** 返回码(1:系统内部错误) */
    public static final int RETURN_CODE_SYSTEM_ERROR = 1;
    public static final String SYSTEM_ERROR_MESSAGE = "system error";
    /** 返回码(2:参数类型错误) */
    public static final int RETURN_CODE_PARAMETER_TYPE_ERROR = 2;
    public static final String PARAMETER_TYPE_ERROR_MESSAGE = "parameter type error";
    /** 返回码(3:缺少关键参数) */
    public static final int RETURN_CODE_LACK_KEY_PARAMETER_ERROR = 3;
    public static final String LACK_KEY_PARAMETER_ERROR_MESSAGE = "lack key parameter error";
    /** 返回码(4:参数解析错误) */
    public static final int RETURN_CODE_PARAMETER_NAME_PARSE_ERROR = 4;
    public static final String PARAMETER_NAME_PARSE_ERROR_MESSAGE = "parameter name parse error";
    /** 返回码(5:业务处理异常) */
    public static final int RETURN_CODE_BUSINESS_ERROR = 5;

    /** app端要求的返回信息：成功 */
    //public static final String RETURN_MESSAGE_SUCCESS = "{\"result\":\"success\"}";
    public static final String RETURN_MESSAGE_SUCCESS = "success";
    public static final String QUTED_RETURN_MESSAGE_SUCCESS = "\"success\"";
    /** app端要求的返回信息：失败 */
    //public static final String RETURN_MESSAGE_FAIL = "{\"result\":\"fail\"}";
    public static final String RETURN_MESSAGE_FAIL = "fail";
    public static final String QUTED_RETURN_MESSAGE_FAIL = "\"fail\"";
    
    public static final int ALLOCATE_AND_RECYCLE_ADDRESS_SUCCESS = 0;
    public static final int ALLOCATE_AND_RECYCLE_ADDRESS_ERROR = 100;
    public static final int RECYCLE_ADDRESS_NOT_EXIST = 101;
    public static final int ALLOCATE_ADDRESS_INDEX_REPEAT = 102;
    public static final int ALLOCATE_ADDRESS_NAME_REPEAT = 104;

    /** 设备状态(0:连接中) */
    public static final int DEVICE_STATE_CONNECTING = 0;
    /** 设备状态(1:活着) */
    public static final int DEVICE_STATE_ALIVE = 1;
    public static final String DEVICE_STATE_ALIVE_STRING = "y";
    /** 设备状态(2:死亡) */
    public static final int DEVICE_STATE_DEAD = 2;
    public static final String DEVICE_STATE_DEAD_STRING = "n";

    /** 默认的设备用户名 */
    public static final String DEFAULT_DEVICE_USER_NAME = "admin";
    /** 默认的设备密码 */
    public static final String DEFAULT_DEVICE_PASSWORD = "admin";

    /** 多个dns的分隔符 */
    public static final String DNS_SPLIT_FLAG = ",";

    /** 设备类型 */
    public static String DEVICE_TYPE_BRASS = "brass";
    public static String DEVICE_TYPE_FIREWALL = "firewall";

    /** 协议类型(0:ipv6) */
    public static final int PROTOCOL_TYPE_IPV6 = 0;
    /** 协议类型(1:ipv4) */
    public static final int PROTOCOL_TYPE_IPV4 = 1;
    
    public static final int RELEASE_ADDRESS_POOL_SUCCESS = 0;
    public static final int RELEASE_ADDRESS_POOL_ERROR = 110;
    public static final int RELEASE_ADDRESS_POOL_NOT_EXIST = 111;

}
