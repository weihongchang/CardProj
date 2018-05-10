for %%i in (*.proto) do (  
    protoc_2.4.1.exe -o %%i.pb %%i
)

pause