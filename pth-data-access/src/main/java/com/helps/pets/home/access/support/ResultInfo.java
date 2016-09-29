package com.helps.pets.home.access.support;

import java.io.Serializable;

/**
 * Author : pengpeng
 * Date   : 2016-07-08
 * time   : 下午4:09
 */
public class ResultInfo implements Serializable {
    
    private static final long serialVersionUID = 8036561402266912292L;
    private int retcode;
    private String retdesc = "";
    
    public ResultInfo() {
    }
    
    public ResultInfo(int retcode, String retdesc) {
        this.retcode = retcode;
        this.retdesc = retdesc;
    }
    
    public int getRetcode() {
        return this.retcode;
    }
    
    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }
    
    public String getRetdesc() {
        return this.retdesc;
    }
    
    public void setRetdesc(String retdesc) {
        this.retdesc = retdesc;
    }
}
