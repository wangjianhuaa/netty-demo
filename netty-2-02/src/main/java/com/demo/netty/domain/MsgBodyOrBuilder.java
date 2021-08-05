package com.demo.netty.domain;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/5 14:44
 */
public interface MsgBodyOrBuilder extends MessageOrBuilder {

    /**
     * 获取管道id
     * <code>String channelId = 1;</code>
     * @return id
     */
    String getChannelId();

    /**
     * 获取byte
     * <code>String channelId = 1;</code>
     * @return byte
     */
    ByteString getChannelIdBytes();

    /**
     * 获取MsgInfo
     * <code>String msgInfo = 2;</code>
     * @return MsgInfo
     */
    String getMsgInfo();

    /**
     * 获取MsgInfoBytes
     * <code>String msgInfo = 2;</code>
     * @return MsgInfoBytes
     */
    ByteString getMsgInfoBytes();
}
