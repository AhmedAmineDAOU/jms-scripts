import javax.naming.InitialContext;

import javax.jms.*;
import javax.naming.*;
import java.lang.System;
import java.util.ArrayList;


public class Sender {
    public static void main(String[] args) {
		try{
			
        // get the initial context
        InitialContext ctx = new InitialContext();
        ArrayList<TextMessage> listOfMessages = new ArrayList();
        // lookup the queue object
        Queue queue = (Queue) ctx.lookup("jms/mQ");

        // lookup the queue connection factory
        QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup("jms/mC");

        // create a queue connection
        QueueConnection queueConn = connFactory.createQueueConnection();

        // create a queue session
        QueueSession queueSession = queueConn.createQueueSession(false,Session.DUPS_OK_ACKNOWLEDGE);

        // create a queue sender
        QueueSender queueSender = queueSession.createSender(queue);
        queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //listOfMessages.add(queueSession.createTextMessage("Hello"));
        listOfMessages.add(queueSession.createTextMessage("Message1"));
        listOfMessages.add(queueSession.createTextMessage("Message2"));
        listOfMessages.add(queueSession.createTextMessage("Message3"));
        listOfMessages.add(queueSession.createTextMessage("Message4"));
        listOfMessages.add(queueSession.createTextMessage("Message5"));

        // create a simple message to say "Hello"
        for (TextMessage t : listOfMessages) {

            /*   TextMessage message = queueSession.createTextMessage("Hello");

               // send the message

            */
            queueSender.send(t);

            // print what we did
            System.out.println( " sent: " + t.getText());
        }

        // close the queue connection
        queueConn.close();
        
         }catch (NamingException ne){
		   System.out.println("ERROR Naming!!"+ ne);}
	     catch (JMSException je){
	       System.out.println("ERROR JMS!!"+  je);}
    }
}
