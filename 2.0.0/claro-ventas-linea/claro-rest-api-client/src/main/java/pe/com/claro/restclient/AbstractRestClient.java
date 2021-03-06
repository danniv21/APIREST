package pe.com.claro.restclient;

import pe.com.claro.restclient.RestClient.RestClientBuilder;

public class AbstractRestClient {
	
	protected RestClient restClient;

    protected void loadRestClient(String url, int connectionTimeout) {
    	restClient= new RestClientBuilder(url).connectionTimeout(connectionTimeout).build();
    }

	public RestClient getRestClient() {
		return restClient;
	}
	

	
	
	
}
