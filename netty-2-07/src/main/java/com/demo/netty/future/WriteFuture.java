package com.demo.netty.future;


import com.demo.netty.msg.Response;

import java.util.concurrent.Future;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/10 15:15
 */
public interface WriteFuture<T> extends Future<T> {

    /**
     * 获取cause
     * @return Throwable
     */
    Throwable cause();

    /**
     * 设置cause
     * @param cause cause
     */
    void setCause(Throwable cause);

    /**
     * 是否写入成功
     * @return boolean
     */
    boolean isWriteSuccess();

    /**
     * 设置写入结果
     * @param result result
     */
    void setWriteResult(boolean result);

    /**
     * 请求id
     * @return id
     */
    String requestId();

    /**
     * 获取response
     * @return response
     */
    T response();

    /**
     * 设置response
     * @param response response
     */
    void setResponse(Response response);

    /**
     * 是否超时
     * @return boolean
     */
    boolean isTimeout();
}
