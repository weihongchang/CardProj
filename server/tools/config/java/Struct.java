// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: struct.proto

public final class Struct {
  private Struct() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PbNewsPromptOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 newsPromptId = 1;
    /**
     * <code>required int32 newsPromptId = 1;</code>
     *
     * <pre>
     *消息模块id
     * </pre>
     */
    boolean hasNewsPromptId();
    /**
     * <code>required int32 newsPromptId = 1;</code>
     *
     * <pre>
     *消息模块id
     * </pre>
     */
    int getNewsPromptId();

    // required bool isShow = 2;
    /**
     * <code>required bool isShow = 2;</code>
     *
     * <pre>
     *是否显示提示符号  true显示  false 不显示
     * </pre>
     */
    boolean hasIsShow();
    /**
     * <code>required bool isShow = 2;</code>
     *
     * <pre>
     *是否显示提示符号  true显示  false 不显示
     * </pre>
     */
    boolean getIsShow();
  }
  /**
   * Protobuf type {@code PbNewsPrompt}
   *
   * <pre>
   *消息提醒
   * </pre>
   */
  public static final class PbNewsPrompt extends
      com.google.protobuf.GeneratedMessage
      implements PbNewsPromptOrBuilder {
    // Use PbNewsPrompt.newBuilder() to construct.
    private PbNewsPrompt(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PbNewsPrompt(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PbNewsPrompt defaultInstance;
    public static PbNewsPrompt getDefaultInstance() {
      return defaultInstance;
    }

    public PbNewsPrompt getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PbNewsPrompt(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              newsPromptId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              isShow_ = input.readBool();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return Struct.internal_static_PbNewsPrompt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return Struct.internal_static_PbNewsPrompt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              Struct.PbNewsPrompt.class, Struct.PbNewsPrompt.Builder.class);
    }

    public static com.google.protobuf.Parser<PbNewsPrompt> PARSER =
        new com.google.protobuf.AbstractParser<PbNewsPrompt>() {
      public PbNewsPrompt parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PbNewsPrompt(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PbNewsPrompt> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 newsPromptId = 1;
    public static final int NEWSPROMPTID_FIELD_NUMBER = 1;
    private int newsPromptId_;
    /**
     * <code>required int32 newsPromptId = 1;</code>
     *
     * <pre>
     *消息模块id
     * </pre>
     */
    public boolean hasNewsPromptId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 newsPromptId = 1;</code>
     *
     * <pre>
     *消息模块id
     * </pre>
     */
    public int getNewsPromptId() {
      return newsPromptId_;
    }

    // required bool isShow = 2;
    public static final int ISSHOW_FIELD_NUMBER = 2;
    private boolean isShow_;
    /**
     * <code>required bool isShow = 2;</code>
     *
     * <pre>
     *是否显示提示符号  true显示  false 不显示
     * </pre>
     */
    public boolean hasIsShow() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required bool isShow = 2;</code>
     *
     * <pre>
     *是否显示提示符号  true显示  false 不显示
     * </pre>
     */
    public boolean getIsShow() {
      return isShow_;
    }

    private void initFields() {
      newsPromptId_ = 0;
      isShow_ = false;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasNewsPromptId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasIsShow()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, newsPromptId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBool(2, isShow_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, newsPromptId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(2, isShow_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static Struct.PbNewsPrompt parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Struct.PbNewsPrompt parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Struct.PbNewsPrompt parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static Struct.PbNewsPrompt parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static Struct.PbNewsPrompt parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Struct.PbNewsPrompt parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static Struct.PbNewsPrompt parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static Struct.PbNewsPrompt parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static Struct.PbNewsPrompt parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static Struct.PbNewsPrompt parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(Struct.PbNewsPrompt prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code PbNewsPrompt}
     *
     * <pre>
     *消息提醒
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements Struct.PbNewsPromptOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return Struct.internal_static_PbNewsPrompt_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return Struct.internal_static_PbNewsPrompt_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                Struct.PbNewsPrompt.class, Struct.PbNewsPrompt.Builder.class);
      }

      // Construct using Struct.PbNewsPrompt.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        newsPromptId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        isShow_ = false;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return Struct.internal_static_PbNewsPrompt_descriptor;
      }

      public Struct.PbNewsPrompt getDefaultInstanceForType() {
        return Struct.PbNewsPrompt.getDefaultInstance();
      }

      public Struct.PbNewsPrompt build() {
        Struct.PbNewsPrompt result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public Struct.PbNewsPrompt buildPartial() {
        Struct.PbNewsPrompt result = new Struct.PbNewsPrompt(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.newsPromptId_ = newsPromptId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.isShow_ = isShow_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof Struct.PbNewsPrompt) {
          return mergeFrom((Struct.PbNewsPrompt)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(Struct.PbNewsPrompt other) {
        if (other == Struct.PbNewsPrompt.getDefaultInstance()) return this;
        if (other.hasNewsPromptId()) {
          setNewsPromptId(other.getNewsPromptId());
        }
        if (other.hasIsShow()) {
          setIsShow(other.getIsShow());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasNewsPromptId()) {
          
          return false;
        }
        if (!hasIsShow()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        Struct.PbNewsPrompt parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (Struct.PbNewsPrompt) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 newsPromptId = 1;
      private int newsPromptId_ ;
      /**
       * <code>required int32 newsPromptId = 1;</code>
       *
       * <pre>
       *消息模块id
       * </pre>
       */
      public boolean hasNewsPromptId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 newsPromptId = 1;</code>
       *
       * <pre>
       *消息模块id
       * </pre>
       */
      public int getNewsPromptId() {
        return newsPromptId_;
      }
      /**
       * <code>required int32 newsPromptId = 1;</code>
       *
       * <pre>
       *消息模块id
       * </pre>
       */
      public Builder setNewsPromptId(int value) {
        bitField0_ |= 0x00000001;
        newsPromptId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 newsPromptId = 1;</code>
       *
       * <pre>
       *消息模块id
       * </pre>
       */
      public Builder clearNewsPromptId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        newsPromptId_ = 0;
        onChanged();
        return this;
      }

      // required bool isShow = 2;
      private boolean isShow_ ;
      /**
       * <code>required bool isShow = 2;</code>
       *
       * <pre>
       *是否显示提示符号  true显示  false 不显示
       * </pre>
       */
      public boolean hasIsShow() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required bool isShow = 2;</code>
       *
       * <pre>
       *是否显示提示符号  true显示  false 不显示
       * </pre>
       */
      public boolean getIsShow() {
        return isShow_;
      }
      /**
       * <code>required bool isShow = 2;</code>
       *
       * <pre>
       *是否显示提示符号  true显示  false 不显示
       * </pre>
       */
      public Builder setIsShow(boolean value) {
        bitField0_ |= 0x00000002;
        isShow_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required bool isShow = 2;</code>
       *
       * <pre>
       *是否显示提示符号  true显示  false 不显示
       * </pre>
       */
      public Builder clearIsShow() {
        bitField0_ = (bitField0_ & ~0x00000002);
        isShow_ = false;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:PbNewsPrompt)
    }

    static {
      defaultInstance = new PbNewsPrompt(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:PbNewsPrompt)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_PbNewsPrompt_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_PbNewsPrompt_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014struct.proto\032\nenum.proto\"4\n\014PbNewsProm" +
      "pt\022\024\n\014newsPromptId\030\001 \002(\005\022\016\n\006isShow\030\002 \002(\010" +
      "B\010B\006Struct"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_PbNewsPrompt_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_PbNewsPrompt_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_PbNewsPrompt_descriptor,
              new java.lang.String[] { "NewsPromptId", "IsShow", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          Enum.getDescriptor(),
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
