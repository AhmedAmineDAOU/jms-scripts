import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Sender {
    public static void main(String[] args) {
		
InitialContext messaging =null;
 QueueConnection connection = null;
try {
 messaging = new InitialContext();
 QueueConnectionFactory connectionFactory = (QueueConnectionFactory) messaging.lookup("jms/mC");
 Queue queue = (Queue) messaging.lookup("jms/mQ");
 connection = connectionFactory.createQueueConnection();
 QueueSession session = connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
 connection.start();
 
 QueueSender emetteur =session.createSender(queue);
  TextMessage msg = session.createTextMessage();
  for (int i = 0; i < 5; i++) {
 msg.setText("bonjour "+" "+i);
 System.out.println("bonjour  "+i+ "   envoye");
 emetteur.send(msg);
 }
 connection.close();
 	System.exit(0);

 
	} catch (JMSException e){ 
		e.printStackTrace( ); 
	}
	catch(NamingException e){
		e.printStackTrace();
	}
	finally{
		try{
			if (connection!=null) connection.close();
		} catch (JMSException e){ 
			e.printStackTrace( ); 
		}
	}
	System.exit(0);
	}

    
}
