package pe.com.claro.ventas.linea.integration.message.producer.impl;

import java.util.ArrayList;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.jms.JMSException;

import pe.com.claro.messaging.AbstractJMSProducer;
import pe.com.claro.ventas.linea.integration.message.producer.ClienteMessageProducerLocal;

@Stateless
@Asynchronous
public class ClienteMessageProducer extends AbstractJMSProducer implements ClienteMessageProducerLocal{

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
		// TODO Auto-generated catch block
		e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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