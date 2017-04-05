package pe.com.claro.ventas.linea.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the CLIENTE database table.
 * 
 */
@Entity
@Table(name="Cliente")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id",scope = Cliente.class)
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	private String acivo;
	private String apellido;
	private String correo;
	private Date fechacre;
	private String nombre;
	private Date ultimaact;
	

	private Long id;	
	
	@Id
	@SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_JUST_FOR_TEST", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
	@Column(name="id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	

	public Cliente() {
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


	@Temporal(TemporalType.DATE)
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


	@Temporal(TemporalType.DATE)
	public Date getUltimaact() {
		return this.ultimaact;
	}

	public void setUltimaact(Date ultimaact) {
		this.ultimaact = ultimaact;
	}

	/*
	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Direccion> getDireccions() {
		return this.direccions;
	}

	public void setDireccions(List<Direccion> direccions) {
		this.direccions = direccions;
	}

	public Direccion addDireccion(Direccion direccion) {
		getDireccions().add(direccion);
		direccion.setCliente(this);

		return direccion;
	}

	public Direccion removeDireccion(Direccion direccion) {
		getDireccions().remove(direccion);
		direccion.setCliente(null);

		return direccion;
	}
*/
}