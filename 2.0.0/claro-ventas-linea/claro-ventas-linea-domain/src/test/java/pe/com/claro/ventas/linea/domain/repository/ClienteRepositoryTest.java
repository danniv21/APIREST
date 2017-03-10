package pe.com.claro.ventas.linea.domain.repository;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pe.com.claro.ventas.linea.model.Cliente;
import pe.com.claro.ventas.linea.model.Direccion;
@RunWith(MockitoJUnitRunner.Silent.class)
public class ClienteRepositoryTest {

	    @Mock
	    EntityManager entityManager;
	    @InjectMocks
	    ClienteRepository clienteRepository;
	    @Test
	    public void buscarCliente() throws Exception {
	    	Query query =  mock(Query.class);
	    	Cliente cli= new Cliente();
	        cli.setAcivo("1");
	        cli.setNombre("Jerry");
	        cli.setId(1l);
	        cli.setApellido("Rivera Navarrete");
	        cli.setCorreo("cdiax@gmail.com");   
	        Direccion dir= new Direccion();
	        dir.setId(1l);
	        dir.setDireccion1("lima peru");
	        List<Direccion> lista= new ArrayList<Direccion>();
	        lista.add(dir);
	        cli.setDireccions(lista);
	        List<Cliente> l= new ArrayList<Cliente>();
	        l.add(cli);
	        when(entityManager.createQuery("SELECT p FROM Cliente p JOIN FETCH p.direccions WHERE p.id = :id")).thenReturn(query);
	        when(query.setParameter("id", 1l)).thenReturn(query);
	        when(query.getResultList()).thenReturn(l);
	        Cliente value = clienteRepository.buscarCliente(1l);
	        assertEquals(new Long(1), value.getId());
	    }	

		

	
}
