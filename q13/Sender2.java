import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Sender2 {
	public static void main(String[] args) {


		TopicConnection connection = null;
		try {
			InitialContext  messaging = new InitialContext();
			TopicConnectionFactory connectionFactory =  (TopicConnectionFactory) messaging.lookup("jms/TC");
			Topic topic = (Topic) messaging.lookup("jms/TQ");
			connection = connectionFactory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicPublisher emetteur = session.createPublisher(topic);
			//MessageProducer producer = session.createProducer(topic);
			TextMessage message = session.createTextMessage();
			String text =  "message prioritaire " + 9;
			TextMessage messagepr = session.createTextMessage(text);


			for (int i = 0; i < 5; i++) {
				message.setText("Question 13 " + " " + (i + 1));
				emetteur.publish(message, DeliveryMode.NON_PERSISTENT, 1, 0);

			}
			emetteur.publish(messagepr, DeliveryMode.NON_PERSISTENT, 9, 0);

			connection.close();

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
