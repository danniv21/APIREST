package pe.com.claro.messaging.producer;


import pe.com.claro.messaging.WLJMSProducer;

public class ServerProducerSAF extends WLJMSProducer
{
  public void  sendMessageBatch(String pMessageBase, int pMessageCount, long pIntervalTimeInMillis) {

	    try
	    {
	        this.sendBatch(pMessageBase, pMessageCount, pIntervalTimeInMillis);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
}

  public ServerProducerSAF(String pInitialContext, String pURL, String pUsername, String pPassword, String pCFName, String pQueueJNDIName) throws Exception
  {
	  super(pInitialContext, pURL, pUsername, pPassword, pCFName, pQueueJNDIName);
  }
}