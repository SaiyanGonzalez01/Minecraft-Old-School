@echo off
java -jar jars/CompilePackage.jar "resources/" "web/resources.mc"
./gradlew generatejavascript
pause