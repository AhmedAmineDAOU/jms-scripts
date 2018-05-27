import javax.naming.InitialContext;

import javax.jms.*;
import javax.naming.*;
import java.lang.System;



public class Sender {
    public static void main(String[] args) {

        try {

            // get the initial context
            InitialContext ctx = new InitialContext();

            // lookup the queue object
            Queue queue = (Queue) ctx.lookup("jms/mQ");


            // lookup the queue connection factory
            QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup("jms/mC");


            // create a queue connection
            QueueConnection queueConn = connFactory.createQueueConnection();

            // create a queue session
            QueueSession queueSession = queueConn.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

            // create a queue sender
            QueueSender queueSender = queueSession.createSender(queue);
            queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);


            TextMessage message = queueSession.createTextMessage("Hello");

            // send the message
            queueSender.send(message);


            // print what we did
            System.out.println("sent: " + message.getText());

            // close the queue connection
            queueConn.close();

        } catch ( NamingException ne) {
            System.out.println("ERROR Naming!!" + ne);
        } catch (JMSException je) {
            System.out.println("ERROR JMS!!" +  je);
        }
    }
}
