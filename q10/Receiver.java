import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/mQ",
activationConfig = {
	@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "recepteur='ahmed'")
})
public class Receiver implements MessageListener {

	@EJB
	TraitementMessage traiteur;

	@Override
	public void onMessage(Message msg) {
		TextMessage txtMsg = null;
		try {
			if (msg instanceof TextMessage) {
				txtMsg = (TextMessage) msg;
				traiteur.traitement(txtMsg);
				System.out.println("message :" + txtMsg.getText());

			} else {
				System.out.println("Le message n'est pas de type TextMessage");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
