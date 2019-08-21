package com.afmoney.commlibrary.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenguowu on 2018/12/21.
 */

public class LogBean {
   private String time ;
   private String message;

    public LogBean() {
    }

    public LogBean(String message) {
        this.time = new SimpleDateFormat("yyyy年MM月dd日 hh:mm").format(new Date());
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
