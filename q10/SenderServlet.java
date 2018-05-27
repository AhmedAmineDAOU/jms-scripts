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

public class SenderServlet extends HttpServlet {



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = req.getParameter("nom");
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
			resp.getOutputStream().println("");

		} catch (NamingException e) {
			System.err.println("Naming exception" + e);
		} catch (JMSException a) {
			System.err.println("JMS  Exception" + a);

		}
	}
}
