package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Account;
import bean.Conversation;
import bean.Feedback;
import bean.Group;
import bean.Message;
import bean.Registration;
import bean.School;
import bean.Staff;
import bean.StaffAccount;

public class DBAO {
	
	Connection con;
	
	//Database Configuration
	public static String url = "jdbc:mysql://mysql30495-env-3560749.whelastic.net:3306/enterpriseprojectlaptop";
	public static String dbdriver = "com.mysql.jdbc.Driver";
	public static String username = "project";
	public static String password = "root";
	
	public DBAO () throws Exception{
		try{
			Class.forName(dbdriver);
			con = DriverManager.getConnection(url,username,password);//Getting connection to database
			con.setAutoCommit(true);
		}
		catch(Exception ex){
			System.out.println("Exception in DBAO "+ ex);
			throw new Exception("Couldn't open connection to database: " + ex.getMessage());
		}
	}

	public int addRegistartion(Registration reg,Group g,School s)
	{
		int status=0;
		try{
			PreparedStatement pstmt = null;
			String insertStmt = "INSERT INTO  participant (teamMember,gender,dob,nric,contactNo,tic,foodpre,foodOtherCom,email,attendance,leaderStatus,groupCode,schCode)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt =  con.prepareStatement(insertStmt);
			pstmt.setString(1, reg.getMemberName());
			pstmt.setString(2, reg.getGender());
			pstmt.setString(3, reg.getDOB());
			pstmt.setString(4, reg.getNric());
			pstmt.setString(5, reg.getContactNo());
			pstmt.setString(6, reg.getTic());
			pstmt.setString(7, reg.getFoodPre());
			pstmt.setString(8, reg.getFoodOtherCom());
			pstmt.setString(9, reg.getEmailAdd());
			pstmt.setString(10, reg.getAttendance());
			pstmt.setString(11, reg.getLeaderStatus());
			pstmt.setString(12, g.getTeamCode());
			pstmt.setString(13, s.getSchoolCode());
			status=pstmt.executeUpdate();
			System.out.println("Status= "+status);
		}
		catch(Exception ex)
		{
			System.out.println("SQL Error= " +ex.getMessage());
		}
		return status;
	}
	
	public int addGroup(Group grp)
	{
		int status=0;
		try{
			PreparedStatement pstmt = null;
			String insertStmt = "INSERT INTO  `group` (teamCode,teamName,teamPic)"
					+ " values(?,?,?)";
			pstmt =  con.prepareStatement(insertStmt);
			pstmt.setString(1, grp.getTeamCode());
			pstmt.setString(2, grp.getTeamName());
			pstmt.setString(3, grp.getTeamPic());
			status=pstmt.executeUpdate();
			System.out.println("Status= "+status);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("SQL Error= " +ex.getMessage());
		}
		return status;
	}
	
	public School[] getAllSchools()
	{
		//SQL Statement 
		String sql = "SELECT * FROM school";
		
		//Create an array list for our product java beans
		ArrayList<School> schools = new ArrayList<School>();
		
		try{
			
			//Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			
			//Execute the SQL prepared statement to retrieve the records 
			//The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();
			
			//Iterates through each record of the result set
			while(rs.next()){
				//Each record will be changes into a Java bean
				//rs.getXXX() methods  a value from
				//a cloumn in the current record()
				School school = new School();
				school.setSchoolCode(rs.getString("schCode"));
				school.setSchoolName(rs.getString("schName"));
				school.setSchoolEmail(rs.getString("schEmail"));
				
				//Each java bean is then added to the array list "products"
				schools.add(school);
			}
			
			//Return the array list as a product array (Product[])
			return schools.toArray(new School [schools.size()]);
			
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public int addAccount(Account acc,Group g)
	{
		int status=0;
		try{
			PreparedStatement pstmt = null;
			String insertStmt = "INSERT INTO  account (accUsername,accPassword,fk_grpCode)"
					+ "values(?,?,?)";
			pstmt =  con.prepareStatement(insertStmt);
			pstmt.setString(1, acc.getAccUserName());
			pstmt.setString(2, acc.getAccPassword());
			pstmt.setString(3, g.getTeamCode());
			status=pstmt.executeUpdate();
			System.out.println("Status= "+status);
		}
		catch(Exception ex)
		{
			System.out.println("SQL Error= " +ex.getMessage());
		}
		return status;
	}
	
	public boolean checkUsers(String username, String password)
	{
		//SQL Statement 
		String sql = "SELECT * FROM account WHERE accUsername = ? AND accPassword = ?";
		
		
		try{
			
			//Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			//Execute the SQL prepared statement to retrieve the records 
			//The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();
			
			//Iterates through each record of the result set
			if(rs.next()){	
				return true;
			}
			return false;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public Group checkandGetUsers(String username, String password)
	{
		//SQL Statement 
		String sql = "SELECT a.*,g.* FROM account a  INNER JOIN `group` g ON g.teamCode=a.fk_grpCode WHERE a.accUsername = ? AND a.accPassword = ?";
		Group grp = null;
		
		try{
			
			//Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			//Execute the SQL prepared statement to retrieve the records 
			//The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();
			
			
			//Iterates through each record of the result set
			if(rs.next()){	
				grp = new Group();
				grp.setTeamCode(rs.getString("teamCode"));
				grp.setTeamName(rs.getString("teamName"));
				grp.setTeamPic(rs.getString("teamPic"));
			}
			//return false;
		}catch (Exception e){
			e.printStackTrace();
			//return false;
		}
		return grp;
	}
	public School retrieveSchool(String username) {

		School sch = new School();

		// SQL Statement
		String sql = "SELECT s.*, p.*, a.* FROM account a INNER JOIN `participant` p ON a.fk_grpCode=p.groupCode INNER JOIN `school` s ON p.schCode=s.schCode WHERE a.accUsername = ?";

		try {

			// Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);

			// Execute the SQL prepared statement to retrieve the records
			// The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();

			// iterates through each record of the result set
			if (rs.next()) {
				sch.setSchoolCode(rs.getString("schCode"));
				sch.setSchoolName(rs.getString("schName"));
				sch.setSchoolEmail(rs.getString("schEmail"));
			}
			return sch;
		} catch (Exception e) {
			e.printStackTrace();
			return sch;
		}
	}

		public Group retrieveGroup(String username)
		{
			
			Group grp = new Group();
			
			//SQL Statement 
			String sql = "SELECT a.*,g.* FROM account a INNER JOIN `group` g ON g.teamCode = a.fk_grpCode WHERE a.accUsername= ?";
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				if(rs.next()){	
					grp.setTeamCode(rs.getString("teamCode"));
					grp.setTeamName(rs.getString("teamName"));
					grp.setTeamPic(rs.getString("teamPic"));
					System.out.println(grp.getTeamPic());
				}
				return grp;
			}catch (Exception e){
				e.printStackTrace();
				return grp;
			}
	}
		
		public Group retrieveGroupDetails(String name)
		{
			
			Group grp = new Group();
			
			//SQL Statement 
			String sql = "SELECT * FROM  group WHERE teamName = ?";
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				if(rs.next()){	
					grp.setTeamCode(rs.getString("teamCode"));
					grp.setTeamName(rs.getString("teamName"));
					grp.setTeamPic(rs.getString("teamPic"));
					System.out.println(grp.getTeamPic());
				}
				return grp;
			}catch (Exception e){
				e.printStackTrace();
				return grp;
			}
	}
		
		public int addStaff(Staff s)
		{
			int status=0;
			try{
				PreparedStatement pstmt = null;
				String insertStmt = "INSERT INTO  staff (idstaff,staffName,staffType,staffContactNo,staffEmailNo)"
						+ "values(?,?,?,?,?)";
				pstmt =  con.prepareStatement(insertStmt);
				pstmt.setString(1, s.getIdstaff());
				pstmt.setString(2, s.getStaffName());
				pstmt.setString(3, s.getStaffType());
				pstmt.setString(4, s.getStaffContactNo());
				pstmt.setString(5, s.getStaffEmailNo());
				status=pstmt.executeUpdate();
				System.out.println("Status= "+status);
			}
			catch(Exception ex)
			{
				System.out.println("SQL Error= " +ex.getMessage());
			}
			return status;
		}
		
		public int addStaffAccount(StaffAccount sa,Staff s)
		{
			int status=0;
			try{
				PreparedStatement pstmt = null;
				String insertStmt = "INSERT INTO  staffaccount (username,staffPassword,fk_staffID)"
						+ "values(?,?,?)";
				pstmt =  con.prepareStatement(insertStmt);
				pstmt.setString(1, sa.getAccUserName());
				pstmt.setString(2, sa.getAccPassword());
				pstmt.setString(3, s.getIdstaff());
				status=pstmt.executeUpdate();
				System.out.println("Status= "+status);
			}
			catch(Exception ex)
			{
				System.out.println("SQL Error= " +ex.getMessage());
			}
			return status;
		}
		
		public boolean checkStaffUsers(String username, String password)
		{
			//SQL Statement 
			String sql = "SELECT * FROM staffaccount WHERE username = ? AND staffPassword = ?";
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				if(rs.next()){	
					return true;
				}
				return false;
			}catch (Exception e){
				e.printStackTrace();
				return false;
			}
		}
		
		public Staff retrieveStaff(String username)
		{
			
			Staff s = new Staff();
			
			//SQL Statement 
			String sql = "SELECT sa.*,s.* FROM staffaccount sa INNER JOIN `staff` s ON s.idstaff = sa.fk_staffID WHERE sa.username= ?";
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				if(rs.next()){	
					s.setIdStaff(rs.getString("idStaff"));
					s.setStaffName(rs.getString("staffName"));
					s.setStaffType(rs.getString("staffType"));
					s.setStaffContactNo(rs.getString("staffContactNo"));
					s.setStaffEmailNo(rs.getString("staffEmailNo"));
				}
				return s;
			}catch (Exception e){
				e.printStackTrace();
				return s;
			}
	}
		

		public Staff retrieveStaffDetails(String name)
		{
			
			Staff s = new Staff();
			
			//SQL Statement 
			String sql = "SELECT * FROM staff s WHERE s.staffName= ?";
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				if(rs.next()){	
					s.setIdStaff(rs.getString("idStaff"));
					s.setStaffName(rs.getString("staffName"));
					s.setStaffType(rs.getString("staffType"));
					s.setStaffContactNo(rs.getString("staffContactNo"));
					s.setStaffEmailNo(rs.getString("staffEmailNo"));
				}
				return s;
			}catch (Exception e){
				e.printStackTrace();
				return s;
			}
	}
		public ArrayList<Registration> retrieveStudentsByGroup(String groupCode) throws SQLException{
			ArrayList<Registration> list = new ArrayList<Registration>();
			PreparedStatement ps = con.prepareStatement("Select * from participant p where p.groupCode = ?");
			ps.setString(1, groupCode);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Registration reg = new Registration();
				reg.setMemberName(rs.getString("teamMember"));
				reg.setGender(rs.getString("gender"));
				reg.setDOB(rs.getString("dob"));
				reg.setNric(rs.getString("nric"));
				reg.setContactNo(rs.getString("contactNo"));
				reg.setTic(rs.getString("tic"));
				reg.setFoodPre(rs.getString("foodpre"));
				reg.setFoodOtherCom(rs.getString("foodOtherCom"));
				reg.setEmailAdd(rs.getString("email"));
				reg.setAttendance(rs.getString("attendance"));
				reg.setLeaderStatus(rs.getString("leaderStatus"));
				
				list.add(reg);
			}
			return list;
			
		}
		public ArrayList<Group> retrieveGroup(){
			try {
				ArrayList<Group> list = new ArrayList<Group>();
				//PreparedStatement ps = con.prepareStatement("Select * from participant p inner join `group` g ON g.teamCode = p.groupCode where g.teamName= ?");
				PreparedStatement ps = con.prepareStatement("Select * from `group`");
				
				//	ps.setString(1, grpName);
				
				/*
				Registration reg = new Registration();
				reg.setMemberName(rs.getString("teamMember"));
				reg.setGender(rs.getString("gender"));
				reg.setDOB(rs.getString("dob"));
				reg.setNric(rs.getString("nric"));
				reg.setContactNo(rs.getString("contactNo"));
				reg.setTic(rs.getString("tic"));
				reg.setFoodPre(rs.getString("foodpre"));
				reg.setFoodOtherCom(rs.getString("foodOtherCom"));
				reg.setEmailAdd(rs.getString("email"));
				reg.setAttendance(rs.getString("attendance"));
				reg.setLeaderStatus(rs.getString("leaderStatus"));
				*/
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Group grp = new Group();
					grp.setTeamCode(rs.getString("teamCode"));
					grp.setTeamName(rs.getString("teamName"));
					grp.setTeamPic(rs.getString("teamPic"));
					
					grp.setStudents(retrieveStudentsByGroup(grp.getTeamCode()));
					
					list.add(grp);
				}
				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		public ArrayList<Staff> retrieveStaffByStaffCode(String teacherCode)
		{
			
			//SQL Statement 
			String sql = "SELECT * FROM staff s WHERE s.idstaff = ?";
		
			ArrayList<Staff> staffs = new ArrayList<Staff>();
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, teacherCode);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				while(rs.next()){	
					Staff s = new Staff();
					s.setIdStaff(rs.getString("idStaff"));
					s.setStaffName(rs.getString("staffName"));
					s.setStaffType(rs.getString("staffType"));
					s.setStaffContactNo(rs.getString("staffContactNo"));
					s.setStaffEmailNo(rs.getString("staffEmailNo"));
					
					staffs.add(s);
				}
				return staffs;
			}catch (Exception e){
				e.printStackTrace();
				return staffs;
			}
	}
		
		public StaffAccount retrieveStaffAccount(String username)
		{
			
			StaffAccount sa = new StaffAccount();
			
			//SQL Statement 
			String sql = "SELECT sa.*,s.* FROM staffaccount sa INNER JOIN `staff` s ON s.idstaff = sa.fk_staffID WHERE sa.username= ?";
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				if(rs.next()){	
					sa.setAccPassword(rs.getString("staffPassword"));
				}
				return sa;
			}catch (Exception e){
				e.printStackTrace();
				return sa;
			}
	}
		
		
		public Staff[] retrieveAllStaff()
		{

			//SQL Statement 
			String sql = "SELECT * FROM staff";
			
			//Create an array list for our product java beans
			ArrayList<Staff> staffs = new ArrayList<Staff>();
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				while(rs.next()){
					//Each record will be changes into a Java bean
					//rs.getXXX() methods  a value from
					//a cloumn in the current record()
					Staff staff = new Staff();
					staff.setIdStaff(rs.getString("idstaff"));
					staff.setStaffName(rs.getString("staffName"));
					staff.setStaffType(rs.getString("staffType"));
					staff.setStaffContactNo(rs.getString("staffContactNo"));
					staff.setStaffEmailNo(rs.getString("staffEmailNo"));
					
					//Each java bean is then added to the array list "products"
					staffs.add(staff);
				}
				
				//Return the array list as a product array (Product[])
				return staffs.toArray(new Staff [staffs.size()]);
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
	}
		
		public ArrayList<Staff> retrieveAllStaffs()
		{

			//SQL Statement 
			String sql = "SELECT * FROM staff";
			
			//Create an array list for our product java beans
			ArrayList<Staff> staffs = new ArrayList<Staff>();
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				while(rs.next()){
					//Each record will be changes into a Java bean
					//rs.getXXX() methods  a value from
					//a cloumn in the current record()
					Staff staff = new Staff();
					staff.setIdStaff(rs.getString("idstaff"));
					staff.setStaffName(rs.getString("staffName"));
					staff.setStaffType(rs.getString("staffType"));
					staff.setStaffContactNo(rs.getString("staffContactNo"));
					staff.setStaffEmailNo(rs.getString("staffEmailNo"));
					
					//Each java bean is then added to the array list "products"
					staffs.add(staff);
				}
				
				//Return the array list as a product array (Product[])
				return staffs;
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
	}
		
		public ArrayList<Group> retrieveAllGroups()
		{

			//SQL Statement 
			String sql = "SELECT * FROM  group";
			
			//Create an array list for our product java beans
			ArrayList<Group> groups = new ArrayList<Group>();
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				while(rs.next()){
					//Each record will be changes into a Java bean
					//rs.getXXX() methods  a value from
					//a cloumn in the current record()
					Group g = new Group();
					g.setTeamCode(rs.getString("teamCode"));
					g.setTeamName(rs.getString("teamName"));
					g.setTeamPic(rs.getString("teamPic"));
					
					//Each java bean is then added to the array list "products"
					groups.add(g);
				}
				
				//Return the array list as a product array (Product[])
				return groups;
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
	}
		
		public int updateStaff(Staff s){
			int status=0;
			try{
				PreparedStatement pstmt = null;
				String updateStatement = "UPDATE  staff"+" SET staffName = ?,"+" staffType = ?,"+" staffContactNo = ?,"+" staffEmailNo = ? "+"WHERE idStaff = ?";
				
				pstmt = con.prepareStatement(updateStatement);
				
				pstmt.setString(1, s.getStaffName());
				pstmt.setString(2, s.getStaffType());
				pstmt.setString(3, s.getStaffContactNo());
				pstmt.setString(4, s.getStaffEmailNo());
				pstmt.setString(5, s.getIdstaff());
				
				status=pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println("SQL Error= " +ex.getMessage());
			}
			return status;
		}
		
		public int updateStaffAccount(StaffAccount sa,Staff s){
			int status=0;
			try{
				PreparedStatement pstmt = null;
				String updateStatement = "UPDATE  staffaccount"+" SET staffPassword = ? "+" WHERE fk_staffID = ?";
				
				pstmt = con.prepareStatement(updateStatement);
				
				pstmt.setString(1, sa.getAccPassword());
				pstmt.setString(2, s.getIdstaff());
				
				status=pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println("SQL Error= " +ex.getMessage());
			}
			return status;
		}
		
		public int addFeedback(Feedback fb,Group g)
		{
			int status=0;
			try{
				PreparedStatement pstmt = null;
				String insertStmt = "INSERT INTO  feedback (designQns,friendlyQns,favourFeatures,comments,fk_groupCode)"
						+ "values(?,?,?,?,?)";
				pstmt =  con.prepareStatement(insertStmt);
				pstmt.setString(1, fb.getDesignQns());
				pstmt.setString(2, fb.getFriendlyQns());
				pstmt.setString(3, fb.getFavourFeatures());
				pstmt.setString(4, fb.getComments());
				pstmt.setString(5, g.getTeamCode());
				status=pstmt.executeUpdate();
				System.out.println("Status= "+status);
			}
			catch(Exception ex)
			{
				System.out.println("SQL Error= " +ex.getMessage());
			}
			return status;
		}
		
		public Feedback[] retrieveAllFeedback()
		{

			//SQL Statement 
			String sql = "SELECT * FROM  feedback";
			
			//Create an array list for our product java beans
			ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				while(rs.next()){
					//Each record will be changes into a Java bean
					//rs.getXXX() methods  a value from
					//a cloumn in the current record()
					Feedback feedback = new Feedback();
					feedback.setIdfeedback(rs.getInt("idfeedback"));
					feedback.setDesignQns(rs.getString("designQns"));
					feedback.setFriendlyQns(rs.getString("friendlyQns"));
					feedback.setFavourFeatures(rs.getString("favourFeatures"));
					feedback.setComments(rs.getString("comments"));
					feedback.setFk_groupCode(rs.getString("fk_groupCode"));
					
					//Each java bean is then added to the array list "products"
					feedbacks.add(feedback);
				}
				
				//Return the array list as a product array (Product[])
				return feedbacks.toArray(new Feedback [feedbacks.size()]);
				
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
	}
		
		public boolean deleteFeedback(){
			PreparedStatement pstmt = null;
			String updateStatement = "DELETE FROM  feedback";
			
			try{
				pstmt = con.prepareStatement(updateStatement);
				pstmt.executeUpdate();
				return true;
			}
			catch(Exception e){
				System.out.println("SQL ERROR=" + e.getMessage());
				return false;
			}
		}
		
		public boolean deleteOneFeedback(int id){
			PreparedStatement pstmt = null;
			String updateStatement = "DELETE FROM  feedback WHERE idfeedback=?";
			
			try{
				pstmt = con.prepareStatement(updateStatement);
				pstmt.setInt(1,id);
				pstmt.executeUpdate();
				return true;
			}
			catch(Exception e){
				System.out.println("SQL ERROR=" + e.getMessage());
				return false;
			}
		}
		
		public boolean checkFeedback(String teamCode)
		{
			//SQL Statement 
			String sql = "SELECT * FROM  feedback WHERE fk_groupCode = ? ";
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, teamCode);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				if(rs.next()){	
					return true;
				}
				return false;
			}catch (Exception e){
				e.printStackTrace();
				return false;
			}
		}
		
		public Feedback retrieveFeedback(String username)
		{
			
			Feedback f = new Feedback();
			
			//SQL Statement 
			String sql = "SELECT a.*,f.* FROM account a INNER JOIN `feedback` f ON f.fk_groupCode = a.fk_grpCode WHERE a.accUsername= ?";
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				if(rs.next()){	
					f.setIdfeedback(rs.getInt("idfeedback"));
					f.setDesignQns(rs.getString("designQns"));
					f.setFriendlyQns(rs.getString("friendlyQns"));
					f.setFavourFeatures(rs.getString("favourFeatures"));
					f.setComments(rs.getString("comments"));
					f.setFk_groupCode(rs.getString("fk_groupCode"));
				}
				return f;
			}catch (Exception e){
				e.printStackTrace();
				return f;
			}
	}

		public boolean checkUserUsername(String username) {
			// TODO Auto-generated method stub
			//SQL Statement 
			String sql = "SELECT * FROM  account WHERE accUsername = ? ";
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				if(rs.next()){	
					return true;
				}
				return false;
			}catch (Exception e){
				e.printStackTrace();
				return false;
			}
		}
		
		public int updatePassword(String username,String password){
			int status=0;
			try{
				PreparedStatement pstmt = null;
				String updateStatement = "UPDATE  account"+" SET accPassword = ? "+"WHERE accUsername = ?";
				
				pstmt = con.prepareStatement(updateStatement);
				
				pstmt.setString(1, password);
				pstmt.setString(2, username);
				
				status=pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println("SQL Error= " +ex.getMessage());
			}
			return status;
		}

		public boolean checkStaffUsername(String username) {
			// TODO Auto-generated method stub
			//SQL Statement 
			String sql = "SELECT * FROM  staffaccount WHERE username = ? ";
			try{
							
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
							
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
							
				//Iterates through each record of the result set
		if(rs.next()){	
						return true;
					}
						return false;
	       }catch (Exception e){
					e.printStackTrace();
						
					return false;
				}
		}

		public int updateStaffPassword(String username, String password) {
			int status=0;
			try{
				PreparedStatement pstmt = null;
				String updateStatement = "UPDATE  staffaccount"+" SET staffPassword = ? "+"WHERE username = ?";
				
				pstmt = con.prepareStatement(updateStatement);
				
				pstmt.setString(1, password);
				pstmt.setString(2, username);
				
				status=pstmt.executeUpdate();
			}
			catch(Exception ex)
			{
				System.out.println("SQL Error= " +ex.getMessage());
			}
			return status;
		}

		public int CreateConversation(Conversation conversation) {
			// TODO Auto-generated method stub
			int status=0;
			try{
				PreparedStatement pstmt = null;
				String insertStmt = "INSERT INTO  conversation (sender,reciever,staff_idstaff,group_teamCode)" + "values(?,?,?,?)";
				
				pstmt =  con.prepareStatement(insertStmt);
				pstmt.setString(1, conversation.getSender());
				pstmt.setString(2, conversation.getReciever());
				pstmt.setString(3, conversation.getStaff_idstaff());
				pstmt.setString(4, conversation.getGroup_teamCode());
				
				status=pstmt.executeUpdate();
				System.out.println("Status= "+status);
			}
			catch(Exception ex)
			{
				System.out.println("SQL Error= " +ex.getMessage());
			}
			return status;
		}
		
		public Conversation retrieveConversationId()
		{
			
			Conversation conversation = new Conversation();
			
			//SQL Statement 
			String sql = "SELECT MAX(idconversation) FROM  conversation";
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);

				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();

				//Iterates through each record of the result set
				if(rs.next()){	
					conversation.setIdconversation(rs.getInt("MAX(idconversation)"));
					
				}
				return conversation;
				
			}catch (Exception e){
				e.printStackTrace();
				return conversation;
			}
	}
		
		public ArrayList<Conversation> retrieveConversationStaff(String grpCode)
		{	
			//SQL Statement 
			String sql = "SELECT DISTINCT staff_idstaff FROM  conversation WHERE group_teamCode = ? ";
			
			ArrayList<Conversation> convo = new ArrayList<Conversation>();
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, grpCode);

				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();

				//Iterates through each record of the result set
				while(rs.next()){
						Conversation conversation = new Conversation();
						conversation.setStaff_idstaff(rs.getString("staff_idstaff"));
						convo.add(conversation);
				}
				return convo;
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
	}
		
		public ArrayList<Conversation> retrieveConversationGroup(String staffCode)
		{	
			//SQL Statement 
			String sql = "SELECT DISTINCT group_teamCode FROM  conversation WHERE staff_idstaff = ? ";
			
			ArrayList<Conversation> convo = new ArrayList<Conversation>();
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, staffCode);

				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();

				//Iterates through each record of the result set
				while(rs.next()){
						Conversation conversation = new Conversation();
						conversation.setGroup_teamCode(rs.getString("group_teamCode"));
						convo.add(conversation);
				}
				return convo;
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
	}

		public int createMessage(Message msg) {
			// TODO Auto-generated method stub
						int status=0;
						try{
							PreparedStatement pstmt = null;
							String insertStmt = "INSERT INTO  message (messageDetails,messageDate,messageTime,conversation_idconversation)" + "values(?,?,?,?)";
							
							pstmt =  con.prepareStatement(insertStmt);
							pstmt.setString(1, msg.getMessageDetails());
							pstmt.setString(2, msg.getMessageDate());
							pstmt.setString(3, msg.getMessageTime());
							pstmt.setInt(4, msg.getConversation_idconversation());
							status=pstmt.executeUpdate();
							System.out.println("Status= "+status);
						}
						catch(Exception ex)
						{
							System.out.println("SQL Error= " +ex.getMessage());
						}
						return status;
		}
		
		public ArrayList<Message> retrieveGrpSenderDetails(String grpCode)
		{
			
			ArrayList<Message> msg = new ArrayList<Message>();
			
			//SQL Statement 
			String sql = "SELECT c.*,m.* FROM conversation c INNER JOIN `message` m ON c.idconversation = m.conversation_idconversation WHERE c.sender = ? OR c.reciever = ? ORDER BY STR_TO_DATE(concat(m.messageTime,'',m.messageDate), '%H:%i:%S %d/%c/%Y') ASC";
			
			try{
				
				//Use the connection to create a PreparedStatement object
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, grpCode);
				ps.setString(2, grpCode);
				
				//Execute the SQL prepared statement to retrieve the records 
				//The records are returned as a ResultSet
				ResultSet rs = ps.executeQuery();
				
				//Iterates through each record of the result set
				while(rs.next()){	
					Conversation conversation = new Conversation();
					conversation.setIdconversation(rs.getInt("idconversation"));
					conversation.setSender(rs.getString("sender"));
					conversation.setReciever(rs.getString("reciever"));
					conversation.setStaff_idstaff(rs.getString("staff_idstaff"));
					conversation.setGroup_teamCode(rs.getString("group_teamCode"));
					
					Message msgs = new Message();
					msgs.setMessageDetails(rs.getString("messageDetails"));
					msgs.setMessageDate(rs.getString("messageDate"));
					msgs.setMessageTime(rs.getString("messageTime"));
					msgs.setConversation(conversation);
					msg.add(msgs);
				}
				return msg;
			}catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}
			
			public ArrayList<Message> retrieveTeacherSenderDetails(String teacherCode)
			{
				
				ArrayList<Message> msg = new ArrayList<Message>();
				
				//SQL Statement 
				String sql = "SELECT c.*,m.* FROM conversation c INNER JOIN `message` m ON c.idconversation = m.conversation_idconversation WHERE c.sender = ? OR c.reciever = ? ORDER BY STR_TO_DATE(concat(m.messageTime,'',m.messageDate), '%H:%i:%S %d/%c/%Y') ASC";
				
				try{
					
					//Use the connection to create a PreparedStatement object
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, teacherCode);
					ps.setString(2, teacherCode);
					
					//Execute the SQL prepared statement to retrieve the records 
					//The records are returned as a ResultSet
					ResultSet rs = ps.executeQuery();
					
					//Iterates through each record of the result set
					while(rs.next()){
						Conversation conversation = new Conversation();
						conversation.setIdconversation(rs.getInt("idconversation"));
						conversation.setSender(rs.getString("sender"));
						conversation.setReciever(rs.getString("reciever"));
						conversation.setStaff_idstaff(rs.getString("staff_idstaff"));
						conversation.setGroup_teamCode(rs.getString("group_teamCode"));
						
						Message msgs = new Message();
						msgs.setMessageDetails(rs.getString("messageDetails"));
						msgs.setMessageDate(rs.getString("messageDate"));
						msgs.setMessageTime(rs.getString("messageTime"));
						msgs.setConversation(conversation);
						msg.add(msgs);
					}
					return msg;
				}catch (Exception e){
					e.printStackTrace();
					return null;
				}
	}
			//CHANGE: to allow system to get teamCode by teamName
			
			
}