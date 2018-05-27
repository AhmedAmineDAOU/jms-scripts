import javax.naming.InitialContext;

import javax.jms.*;
import javax.naming.*;
import java.lang.System;

//import java.io;


public class Receiver {
    public static void main(String[] args){
		try{
			
        // get the initial context
        InitialContext ctx = new InitialContext();

       // lookup the queue object
        Queue queue = (Queue) ctx.lookup("jms/mQ");

        // lookup the queue connection factory
        QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx. lookup("jms/mC");
	
	
        
        // create a queue connection
        QueueConnection queueConn = connFactory.createQueueConnection();

        // create a queue session
        QueueSession queueSession = queueConn.createQueueSession(false,
                                    Session.AUTO_ACKNOWLEDGE);

        // create a queue receiver
        QueueReceiver queueReceiver = queueSession.createReceiver(queue);

        // start the connection
        queueConn.start();

      

        // receive a message
        TextMessage message = (TextMessage) queueReceiver.receive();

    
       
    
        // print the message
        System.out.println("received: " + message.getText());

        // close the queue connection
        queueConn.close();
	}catch ( NamingException ne){
		System.out.println("ERROR Naming!!"+ ne);}
	catch (JMSException je){
	    System.out.println("ERROR JMS!!"+  je);}
		}
    }

