
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
			//creer le context
			InitialContext  messaging = new InitialContext();
			//creer la connection factory
			TopicConnectionFactory connectionFactory =  (TopicConnectionFactory) messaging.lookup("jms/TC");
			//creer le topic
			Topic topic = (Topic) messaging.lookup("jms/TQ");
			//creer topic connection
			connection = connectionFactory.createTopicConnection();
			//creer topic session
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			//creer un publisher sur la session
			TopicPublisher sender = session.createPublisher(topic);
			TextMessage message = session.createTextMessage();
			System.out.println("Sender");
			for (int i = 0; i < 5; i++) {
				message.setText("Message  " + " " + (i + 1));
				sender.publish(message);
				//publish
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
