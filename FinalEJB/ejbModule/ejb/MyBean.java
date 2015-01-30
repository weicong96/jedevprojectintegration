package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Submission;

/**
 * Session Bean implementation class MyBean
 */
@Stateless
@LocalBean
public class MyBean {
	@PersistenceContext(name="JEDEVJPA")
	private EntityManager manager;
    public MyBean() {
        // TODO Auto-generated constructor stub
    }
    public String sayHello(){
    	return "Hello String";
    }
    
    public void add(Submission bean){
    	manager.persist(bean);
    }
}
