@echo off

echo.
echo start building java class
javac -encoding utf-8 -classpath libs/mybatis-generator-core-1.4.0.jar -d ./ src/generator/*.java

echo.
echo building completed

echo.
echo start generating code
java -cp .;libs/mybatis-generator-core-1.4.0.jar generator.Main

echo.
echo code generation completed

echo.
pause
exit