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
            QueueSession queueSession = queueConn.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

            // create a queue sender
            QueueSender queueSender = queueSession.createSender(queue);
            queueSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Default receiver
            TextMessage defaul = queueSession.createTextMessage();
            /* defaul.setText("default message");
             queueSender.send(defaul);*/




            // Hello for the desired receiver and definition of the destination parameter
            TextMessage msg = queueSession.createTextMessage();
            msg.setText(args[0]);
            System.out.println(args.length);
            
            if (args.length == 2 ) {
                msg.setStringProperty("destination", args[1]);
                System.out.println( msg.getText() + "   sent  only to   " + args[1] );
                queueSender.send(msg);
            }

            if (args.length == 1)  {//si j'ai un seul argument j'envoi le message a tout le monde 


                System.out.println("message " + args[0] + "sent to all");
                queueSender.send(msg);
            }

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
