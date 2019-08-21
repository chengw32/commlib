package com.afmoney.commlibrary.beans;

import java.io.Serializable;

/**
 * Created by chenguowu on 2019/1/9.
 */
public class BaseBean implements Serializable {

//    {"success":true,"message":"短信发送成功！","code":200,"result":"888947","timestamp":1560169679439}

    protected String code;
    protected String timestamp;
    protected String message;
    protected int status;
    protected boolean success;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
