package pe.com.claro.ventas.linea.integration.message.producer.impl;

import java.util.ArrayList;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;

import pe.com.claro.messaging.producer.ServerProducerSAF;
import pe.com.claro.ventas.linea.integration.message.producer.ClienteMessageProducerLocal;

@Stateless
public class ClienteMessageProducer implements ClienteMessageProducerLocal{
		
	  @Asynchronous
	  public void sendMessageBatch(Object payload){
		    int intervalDelay = 1000;
		    ArrayList<String> list= (ArrayList<String>)payload;
			 ServerProducerSAF sp=null;
		 try {
			 sp= new ServerProducerSAF(null, null);
			 sp.beginSession(false);
			    for (int x = 0; x < list.size(); x++)
			    {
			      String text = list.get(x)+ "-" + x;
			      sp.send(text);
			      sp.sleep(intervalDelay);
			    }
			    sp.endSession();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally
		    {
		      if (sp != null)
		      {
		        try
		        {
		          sp.close();
		        }
		        catch (Exception e)
		        {
		          e.printStackTrace();
		        }
		      }
		    }
		 
	  }
	  
	  
	  
	  @Asynchronous
	  public void sendMessage(Object payload)
	  {
		/*  try {
			init(null, null);
			 String  text= (String)payload;
			 send(text);
			 endSession();
		} catch (NamingException | JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  */
	  
	  } 
}
