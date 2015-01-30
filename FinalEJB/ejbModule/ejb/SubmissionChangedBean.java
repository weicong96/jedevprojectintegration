package ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Submission;
import model.Submissionchanged;

/**
 * Session Bean implementation class SubmissionChangedBean
 */
@Stateless
@LocalBean
public class SubmissionChangedBean {
	@PersistenceContext(name="JEDEVJPA")
	private EntityManager manager;

    /**
     * Default constructor. 
     */
    public SubmissionChangedBean() {
        // TODO Auto-generated constructor stub
    }
    public void add(Submissionchanged sc,int id){
    	ArrayList<Submissionchanged> list = this.findByOriginalSubmissionID(id);
    	int nextVer = 0;
    	if(list == null || list.size() == 0)
    		nextVer = 0;
    	else
    		nextVer = list.get(list.size()-1).getVersion();
    	nextVer++;
    	sc.setVersion(nextVer);
    	manager.persist(sc);
    }
    
    public ArrayList<Submissionchanged> findByOriginalSubmissionID(int originalSubmission){
    	Query q = manager.createQuery("Select s from Submissionchanged s where s.originalSubID = :subID order by s.version asc");
    	q.setParameter("subID", originalSubmission);
    	ArrayList<Submissionchanged> list =  new ArrayList<Submissionchanged>(q.getResultList());
    	Query _q = manager.createQuery("Select s from Submission s where s.submissionID = :subID");
    	_q.setParameter("subID", originalSubmission);
    	Submission current = (Submission) _q.getResultList().get(0);
    	list.add(new Submissionchanged(current));
    	return list;
    	
    }
}
