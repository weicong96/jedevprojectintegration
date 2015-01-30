package ejb;


import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.GroupJPA;

/**
 * Session Bean implementation class GroupEJB
 */
@Stateless
@LocalBean
public class GroupEJB {
	@PersistenceContext(name="ICodeManagementSystemJPA")
    private EntityManager manager;
    public GroupEJB() {
        // TODO Auto-generated constructor stub
    }
    public GroupJPA findByPk(String grpCode){
    	return manager.find(GroupJPA.class,grpCode);
    }
    
    public GroupJPA findByName(String teamName){
    	Query query = manager.createQuery("Select g from GroupJPA g where g.groupName=:groupName");
    	query.setParameter("groupName",teamName);

    	return (GroupJPA) query.getSingleResult();
    }
    
	public ArrayList<GroupJPA> findAll() {
		// TODO Auto-generated method stub
		   return new ArrayList<GroupJPA> (manager.createQuery("Select g from GroupJPA g").getResultList());
	}
	
	 public GroupJPA retrieveById(String id){
	    	return (GroupJPA)manager.find(GroupJPA.class, id);
	    }
	    public ArrayList<GroupJPA> retrieveAll(){
	    	return new ArrayList<GroupJPA>(manager.createQuery("Select g from GroupJPA g LEFT JOIN FETCH g.submissions").getResultList());
	    }
	    public boolean update(GroupJPA grp){
	    	return (manager.merge(grp) != null);
	    }
	    public boolean getDeadlineOver(String id){
	    	Query query = manager.createQuery("Select g from GroupJPA g WHERE g.deadline.deadLine > CURRENT_TIMESTAMP AND g.teamCode = :teamCode");
	    	query.setParameter("teamCode", id);
	    	return query.getResultList().size() > 0;
	    }
    
}
