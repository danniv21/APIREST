package pe.com.claro.ventas.linea.domain.integration.client;

public interface ClienteQueueClient {
	  String sendMessageBatch(String data);
}
