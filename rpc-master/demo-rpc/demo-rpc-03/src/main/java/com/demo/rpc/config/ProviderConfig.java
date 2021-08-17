package com.demo.rpc.config;

/**
 * @author wangjianhua
 * @Description 生产者信息
 * @date 2021/8/17 10:28
 */
public class ProviderConfig {

    /**
     * 接口
     */
    protected String nozzle;

    /**
     * 映射
     */
    protected String ref;

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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
