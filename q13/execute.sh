javac -d WEB-INF/classes *.java
jar cvf q13.war WEB-INF/*
asadmin deploy --force q13.war
cd WEB-INF/classes
gnome-terminal -x sh -c "sleep 4;java Sender2"
java Receiver1

