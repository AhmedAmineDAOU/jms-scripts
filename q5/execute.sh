javac *.java
java Sender
echo lancer le Receiver et voir que le message a ete supprime
gnome-terminal -x sh -c "bash runQB.sh"
sleep 14
java Receiver
