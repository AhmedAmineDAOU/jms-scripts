javac -d WEB-INF/classes src/*.java
wsgen -cp WEB-INF/classes:$CLASSPATH -d WEB-INF/classes/ -wsdl src.WSEmetteur
jar cvf WS.war WEB-INF/*
asadmin deploy --force WS.war
firefox localhost:8080/WS/WSEmetteurService?Tester
sleep 4
subl ~/domain1/logs/server.log

