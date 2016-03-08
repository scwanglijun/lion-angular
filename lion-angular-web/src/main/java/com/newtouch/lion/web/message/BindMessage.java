package com.newtouch.lion.web.message;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * Created by wanglijun on 16/1/6.
 */
public class BindMessage implements Serializable{
    /**是否成功*/
    private Boolean success=Boolean.TRUE;

    private String  message;

    public BindMessage() {
        super();
    }

    /***
     *
     * @param success
     * @param message
     */
    public BindMessage(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /***
     *
     * @param success
     * @param message
     * @param params
     */
    public BindMessage(Boolean success,String message,Object[] params){
        this.success=success;
        this.message=MessageFormat.format(message,params);
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BindMessage{" +
                "message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
