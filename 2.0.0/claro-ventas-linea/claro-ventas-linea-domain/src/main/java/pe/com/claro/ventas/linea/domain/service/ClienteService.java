package pe.com.claro.ventas.linea.domain.service;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import pe.com.claro.common.resource.exception.BadUsageException;
import pe.com.claro.ventas.linea.canonical.request.linea.ClienteRequest;
import pe.com.claro.ventas.linea.canonical.request.linea.DireccionRequest;
import pe.com.claro.ventas.linea.canonical.response.linea.ClienteDireccionResponse;
import pe.com.claro.ventas.linea.canonical.response.linea.DireccionResponse;
import pe.com.claro.ventas.linea.domain.repository.ClienteRepository;
import pe.com.claro.ventas.linea.domain.repository.DireccionRepository;
import pe.com.claro.ventas.linea.integration.client.CatalogoRestClientLocal;
import pe.com.claro.ventas.linea.integration.message.producer.ClienteMessageProducerLocal;
import pe.com.claro.ventas.linea.model.Cliente;
import pe.com.claro.ventas.linea.model.Direccion;

@Stateless
public class ClienteService implements Serializable{

      /**
	 * 
	 */
	private static final long serialVersionUID = -5283145043938691369L;
	@EJB
    private ClienteRepository clienteRepository;
	
	@EJB
    private DireccionRepository direccionRepository;
	
	@EJB
    private ClienteMessageProducerLocal clienteMessageProducerLocal;
	
	@EJB
    private CatalogoRestClientLocal catalogoRestClientLocal;
	
	

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public ClienteDireccionResponse findId(Long id)throws BadUsageException {
       ModelMapper modelMapper = new ModelMapper();
       Cliente cliente=clienteRepository.buscarCliente(id);
       if(cliente==null){
    	    /*throw new BadUsageException(Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                    "No se encontraron clientes");*/
    	   return null;
       }
       List<Direccion> listaDireccion= direccionRepository.buscarDireccionXCliente(cliente.getId());
       Type listType = new TypeToken<List<DireccionResponse>>() {}.getType();
       List<DireccionResponse> DirR = modelMapper.map(listaDireccion, listType);
       return new ClienteDireccionResponse(cliente.getApellido(), cliente.getCorreo(), cliente.getNombre(), DirR);
    }
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public List<ClienteDireccionResponse> getAllClientesPaginated(int start, int size) {
    	List<ClienteDireccionResponse> list= new ArrayList<ClienteDireccionResponse>();
    	List<Cliente> listaClientes=clienteRepository.listAll(start, size);
    	listaClientes.forEach(c -> list.add(clienteDireccionResponse(c)));
		return list;
	}
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteById(Long id) {
    	Cliente cliente= new Cliente();
    	cliente.setId(id);
    	clienteRepository.remove(cliente);
		return true;
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ClienteDireccionResponse adicionarCliente(ClienteRequest clienteRequest) { 	
   	    ModelMapper modelMapper = new ModelMapper();
    		List<Direccion> list= new ArrayList<Direccion>();
			 Cliente cliente1= modelMapper.map(clienteRequest, Cliente.class);
			 clienteRepository.persist(cliente1);
		     clienteRequest.getListaDirecciones().forEach(c -> list.add(convertToEntity(c,cliente1.getId())));
			 ClienteDireccionResponse clienteDireccionResponse = modelMapper.map(clienteRequest,
			 ClienteDireccionResponse.class);
		return clienteDireccionResponse;
	}
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ClienteDireccionResponse actualizarCliente(ClienteRequest clienteRequest) {
        ModelMapper modelMapper = new ModelMapper();
    	Cliente cliente1= modelMapper.map(clienteRequest, Cliente.class);
    	cliente1.setId(43l);
		return clienteDireccionResponse(clienteRepository.merge(cliente1));
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
    
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public ClienteDireccionResponse findCatalogoId(Long id) throws Exception {
	  Map<String, String> payload = new HashMap<String, String>();
	  payload.put("Id", String.valueOf(id));
	  return catalogoRestClientLocal.getEstadoCliente(payload);
  }
  
  
 private ClienteDireccionResponse clienteDireccionResponse(Cliente cliente){
      return new 
      ClienteDireccionResponse(cliente.getApellido(), cliente.getCorreo(),cliente.getNombre());		   
 }
 
 private Direccion convertToEntity(DireccionRequest direccionRequest, Long idCliente) {
	 ModelMapper modelMapper = new ModelMapper();
	 Direccion direccion = modelMapper.map(direccionRequest, Direccion.class);
	 Cliente cliente= new Cliente(); 
	 cliente.setId(idCliente);
	 direccion.setCliente(cliente);
	 direccionRepository.persist(direccion);
	 return direccion;
	}
}
