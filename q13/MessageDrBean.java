

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/TC"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/TQ"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/TQ"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MessageDrBean implements MessageListener {



    @Override
    public void onMessage(Message message) {

        try {
            TextMessage msg = (TextMessage) message;
            System.out.println("Received Asyn 13: " + msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(MessageDrBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
