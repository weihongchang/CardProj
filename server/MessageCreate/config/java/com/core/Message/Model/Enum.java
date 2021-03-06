// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: enum.proto

package com.core.Message.Model;

public final class Enum {
  private Enum() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code com.core.Message.Model.ProtoRarity}
   *
   * <pre>
   * =============== 该文件只存放枚举 ==============
   * 所有枚举命名以Proto开头
   * protobuf 要求所有枚举变量唯一，新加的枚举变量一定要加前缀
   * </pre>
   */
  public enum ProtoRarity
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>GRAY = 0;</code>
     */
    GRAY(0, 0),
    /**
     * <code>GREEN = 1;</code>
     */
    GREEN(1, 1),
    /**
     * <code>BLUE = 2;</code>
     */
    BLUE(2, 2),
    /**
     * <code>YELLOW = 3;</code>
     */
    YELLOW(3, 3),
    /**
     * <code>RED = 4;</code>
     */
    RED(4, 4),
    ;

    /**
     * <code>GRAY = 0;</code>
     */
    public static final int GRAY_VALUE = 0;
    /**
     * <code>GREEN = 1;</code>
     */
    public static final int GREEN_VALUE = 1;
    /**
     * <code>BLUE = 2;</code>
     */
    public static final int BLUE_VALUE = 2;
    /**
     * <code>YELLOW = 3;</code>
     */
    public static final int YELLOW_VALUE = 3;
    /**
     * <code>RED = 4;</code>
     */
    public static final int RED_VALUE = 4;


    public final int getNumber() { return value; }

    public static ProtoRarity valueOf(int value) {
      switch (value) {
        case 0: return GRAY;
        case 1: return GREEN;
        case 2: return BLUE;
        case 3: return YELLOW;
        case 4: return RED;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ProtoRarity>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ProtoRarity>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ProtoRarity>() {
            public ProtoRarity findValueByNumber(int number) {
              return ProtoRarity.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.core.Message.Model.Enum.getDescriptor().getEnumTypes().get(0);
    }

    private static final ProtoRarity[] VALUES = values();

    public static ProtoRarity valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ProtoRarity(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.core.Message.Model.ProtoRarity)
  }

  /**
   * Protobuf enum {@code com.core.Message.Model.ProtoErrorCode}
   *
   * <pre>
   * 新加的以CODE开头
   * </pre>
   */
  public enum ProtoErrorCode
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>SUCCESS = 0;</code>
     *
     * <pre>
     *操作成功
     * </pre>
     */
    SUCCESS(0, 0),
    /**
     * <code>NETWORK_TIMEOUT = 1;</code>
     *
     * <pre>
     *网络超时
     * </pre>
     */
    NETWORK_TIMEOUT(1, 1),
    /**
     * <code>SERVER_IS_UNDER_MAINTENANCE = 2;</code>
     *
     * <pre>
     *服务器正在维护
     * </pre>
     */
    SERVER_IS_UNDER_MAINTENANCE(2, 2),
    /**
     * <code>SYSTEM_ERROR = 3;</code>
     */
    SYSTEM_ERROR(3, 3),
    ;

    /**
     * <code>SUCCESS = 0;</code>
     *
     * <pre>
     *操作成功
     * </pre>
     */
    public static final int SUCCESS_VALUE = 0;
    /**
     * <code>NETWORK_TIMEOUT = 1;</code>
     *
     * <pre>
     *网络超时
     * </pre>
     */
    public static final int NETWORK_TIMEOUT_VALUE = 1;
    /**
     * <code>SERVER_IS_UNDER_MAINTENANCE = 2;</code>
     *
     * <pre>
     *服务器正在维护
     * </pre>
     */
    public static final int SERVER_IS_UNDER_MAINTENANCE_VALUE = 2;
    /**
     * <code>SYSTEM_ERROR = 3;</code>
     */
    public static final int SYSTEM_ERROR_VALUE = 3;


    public final int getNumber() { return value; }

    public static ProtoErrorCode valueOf(int value) {
      switch (value) {
        case 0: return SUCCESS;
        case 1: return NETWORK_TIMEOUT;
        case 2: return SERVER_IS_UNDER_MAINTENANCE;
        case 3: return SYSTEM_ERROR;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ProtoErrorCode>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ProtoErrorCode>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ProtoErrorCode>() {
            public ProtoErrorCode findValueByNumber(int number) {
              return ProtoErrorCode.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.core.Message.Model.Enum.getDescriptor().getEnumTypes().get(1);
    }

    private static final ProtoErrorCode[] VALUES = values();

    public static ProtoErrorCode valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ProtoErrorCode(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:com.core.Message.Model.ProtoErrorCode)
  }


  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nenum.proto\022\026com.core.Message.Model*A\n\013" +
      "ProtoRarity\022\010\n\004GRAY\020\000\022\t\n\005GREEN\020\001\022\010\n\004BLUE" +
      "\020\002\022\n\n\006YELLOW\020\003\022\007\n\003RED\020\004*e\n\016ProtoErrorCod" +
      "e\022\013\n\007SUCCESS\020\000\022\023\n\017NETWORK_TIMEOUT\020\001\022\037\n\033S" +
      "ERVER_IS_UNDER_MAINTENANCE\020\002\022\020\n\014SYSTEM_E" +
      "RROR\020\003B\006B\004Enum"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
