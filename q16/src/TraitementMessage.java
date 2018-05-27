package src;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.TextMessage;

@Stateless
public class TraitementMessage {
	public void traitement(TextMessage msg) {
		try {

			System.out.println("le message est :" + msg.getText() + "est en cours de traitement");
		} catch (Exception e) {
		}
	}
}
