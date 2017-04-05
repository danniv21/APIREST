package pe.com.claro.ventas.linea.canonical.request.linea;

import java.util.Collection;

public class ClienteRequest {

	private String apellido;
	private String correo;
	private String nombre;
	private String acivo;
	private Collection<DireccionRequest> listaDirecciones;
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Collection<DireccionRequest> getListaDirecciones() {
		return listaDirecciones;
	}
	public void setListaDirecciones(Collection<DireccionRequest> listaDirecciones) {
		this.listaDirecciones = listaDirecciones;
	}
	public ClienteRequest() {
	}
	public String getAcivo() {
		return acivo;
	}
	public void setAcivo(String acivo) {
		this.acivo = acivo;
	}
	public ClienteRequest(String apellido, String correo, String nombre, String acivo,
			Collection<DireccionRequest> listaDirecciones) {
		super();
		this.apellido = apellido;
		this.correo = correo;
		this.nombre = nombre;
		this.acivo = acivo;
		this.listaDirecciones = listaDirecciones;
	}	
}
