package ejb;

import java.util.ArrayList;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Extrafield;

/**
 * Session Bean implementation class ExtraBean
 */
@Stateless
@LocalBean
public class ExtraBean {

	@PersistenceContext(name="JEDEVJPA")
	private EntityManager manager;	
    public ExtraBean() {
        // TODO Auto-generated constructor stub
    }
    public ArrayList<Extrafield> getRequiredSubmissionFields(){
    	return new ArrayList<Extrafield>(manager.createQuery("Select e from Extrafield e").getResultList());
    }
    public void add(Extrafield e){
    	manager.persist(e);
    }
    public void update(ArrayList<Extrafield> e){
    	for(Extrafield item : e){
    		update(item);
    	}
    }
    public void update(Extrafield e){
    	manager.merge(e);
    }
	
}
