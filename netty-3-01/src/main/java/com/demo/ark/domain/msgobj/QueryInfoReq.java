package com.demo.ark.domain.msgobj;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 14:36
 */
public class QueryInfoReq {

    /**
     * 类型 FeedBack 1/2
     */
    private Integer stateType;

    public QueryInfoReq(Integer stateType) {
        this.stateType = stateType;
    }

    public Integer getStateType() {
        return stateType;
    }

    public void setStateType(Integer stateType) {
        this.stateType = stateType;
    }
}
