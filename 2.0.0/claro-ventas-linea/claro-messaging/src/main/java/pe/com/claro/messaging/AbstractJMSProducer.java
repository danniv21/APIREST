package pe.com.claro.messaging;

import java.util.Hashtable;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weblogic.jms.extensions.WLDestination;
import weblogic.jms.extensions.WLMessageProducer;
import weblogic.jms.extensions.WLSession;


public class AbstractJMSProducer
{
  private static final Logger LOG = LoggerFactory.getLogger(AbstractJMSProducer.class);
  protected WLSession mSession=null;
  protected WLMessageProducer mProducer=null;
  protected Connection mConnection=null;
  protected WLDestination mDestination=null;
  private  String SAF_INITIAL_CONTEXT = "weblogic.jndi.WLInitialContextFactory";
  protected static String JMS_CF_JNDI = null;
  protected static String JMS_QUEUE_JNDI = null;

  protected static void sleep(long time) throws Exception
  {
      Thread.sleep(time);
  }

  protected void send(String payload) throws JMSException
  {
    if (mSession == null)
    {
      beginSession(false);
    }

    final Message sendMsg = mSession.createTextMessage(payload);
    LOG.info("Sending TextMessage=[" + payload + "]");
    mProducer.send(sendMsg);
  }

  protected void beginSession(boolean pTransacted) throws JMSException
  {
	LOG.info("Creating session...");
    mSession = (WLSession) mConnection.createSession(pTransacted, 0);

    LOG.info("Creating producer...");
    mProducer = (WLMessageProducer) mSession.createProducer(mDestination);
  }

  protected void endSession() throws JMSException
  {
    LOG.info("Closing Producer...");
    mProducer.close();

    LOG.info("Closing Session...");
    mSession.close();
  }

  protected void commitSession() throws JMSException
  {
    LOG.info("Committing session...");
    mSession.commit();
  }

  //@PreDestroy
  protected void close() throws JMSException
  {
    LOG.info("Closing WL JMS Producer...");

    LOG.info("Closing Connection...");
    if (mConnection != null)
    {
    	mConnection.close();
    }
  }


  protected void init() throws NamingException, JMSException
  {
    final InitialContext context;
    Hashtable<String, String> h = new Hashtable<String, String>(7);
    h.put(Context.INITIAL_CONTEXT_FACTORY, SAF_INITIAL_CONTEXT);
    context = new InitialContext(h);

    LOG.info("Looking up CF jndi=[" + JMS_CF_JNDI + "]...");
    QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(JMS_CF_JNDI);

    LOG.info("Looking up Destination jndi=[" + JMS_QUEUE_JNDI + "]");
    mDestination = (WLDestination) context.lookup(JMS_QUEUE_JNDI);

    LOG.info("Creating connection...");
    mConnection = connectionFactory.createQueueConnection();
  }
}