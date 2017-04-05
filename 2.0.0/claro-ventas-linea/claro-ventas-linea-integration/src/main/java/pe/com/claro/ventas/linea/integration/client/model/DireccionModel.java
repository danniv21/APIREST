package pe.com.claro.ventas.linea.integration.client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class DireccionModel implements Serializable {	
private static final long serialVersionUID = 1L;
	private BigDecimal ciudadid;
	private String codigopostal;
	private String direccion1;
	private String direccion2;
	private String distrito;
	private String telefono;
	private Date ultimaact;
	
private Long id;	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getCiudadid() {
		return this.ciudadid;
	}

	public void setCiudadid(BigDecimal ciudadid) {
		this.ciudadid = ciudadid;
	}


	public String getCodigopostal() {
		return this.codigopostal;
	}

	public void setCodigopostal(String codigopostal) {
		this.codigopostal = codigopostal;
	}


	public String getDireccion1() {
		return this.direccion1;
	}

	public void setDireccion1(String direccion1) {
		this.direccion1 = direccion1;
	}


	public String getDireccion2() {
		return this.direccion2;
	}

	public void setDireccion2(String direccion2) {
		this.direccion2 = direccion2;
	}


	public String getDistrito() {
		return this.distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}


	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Date getUltimaact() {
		return this.ultimaact;
	}

	public void setUltimaact(Date ultimaact) {
		this.ultimaact = ultimaact;
	}

}