package pe.com.claro.ventas.linea.integration.client;

import java.util.Map;

import javax.ejb.Local;

import pe.com.claro.ventas.linea.canonical.response.linea.ClienteDireccionResponse;

@Local
public interface CatalogoRestClientLocal {
	ClienteDireccionResponse getEstadoCliente(Map<String, String> payload)throws Exception;
}
