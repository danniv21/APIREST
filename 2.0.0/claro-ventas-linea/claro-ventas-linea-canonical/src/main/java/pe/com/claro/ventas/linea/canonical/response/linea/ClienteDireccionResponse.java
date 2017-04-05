package pe.com.claro.ventas.linea.canonical.response.linea;

import java.util.Collection;

public class ClienteDireccionResponse {

	private String apellido;
	private String correo;
	private String nombre;
	private Collection<DireccionResponse> listaDirecciones;
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
	public Collection<DireccionResponse> getListaDirecciones() {
		return listaDirecciones;
	}
	public void setListaDirecciones(Collection<DireccionResponse> listaDirecciones) {
		this.listaDirecciones = listaDirecciones;
	}
	public ClienteDireccionResponse(String apellido, String correo, String nombre,
			Collection<DireccionResponse> listaDirecciones) {
		super();
		this.apellido = apellido;
		this.correo = correo;
		this.nombre = nombre;
		this.listaDirecciones = listaDirecciones;
	}
	
	public ClienteDireccionResponse(String apellido, String correo, String nombre) {
		super();
		this.apellido = apellido;
		this.correo = correo;
		this.nombre = nombre;
	}
	public ClienteDireccionResponse() {
	}

}
