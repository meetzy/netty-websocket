package com.xfmeet.websocket.exception;

/**
 * @author meetzy
 */
public class CommonException extends Exception {
    
    private String msg;
    
    public CommonException(String msg) {
        super(msg);
        this.msg = msg;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
}
