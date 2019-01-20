del ..\..\library\ultimatebox.jar
rmdir /S ..\..\reference
rmdir /S bin\com
mkdir bin
javac -d bin -cp C:\mySandBox\AppsBinFolder\P3\current\processing\core\library\core.jar com\UltimateBox\*.java -verbose
jar -cf ultimatebox.jar -C bin/ . && move ultimatebox.jar ..\..\library\
javadoc -cp C:\mySandBox\AppsBinFolder\P3\current\processing\core\library\core.jar -sourcepath com\UltimateBox -d reference com.UltimateBox com\UltimateBox\*.java && move reference ..\..\
jar tf ..\..\library\ultimatebox.jar
