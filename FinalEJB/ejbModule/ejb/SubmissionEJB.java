package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import model.Submission;

/**
 * Session Bean implementation class SubmissionEJB
 */
@Stateless
@LocalBean
public class SubmissionEJB {
	@PersistenceContext(name="JEDEVJPA")
	private EntityManager manager;
   
    public SubmissionEJB() {
        // TODO Auto-generated constructor stub
    }
    public int add(Submission sub){
    	manager.persist(sub);
    	manager.flush();
    	int id = sub.getSubmissionID();
    	return id;
    }
    public ArrayList<Submission> retrieveAll(){//assume nothing has beeen changed, used in : ExtendDeadline, 
    //	return new ArrayList<Submission>(manager.createQuery("Select s from Submission s LEFT JOIN FETCH s.group").getResultList());
    	ArrayList<Submission> returnlist = new ArrayList<Submission>(); 
    	List<Submission> list =  (List<Submission>)this.retrieveAll(new String[0], new String[0], "dateSubmitted", true, 0)[0];
    	for(int i = 0; i < list.size();i++){
    		returnlist.add(list.get(i));
    	}
    	return returnlist;
    }
    public Submission retrieveById(int id){
    	return retrieveById(id,false);
    }
    public Submission retrieveById(int id,boolean includeName){
    	Submission sub = (Submission)manager.find(Submission.class, id);
    	manager.detach(sub);
    	if(!includeName)
    		return sub;
    	else{
    		String prefix = sub.getName();
    		sub.setCompiled(prefix+"_"+sub.getCompiled());
    		sub.setIcon(prefix+"_"+sub.getIcon());
    		sub.setSourceCode(prefix+"_"+sub.getSourceCode());
    		sub.setVideo(prefix+"_"+sub.getVideo());
    		String screenShot = "";
    		int i = 0;
    		for(String scFile : sub.getScreenshot().split(",")){
    			if(i==0)
    				screenShot+=prefix+"_"+scFile;
    			else
    				screenShot+=","+prefix+"_"+scFile;
    			i++;
    		}
    		sub.setScreenshot(screenShot);
    	}
    	return sub;
    }
   
   
    public boolean update(Submission sub){
    	return (manager.merge(sub) != null);
    }
    public Object[] retrieveAll(String[] selection,String[] selectionArgs,String orderBy,boolean desc,int page){
    	try {
			return retrieveAll(selection,selectionArgs,orderBy,desc,page,false,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    private String convertDynamicToEntity(String dynamicName){
    	String[] dynamic = {"teamCode","entryName","description","compiled","icon","screenshot","video","sourceCode","lastChanged","status"};
    	String[] columnName = {"teamCode","name","description","compiled","icon","screenshot","video","sourceCode","dateSubmitted","status"};
    	String value = null;
    	for(int i = 0; i < columnName.length;i++){
    		if(dynamicName.equalsIgnoreCase(dynamic[i])){
    			value = columnName[i];
        	}
    	}
    	System.out.println(dynamicName);
    	if(value == null){
    		value = "additional";
    	}
    	return value;
    }
    public ArrayList<Submission> getGroupSubmissions(String teamCode){
    	Query q = manager.createQuery("Select s from Submission s where s.group.teamCode = :teamCode");
    	q.setParameter("teamCode", teamCode);
    	return new ArrayList<Submission>(q.getResultList());
    }
   private boolean displayInactive;
	public Object[] retrieveAll(String[] selection, String[] selectionArgs,
			String orderBy, boolean desc, int page, boolean useGrp,
			String orderByGrp) throws Exception {
		page = (page != 0) ? page : 0;
		orderBy = convertDynamicToEntity(orderBy);
		if (selection.length != selectionArgs.length)
			throw new Exception("Selection queries are not equal");
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Submission> query = builder.createQuery(Submission.class);// create
																				// query
																				// based
																				// on
																				// submission
		Root<Submission> root = query.from(Submission.class);// tell query that
		Join<Submission, GroupEJB> grpJoin = null;
		if (useGrp)
			grpJoin = root.join("group");
		query.select(root);
		for (int i = 0; i < selection.length; i++) {
			query.where(builder.like(root.<String>get(selection[i]),selectionArgs[i]));
		}
		
		if (orderBy != null && !orderBy.isEmpty()) {
			if (!desc)
				query.orderBy(builder.asc(root.get(orderBy)));
			else
				query.orderBy(builder.desc(root.get(orderBy)));
		} else if (orderByGrp != null && !orderByGrp.isEmpty()) {
			if (!desc)
				query.orderBy(builder.asc(grpJoin.get(orderByGrp)));
			else
				query.orderBy(builder.desc(grpJoin.get(orderByGrp)));
		}
		TypedQuery<Submission> typedQuery = manager.createQuery(query);
		System.out.println(typedQuery.unwrap(
				org.apache.openjpa.persistence.QueryImpl.class)
				.getQueryString());
   	 int count = typedQuery.getResultList().size();
		//if (page != -1) {
			//typedQuery.setFirstResult((page * 2));
			//typedQuery.setMaxResults(((page + 1)) * 2);
		//}
		List<Submission> list = typedQuery.getResultList();
		for (Submission sub : list) {
			manager.detach(sub);
		}
		return new Object[]{list,count};
	}
	public boolean checkExists(String entryName){
		Query query = manager.createQuery("Select s from Submission s where s.name = :name");
		query.setParameter("name", entryName);
		List<Submission> list = query.getResultList();
		return list.size() > 0;
	}
    public void remove(int id){
    	Submission sub = manager.find(Submission.class, id);
    	sub.setStatus("D");
    	manager.merge(sub);
    }
    public void setInactive(int id){
    	Submission entity = this.retrieveById(id);
    	entity.setStatus("inactive");
    	this.update(entity);
    }
	public boolean isDisplayInactive() {
		return displayInactive;
	}
	public void setDisplayInactive(boolean displayInactive) {
		this.displayInactive = displayInactive;
	}
    
}
