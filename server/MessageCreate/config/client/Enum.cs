// Generated by ProtoGen, Version=2.4.1.473, Culture=neutral, PublicKeyToken=55f7125234beb589.  DO NOT EDIT!
#pragma warning disable 1591, 0612
#region Designer generated code

using pb = global::Google.ProtocolBuffers;
using pbc = global::Google.ProtocolBuffers.Collections;
using pbd = global::Google.ProtocolBuffers.Descriptors;
using scg = global::System.Collections.Generic;
namespace com.core.Message.Model {
  
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
  [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
  [global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
  public static partial class Enum {
  
    #region Extension registration
    public static void RegisterAllExtensions(pb::ExtensionRegistry registry) {
    }
    #endregion
    #region Static variables
    #endregion
    #region Descriptor
    public static pbd::FileDescriptor Descriptor {
      get { return descriptor; }
    }
    private static pbd::FileDescriptor descriptor;
    
    static Enum() {
      byte[] descriptorData = global::System.Convert.FromBase64String(
          "CgplbnVtLnByb3RvEhZjb20uY29yZS5NZXNzYWdlLk1vZGVsKkEKC1Byb3Rv" + 
          "UmFyaXR5EggKBEdSQVkQABIJCgVHUkVFThABEggKBEJMVUUQAhIKCgZZRUxM" + 
          "T1cQAxIHCgNSRUQQBCplCg5Qcm90b0Vycm9yQ29kZRILCgdTVUNDRVNTEAAS" + 
          "EwoPTkVUV09SS19USU1FT1VUEAESHwobU0VSVkVSX0lTX1VOREVSX01BSU5U" + 
          "RU5BTkNFEAISEAoMU1lTVEVNX0VSUk9SEANCBkIERW51bQ==");
      pbd::FileDescriptor.InternalDescriptorAssigner assigner = delegate(pbd::FileDescriptor root) {
        descriptor = root;
        return null;
      };
      pbd::FileDescriptor.InternalBuildGeneratedFileFrom(descriptorData,
          new pbd::FileDescriptor[] {
          }, assigner);
    }
    #endregion
    
  }
  #region Enums
  [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
  [global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
  public enum ProtoRarity {
    GRAY = 0,
    GREEN = 1,
    BLUE = 2,
    YELLOW = 3,
    RED = 4,
  }
  
  [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
  [global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
  public enum ProtoErrorCode {
    SUCCESS = 0,
    NETWORK_TIMEOUT = 1,
    SERVER_IS_UNDER_MAINTENANCE = 2,
    SYSTEM_ERROR = 3,
  }
  
  #endregion
  
}

#endregion Designer generated code
