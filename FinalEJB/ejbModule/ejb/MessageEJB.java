package ejb;


import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.ConversationJPA;
import model.MessageJPA;

/**
 * Session Bean implementation class MessageEJB
 */
@Stateless
@LocalBean
public class MessageEJB {

    @PersistenceContext(name="ICodeManagementSystemJPA")
    private EntityManager manager;
    public MessageEJB() {
        // TODO Auto-generated constructor stub
    }
    public void add(MessageJPA j1){
    	manager.persist(j1);
    }
    public ArrayList<MessageJPA> findAll(){
    	return new ArrayList<MessageJPA> (manager.createQuery("Select m from MessageJPA m where m.messageDetails='Testest' ").getResultList());
    }
    public ConversationJPA getConv(int id){
    	return manager.find(ConversationJPA.class,id);
    }

}
