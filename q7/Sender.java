import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.naming.*;
import javax.jms.*;


public class Sender {

    public static void main(String[] args) {


        try {

            InitialContext ctx = new InitialContext();
            QueueConnectionFactory f = (QueueConnectionFactory)ctx.lookup("jms/mC");
            QueueConnection con = f.createQueueConnection();
            con.start();

            QueueSession ses = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue t = (Queue)ctx.lookup("jms/mQ");

            QueueSender sender = ses.createSender(t);

            TextMessage msg = ses.createTextMessage();


            msg.setText("Message sur server log");

            sender.send(msg);
          //  System.out.println("Message successfully sent.");

            con.close();
        } catch (Exception e) {System.out.println(e);}

    }
}
