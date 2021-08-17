package com.demo.rpc.domain;

/**
 * @author wangjianhua
 * @Description rpc中间件设置
 * @date 2021/8/17/017 20:49
 **/
public class RpcProviderConfig {

    private String nozzle;

    private String ref;

    private String alias;

    private String host;

    private int port;

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
