package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Fieldsupdated;

/**
 * Session Bean implementation class FieldsEJB
 */
@Stateless
@LocalBean
public class FieldsEJB {
	
	@PersistenceContext(name="JEDEVJPA")
	private EntityManager manager;
    
	public FieldsEJB() {
    }
    

    //not sure if i need this yet
    public Fieldsupdated retrieveByPK(int id){
    	return manager.find(Fieldsupdated.class, id);
    }
    
    public Fieldsupdated retrieveBySubID(int subID){
    	Query query = manager.createQuery("Select fu from Fieldsupdated fu where fu.submission_submissionID.submissionID = :subID");
    	query.setParameter(":subID", subID);
    	return (Fieldsupdated) query.getSingleResult();
    }
    public Fieldsupdated save(Fieldsupdated field){
    	return manager.merge(field);
    }    
    public int add(Fieldsupdated field){
    	manager.persist(field);
    	manager.flush();
    	int id = field.getIdFieldsUpdatedEntry();
    	//System.out.println(id);
    	return id;
    	
    }
}
