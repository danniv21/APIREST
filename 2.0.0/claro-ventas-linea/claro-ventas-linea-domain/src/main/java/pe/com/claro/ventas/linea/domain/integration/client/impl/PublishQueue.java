package pe.com.claro.ventas.linea.domain.integration.client.impl;

import java.io.Serializable;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import pe.com.claro.messaging.producer.ServerProducerSAF;
@Stateless
@LocalBean
public class PublishQueue implements Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1656505552601805357L;
	public static final String JMS_CF_JNDI = "jms/DemoConnectionFactoryLocal";
	  public static final String JMS_QUEUE_JNDI = "saf_jms/DemoQueueLocal";
	  public static final String SAF_INITIAL_CONTEXT = "weblogic.jndi.WLInitialContextFactory";
	  @Asynchronous
	  public void sendMessageBatch(String data){
		    int messageCount = 10;
		    int intervalDelay = 1000;
		    String batchId = java.util.UUID.randomUUID().toString();
		   try {
			ServerProducerSAF sender = new ServerProducerSAF
			(SAF_INITIAL_CONTEXT, null, null, null, JMS_CF_JNDI, JMS_QUEUE_JNDI);
			sender.sendMessageBatch(data +" "+ batchId, messageCount,intervalDelay);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	   }
}
