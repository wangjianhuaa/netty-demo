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
    private String nozzle;

    /**
     * 映射
     */
    private String ref;

    /**
     * 别名
     */
    private String alias;

    protected void doExport(){
        System.out.format("生产者信息==> [接口:%s] [映射:%s] [别名:%s] \r\n",nozzle,ref,alias);
    }

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
