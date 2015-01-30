package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Deadline;

/**
 * Session Bean implementation class Deadline
 */
@Stateless
@LocalBean
public class DeadlineEJB {

	@PersistenceContext(name="JEDEVJPA")
	private EntityManager manager;
    public DeadlineEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public void add(Deadline deadline){
    	manager.persist(deadline);
    }
    public Deadline retrieveByPK(String grpCode){
    	return manager.find(Deadline.class, grpCode);
    } 
    public boolean update(Deadline dl){
    	return (manager.merge(dl) != null);
    }    
}
