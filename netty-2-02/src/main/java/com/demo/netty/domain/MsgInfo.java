package com.demo.netty.domain;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/8/5 14:19
 */
public final class MsgInfo {

    private MsgInfo() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry){

    }

    static void registerAllExtensions(ExtensionRegistry registry){
        registerAllExtensions(registry);
    }

    static final Descriptors.Descriptor INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_DESCRIPTOR;

    static final GeneratedMessageV3.FieldAccessorTable INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_FILED_ACCESSOR_TABLE;

    public static Descriptors.FileDescriptor getDescriptor(){
        return descriptor;
    }
    private static Descriptors.FileDescriptor descriptor;

    static {
        String[] descriptorData =
         {
        "\n\rMsgInfo.proto\022\035com.demo.netty." +
        "domain\"-\n\007MsgBody\022\021\n\tchannelId\030\001 \001(\t\022\017\n\007" +
        "msgInfo\030\002 \001(\tB*\n\035com.demo.netty." +
        "domainB\007MsgInfoP\001b\006proto3"
        };

        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new Descriptors.FileDescriptor.InternalDescriptorAssigner() {
                    @Override
                    public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };

        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom
                (descriptorData, new Descriptors.FileDescriptor[]{},assigner);
        INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_DESCRIPTOR = getDescriptor().getMessageTypes().get(0);
        INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_FILED_ACCESSOR_TABLE = new GeneratedMessageV3.FieldAccessorTable
                (INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_DESCRIPTOR,new String[]{"ChannelId","MsgInfo"});
    }





}
