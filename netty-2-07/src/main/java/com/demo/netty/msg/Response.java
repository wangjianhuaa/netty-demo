package com.demo.netty.msg;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 15:03
 */
public class Response {

    private String requestId;

    private String param;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
