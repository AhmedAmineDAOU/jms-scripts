import javax.naming.InitialContext;

import javax.jms.*;
import javax.naming.*;
import java.lang.System;

//import java.io;


public class Receiver {
  public static void main(String[] args) {
    QueueConnection queueConn = null;

    try {

      // get the initial context
      InitialContext ctx = new InitialContext();
      // lookup the queue object
      Queue queue = (Queue) ctx.lookup("jms/mQ");


      // lookup the queue connection factory
      QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx. lookup("jms/mC");



      // create a queue connection
      queueConn = connFactory.createQueueConnection();

      // create a queue session
      QueueSession queueSession = queueConn.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);


      // start the connection
      queueConn.start();

      //Slector in receiving
      QueueReceiver queueReceiver = queueSession.createReceiver(queue, "destination = 'ahmed'");
      TextMessage   msg = (TextMessage) queueReceiver.receive();
      System.out.println("received : " + msg.getText());
      System.out.println(" the message is not deleted yet, it will wait 2s to be deleted");
      try {
        Thread.sleep(2000);
      } catch (InterruptedException i) {
        System.out.println("ERROR thread!!" +  i);
      }
      msg.acknowledge();
      System.out.println("Deleted after being acknowleged ");






    } catch ( NamingException ne) {
      System.out.println("ERROR Naming!!" + ne);
    } catch (JMSException je) {
      System.out.println("ERROR JMS!!" +  je);
    }

    finally {
      try {


        queueConn.close();
        System.exit(0);
      } catch (JMSException je) {
        System.out.println("ERROR JMS!!" +  je);
      }
    }
  }
}


