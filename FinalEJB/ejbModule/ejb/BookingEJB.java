package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.BookingJPA;
import model.MessageJPA;

/**
 * Session Bean implementation class BookingEJB
 */
@Stateless
@LocalBean
public class BookingEJB {

	@PersistenceContext(name = "ICodeManagementSystemJPA")
	private  EntityManager manager;
    /**
     * Default constructor. 
     */
    public BookingEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public void add(BookingJPA b1){
    	manager.persist(b1);
    }

    public BookingJPA findbyBookingID(int id)
    {
    	return manager.find(BookingJPA.class,id);
    	
    }
    
    public int DeleteBookC(int ids)
    {
    	Query q = manager.createQuery("DELETE FROM BookingJPA b WHERE b.idbooking=:ids");
    	q.setParameter("ids",ids);
    	int deleted;
    	return deleted = q.executeUpdate ();
    	
    }
    
    public List<BookingJPA> findbyDateRoom(String date,String room,String tutor){
    	Query q = manager.createQuery("Select b from BookingJPA b where b.bookDate=:date AND b.bookVenue=:room AND b.fk_staffID=:tutor");
    	q.setParameter("date",date);
    	q.setParameter("room",room);
    	q.setParameter("tutor",tutor);
    	return (List<BookingJPA>) q.getResultList();
    }
    
    public List<BookingJPA> findbyUDateRoom(String date,String room){
    	Query q = manager.createQuery("Select b from BookingJPA b where b.bookDate=:date AND b.bookVenue=:room AND (b.fk_groupCode IS NULL OR b.fk_groupCode = '')");
    	q.setParameter("date",date);
    	q.setParameter("room",room);
    	return (List<BookingJPA>) q.getResultList();
    }
    
    public ArrayList<BookingJPA> findByAvaliable(){
    	return new ArrayList<BookingJPA>(manager.createQuery("Select b from BookingJPA b where (b.fk_groupCode IS NULL OR b.fk_groupCode = '')").getResultList());
    }
    
    public ArrayList<BookingJPA> findAll(){
    	return new ArrayList<BookingJPA> (manager.createQuery("Select b from BookingJPA b").getResultList());
    }
    
    public void UpdateSlots(BookingJPA book,String venue,String date,String time,String username){
    	String venues = book.getBookVenue();
    	String dates = book.getBookDate();
    	String times = book.getBookTime();
    	Query q = manager.createQuery("Update BookingJPA b SET b.bookVenue=:venues,b.bookDate=:dates,b.bookTime=:times WHERE b.bookVenue=:venue AND b.bookDate=:date AND b.bookTime=:time AND b.fk_staffID=:teacher");
    	q.setParameter("venues",venues);
    	q.setParameter("dates",dates);
    	q.setParameter("times",times);

    	q.setParameter("venue",venue);
    	q.setParameter("date",date);
    	q.setParameter("time",time);
    	q.setParameter("teacher",username);
    	q.executeUpdate();
    }
    
    public void UpdateUBookingSlots(BookingJPA book,String teamCode){
    	String venues = book.getBookVenue();
    	String dates = book.getBookDate();
    	String times = book.getBookTime();
    	String teacherCode = book.getFk_staffID();
    	Query q = manager.createQuery("Update BookingJPA b SET b.fk_groupCode=:team WHERE b.bookVenue=:venues AND b.bookDate=:dates AND b.bookTime=:times AND b.fk_staffID=:teacher");
    	q.setParameter("team",teamCode);
    	q.setParameter("venues",venues);
    	q.setParameter("dates",dates);
    	q.setParameter("times",times);
    	q.setParameter("teacher",teacherCode);
    	q.executeUpdate();
    }

	public List<BookingJPA> findByUBookedSlots(String teamCode) {
		// TODO Auto-generated method stub
		Query q = manager.createQuery("Select b from BookingJPA b where b.fk_groupCode=:teamCode");
		q.setParameter("teamCode",teamCode);
		return (List<BookingJPA>) q.getResultList();
	}    
	
	 public void CancelBookSlot(int id)
	 {
	    	Query q = manager.createQuery("UPDATE BookingJPA b SET b.fk_groupCode = '' WHERE b.idbooking=:ids");
	    	q.setParameter("ids",id);
	    	q.executeUpdate();
	    	
	 }

	public List<BookingJPA> checkIfSlotAvaliable(BookingJPA book) {
		// TODO Auto-generated method stub
		String date = book.getBookDate();
		String time = book.getBookTime();
		String venue = book.getBookVenue();
		
		Query q = manager.createQuery("Select b from BookingJPA b where b.bookDate=:date AND b.bookTime=:time AND b.bookVenue=:venue");
    	q.setParameter("date",date);
    	q.setParameter("time",time);
    	q.setParameter("venue",venue);
    	return (List<BookingJPA>) q.getResultList();
	}
}
