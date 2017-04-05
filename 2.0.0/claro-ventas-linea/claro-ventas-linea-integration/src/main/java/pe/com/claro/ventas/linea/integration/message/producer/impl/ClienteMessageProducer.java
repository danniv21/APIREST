package pe.com.claro.ventas.linea.integration.message.producer.impl;

import java.util.ArrayList;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.messaging.AbstractJMSProducer;
import pe.com.claro.ventas.linea.integration.message.producer.ClienteMessageProducerLocal;


@Stateless
@Asynchronous
public class ClienteMessageProducer extends AbstractJMSProducer implements ClienteMessageProducerLocal{
	private static final Logger LOG = LoggerFactory.getLogger(ClienteMessageProducer.class);
	@Override
	public void sendMessageBatch(Object payload) {
		// TODO Auto-generated method stub
	    int intervalDelay = 1000;
	    ArrayList<String> list= (ArrayList<String>)payload;
	 try {
		 init();
		 beginSession(false);
		    for (int x = 0; x < list.size(); x++)
		    {
		      String text = list.get(x)+ "-" + x;
		      send(text);
		      sleep(intervalDelay);
		    }
		    endSession();
	} catch (Exception e) {
		LOG.error("ERROR: [Exception] - [" + e.getMessage() + "] ", e ); 
	} 
	 finally
	    {
	          try {
				close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
	    }
		
	}

	@Override
	public void sendMessage(Object payload){
		// TODO Auto-generated method stub
		 try {
			 	 init();
				 beginSession(false);
				 String text= (String)payload;
				 send(text);
				 endSession();
		 } catch (Exception e) {
			 LOG.error("ERROR: [Exception] - [" + e.getMessage() + "] ", e ); 
		} 
		 finally
		    {
		          try {
					close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
		    }	  
	}
	
}