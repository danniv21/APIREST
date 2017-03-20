package pe.com.claro.messaging;

import weblogic.jms.extensions.WLDestination;
import weblogic.jms.extensions.WLMessageProducer;
import weblogic.jms.extensions.WLSession;

import javax.annotation.PreDestroy;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;


public class WLJMSProducer
{
  protected WLSession mSession;
  protected WLMessageProducer mProducer;
  protected Connection mConnection;
  protected WLDestination mDestination;

  protected void sendBatch(String pMessageBase, int pMessageCount, long pIntervalTimeInMillis) throws JMSException
  {
    beginSession(false);

    for (int x = 1; x <= pMessageCount; x++)
    {
      String text = pMessageBase + "-" + x;
      send(text);
      sleep(pIntervalTimeInMillis);
    }

    endSession();
  }

  protected static void sleep(long time)
  {
    try
    {
      Thread.sleep(time);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  protected void send(String text) throws JMSException
  {
    if (mSession == null)
    {
      beginSession(false);
    }

    final Message sendMsg = mSession.createTextMessage(text);
    System.out.println("Sending TextMessage=[" + text + "]");
    mProducer.send(sendMsg);
  }

  protected void beginSession(boolean pTransacted) throws JMSException
  {
    System.out.println("Creating session...");
    mSession = (WLSession) mConnection.createSession(pTransacted, 0);

    System.out.println("Creating producer...");
    mProducer = (WLMessageProducer) mSession.createProducer(mDestination);
  }

  protected void endSession() throws JMSException
  {
    System.out.println("Closing Producer...");
    mProducer.close();

    System.out.println("Closing Session...");
    mSession.close();
  }

  protected void commitSession() throws JMSException
  {
    System.out.println("Committing session...");
    mSession.commit();
  }

  @PreDestroy
  protected void close() throws JMSException
  {
    System.out.println("Closing WL JMS Producer...");

    System.out.println("Closing Connection...");
    mConnection.close();
  }

  protected WLJMSProducer()
  {
  }

  public WLJMSProducer(String pInitialContext, String pURL, String pUsername, String pPassword, String pCFName, String pQueueJNDIName) throws NamingException, JMSException
  {
    final InitialContext context;
    Hashtable<String, String> h = new Hashtable<String, String>(7);
    h.put(Context.INITIAL_CONTEXT_FACTORY, pInitialContext);
  //  h.put(Context.PROVIDER_URL, pURL);
  //  h.put(Context.SECURITY_PRINCIPAL, pUsername);
  //  h.put(Context.SECURITY_CREDENTIALS, pPassword);

    System.out.println("Getting context with URL=[" + pURL + "]...");
    context = new InitialContext(h);

    System.out.println("Looking up CF jndi=[" + pCFName + "]...");
    QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(pCFName);

    System.out.println("Looking up Destination jndi=[" + pQueueJNDIName + "]");
    mDestination = (WLDestination) context.lookup(pQueueJNDIName);

    System.out.println("Creating connection...");
    mConnection = connectionFactory.createQueueConnection();
  }
}