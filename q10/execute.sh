javac -d WEB-INF/classes *.java
jar cvf q10.war index.html WEB-INF/*
asadmin deploy --force q10.war
firefox http://localhost:8080/q10/index.html
subl ~/domain1/logs/server.log
