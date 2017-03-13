package pe.com.claro.ventas.linea.domain.repository;
import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pe.com.claro.common.domain.repository.AbstractRepository;
import pe.com.claro.ventas.linea.model.Cliente;

@Stateless
public class ClienteRepository extends AbstractRepository<Cliente> implements Serializable{

    /**
	 * 
	 */
	@PersistenceContext(unitName = "pe.com.claro.ventas.linea")
	public void setPersistenceUnit00(final EntityManager em) {
	    this.entityManager=em;
	    System.out.println("esta cargando el contexto");
	}
	
	 
	private static final long serialVersionUID = 6079001789833563579L;

	public ClienteRepository()
	{
	      super(Cliente.class);
	}
	

	public Cliente buscarCliente(Long id) {
		Query q = entityManager.createQuery("SELECT p FROM Cliente p JOIN FETCH p.direccions WHERE p.id = :id");
		q.setParameter("id", id);
		return (q.getResultList().size() > 0) ? (Cliente) q.getResultList().get(0) : null;
	}


	@Override
	protected Predicate[] getSearchPredicates(Root<Cliente> root, Cliente example) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
