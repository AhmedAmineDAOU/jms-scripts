import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
public class Receiver1 {

	public static void main(String[] args) {

		InitialContext messaging =null;
		QueueConnectionFactory connectionFactory = null;
		Queue queue = null;
		QueueConnection connection = null;
		QueueSession session = null;
		String	nom = args[0];
		String  wait = args[1];
		//boucle for
		for (int i = 0; i < 5; i++) {

			try {

				messaging = new InitialContext();
				connectionFactory = (QueueConnectionFactory)messaging.lookup("jms/mC");
				queue = (Queue)messaging.lookup("jms/mQ");


			} catch (NamingException e) {

				System.out.println("probleme lors des creations de la cf ou la queue");
			}

			try {
				connection = connectionFactory.createQueueConnection();
				session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
				connection.start();
			} catch (JMSException e) {
				System.out.println("probleme lors de la connexion a la queue");
			}



			try {

				QueueReceiver receiver = session.createReceiver(queue);
				TextMessage msg = (TextMessage) receiver.receive();
				System.out.println("mon recepteur : " + nom + "\n");
				System.out.println("Message recu : " + msg.getText() + "\n");
				connection.close();//donner la main
			} catch (JMSException e) {}

			try {
				Thread.sleep(Integer.parseInt(wait));
			} catch (InterruptedException e) {}
		}
	}
}
