package pe.com.claro.ventas.linea.integration.client.impl;

import java.util.Map;

import javax.ejb.Stateless;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import pe.com.claro.restclient.AbstractRestClient;
import pe.com.claro.ventas.linea.canonical.response.linea.ClienteDireccionResponse;
import pe.com.claro.ventas.linea.integration.client.CatalogoRestClientLocal;

@Stateless
public class CatalogoRestClient extends AbstractRestClient implements CatalogoRestClientLocal{
	@Context
	private Configuration configuration;
	
	@Override
	public ClienteDireccionResponse getEstadoCliente(Map<String, String> payload) throws Exception {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		loadRestClient(configuration.getProperty("catalogo.rest.client.host").toString(), 5000);
		String uri=configuration.getProperty("catalogo.rest.client.uri").toString()+payload.get("Id");
		String data = getRestClient().get(uri, String.class, null);
		ClienteDireccionResponse value = mapper.readValue(data, ClienteDireccionResponse.class);
		return value;
	}
}