// Generated by ProtoGen, Version=2.4.1.473, Culture=neutral, PublicKeyToken=55f7125234beb589.  DO NOT EDIT!
#pragma warning disable 1591, 0612
#region Designer generated code

using pb = global::Google.ProtocolBuffers;
using pbc = global::Google.ProtocolBuffers.Collections;
using pbd = global::Google.ProtocolBuffers.Descriptors;
using scg = global::System.Collections.Generic;
[global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
[global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
[global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
public static partial class MsgTest {

  #region Extension registration
  public static void RegisterAllExtensions(pb::ExtensionRegistry registry) {
  }
  #endregion
  #region Static variables
  internal static pbd::MessageDescriptor internal__static_LoginReq__Descriptor;
  internal static pb::FieldAccess.FieldAccessorTable<global::LoginReq, global::LoginReq.Builder> internal__static_LoginReq__FieldAccessorTable;
  internal static pbd::MessageDescriptor internal__static_LoginResp__Descriptor;
  internal static pb::FieldAccess.FieldAccessorTable<global::LoginResp, global::LoginResp.Builder> internal__static_LoginResp__FieldAccessorTable;
  internal static pbd::MessageDescriptor internal__static_LoginTest__Descriptor;
  internal static pb::FieldAccess.FieldAccessorTable<global::LoginTest, global::LoginTest.Builder> internal__static_LoginTest__FieldAccessorTable;
  #endregion
  #region Descriptor
  public static pbd::FileDescriptor Descriptor {
    get { return descriptor; }
  }
  private static pbd::FileDescriptor descriptor;
  
  static MsgTest() {
    byte[] descriptorData = global::System.Convert.FromBase64String(
        "Cg1Nc2dUZXN0LnByb3RvIj8KCExvZ2luUmVxEg0KBWVtYWlsGAEgAigJEhAK" + 
        "CHBhc3N3b3JkGAIgASgJEhIKCnNlc3Npb25LZXkYAyABKAkiGwoJTG9naW5S" + 
        "ZXNwEg4KBnN0YXR1cxgBIAIoBSIbCglMb2dpblRlc3QSDgoGc3RhdHVzGAEg" + 
        "AigFQhgKDVNlcnZlck1lc3NhZ2VCB01zZ1Rlc3Q=");
    pbd::FileDescriptor.InternalDescriptorAssigner assigner = delegate(pbd::FileDescriptor root) {
      descriptor = root;
      internal__static_LoginReq__Descriptor = Descriptor.MessageTypes[0];
      internal__static_LoginReq__FieldAccessorTable = 
          new pb::FieldAccess.FieldAccessorTable<global::LoginReq, global::LoginReq.Builder>(internal__static_LoginReq__Descriptor,
              new string[] { "Email", "Password", "SessionKey", });
      internal__static_LoginResp__Descriptor = Descriptor.MessageTypes[1];
      internal__static_LoginResp__FieldAccessorTable = 
          new pb::FieldAccess.FieldAccessorTable<global::LoginResp, global::LoginResp.Builder>(internal__static_LoginResp__Descriptor,
              new string[] { "Status", });
      internal__static_LoginTest__Descriptor = Descriptor.MessageTypes[2];
      internal__static_LoginTest__FieldAccessorTable = 
          new pb::FieldAccess.FieldAccessorTable<global::LoginTest, global::LoginTest.Builder>(internal__static_LoginTest__Descriptor,
              new string[] { "Status", });
      return null;
    };
    pbd::FileDescriptor.InternalBuildGeneratedFileFrom(descriptorData,
        new pbd::FileDescriptor[] {
        }, assigner);
  }
  #endregion
  
}
#region Messages
[global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
[global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
[global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
public sealed partial class LoginReq : pb::GeneratedMessage<LoginReq, LoginReq.Builder> {
  private LoginReq() { }
  private static readonly LoginReq defaultInstance = new LoginReq().MakeReadOnly();
  private static readonly string[] _loginReqFieldNames = new string[] { "email", "password", "sessionKey" };
  private static readonly uint[] _loginReqFieldTags = new uint[] { 10, 18, 26 };
  public static LoginReq DefaultInstance {
    get { return defaultInstance; }
  }
  
  public override LoginReq DefaultInstanceForType {
    get { return DefaultInstance; }
  }
  
  protected override LoginReq ThisMessage {
    get { return this; }
  }
  
  public static pbd::MessageDescriptor Descriptor {
    get { return global::MsgTest.internal__static_LoginReq__Descriptor; }
  }
  
  protected override pb::FieldAccess.FieldAccessorTable<LoginReq, LoginReq.Builder> InternalFieldAccessors {
    get { return global::MsgTest.internal__static_LoginReq__FieldAccessorTable; }
  }
  
  public const int EmailFieldNumber = 1;
  private bool hasEmail;
  private string email_ = "";
  public bool HasEmail {
    get { return hasEmail; }
  }
  public string Email {
    get { return email_; }
  }
  
  public const int PasswordFieldNumber = 2;
  private bool hasPassword;
  private string password_ = "";
  public bool HasPassword {
    get { return hasPassword; }
  }
  public string Password {
    get { return password_; }
  }
  
  public const int SessionKeyFieldNumber = 3;
  private bool hasSessionKey;
  private string sessionKey_ = "";
  public bool HasSessionKey {
    get { return hasSessionKey; }
  }
  public string SessionKey {
    get { return sessionKey_; }
  }
  
  public override bool IsInitialized {
    get {
      if (!hasEmail) return false;
      return true;
    }
  }
  
  public override void WriteTo(pb::ICodedOutputStream output) {
    int size = SerializedSize;
    string[] field_names = _loginReqFieldNames;
    if (hasEmail) {
      output.WriteString(1, field_names[0], Email);
    }
    if (hasPassword) {
      output.WriteString(2, field_names[1], Password);
    }
    if (hasSessionKey) {
      output.WriteString(3, field_names[2], SessionKey);
    }
    UnknownFields.WriteTo(output);
  }
  
  private int memoizedSerializedSize = -1;
  public override int SerializedSize {
    get {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
      
      size = 0;
      if (hasEmail) {
        size += pb::CodedOutputStream.ComputeStringSize(1, Email);
      }
      if (hasPassword) {
        size += pb::CodedOutputStream.ComputeStringSize(2, Password);
      }
      if (hasSessionKey) {
        size += pb::CodedOutputStream.ComputeStringSize(3, SessionKey);
      }
      size += UnknownFields.SerializedSize;
      memoizedSerializedSize = size;
      return size;
    }
  }
  
  public static LoginReq ParseFrom(pb::ByteString data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static LoginReq ParseFrom(pb::ByteString data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static LoginReq ParseFrom(byte[] data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static LoginReq ParseFrom(byte[] data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static LoginReq ParseFrom(global::System.IO.Stream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static LoginReq ParseFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  public static LoginReq ParseDelimitedFrom(global::System.IO.Stream input) {
    return CreateBuilder().MergeDelimitedFrom(input).BuildParsed();
  }
  public static LoginReq ParseDelimitedFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return CreateBuilder().MergeDelimitedFrom(input, extensionRegistry).BuildParsed();
  }
  public static LoginReq ParseFrom(pb::ICodedInputStream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static LoginReq ParseFrom(pb::ICodedInputStream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  private LoginReq MakeReadOnly() {
    return this;
  }
  
  public static Builder CreateBuilder() { return new Builder(); }
  public override Builder ToBuilder() { return CreateBuilder(this); }
  public override Builder CreateBuilderForType() { return new Builder(); }
  public static Builder CreateBuilder(LoginReq prototype) {
    return new Builder(prototype);
  }
  
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
  [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
  [global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
  public sealed partial class Builder : pb::GeneratedBuilder<LoginReq, Builder> {
    protected override Builder ThisBuilder {
      get { return this; }
    }
    public Builder() {
      result = DefaultInstance;
      resultIsReadOnly = true;
    }
    internal Builder(LoginReq cloneFrom) {
      result = cloneFrom;
      resultIsReadOnly = true;
    }
    
    private bool resultIsReadOnly;
    private LoginReq result;
    
    private LoginReq PrepareBuilder() {
      if (resultIsReadOnly) {
        LoginReq original = result;
        result = new LoginReq();
        resultIsReadOnly = false;
        MergeFrom(original);
      }
      return result;
    }
    
    public override bool IsInitialized {
      get { return result.IsInitialized; }
    }
    
    protected override LoginReq MessageBeingBuilt {
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
      get { return global::LoginReq.Descriptor; }
    }
    
    public override LoginReq DefaultInstanceForType {
      get { return global::LoginReq.DefaultInstance; }
    }
    
    public override LoginReq BuildPartial() {
      if (resultIsReadOnly) {
        return result;
      }
      resultIsReadOnly = true;
      return result.MakeReadOnly();
    }
    
    public override Builder MergeFrom(pb::IMessage other) {
      if (other is LoginReq) {
        return MergeFrom((LoginReq) other);
      } else {
        base.MergeFrom(other);
        return this;
      }
    }
    
    public override Builder MergeFrom(LoginReq other) {
      if (other == global::LoginReq.DefaultInstance) return this;
      PrepareBuilder();
      if (other.HasEmail) {
        Email = other.Email;
      }
      if (other.HasPassword) {
        Password = other.Password;
      }
      if (other.HasSessionKey) {
        SessionKey = other.SessionKey;
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
          int field_ordinal = global::System.Array.BinarySearch(_loginReqFieldNames, field_name, global::System.StringComparer.Ordinal);
          if(field_ordinal >= 0)
            tag = _loginReqFieldTags[field_ordinal];
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
          case 10: {
            result.hasEmail = input.ReadString(ref result.email_);
            break;
          }
          case 18: {
            result.hasPassword = input.ReadString(ref result.password_);
            break;
          }
          case 26: {
            result.hasSessionKey = input.ReadString(ref result.sessionKey_);
            break;
          }
        }
      }
      
      if (unknownFields != null) {
        this.UnknownFields = unknownFields.Build();
      }
      return this;
    }
    
    
    public bool HasEmail {
      get { return result.hasEmail; }
    }
    public string Email {
      get { return result.Email; }
      set { SetEmail(value); }
    }
    public Builder SetEmail(string value) {
      pb::ThrowHelper.ThrowIfNull(value, "value");
      PrepareBuilder();
      result.hasEmail = true;
      result.email_ = value;
      return this;
    }
    public Builder ClearEmail() {
      PrepareBuilder();
      result.hasEmail = false;
      result.email_ = "";
      return this;
    }
    
    public bool HasPassword {
      get { return result.hasPassword; }
    }
    public string Password {
      get { return result.Password; }
      set { SetPassword(value); }
    }
    public Builder SetPassword(string value) {
      pb::ThrowHelper.ThrowIfNull(value, "value");
      PrepareBuilder();
      result.hasPassword = true;
      result.password_ = value;
      return this;
    }
    public Builder ClearPassword() {
      PrepareBuilder();
      result.hasPassword = false;
      result.password_ = "";
      return this;
    }
    
    public bool HasSessionKey {
      get { return result.hasSessionKey; }
    }
    public string SessionKey {
      get { return result.SessionKey; }
      set { SetSessionKey(value); }
    }
    public Builder SetSessionKey(string value) {
      pb::ThrowHelper.ThrowIfNull(value, "value");
      PrepareBuilder();
      result.hasSessionKey = true;
      result.sessionKey_ = value;
      return this;
    }
    public Builder ClearSessionKey() {
      PrepareBuilder();
      result.hasSessionKey = false;
      result.sessionKey_ = "";
      return this;
    }
  }
  static LoginReq() {
    object.ReferenceEquals(global::MsgTest.Descriptor, null);
  }
}

[global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
[global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
[global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
public sealed partial class LoginResp : pb::GeneratedMessage<LoginResp, LoginResp.Builder> {
  private LoginResp() { }
  private static readonly LoginResp defaultInstance = new LoginResp().MakeReadOnly();
  private static readonly string[] _loginRespFieldNames = new string[] { "status" };
  private static readonly uint[] _loginRespFieldTags = new uint[] { 8 };
  public static LoginResp DefaultInstance {
    get { return defaultInstance; }
  }
  
  public override LoginResp DefaultInstanceForType {
    get { return DefaultInstance; }
  }
  
  protected override LoginResp ThisMessage {
    get { return this; }
  }
  
  public static pbd::MessageDescriptor Descriptor {
    get { return global::MsgTest.internal__static_LoginResp__Descriptor; }
  }
  
  protected override pb::FieldAccess.FieldAccessorTable<LoginResp, LoginResp.Builder> InternalFieldAccessors {
    get { return global::MsgTest.internal__static_LoginResp__FieldAccessorTable; }
  }
  
  public const int StatusFieldNumber = 1;
  private bool hasStatus;
  private int status_;
  public bool HasStatus {
    get { return hasStatus; }
  }
  public int Status {
    get { return status_; }
  }
  
  public override bool IsInitialized {
    get {
      if (!hasStatus) return false;
      return true;
    }
  }
  
  public override void WriteTo(pb::ICodedOutputStream output) {
    int size = SerializedSize;
    string[] field_names = _loginRespFieldNames;
    if (hasStatus) {
      output.WriteInt32(1, field_names[0], Status);
    }
    UnknownFields.WriteTo(output);
  }
  
  private int memoizedSerializedSize = -1;
  public override int SerializedSize {
    get {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
      
      size = 0;
      if (hasStatus) {
        size += pb::CodedOutputStream.ComputeInt32Size(1, Status);
      }
      size += UnknownFields.SerializedSize;
      memoizedSerializedSize = size;
      return size;
    }
  }
  
  public static LoginResp ParseFrom(pb::ByteString data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static LoginResp ParseFrom(pb::ByteString data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static LoginResp ParseFrom(byte[] data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static LoginResp ParseFrom(byte[] data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static LoginResp ParseFrom(global::System.IO.Stream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static LoginResp ParseFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  public static LoginResp ParseDelimitedFrom(global::System.IO.Stream input) {
    return CreateBuilder().MergeDelimitedFrom(input).BuildParsed();
  }
  public static LoginResp ParseDelimitedFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return CreateBuilder().MergeDelimitedFrom(input, extensionRegistry).BuildParsed();
  }
  public static LoginResp ParseFrom(pb::ICodedInputStream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static LoginResp ParseFrom(pb::ICodedInputStream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  private LoginResp MakeReadOnly() {
    return this;
  }
  
  public static Builder CreateBuilder() { return new Builder(); }
  public override Builder ToBuilder() { return CreateBuilder(this); }
  public override Builder CreateBuilderForType() { return new Builder(); }
  public static Builder CreateBuilder(LoginResp prototype) {
    return new Builder(prototype);
  }
  
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
  [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
  [global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
  public sealed partial class Builder : pb::GeneratedBuilder<LoginResp, Builder> {
    protected override Builder ThisBuilder {
      get { return this; }
    }
    public Builder() {
      result = DefaultInstance;
      resultIsReadOnly = true;
    }
    internal Builder(LoginResp cloneFrom) {
      result = cloneFrom;
      resultIsReadOnly = true;
    }
    
    private bool resultIsReadOnly;
    private LoginResp result;
    
    private LoginResp PrepareBuilder() {
      if (resultIsReadOnly) {
        LoginResp original = result;
        result = new LoginResp();
        resultIsReadOnly = false;
        MergeFrom(original);
      }
      return result;
    }
    
    public override bool IsInitialized {
      get { return result.IsInitialized; }
    }
    
    protected override LoginResp MessageBeingBuilt {
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
      get { return global::LoginResp.Descriptor; }
    }
    
    public override LoginResp DefaultInstanceForType {
      get { return global::LoginResp.DefaultInstance; }
    }
    
    public override LoginResp BuildPartial() {
      if (resultIsReadOnly) {
        return result;
      }
      resultIsReadOnly = true;
      return result.MakeReadOnly();
    }
    
    public override Builder MergeFrom(pb::IMessage other) {
      if (other is LoginResp) {
        return MergeFrom((LoginResp) other);
      } else {
        base.MergeFrom(other);
        return this;
      }
    }
    
    public override Builder MergeFrom(LoginResp other) {
      if (other == global::LoginResp.DefaultInstance) return this;
      PrepareBuilder();
      if (other.HasStatus) {
        Status = other.Status;
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
          int field_ordinal = global::System.Array.BinarySearch(_loginRespFieldNames, field_name, global::System.StringComparer.Ordinal);
          if(field_ordinal >= 0)
            tag = _loginRespFieldTags[field_ordinal];
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
            result.hasStatus = input.ReadInt32(ref result.status_);
            break;
          }
        }
      }
      
      if (unknownFields != null) {
        this.UnknownFields = unknownFields.Build();
      }
      return this;
    }
    
    
    public bool HasStatus {
      get { return result.hasStatus; }
    }
    public int Status {
      get { return result.Status; }
      set { SetStatus(value); }
    }
    public Builder SetStatus(int value) {
      PrepareBuilder();
      result.hasStatus = true;
      result.status_ = value;
      return this;
    }
    public Builder ClearStatus() {
      PrepareBuilder();
      result.hasStatus = false;
      result.status_ = 0;
      return this;
    }
  }
  static LoginResp() {
    object.ReferenceEquals(global::MsgTest.Descriptor, null);
  }
}

[global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
[global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
[global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
public sealed partial class LoginTest : pb::GeneratedMessage<LoginTest, LoginTest.Builder> {
  private LoginTest() { }
  private static readonly LoginTest defaultInstance = new LoginTest().MakeReadOnly();
  private static readonly string[] _loginTestFieldNames = new string[] { "status" };
  private static readonly uint[] _loginTestFieldTags = new uint[] { 8 };
  public static LoginTest DefaultInstance {
    get { return defaultInstance; }
  }
  
  public override LoginTest DefaultInstanceForType {
    get { return DefaultInstance; }
  }
  
  protected override LoginTest ThisMessage {
    get { return this; }
  }
  
  public static pbd::MessageDescriptor Descriptor {
    get { return global::MsgTest.internal__static_LoginTest__Descriptor; }
  }
  
  protected override pb::FieldAccess.FieldAccessorTable<LoginTest, LoginTest.Builder> InternalFieldAccessors {
    get { return global::MsgTest.internal__static_LoginTest__FieldAccessorTable; }
  }
  
  public const int StatusFieldNumber = 1;
  private bool hasStatus;
  private int status_;
  public bool HasStatus {
    get { return hasStatus; }
  }
  public int Status {
    get { return status_; }
  }
  
  public override bool IsInitialized {
    get {
      if (!hasStatus) return false;
      return true;
    }
  }
  
  public override void WriteTo(pb::ICodedOutputStream output) {
    int size = SerializedSize;
    string[] field_names = _loginTestFieldNames;
    if (hasStatus) {
      output.WriteInt32(1, field_names[0], Status);
    }
    UnknownFields.WriteTo(output);
  }
  
  private int memoizedSerializedSize = -1;
  public override int SerializedSize {
    get {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
      
      size = 0;
      if (hasStatus) {
        size += pb::CodedOutputStream.ComputeInt32Size(1, Status);
      }
      size += UnknownFields.SerializedSize;
      memoizedSerializedSize = size;
      return size;
    }
  }
  
  public static LoginTest ParseFrom(pb::ByteString data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static LoginTest ParseFrom(pb::ByteString data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static LoginTest ParseFrom(byte[] data) {
    return ((Builder) CreateBuilder().MergeFrom(data)).BuildParsed();
  }
  public static LoginTest ParseFrom(byte[] data, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(data, extensionRegistry)).BuildParsed();
  }
  public static LoginTest ParseFrom(global::System.IO.Stream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static LoginTest ParseFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  public static LoginTest ParseDelimitedFrom(global::System.IO.Stream input) {
    return CreateBuilder().MergeDelimitedFrom(input).BuildParsed();
  }
  public static LoginTest ParseDelimitedFrom(global::System.IO.Stream input, pb::ExtensionRegistry extensionRegistry) {
    return CreateBuilder().MergeDelimitedFrom(input, extensionRegistry).BuildParsed();
  }
  public static LoginTest ParseFrom(pb::ICodedInputStream input) {
    return ((Builder) CreateBuilder().MergeFrom(input)).BuildParsed();
  }
  public static LoginTest ParseFrom(pb::ICodedInputStream input, pb::ExtensionRegistry extensionRegistry) {
    return ((Builder) CreateBuilder().MergeFrom(input, extensionRegistry)).BuildParsed();
  }
  private LoginTest MakeReadOnly() {
    return this;
  }
  
  public static Builder CreateBuilder() { return new Builder(); }
  public override Builder ToBuilder() { return CreateBuilder(this); }
  public override Builder CreateBuilderForType() { return new Builder(); }
  public static Builder CreateBuilder(LoginTest prototype) {
    return new Builder(prototype);
  }
  
  [global::System.Diagnostics.DebuggerNonUserCodeAttribute()]
  [global::System.Runtime.CompilerServices.CompilerGeneratedAttribute()]
  [global::System.CodeDom.Compiler.GeneratedCodeAttribute("ProtoGen", "2.4.1.473")]
  public sealed partial class Builder : pb::GeneratedBuilder<LoginTest, Builder> {
    protected override Builder ThisBuilder {
      get { return this; }
    }
    public Builder() {
      result = DefaultInstance;
      resultIsReadOnly = true;
    }
    internal Builder(LoginTest cloneFrom) {
      result = cloneFrom;
      resultIsReadOnly = true;
    }
    
    private bool resultIsReadOnly;
    private LoginTest result;
    
    private LoginTest PrepareBuilder() {
      if (resultIsReadOnly) {
        LoginTest original = result;
        result = new LoginTest();
        resultIsReadOnly = false;
        MergeFrom(original);
      }
      return result;
    }
    
    public override bool IsInitialized {
      get { return result.IsInitialized; }
    }
    
    protected override LoginTest MessageBeingBuilt {
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
      get { return global::LoginTest.Descriptor; }
    }
    
    public override LoginTest DefaultInstanceForType {
      get { return global::LoginTest.DefaultInstance; }
    }
    
    public override LoginTest BuildPartial() {
      if (resultIsReadOnly) {
        return result;
      }
      resultIsReadOnly = true;
      return result.MakeReadOnly();
    }
    
    public override Builder MergeFrom(pb::IMessage other) {
      if (other is LoginTest) {
        return MergeFrom((LoginTest) other);
      } else {
        base.MergeFrom(other);
        return this;
      }
    }
    
    public override Builder MergeFrom(LoginTest other) {
      if (other == global::LoginTest.DefaultInstance) return this;
      PrepareBuilder();
      if (other.HasStatus) {
        Status = other.Status;
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
          int field_ordinal = global::System.Array.BinarySearch(_loginTestFieldNames, field_name, global::System.StringComparer.Ordinal);
          if(field_ordinal >= 0)
            tag = _loginTestFieldTags[field_ordinal];
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
            result.hasStatus = input.ReadInt32(ref result.status_);
            break;
          }
        }
      }
      
      if (unknownFields != null) {
        this.UnknownFields = unknownFields.Build();
      }
      return this;
    }
    
    
    public bool HasStatus {
      get { return result.hasStatus; }
    }
    public int Status {
      get { return result.Status; }
      set { SetStatus(value); }
    }
    public Builder SetStatus(int value) {
      PrepareBuilder();
      result.hasStatus = true;
      result.status_ = value;
      return this;
    }
    public Builder ClearStatus() {
      PrepareBuilder();
      result.hasStatus = false;
      result.status_ = 0;
      return this;
    }
  }
  static LoginTest() {
    object.ReferenceEquals(global::MsgTest.Descriptor, null);
  }
}

#endregion


#endregion Designer generated code
