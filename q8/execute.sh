javac *.java
jar cvf q8.jar META-INF/* Receiver.class
asadmin deploy --force q8.jar
java Sender message_ahmed ahmed
#firefox http://localhost:4848/common/logViewer/logViewerRaw.jsf?instanceName=server
subl ~/domain1/logs/server.log
