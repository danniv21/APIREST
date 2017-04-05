package pe.com.claro.ventas.linea.domain.repository;
import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.domain.repository.AbstractRepository;
import pe.com.claro.ventas.linea.model.Direccion;

@Stateless
public class DireccionRepository extends AbstractRepository<Direccion> implements Serializable{
	private static final Logger LOG = LoggerFactory.getLogger(DireccionRepository.class);
    /**
	 * 
	 */
	@PersistenceContext(unitName = "pe.com.claro.ventas.linea")
	public void setPersistenceUnit00(final EntityManager em) {
	    this.entityManager=em;
	    LOG.info("esta cargando el contexto");
	}
	private static final long serialVersionUID = 6079001789833563579L;

	public DireccionRepository()
	{
	      super(Direccion.class);
	}
	
	
	public List<Direccion> buscarDireccionXCliente(Long id) {
		TypedQuery<Direccion> q  = entityManager.createQuery("SELECT p FROM Direccion p WHERE p.cliente.id = :id", Direccion.class);
		q.setParameter("id", id);
		return (q.getResultList().size() > 0) ? q.getResultList() : null;
	}

	@Override
	protected Predicate[] getSearchPredicates(Root<Direccion> root, Direccion example) {
		// TODO Auto-generated method stub
		return null;
	}
}
