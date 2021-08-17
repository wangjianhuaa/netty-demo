package com.demo.rpc.network.future;

import com.demo.rpc.network.msg.Response;

import java.util.concurrent.Future;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/17 15:50
 */
public interface WriteFuture<T> extends Future<T> {

    /**
     * 报错
     * @return Throwable
     */
    Throwable cause();

    /**
     * 设置报错原因
     * @param cause cause
     */
    void setCause(Throwable cause);

    /**
     * 写入成功结果
     * @return boolean
     */
    boolean isWriteSuccess();

    /**
     * 设置写入结果
     * @param result result
     */
    void setWriteResult(boolean result);

    /**
     * 获取请求id
     * @return id
     */
    String requestId();

    /**
     * 返回
     * @return 泛型
     */
    T response();

    /**
     * 设置返回值
     * @param response 返回值
     */
    void setResponse(Response response);

    /**
     * 是否超时
     * @return boolean
     */
    boolean isTimeout();
}
