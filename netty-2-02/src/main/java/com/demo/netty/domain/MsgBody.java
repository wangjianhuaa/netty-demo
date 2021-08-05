package com.demo.netty.domain;

import com.google.protobuf.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @author wangjianhua
 * @Description
 * @date 2021-08-05 17:25:37
 */
public  final class MsgBody extends GeneratedMessageV3 implements MsgBodyOrBuilder {

    private static final long serialVersionUID = 0L;
    
    private MsgBody(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }
    private MsgBody() {
        channelId_ = "";
        msgInfo_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MsgBody(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields =
                UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            while (!done) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        done = true;
                        break;
                    default: {
                        if (!parseUnknownFieldProto3(
                                input, unknownFields, extensionRegistry, tag)) {
                            done = true;
                        }
                        break;
                    }
                    case 10: {
                        channelId_ = input.readStringRequireUtf8();
                        break;
                    }
                    case 18: {
                        msgInfo_ = input.readStringRequireUtf8();
                        break;
                    }
                }
            }
        }
        catch (InvalidProtocolBufferException e)
        {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e)
        {
            throw new InvalidProtocolBufferException(
                    e).setUnfinishedMessage(this);
        }
        finally
        {
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MsgInfo.INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_DESCRIPTOR;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MsgInfo.INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_FILED_ACCESSOR_TABLE
                .ensureFieldAccessorsInitialized(
                        MsgBody.class, MsgBody.Builder.class);
    }

    public static final int CHANNELID_FIELD_NUMBER = 1;
    private volatile Object channelId_;
    
    /**
     * <code>string channelId = 1;</code>
     */
    @Override
    public String getChannelId() {
        Object ref = channelId_;
        if (ref instanceof String) {
            return (String) ref;
        } else {
            ByteString bs =
                    (ByteString) ref;
            String s = bs.toStringUtf8();
            channelId_ = s;
            return s;
        }
    }
    /**
     * <code>string channelId = 1;</code>
     */
    @Override
    public ByteString getChannelIdBytes() {
        Object ref = channelId_;
        if (ref instanceof String) {
            ByteString b =
                    ByteString.copyFromUtf8(
                            (String) ref);
            channelId_ = b;
            return b;
        } else {
            return (ByteString) ref;
        }
    }

    public static final int MSGINFO_FIELD_NUMBER = 2;

    private volatile Object msgInfo_;
    /**
     * <code>string msgInfo = 2;</code>
     */
    @Override
    public String getMsgInfo() {
        Object ref = msgInfo_;
        if (ref instanceof String) {
            return (String) ref;
        } else {
            ByteString bs =
                    (ByteString) ref;
            String s = bs.toStringUtf8();
            msgInfo_ = s;
            return s;
        }
    }

    /**
     * <code>string msgInfo = 2;</code>
     */
    @Override
    public ByteString getMsgInfoBytes() {
        Object ref = msgInfo_;
        if (ref instanceof String) {
            ByteString b =
                    ByteString.copyFromUtf8(
                            (String) ref);
            msgInfo_ = b;
            return b;
        } else {
            return (ByteString) ref;
        }
    }

    private byte memoizedIsInitialized = -1;
    
    @Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1)
        {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }

        memoizedIsInitialized = 1;
        return true;
    }

    @Override
    public void writeTo(CodedOutputStream output) throws IOException {
        if (!getChannelIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, channelId_);
        }
        if (!getMsgInfoBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, msgInfo_);
        }
        unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1)
        {
            return size;
        }

        size = 0;
        if (!getChannelIdBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(1, channelId_);
        }
        if (!getMsgInfoBytes().isEmpty()) {
            size += GeneratedMessageV3.computeStringSize(2, msgInfo_);
        }
        size += unknownFields.getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MsgBody)) {
            return super.equals(obj);
        }
        MsgBody other = (MsgBody) obj;

        boolean result = true;
        result = result && getChannelId()
                .equals(other.getChannelId());
        result = result && getMsgInfo()
                .equals(other.getMsgInfo());
        result = result && unknownFields.equals(other.unknownFields);
        return result;
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + CHANNELID_FIELD_NUMBER;
        hash = (53 * hash) + getChannelId().hashCode();
        hash = (37 * hash) + MSGINFO_FIELD_NUMBER;
        hash = (53 * hash) + getMsgInfo().hashCode();
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    public static MsgBody parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }
    public static MsgBody parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }
    public static MsgBody parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }
    public static MsgBody parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }
    public static MsgBody parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }
    public static MsgBody parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }
    public static MsgBody parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    public static MsgBody parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static MsgBody parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    public static MsgBody parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static MsgBody parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    public static MsgBody parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType()
    {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(MsgBody prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }
    /**
     * Protobuf type {@code MsgBody}
     */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MsgBodyOrBuilder {
        public static final Descriptors.Descriptor

        getDescriptor() {
            return MsgInfo.INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_DESCRIPTOR;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MsgInfo.INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_FILED_ACCESSOR_TABLE
                    .ensureFieldAccessorsInitialized(MsgBody.class, MsgBody.Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(
                GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (GeneratedMessageV3.alwaysUseFieldBuilders) {
            }
        }
        @Override
        public Builder clear() {
            super.clear();
            channelId_ = "";
            msgInfo_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return MsgInfo.INTERNAL_STATIC_COM_DEMO_NETTY_DOMAIN_MSG_BODY_DESCRIPTOR;
        }

        @Override
        public MsgBody getDefaultInstanceForType() {
            return MsgBody.getDefaultInstance();
        }

        @Override
        public MsgBody build() {
            MsgBody result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @Override
        public MsgBody buildPartial() {
            MsgBody result = new MsgBody(this);
            result.channelId_ = channelId_;
            result.msgInfo_ = msgInfo_;
            onBuilt();
            return result;
        }

        @Override
        public Builder clone() {
            return (Builder) super.clone();
        }
        @Override
        public Builder setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }
        @Override
        public Builder clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }
        @Override
        public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }
        @Override
        public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }
        @Override
        public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }
        @Override
        public Builder mergeFrom(Message other) {
            if (other instanceof MsgBody) {
                return mergeFrom((MsgBody)other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(MsgBody other) {
            if (other == MsgBody.getDefaultInstance())
            {
                return this;
            }
            if (!other.getChannelId().isEmpty()) {
                channelId_ = other.channelId_;
                onChanged();
            }
            if (!other.getMsgInfo().isEmpty()) {
                msgInfo_ = other.msgInfo_;
                onChanged();
            }
            this.mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        @Override
        public final boolean isInitialized() {
            return true;
        }

        @Override
        public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            MsgBody parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (InvalidProtocolBufferException e) {
                parsedMessage = (MsgBody) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private Object channelId_ = "";
        /**
         * <code>string channelId = 1;</code>
         */
        @Override
        public String getChannelId() {
            Object ref = channelId_;
            if (!(ref instanceof String)) {
                ByteString bs =
                        (ByteString) ref;
                String s = bs.toStringUtf8();
                channelId_ = s;
                return s;
            } else {
                return (String) ref;
            }
        }
        /**
         * <code>string channelId = 1;</code>
         */
        @Override
        public ByteString getChannelIdBytes() {
            Object ref = channelId_;
            if (ref instanceof String) {
                ByteString b =
                        ByteString.copyFromUtf8(
                                (String) ref);
                channelId_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }
        /**
         * <code>string channelId = 1;</code>
         */
        public Builder setChannelId(
                String value) {
            if (value == null) {
                throw new NullPointerException();
            }

            channelId_ = value;
            onChanged();
            return this;
        }
        /**
         * <code>string channelId = 1;</code>
         */
        public Builder clearChannelId() {

            channelId_ = getDefaultInstance().getChannelId();
            onChanged();
            return this;
        }
        /**
         * <code>string channelId = 1;</code>
         */
        public Builder setChannelIdBytes(
                ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);

            channelId_ = value;
            onChanged();
            return this;
        }

        private Object msgInfo_ = "";
        /**
         * <code>string msgInfo = 2;</code>
         */
        @Override
        public String getMsgInfo() {
            Object ref = msgInfo_;
            if (!(ref instanceof String)) {
                ByteString bs =
                        (ByteString) ref;
                String s = bs.toStringUtf8();
                msgInfo_ = s;
                return s;
            } else {
                return (String) ref;
            }
        }
        /**
         * <code>string msgInfo = 2;</code>
         */
        @Override
        public ByteString getMsgInfoBytes() {
            Object ref = msgInfo_;
            if (ref instanceof String) {
                ByteString b =
                        ByteString.copyFromUtf8(
                                (String) ref);
                msgInfo_ = b;
                return b;
            } else {
                return (ByteString) ref;
            }
        }
        /**
         * <code>string msgInfo = 2;</code>
         */
        public Builder setMsgInfo(
                String value) {
            if (value == null) {
                throw new NullPointerException();
            }

            msgInfo_ = value;
            onChanged();
            return this;
        }
        /**
         * <code>string msgInfo = 2;</code>
         */
        public Builder clearMsgInfo() {

            msgInfo_ = getDefaultInstance().getMsgInfo();
            onChanged();
            return this;
        }
        /**
         * <code>string msgInfo = 2;</code>
         */
        public Builder setMsgInfoBytes(
                ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);

            msgInfo_ = value;
            onChanged();
            return this;
        }
        @Override
        public final Builder setUnknownFields(final UnknownFieldSet unknownFields) {
            return super.setUnknownFieldsProto3(unknownFields);
        }

        @Override
        public final Builder mergeUnknownFields(final UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }


    }

    private static final MsgBody DEFAULT_INSTANCE;
    static {
        DEFAULT_INSTANCE = new MsgBody();
    }

    public static MsgBody getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    private static final Parser<MsgBody> PARSER = new AbstractParser<MsgBody>() {
        @Override
        public MsgBody parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry)
                throws InvalidProtocolBufferException {
            return new MsgBody(input, extensionRegistry);
        }
    };

    public static Parser<MsgBody> parser() {
        return PARSER;
    }

    @Override
    public Parser<MsgBody> getParserForType() {
        return PARSER;
    }

    @Override
    public MsgBody getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

}
