package com.demo.ark.domain.msgobj;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/18 14:32
 */
public class Feedback {

    /**
     * 设备id 管带id
     */
    private String channelId;

    /**
     * 设备类型 1/2
     */
    private Integer stateType;

    /**
     * 状态信息
     */
    private String stateMsg;

    public Feedback(String channelId, Integer stateType, String stateMsg) {
        this.channelId = channelId;
        this.stateType = stateType;
        this.stateMsg = stateMsg;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Integer getStateType() {
        return stateType;
    }

    public void setStateType(Integer stateType) {
        this.stateType = stateType;
    }

    public String getStateMsg() {
        return stateMsg;
    }

    public void setStateMsg(String stateMsg) {
        this.stateMsg = stateMsg;
    }
}
