import javax.naming.InitialContext;

import javax.jms.*;
import javax.naming.*;
import java.lang.System;



public class Receiver {
  public static void main(String[] args) {
    QueueConnection queueConn = null;

    try {

      InitialContext ctx = new InitialContext();
      Queue queue = (Queue) ctx.lookup("jms/mQ");


      QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx. lookup("jms/mC");



      queueConn = connFactory.createQueueConnection();

      QueueSession queueSession = queueConn.createQueueSession(false,
                                  Session.AUTO_ACKNOWLEDGE);

      QueueReceiver queueReceiver;
      TextMessage msg;
      // start the connection
      queueConn.start();

      //Slector in receiving
      if (args.length == 0) {
        //System.out.println("sans args");
        queueReceiver = queueSession.createReceiver(queue); //ici pas de filtre
        msg = (TextMessage) queueReceiver.receive();
        System.out.println("Received : " + msg.getText());



      } else if (args.length > 0) {
        for (int i = 0; i < args.length; i++) {
          String   a = args[i];

          String chaine = "destination = '" + a + "'";
          queueReceiver = queueSession.createReceiver(queue, chaine); //ici filtre
          msg = (TextMessage) queueReceiver.receive();
          System.out.println("Received from " + args[i] + "  " + msg.getText());
        }

      }



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


