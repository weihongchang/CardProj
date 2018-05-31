
Set ProjectPath=..\gameserver\src\main\java\com\core\Message
java -jar mapCreate.jar

copy /y config\java\*.java %ProjectPath%\Model
copy /y config\java\com\core\Message\Model\*.java %ProjectPath%\Model

pause