package src;
import java.io.IOException;
import java.io.PrintStream;
import javax.naming.*;
import javax.jms.*;
import java.lang.System;
import java.lang.*;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jws.WebService;

@WebService(targetNamespace = "http://localhost:8080/", endpointInterface = "src.IEmetteur")
public class WSEmetteur implements IEmetteur {




	public String emettre(String message) throws ServletException, IOException {
		try {

			InitialContext ic = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) ic.lookup("jms/mC");

			Queue queue = (Queue) ic.lookup("jms/mQ");
			QueueConnection connection = factory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			connection.start();
			QueueSender sender = session.createSender(queue);
			TextMessage msg = session.createTextMessage();
			msg.setText(" message :" + message);


			msg.setStringProperty("recepteur", "ahmed");
			sender.send(msg);
			return msg.toString() + "    envoyé avec Succes";


		} catch (NamingException e) {
			System.err.println("Naming exception" + e);
			return "Erreur lors de l'envoie de la chaine";
		} catch (JMSException a) {
			System.err.println("JMS  Exception" + a);
			return "Erreur lors de l'envoie de la chaine";
		}
	}
}
