package pe.com.claro.ventas.linea.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.claro.common.resource.util.MessageFilterBean;
import pe.com.claro.ventas.linea.canonical.request.linea.ClienteRequest;
import pe.com.claro.ventas.linea.canonical.request.linea.DireccionRequest;
import pe.com.claro.ventas.linea.canonical.response.linea.ClienteDireccionResponse;
import pe.com.claro.ventas.linea.canonical.response.linea.DireccionResponse;
import pe.com.claro.ventas.linea.domain.service.ClienteService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClienteResourceTest {
	@Mock
	ClienteService customerService;
	@InjectMocks
	ClienteResource clienteResource;

	@Test
	public void getClienteById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ClienteDireccionResponse clienteDireccionResponse = new ClienteDireccionResponse();
		clienteDireccionResponse.setNombre("Jerry");
		clienteDireccionResponse.setApellido("Rivera Navarrete");
		clienteDireccionResponse.setCorreo("cdiax@gmail.com");
		Collection<DireccionResponse> listaDirecciones = new ArrayList<DireccionResponse>();
		DireccionResponse direccionResponse = new DireccionResponse();
		direccionResponse.setDireccion1("lima peru");
		listaDirecciones.add(direccionResponse);
		clienteDireccionResponse.setListaDirecciones(listaDirecciones);
		when(customerService.findId(1l)).thenReturn(clienteDireccionResponse);
		Response response = clienteResource.getClienteById(1l);
		ClienteDireccionResponse value = mapper.readValue(response.getEntity().toString(), 
		ClienteDireccionResponse.class);
	    assertEquals("Jerry", value.getNombre());
	}

	@Test
	public void getClienteAll() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		MessageFilterBean filerBean = mock(MessageFilterBean.class);
		when(filerBean.getStart()).thenReturn(1);
		when(filerBean.getSize()).thenReturn(3);
		filerBean.setSize(3);
		filerBean.setStart(1);
		List<ClienteDireccionResponse> list= new ArrayList<ClienteDireccionResponse>();
		ClienteDireccionResponse clienteDireccionResponse1 = new ClienteDireccionResponse();
		clienteDireccionResponse1.setNombre("Jerry1");
		clienteDireccionResponse1.setApellido("Rivera Navarrete1");
		clienteDireccionResponse1.setCorreo("cdiax1@gmail.com");
		ClienteDireccionResponse clienteDireccionResponse2 = new ClienteDireccionResponse();
		clienteDireccionResponse2.setNombre("Jerry2");
		clienteDireccionResponse2.setApellido("Rivera Navarrete2");
		clienteDireccionResponse2.setCorreo("cdiax2@gmail.com");
		list.add(clienteDireccionResponse1);
		list.add(clienteDireccionResponse2);
	    when(customerService.getAllClientesPaginated(filerBean.getStart(), filerBean.getSize()))
				.thenReturn(list);
		Response response = clienteResource.getClienteAll(filerBean);
		List<ClienteDireccionResponse>  value = mapper.readValue(response.getEntity().toString(),
				mapper.getTypeFactory().constructCollectionType(List.class, ClienteDireccionResponse.class));
		assertEquals(2, value.size());
	}

	@Test
	public void adicionarCliente() throws Exception {
		ClienteRequest clienteRequest = mock(ClienteRequest.class);
		clienteRequest.setNombre("Jerry1");
		clienteRequest.setApellido("Rivera Navarrete1");
		clienteRequest.setCorreo("cdiax1@gmail.com");
		clienteRequest.setAcivo("1");
		DireccionRequest dir= new DireccionRequest();
		dir.setCodigopostal("001");
		dir.setDireccion1("calle begonias 254");
		dir.setDistrito("lince");
		dir.setTelefono("989665445");
		Collection<DireccionRequest> listaDireccionRequest= new ArrayList<DireccionRequest>();
		listaDireccionRequest.add(dir);
		clienteRequest.setListaDirecciones(listaDireccionRequest);
		ClienteDireccionResponse clienteDireccionResponse= new ClienteDireccionResponse();
		clienteDireccionResponse.setApellido("Rivera Navarrete1");
		clienteDireccionResponse.setNombre("Jerry1");
		when(customerService.adicionarCliente(clienteRequest)).thenReturn(clienteDireccionResponse);
		Response response = clienteResource.adicionarCliente(clienteRequest);
		ClienteDireccionResponse value = (ClienteDireccionResponse) response.getEntity();
		assertEquals("Jerry1", value.getNombre());
	}
}
