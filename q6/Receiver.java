import javax.jms.*;
import javax.naming.InitialContext;

public class Receiver {
    public static void main(String[] args) {
        try {

            InitialContext ctx = new InitialContext();
            QueueConnectionFactory f = (QueueConnectionFactory)ctx.lookup("jms/myCo");
            QueueConnection con = f.createQueueConnection();
            con.start();

            QueueSession ses = con.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);

            Queue t = (Queue)ctx.lookup("jms/myQu");

            QueueReceiver receiver;


            String recepteurNom = "";
            recepteurNom = "(recepteur ='" + args[0] + "')";


            receiver = ses.createReceiver(t, recepteurNom);


            TextMessage msg = (TextMessage) receiver.receive();
            System.out.println(msg.getStringProperty("recepteur") + " : " + msg.getText());
            msg.acknowledge();

            QueueSender sender = ses.createSender(null);//
            TextMessage msgRep = ses.createTextMessage();
            msgRep.setText("Message envoyé de Sender(id=" + msg.getJMSCorrelationID()+")");
            sender.send(msg.getJMSReplyTo(), msgRep);

        } catch (Exception e) {System.out.println(e);}
    }

}
