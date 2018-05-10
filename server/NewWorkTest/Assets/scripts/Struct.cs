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
  public static partial class Struct {
  
    #region Extension registration
    public static void RegisterAllExtensions(pb::ExtensionRegistry registry) {
    }
    #endregion
    #region Static variables
    internal static pbd::MessageDescriptor internal__static_com_core_Message_Model_PbNewsPrompt__Descriptor;
    internal static pb::FieldAccess.FieldAccessorTable<global::com.core.Message.Model.PbNewsPrompt, global::com.core.Message.Model.PbNewsPrompt.Builder> internal__static_com_core_Message_Model_PbNewsPrompt__FieldAccessorTable;
    #endregion
    #region Descriptor
    public static pbd::FileDescriptor Descriptor {
      get { return descriptor; }
    }
    private static pbd::FileDescriptor descriptor;
    
    static Struct() {
      byte[] descriptorData = global::System.Convert.FromBase64String(
          "CgxzdHJ1Y3QucHJvdG8SFmNvbS5jb3JlLk1lc3NhZ2UuTW9kZWwaCmVudW0u" + 
          "cHJvdG8iNAoMUGJOZXdzUHJvbXB0EhQKDG5ld3NQcm9tcHRJZBgBIAIoBRIO" + 
          "CgZpc1Nob3cYAiACKAhCCEIGU3RydWN0");
      pbd::FileDescriptor.InternalDescriptorAssigner assigner = delegate(pbd::FileDescriptor root) {
        descriptor = root;
        internal__static_com_core_Message_Model_PbNewsPrompt__Descriptor = Descriptor.MessageTypes[0];
        internal__static_com_core_Message_Model_PbNewsPrompt__FieldAccessorTable = 
            new pb::FieldAccess.FieldAccessorTable<global::com.core.Message.Model.PbNewsPrompt, global::com.core.Message.Model.PbNewsPrompt.Builder>(internal__static_com_core_Message_Model_PbNewsPrompt__Descriptor,
                new string[] { "NewsPromptId", "IsShow", });
        return null;
      };
      pbd::FileDescriptor.InternalBuildGeneratedFileFrom(descriptorData,
          new pbd::FileDescriptor[] {
          global::com.core.Message.Model.Enum.Descriptor, 
          }, assigner);
    }
    #endregion
    
  }
  #region Messages
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
  [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
  [global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
  public sealed partial class PbNewsPrompt : pb::GeneratedMessage<PbNewsPrompt, PbNewsPrompt.Builder> {
    private PbNewsPrompt() { }
    private static readonly PbNewsPrompt defaultInstance = new PbNewsPrompt().MakeReadOnly();
    private static readonly string[] _pbNewsPromptFieldNames = new string[] { "isShow", "newsPromptId" };
    private static readonly uint[] _pbNewsPromptFieldTags = new uint[] { 16, 8 };
    public static PbNewsPrompt DefaultInstance {
      get { return defaultInstance; }
    }
    
    public override PbNewsPrompt DefaultInstanceForType {
      get { return DefaultInstance; }
    }
    
    protected override PbNewsPrompt ThisMessage {
      get { return this; }
    }
    
    public static pbd::MessageDescriptor Descriptor {
      get { return global::com.core.Message.Model.Struct.internal__static_com_core_Message_Model_PbNewsPrompt__Descriptor; }
    }
    
    protected override pb::FieldAccess.FieldAccessorTable<PbNewsPrompt, PbNewsPrompt.Builder> InternalFieldAccessors {
      get { return global::com.core.Message.Model.Struct.internal__static_com_core_Message_Model_PbNewsPrompt__FieldAccessorTable; }
    }
    
    public const int NewsPromptIdFieldNumber = 1;
    private bool hasNewsPromptId;
    private int newsPromptId_;
    public bool HasNewsPromptId {
      get { return hasNewsPromptId; }
    }
    public int NewsPromptId {
      get { return newsPromptId_; }
    }
    
    public const int IsShowFieldNumber = 2;
    private bool hasIsShow;
    private bool isShow_;
    public bool HasIsShow {
      get { return hasIsShow; }
    }
    public bool IsShow {
      get { return isShow_; }
    }
    
    public override bool IsInitialized {
      get {
        if (!hasNewsPromptId) return false;
        if (!hasIsShow) return false;
        return true;
      }
    }
    
    public override void WriteTo(pb::ICodedOutputStream output) {
      int size = SerializedSize;
      string[] field_names = _pbNewsPromptFieldNames;
      if (hasNewsPromptId) {
        output.WriteInt32(1, field_names[1], NewsPromptId);
      }
      if (hasIsShow) {
        output.WriteBool(2, field_names[0], IsShow);
      }
      UnknownFields.WriteTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public override int SerializedSize {
      get {
        int size = memoizedSerializedSize;
        if (size != -1) return size;
        
        size = 0;
        if (hasNewsPromptId) {
          size += pb::CodedOutputStream.ComputeInt32Size(1, NewsPromptId);
        }
        if (hasIsShow) {
          size += pb::CodedOutputStream.ComputeBoolSize(2, IsShow);
        }
        size += UnknownFields.SerializedSize;
        memoizedSerializedSize = size;
        return size;
      }
    }
    
    public static PbNewsPrompt ParseFrom(pb::ByteString data) {
      return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
    }
    public static PbNewsPrompt ParseFrom(pb::ByteString data, pb::ExtensionRegistry extensionRegistry) {
      return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
    }
    public static PbNewsPrompt ParseFrom(byte[] data) {
      return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
    }
    public static PbNewsPrompt ParseFrom(byte[] data, pb::ExtensionRegistry extensionRegistry) {
      return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
    }
    public static PbNewsPrompt ParseFrom(global::System.IO.Stream input) {
      return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
    }
    public static PbNewsPrompt ParseFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
      return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
    }
    public static PbNewsPrompt ParseDelimitedFrom(global::System.IO.Stream input) {
      return CreateBuilder().MergeDelimitedFrom(input).BuildParsed();
    }
    public static PbNewsPrompt ParseDelimitedFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
      return CreateBuilder().MergeDelimitedFrom(input, extensionRegistry).BuildParsed();
    }
    public static PbNewsPrompt ParseFrom(pb::ICodedInputStream input) {
      return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
    }
    public static PbNewsPrompt ParseFrom(pb::ICodedInputStream input, pb::ExtensionRegistry extensionRegistry) {
      return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
    }
    private PbNewsPrompt MakeReadOnly() {
      return this;
    }
    
    public static Builder CreateBuilder() { return new Builder(); }
    public override Builder ToBuilder() { return CreateBuilder(this); }
    public override Builder CreateBuilderForType() { return new Builder(); }
    public static Builder CreateBuilder(PbNewsPrompt prototype) {
      return new Builder(prototype);
    }
    
    [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
    [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
    [global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
    public sealed partial class Builder : pb::GeneratedBuilder<PbNewsPrompt, Builder> {
      protected override Builder ThisBuilder {
        get { return this; }
      }
      public Builder() {
        result = DefaultInstance;
        resultIsReadOnly = true;
      }
      internal Builder(PbNewsPrompt cloneFrom) {
        result = cloneFrom;
        resultIsReadOnly = true;
      }
      
      private bool resultIsReadOnly;
      private PbNewsPrompt result;
      
      private PbNewsPrompt PrepareBuilder() {
        if (resultIsReadOnly) {
          PbNewsPrompt original = result;
          result = new PbNewsPrompt();
          resultIsReadOnly = false;
          MergeFrom(original);
        }
        return result;
      }
      
      public override bool IsInitialized {
        get { return result.IsInitialized; }
      }
      
      protected override PbNewsPrompt MessageBeingBuilt {
        get { return PrepareBuilder(); }
      }
      
      public override Builder Clear() {
        result = DefaultInstance;
        resultIsReadOnly = true;
        return this;
      }
      
      public override Builder Clone() {
        if (resultIsReadOnly) {
          return new Builder(result);
        } else {
          return new Builder().MergeFrom(result);
        }
      }
      
      public override pbd::MessageDescriptor DescriptorForType {
        get { return global::com.core.Message.Model.PbNewsPrompt.Descriptor; }
      }
      
      public override PbNewsPrompt DefaultInstanceForType {
        get { return global::com.core.Message.Model.PbNewsPrompt.DefaultInstance; }
      }
      
      public override PbNewsPrompt BuildPartial() {
        if (resultIsReadOnly) {
          return result;
        }
        resultIsReadOnly = true;
        return result.MakeReadOnly();
      }
      
      public override Builder MergeFrom(pb::IMessage other) {
        if (other is PbNewsPrompt) {
          return MergeFrom((PbNewsPrompt) other);
        } else {
          base.MergeFrom(other);
          return this;
        }
      }
      
      public override Builder MergeFrom(PbNewsPrompt other) {
        if (other == global::com.core.Message.Model.PbNewsPrompt.DefaultInstance) return this;
        PrepareBuilder();
        if (other.HasNewsPromptId) {
          NewsPromptId = other.NewsPromptId;
        }
        if (other.HasIsShow) {
          IsShow = other.IsShow;
        }
        this.MergeUnknownFields(other.UnknownFields);
        return this;
      }
      
      public override Builder MergeFrom(pb::ICodedInputStream input) {
        return MergeFrom(input, pb::ExtensionRegistry.Empty);
      }
      
      public override Builder MergeFrom(pb::ICodedInputStream input, pb::ExtensionRegistry extensionRegistry) {
        PrepareBuilder();
        pb::UnknownFieldSet.Builder unknownFields = null;
        uint tag;
        string field_name;
        while (input.ReadTag(out tag, out field_name)) {
          if(tag == 0 && field_name != null) {
            int field_ordinal = global::System.Array.BinarySearch(_pbNewsPromptFieldNames, field_name, global::System.StringComparer.Ordinal);
            if(field_ordinal >= 0)
              tag = _pbNewsPromptFieldTags[field_ordinal];
            else {
              if (unknownFields == null) {
                unknownFields = pb::UnknownFieldSet.CreateBuilder(this.UnknownFields);
              }
              ParseUnknownField(input, unknownFields, extensionRegistry, tag, field_name);
              continue;
            }
          }
          switch (tag) {
            case 0: {
              throw pb::InvalidProtocolBufferException.InvalidTag();
            }
            default: {
              if (pb::WireFormat.IsEndGroupTag(tag)) {
                if (unknownFields != null) {
                  this.UnknownFields = unknownFields.Build();
                }
                return this;
              }
              if (unknownFields == null) {
                unknownFields = pb::UnknownFieldSet.CreateBuilder(this.UnknownFields);
              }
              ParseUnknownField(input, unknownFields, extensionRegistry, tag, field_name);
              break;
            }
            case 8: {
              result.hasNewsPromptId = input.ReadInt32(ref result.newsPromptId_);
              break;
            }
            case 16: {
              result.hasIsShow = input.ReadBool(ref result.isShow_);
              break;
            }
          }
        }
        
        if (unknownFields != null) {
          this.UnknownFields = unknownFields.Build();
        }
        return this;
      }
      
      
      public bool HasNewsPromptId {
        get { return result.hasNewsPromptId; }
      }
      public int NewsPromptId {
        get { return result.NewsPromptId; }
        set { SetNewsPromptId(value); }
      }
      public Builder SetNewsPromptId(int value) {
        PrepareBuilder();
        result.hasNewsPromptId = true;
        result.newsPromptId_ = value;
        return this;
      }
      public Builder ClearNewsPromptId() {
        PrepareBuilder();
        result.hasNewsPromptId = false;
        result.newsPromptId_ = 0;
        return this;
      }
      
      public bool HasIsShow {
        get { return result.hasIsShow; }
      }
      public bool IsShow {
        get { return result.IsShow; }
        set { SetIsShow(value); }
      }
      public Builder SetIsShow(bool value) {
        PrepareBuilder();
        result.hasIsShow = true;
        result.isShow_ = value;
        return this;
      }
      public Builder ClearIsShow() {
        PrepareBuilder();
        result.hasIsShow = false;
        result.isShow_ = false;
        return this;
      }
    }
    static PbNewsPrompt() {
      object.ReferenceEquals(global::com.core.Message.Model.Struct.Descriptor, null);
    }
  }
  
  #endregion
  
}

#endregion Designer generated code