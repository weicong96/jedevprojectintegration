package tags;


public class ManageSubmissionsLogic {
	public String processUpdateStatus(String text, int id, String key){
		
		//boolean newUpdate = fields.contains(key);
		boolean newUpdate = Boolean.parseBoolean(text);
		return newUpdate ? "<img src='images/new.png' width='25px' data-value='true' data-id='"+id+"' data-key='"+key+"'/>" : "<div data-value='false'/>";
	}
}
