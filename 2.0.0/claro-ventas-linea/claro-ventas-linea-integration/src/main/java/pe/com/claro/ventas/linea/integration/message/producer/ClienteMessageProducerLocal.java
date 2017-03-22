package pe.com.claro.ventas.linea.integration.message.producer;

import javax.ejb.Local;

@Local
public interface ClienteMessageProducerLocal {
	  void sendMessageBatch(Object payload);
	  void sendMessage(Object payload);
}
