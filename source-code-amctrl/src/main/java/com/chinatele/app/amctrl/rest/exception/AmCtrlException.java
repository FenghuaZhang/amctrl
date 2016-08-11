package com.chinatele.app.amctrl.rest.exception;

public class AmCtrlException extends RuntimeException {

    private static final long serialVersionUID = 2730221671357762154L;

    private int code;

    private String message;

    public AmCtrlException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
