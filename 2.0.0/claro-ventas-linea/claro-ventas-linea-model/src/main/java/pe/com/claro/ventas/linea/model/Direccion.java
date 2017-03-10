package pe.com.claro.ventas.linea.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the DIRECCION database table.
 * 
 */
@Entity
@Table(name="Direccion")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Direccion implements Serializable {	
private static final long serialVersionUID = 1L;
	private BigDecimal ciudadid;
	private String codigopostal;
	private String direccion1;
	private String direccion2;
	private String distrito;
	private String telefono;
	private Date ultimaact;
	private Cliente cliente;
	
private Long id;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Direccion() {
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


	@Temporal(TemporalType.DATE)
	public Date getUltimaact() {
		return this.ultimaact;
	}

	public void setUltimaact(Date ultimaact) {
		this.ultimaact = ultimaact;
	}


	//bi-directional many-to-one association to Cliente
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	@JoinColumn(name="clienteId", referencedColumnName = "id")
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}