package com.demo.rpc.config;

/**
 * @author wangjianhua
 * @Description 消费者信息
 * @date 2021/8/17 10:34
 */
public class ConsumerConfig<T> {

    /**
     * 接口
     */
    protected String nozzle;

    /**
     * 别名
     */
    protected String alias;

    public String getNozzle() {
        return nozzle;
    }

    public void setNozzle(String nozzle) {
        this.nozzle = nozzle;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
