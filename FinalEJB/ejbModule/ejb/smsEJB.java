package ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Sms;

/**
 * Session Bean implementation class smsEJB
 */
@Stateless
@LocalBean
public class smsEJB {
	@PersistenceContext(name="ICodeManagementSystemJPA")
    private EntityManager manager;
    /**
     * Default constructor. 
     */
    public smsEJB() {
        // TODO Auto-generated constructor stub
    }
    public ArrayList<Sms> getAllSMSUnsent(){
    	return new ArrayList<Sms>(manager.createQuery("Select sm from Sms sm where sm.smsTimeSent IS NULL").getResultList());
    }
	public void updateSmsTimeSent(int id) {
		Query query = manager.createQuery("Update Sms s set s.smsTimeSent = CURRENT_TIMESTAMP where s.idsms = :smsId");
		query.setParameter("smsId", id);
		query.executeUpdate();
	}
	public ArrayList<Sms> getAllSmsRecords(){
		Query query = manager.createQuery("Select s from Sms s");
		return new ArrayList<Sms>(query.getResultList());
	}
	public void add(Sms sms){
		manager.persist(sms);
	}
	
}

