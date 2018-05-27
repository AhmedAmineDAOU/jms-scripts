import javax.naming.InitialContext;

import javax.jms.*;
import javax.naming.*;
import java.lang.System;



public class Sender {
    public static void main(String[] args) {
        QueueConnection queueConn = null;

        try {

            // get the initial context
            InitialContext ctx = new InitialContext();

            // lookup the queue object
            Queue queue = (Queue) ctx.lookup("jms/mQ");


            // lookup the queue connection factory
            QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup("jms/mC");


            // create a queue connection
            queueConn = connFactory.createQueueConnection();

            // create a queue session
            QueueSession queueSession = queueConn.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);

            // create a queue sender
            QueueSender queueSender = queueSession.createSender(queue);
            queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Default receiver
            TextMessage defaul = queueSession.createTextMessage();



            // Hello for the desired receiver and definition of the destination parameter
            TextMessage msg = queueSession.createTextMessage();
            msg.setStringProperty("destination", "ahmed");
            msg.setText("Hello");
            System.out.println("message sent only to ahmed  " + msg.getText());
            queueSender.send(msg);


            // close the queue connection
            queueConn.close();

        } catch ( NamingException ne) {
            System.out.println("ERROR Naming!!" + ne);
        } catch (JMSException je) {
            System.out.println("ERROR JMS!!" +  je);
        } finally {
            try {

                // close the queue connection
                queueConn.close();
                System.exit(0);
            } catch (JMSException je) {
                System.out.println("ERROR JMS!!" +  je);
            }
        }
    }
}
