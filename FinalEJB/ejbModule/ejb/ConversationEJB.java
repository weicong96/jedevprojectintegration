package ejb;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.ConversationJPA;
import model.GroupJPA;

/**
 * Session Bean implementation class ConversationEJB
 */
@Stateless
@LocalBean
public class ConversationEJB {

	@PersistenceContext(name = "ICodeManagementSystemJPA")
	private EntityManager manager;

	public ConversationEJB() {
		// TODO Auto-generated constructor stub
	}
	
	public void add(ConversationJPA conv){
		manager.merge(conv);
	}
	
	public int getMaxID(){
    	Query query = manager.createQuery("Select MAX(c.idconversation) from ConversationJPA c");

    	return (Integer) query.getSingleResult();
    }
}
