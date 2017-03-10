package pe.com.claro.restclient;

import java.util.HashMap;

import org.junit.Test;

import pe.com.claro.restclient.RestClient.RestClientBuilder;


public class RestClientTest {
	
	@Test
	public void  test01() {
	   final String RESOURCE_URL = "http://localhost:7001";		
		RestClient restClient = new RestClientBuilder(RESOURCE_URL).connectionTimeout(5000).build();	
		 HashMap<String, String> querys = new HashMap<String, String>();
		 querys.put("start", "0");
		 querys.put("size", "3");
	   String data= restClient.get("/claro-ventas-linea-resource/api/api-docs/clientes", String.class, querys);		
	   System.out.println(data);   
	}

}
