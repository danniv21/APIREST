package pe.com.claro.ventas.linea.domain.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import pe.com.claro.ventas.linea.canonical.response.linea.ClienteDireccionResponse;
import pe.com.claro.ventas.linea.domain.repository.ClienteRepository;
import pe.com.claro.ventas.linea.domain.repository.DireccionRepository;
import pe.com.claro.ventas.linea.model.Cliente;
import pe.com.claro.ventas.linea.model.Direccion;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClienteServiceTest{
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    DireccionRepository direccionRepository;
    
    @InjectMocks
    ClienteService clienteService;
    @Test
    public void findByid() throws Exception {
    	Cliente cli= new Cliente();
        cli.setAcivo("1");
        cli.setNombre("Jerry");
        cli.setId(1l);
        cli.setApellido("Rivera Navarrete");
        cli.setCorreo("cdiax@gmail.com");   
        Direccion dir= new Direccion();
        dir.setId(1l);
        dir.setCliente(cli);;
        dir.setDireccion1("lima peru");
        List<Direccion> lista= new ArrayList<Direccion>();
        lista.add(dir);
        when(clienteRepository.buscarCliente(1l)).thenReturn(cli);
        when(direccionRepository.buscarDireccionXCliente(cli.getId())).thenReturn(lista);
        ClienteDireccionResponse value = clienteService.findId(1l);
        assertEquals("Rivera Navarrete", value.getApellido());
    }	
  
}
