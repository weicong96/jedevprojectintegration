package ejb;


import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.StaffJPA;

/**
 * Session Bean implementation class StaffEJB
 */
@Stateless
@LocalBean
public class StaffEJB {
	@PersistenceContext(name="ICodeManagementSystemJPA")
    private EntityManager manager;
    /**
     * Default constructor. 
     */
    public StaffEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public ArrayList<StaffJPA> findAll(){
    	return new ArrayList<StaffJPA> (manager.createQuery("Select s from StaffJPA s").getResultList());
    }
    public StaffJPA findByName(String name){
    	Query query = manager.createQuery("Select s from StaffJPA s where s.staffName = :staffName");
    	query.setParameter("staffName",name);
    	//query.setParameter(1, name);
    	
    	return (StaffJPA) query.getSingleResult();
    }
    
    public StaffJPA findByPk(String code){
    	return manager.find(StaffJPA.class,code);
    }
    
    
}
