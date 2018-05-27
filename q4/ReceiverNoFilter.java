import javax.naming.InitialContext;

import javax.jms.*;
import javax.naming.*;
import java.lang.System;



public class ReceiverNoFilter {
    public static void main(String[] args) {
        QueueConnection queueConn = null;

        try {

            InitialContext ctx = new InitialContext();

            Queue queue = (Queue) ctx.lookup("jms/mQ");

            QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx. lookup("jms/mC");

            queueConn = connFactory.createQueueConnection();
            QueueSession queueSession = queueConn.createQueueSession(false,
                                        Session.AUTO_ACKNOWLEDGE);


            // start the connection
            queueConn.start();



            QueueReceiver queueReceiver = queueSession.createReceiver(queue);//difference
            TextMessage msg = (TextMessage) queueReceiver.receive();
            System.out.println("Received : " + msg.getText());


        } catch ( NamingException ne) {
            System.out.println("ERROR Naming!!" + ne);
        } catch (JMSException je) {
            System.out.println("ERROR JMS!!" +  je);
        }

        finally {
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


