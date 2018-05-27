javac *.java
jar cvf q7.jar META-INF/* Receiver.class
asadmin deploy --force q7.jar
java Sender
subl /home/paolo/domain1/logs/server.log