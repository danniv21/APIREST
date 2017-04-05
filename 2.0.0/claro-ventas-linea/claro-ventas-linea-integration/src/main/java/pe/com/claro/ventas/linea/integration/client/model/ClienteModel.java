package pe.com.claro.ventas.linea.integration.client.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ClienteModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String acivo;
	private String apellido;
	private String correo;
	private Date fechacre;
	private String nombre;
	private Date ultimaact;
	private List<DireccionModel> direccions;
	

	private Long id;	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}


	public String getAcivo() {
		return this.acivo;
	}

	public void setAcivo(String acivo) {
		this.acivo = acivo;
	}


	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public Date getFechacre() {
		return this.fechacre;
	}

	public void setFechacre(Date fechacre) {
		this.fechacre = fechacre;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getUltimaact() {
		return this.ultimaact;
	}

	public void setUltimaact(Date ultimaact) {
		this.ultimaact = ultimaact;
	}

	public List<DireccionModel> getDireccions() {
		return direccions;
	}

	public void setDireccions(List<DireccionModel> direccions) {
		this.direccions = direccions;
	}
	
	
}