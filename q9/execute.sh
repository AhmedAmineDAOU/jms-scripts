javac -d WEB-INF/classes *.java
jar cvf q9.war index.html WEB-INF/*
asadmin deploy --force q9.war
firefox http://localhost:8080/q9/index.html
sleep 4
subl ~/domain1/logs/server.log
