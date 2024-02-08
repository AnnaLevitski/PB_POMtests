# PB_POMtests

mvn clean test  
mvn test -DsuiteXmlFile=src/test/resources/testing.xml
mvn test -Dbrowser=firefox -DsuiteXmlFile=src/test/resources/browsers_test.xml
mvn test -Dbrowser=MicrosoftEdge -DsuiteXmlFile=src/test/resources/browsers_test.xml
mvn test -Dbrowser=safari -DsuiteXmlFile=src/test/resources/browsers_test.xml