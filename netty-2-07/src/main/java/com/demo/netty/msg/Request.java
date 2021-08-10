package com.demo.netty.msg;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 15:02
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
