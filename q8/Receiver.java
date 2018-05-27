import javax.jms.*;
import javax.naming.*;
import javax.ejb.*;

@MessageDriven(mappedName = "jms/mQ", activationConfig = { @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "recepteur = 'ahmed'")})
public class Receiver implements MessageListener {

    public void onMessage(Message msg) {
        TextMessage tmsg = null;
        try {
            if (msg instanceof TextMessage ) {
                tmsg = (TextMessage) msg;
                System.out.println ("Message reçu : " + tmsg.getText( ));
            } else
                System.out.println ("error");
        } catch (JMSException e) {
            e.printStackTrace();

        }
    }
}
