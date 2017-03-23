package pe.com.claro.ventas.linea.domain.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import pe.com.claro.common.resource.exception.ExceptionType;
import pe.com.claro.common.resource.exception.UnknownResourceException;
import pe.com.claro.ventas.linea.domain.repository.ClienteRepository;
import pe.com.claro.ventas.linea.integration.message.producer.ClienteMessageProducerLocal;
import pe.com.claro.ventas.linea.model.Cliente;

@Stateless
public class ClienteService implements Serializable{

      /**
	 * 
	 */
	private static final long serialVersionUID = -5283145043938691369L;
	@EJB
    private ClienteRepository clienteRepository;
	@EJB
    private ClienteMessageProducerLocal clienteMessageProducerLocal;
	
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public Cliente findId(Long id) throws Exception {
       Cliente customer=clienteRepository.buscarCliente(id);
       return customer;
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public List<Cliente> getAllClientesPaginated(int start, int size) {
		return clienteRepository.listAll(start, size);
	}
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteById(Long id) {
    	Cliente cliente= new Cliente();
    	cliente.setId(id);
    	clienteRepository.remove(cliente);
		return true;
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Cliente adicionarCliente(Cliente cliente) { 	
		return clienteRepository.persist(cliente);
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Cliente actualizarCliente(Cliente cliente) {
		return clienteRepository.merge(cliente);
	}
    
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public String enviarMensajeClienteBatch(String data){
	  
	  List<String> list = new ArrayList<String>();
	  list.add("mensaje entrada");
	  list.add(data);
	  list.add("mensaje salida");
	  clienteMessageProducerLocal.sendMessageBatch(list);
	  return "ok";
    }
  
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public String enviarMensajeCliente(String data){
	  clienteMessageProducerLocal.sendMessage(data);
	  return "ok";
  }
  
}
