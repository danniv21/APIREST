package pe.com.claro.ventas.linea.canonical.response.linea;

import java.io.Serializable;

public class DireccionResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigopostal;
	private String direccion1;
	private String direccion2;
	private String distrito;
	private String telefono;
	
	public DireccionResponse() {
	}
	
	public DireccionResponse(String codigopostal, String direccion1, 
			String direccion2, String distrito,
			String telefono) {
		super();
		this.codigopostal = codigopostal;
		this.direccion1 = direccion1;
		this.direccion2 = direccion2;
		this.distrito = distrito;
		this.telefono = telefono;
	}
	public String getCodigopostal() {
		return codigopostal;
	}
	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}
	public String getDireccion1() {
		return direccion1;
	}
	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}
	public String getDireccion2() {
		return direccion2;
	}
	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}
