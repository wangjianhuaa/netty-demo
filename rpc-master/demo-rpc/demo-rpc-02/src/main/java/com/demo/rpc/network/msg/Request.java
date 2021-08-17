package com.demo.rpc.network.msg;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 15:45
 */
public class Request {

    private String requestId;

    private Object result;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
