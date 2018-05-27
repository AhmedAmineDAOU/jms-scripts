import javax.jms.*;
import javax.naming.*;
import javax.ejb.*;

@MessageDriven(mappedName = "jms/mQ")
public class Receiver implements MessageListener {

    public void onMessage(Message msg) {
        TextMessage tmsg = null;
        try {
            if (msg instanceof TextMessage ) {
                tmsg = (TextMessage) msg;
                System.out.println ("Message reçu : " + tmsg.getText( ));
            } else
                System.out.println ("Message pas de type TextMessage");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
