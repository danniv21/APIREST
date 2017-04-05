package pe.com.claro.ventas.linea.domain.repository;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pe.com.claro.ventas.linea.model.Cliente;
import pe.com.claro.ventas.linea.model.Direccion;
@RunWith(MockitoJUnitRunner.Silent.class)
public class DireccionRepositoryTest {

	    @Mock
	    EntityManager entityManager;
	    @Mock TypedQuery<Direccion> typedQuery;
	    
	    @InjectMocks
	    DireccionRepository direccionRepository;
	    @Test
	    public void buscarCliente() throws Exception {
	    	
	    	Cliente cli= new Cliente();
	        cli.setAcivo("1");
	        cli.setNombre("Jerry");
	        cli.setId(1l);
	        cli.setApellido("Rivera Navarrete");
	        cli.setCorreo("cdiax@gmail.com");  
	    	Direccion dir= new Direccion();   
	        dir.setId(1l);
	        dir.setCliente(cli);
	        dir.setDireccion1("lima peru");
	        List<Direccion> lista= new ArrayList<Direccion>();
	        lista.add(dir);
	        when(entityManager.createQuery("SELECT p FROM Direccion p WHERE p.cliente.id = :id", Direccion.class)).thenReturn(typedQuery);
	        when(typedQuery.setParameter("id", 1l)).thenReturn(typedQuery);
	        when(typedQuery.getResultList()).thenReturn(lista);
	        List<Direccion> value = direccionRepository.buscarDireccionXCliente(1l);
	        assertEquals(1, value.size());
	    }	

		

	
}
