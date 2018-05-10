
Set ProjectPath=E:\NewServer1106\trunk\NewWorkTest\Assets\scripts

copy /y config\protobuf\message.proto 
copy /y config\protobuf\enum.proto 
copy /y config\protobuf\struct.proto 

protoc --descriptor_set_out=Message.protobin --include_imports message.proto
protogen -output_directory=config\client Message.protobin

protoc --descriptor_set_out=Enum.protobin --include_imports enum.proto
protogen -output_directory=config\client Enum.protobin

protoc --descriptor_set_out=Struct.protobin --include_imports struct.proto
protogen -output_directory=config\client Struct.protobin
 
 
del Message.protobin
del Enum.protobin 
del Struct.protobin 

del message.proto 
del enum.proto 
del struct.proto 

copy /y config\client\*.cs %ProjectPath%\

pause