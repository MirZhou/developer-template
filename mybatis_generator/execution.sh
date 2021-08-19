echo start building java class
javac -encoding utf-8 -classpath libs/mybatis-generator-core-1.4.0.jar -d ./ src/generator/*.java

echo building completed

echo start generating code
java -cp .:libs/mybatis-generator-core-1.4.0.jar generator.Main

echo code generation completed