import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.naming.*;
import javax.jms.*;

public class Sender {

    public static void main(String[] args) {


        try {

            InitialContext ctx = new InitialContext();
            QueueConnectionFactory f = (QueueConnectionFactory)ctx.lookup("jms/myCo");
            QueueConnection con = f.createQueueConnection();
            con.start();

            QueueSession ses = con.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
            TemporaryQueue tempQueue = ses.createTemporaryQueue();//queue de reception
            //

            Queue t = (Queue)ctx.lookup("jms/myQu");

            QueueSender sender = ses.createSender(t);

            TextMessage msg = ses.createTextMessage();

            if (args.length == 3) {
                msg.setStringProperty("recepteur", args[1]);
            }

            msg.setJMSReplyTo(tempQueue);
            msg.setText(args[0]);
            msg.setJMSCorrelationID(args[2]);
            sender.send(msg);

            QueueReceiver receiver = receiver = ses.createReceiver(tempQueue);
            TextMessage accuse = (TextMessage) receiver.receive();
            System.out.println(accuse.getText());


            con.close();
        } catch (Exception e) {System.out.println(e);}

    }
}
