import javax.jms.JMSException;
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
			TextMessage message = session.createTextMessage();
			for (int i = 0; i < 5; i++) {
				message.setText("message " + " " + (i + 1));
				emetteur.publish(message);
			}
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
