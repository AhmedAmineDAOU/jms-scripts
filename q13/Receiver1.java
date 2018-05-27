import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Receiver1   {
	public static void main(String[] args) throws InterruptedException {
		TopicConnection connection = null;
		long sec = 2000;
		InitialContext messaging;
		try {
			messaging = new InitialContext();
			TopicConnectionFactory connectionFactory =  (TopicConnectionFactory) messaging.lookup("jms/TC");
			Topic topic = (Topic) messaging.lookup("jms/TQ");
			connection = connectionFactory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			TopicSubscriber subscriber = session.createSubscriber(topic);
			MessageDrBean q12 = new MessageDrBean();
			subscriber.setMessageListener(q12);
			connection.start();
			System.out.print("En attente des messages \n");
			for (int i = 0; i < 15; i++) {
				Thread.sleep(sec);
				System.out.println("en train de faire un truc ");
			}
		} catch (JMSException e) {
			e.printStackTrace( );
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) connection.close();
			} catch (JMSException e) {
				e.printStackTrace( );
			}
		}
		System.exit(0);
	}

}
