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


public class JMSProducer
{
  protected WLSession mSession;
  protected WLMessageProducer mProducer;
  protected Connection mConnection;
  protected WLDestination mDestination;
  public static final String JMS_CF_JNDI = "jms/DemoConnectionFactoryLocal";
  public static final String JMS_QUEUE_JNDI = "saf_jms/DemoQueueLocal";
  public static final String SAF_INITIAL_CONTEXT = "weblogic.jndi.WLInitialContextFactory";

 /* protected void sendBatch(String payload, int pMessageCount, long pIntervalTimeInMillis) throws JMSException
  {
    beginSession(false);

    for (int x = 1; x <= pMessageCount; x++)
    {
      String text = payload + "-" + x;
      send(text);
      sleep(pIntervalTimeInMillis);
    }

    endSession();
  }*/

  public static void sleep(long time)
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

  public void send(String payload) throws JMSException
  {
    if (mSession == null)
    {
      beginSession(false);
    }

    final Message sendMsg = mSession.createTextMessage(payload);
    System.out.println("Sending TextMessage=[" + payload + "]");
    mProducer.send(sendMsg);
  }

  public void beginSession(boolean pTransacted) throws JMSException
  {
    System.out.println("Creating session...");
    mSession = (WLSession) mConnection.createSession(pTransacted, 0);

    System.out.println("Creating producer...");
    mProducer = (WLMessageProducer) mSession.createProducer(mDestination);
  }

  public void endSession() throws JMSException
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
  public void close() throws JMSException
  {
    System.out.println("Closing WL JMS Producer...");

    System.out.println("Closing Connection...");
    mConnection.close();
  }

  protected JMSProducer()
  {
  }

  public JMSProducer(String pUsername, String pPassword) throws NamingException, JMSException
  {
    final InitialContext context;
    Hashtable<String, String> h = new Hashtable<String, String>(7);
    h.put(Context.INITIAL_CONTEXT_FACTORY, SAF_INITIAL_CONTEXT);
  //  h.put(Context.PROVIDER_URL, pURL);
  //  h.put(Context.SECURITY_PRINCIPAL, pUsername);
  //  h.put(Context.SECURITY_CREDENTIALS, pPassword);

    context = new InitialContext(h);

    System.out.println("Looking up CF jndi=[" + JMS_CF_JNDI + "]...");
    QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(JMS_CF_JNDI);

    System.out.println("Looking up Destination jndi=[" + JMS_QUEUE_JNDI + "]");
    mDestination = (WLDestination) context.lookup(JMS_QUEUE_JNDI);

    System.out.println("Creating connection...");
    mConnection = connectionFactory.createQueueConnection();
  }
}