package pe.com.claro.ventas.linea.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.claro.common.resource.util.MessageFilterBean;
import pe.com.claro.ventas.linea.domain.service.ClienteService;
import pe.com.claro.ventas.linea.model.Cliente;
import pe.com.claro.ventas.linea.model.Direccion;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClienteResourceTest {
	@Mock
	ClienteService customerService;
	@InjectMocks
	ClienteResource clienteResource;

	
	@Test
	public void getClienteById() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Cliente cli = new Cliente();
		cli.setAcivo("1");
		cli.setNombre("Jerry");
		cli.setId(1l);
		cli.setApellido("Rivera Navarrete");
		cli.setCorreo("cdiax@gmail.com");
		Direccion dir = new Direccion();
		dir.setId(1l);
		dir.setDireccion1("lima peru");
		List<Direccion> lista = new ArrayList<Direccion>();
		lista.add(dir);
		cli.setDireccions(lista);
		when(customerService.findId(1l)).thenReturn(cli);
		Response response = clienteResource.getClienteById(1l);
		Cliente value = mapper.readValue(response.getEntity().toString(), Cliente.class);
		assertEquals("1", value.getAcivo());
	}

	@Test
	public void getClienteAll() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		MessageFilterBean filerBean = mock(MessageFilterBean.class);
		when(filerBean.getStart()).thenReturn(1);
		when(filerBean.getSize()).thenReturn(3);
		filerBean.setSize(3);
		filerBean.setStart(1);
		List<Cliente> ListCliente = new ArrayList<>();
		Cliente cli = new Cliente();
		cli.setAcivo("1");
		cli.setNombre("Jerry");
		cli.setId(1l);
		cli.setApellido("Rivera Navarrete");
		cli.setCorreo("cdiax@gmail.com");
		Direccion dir = new Direccion();
		dir.setId(1l);
		dir.setDireccion1("lima peru");
		List<Direccion> lista = new ArrayList<Direccion>();
		lista.add(dir);
		cli.setDireccions(lista);
		ListCliente.add(cli);
		when(customerService.getAllClientesPaginated(filerBean.getStart(), filerBean.getSize()))
				.thenReturn(ListCliente);
		Response response = clienteResource.getClienteAll(filerBean);
		List<Cliente> value = mapper.readValue(response.getEntity().toString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Cliente.class));
		assertEquals(1, value.size());
	}

	@Test
	public void adicionarCliente() throws Exception {
		Cliente cli = mock(Cliente.class);
		cli.setAcivo("2");
		cli.setNombre("wildor");
		cli.setApellido("Rivera Navarrete");
		cli.setCorreo("cdiax@gmail.com");
		Cliente cli2 = new Cliente();
		cli2.setId(2l);
		cli2.setAcivo("2");
		cli2.setNombre("wildor");
		cli2.setApellido("Rivera Navarrete");
		cli2.setCorreo("cdiax@gmail.com");
		when(customerService.adicionarCliente(cli)).thenReturn(cli2);
		Response response = clienteResource.adicionarCliente(cli);
		Cliente value = (Cliente) response.getEntity();
		assertEquals(new Long(2), value.getId());
	}
}
