protoc --descriptor_set_out=MsgID.protobin --include_imports MsgID.proto
protogen -output_directory=..\dota MsgID.protobin

protoc --descriptor_set_out=MsgData.protobin --include_imports MsgData.proto
protogen -output_directory=..\dota MsgData.protobin

protoc --descriptor_set_out=SignUpResponse.protobin --include_imports SignUpResponse.proto
protogen -output_directory=..\dota SignUpResponse.protobin
 
protoc --descriptor_set_out=MsgTest.protobin --include_imports MsgTest.proto
protogen -output_directory=..\dota MsgTest.protobin
 
del MsgID.protobin
del MsgData.protobin 
del SignUpResponse.protobin 
del MsgTest.protobin 


pause