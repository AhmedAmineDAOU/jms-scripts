

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Receiver1  {
	public static void main(String[] args) {
		TopicConnection connection = null;

		InitialContext messaging;
		try {
			messaging = new InitialContext();
			TopicConnectionFactory connectionFactory =  (TopicConnectionFactory) messaging.lookup("jms/TC");
			Topic topic = (Topic) messaging.lookup("jms/TQ");
			connection = connectionFactory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber subscriber = session.createSubscriber(topic); //creer un subscriber
			connection.start();
					System.out.println("Receiver");

			while (true) {
				TextMessage message = (TextMessage) subscriber.receive();
				System.out.println("Received : " + message.getText());
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
