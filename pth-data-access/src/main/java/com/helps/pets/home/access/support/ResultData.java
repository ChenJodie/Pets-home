package com.helps.pets.home.access.support;

import java.io.Serializable;

/**
 * Author : pengpeng
 * Date   : 2016-07-08
 * time   : 下午4:08
 */
public class ResultData<T> extends ResultInfo implements Serializable {
    
    private static final long serialVersionUID = 6300337442870893234L;
    private T data = null;
    
    public T getData() {
        return this.data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public ResultData() {
    }
    
    public ResultData(int retcode, String retdesc) {
        super(retcode, retdesc);
    }
    
    public ResultData(int retcode, String retdesc, T data) {
        super(retcode, retdesc);
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "ResultData [data=" + data + "]";
    }
}
