package pe.com.claro.ventas.linea.canonical.response;

import java.io.Serializable;

import pe.com.claro.ventas.linea.canonical.response.linea.ClienteDireccionResponse;

public class ResponseLinea implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2091171559577789705L;

	
	private ClienteDireccionResponse clienteDireccion;


	public ClienteDireccionResponse getClienteDireccion() {
		return clienteDireccion;
	}


	public void setClienteDireccion(ClienteDireccionResponse clienteDireccion) {
		this.clienteDireccion = clienteDireccion;
	}
	
	
	
}
