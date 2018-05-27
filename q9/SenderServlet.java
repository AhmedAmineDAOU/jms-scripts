import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.naming.*;
import javax.jms.*;
import javax.annotation.Resource;

public class SenderServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String nom = request.getParameter("nom");


        try {

            InitialContext ctx = new InitialContext();
            QueueConnectionFactory f = (QueueConnectionFactory)ctx.lookup("jms/mC");
            QueueConnection con = f.createQueueConnection();
            con.start();

            QueueSession ses = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue t = (Queue)ctx.lookup("jms/mQ");

            QueueSender sender = ses.createSender(t);

            TextMessage msg = ses.createTextMessage();


            msg.setStringProperty("recepteur", "ahmed");


            msg.setText(nom);

            sender.send(msg);


            con.close();

            out.println("Message envoyé");
            //out.println("<a href=\"upload.jsp\">Go to JSP form</a>");
            out.println("localhost:4848/common/logViewer/logViewerRaw.jsf?instanceName=server");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
