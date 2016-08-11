package com.chinatele.app.amctrl.rest.vo;

import com.chinatele.app.amctrl.util.Constants;

public class ResponseVo {

    /** 响应码 */
    private int return_code = Constants.RETURN_CODE_OK;

    /** 返回信息 */
    private String message;

    /** 返回结果 */
    private Object result;

    public int getReturn_code() {
        return return_code;
    }

    public void setReturn_code(int return_code) {
        this.return_code = return_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
