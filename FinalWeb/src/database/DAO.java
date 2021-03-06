package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Group;
import bean.Registration;
import bean.School;
import bean.Updates;


public class DAO {

	Connection con;
	public static String url = "jdbc:mysql://mysql30495-env-3560749.whelastic.net:3306/enterpriseprojectlaptop";
	public static String dbdriver = "com.mysql.jdbc.Driver";
	public static String username = "project";
	public static String password = "root";

	public DAO() throws Exception {
		try {
			Class.forName(dbdriver);
			con = DriverManager.getConnection(url, username, password);// Getting
																		// connection
																		// to
																		// database
			con.setAutoCommit(true);
		} catch (Exception ex) {
			System.out.println("Exception in DBAO " + ex);
			throw new Exception("Couldn't open connection to database: "
					+ ex.getMessage());
		}
	}

	public boolean checkUsers(String username, String password) {
		// SQL Statement
		String sql = "SELECT * FROM account WHERE accUsername = ? AND accPassword = ?";

		try {

			// Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			// Execute the SQL prepared statement to retrieve the records
			// The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();

			// Iterates through each record of the result set
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkStaffUsers(String username, String password) {
		// SQL Statement
		String sql = "SELECT * FROM staffaccount WHERE username = ? AND staffPassword = ?";
		try {

			// Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			// Execute the SQL prepared statement to retrieve the records
			// The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();

			// Iterates through each record of the result set
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Group retrieveGroup(String username) {

		Group grp = new Group();

		// SQL Statement
		String sql = "SELECT a.*,g.* FROM account a INNER JOIN `group` g ON g.teamCode = a.fk_grpCode WHERE a.accUsername = ?";

		try {

			// Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);

			// Execute the SQL prepared statement to retrieve the records
			// The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();

			// Iterates through each record of the result set
			if (rs.next()) {
				grp.setTeamCode(rs.getString("teamCode"));
				grp.setTeamName(rs.getString("teamName"));
				grp.setTeamPic(rs.getString("teamPic"));
				System.out.println(grp.getTeamPic());
			}
			return grp;
		} catch (Exception e) {
			e.printStackTrace();
			return grp;
		}
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
	
	public Updates[] retrieveUpdates(String username) {

		// Updates update = new Updates();
		ArrayList<Updates> update = new ArrayList<Updates>();
		// SQL Statement
		String sql = "SELECT u.*,g.*,a.* FROM updates u INNER JOIN `group` g ON g.teamCode = u.recipient INNER JOIN `account` a ON a.fk_grpCode = g.teamCode WHERE a.accUsername = ?";

		try {

			// Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);

			// Execute the SQL prepared statement to retrieve the records
			// The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();

			// Iterates through each record of the result set
			while (rs.next()) {
				Updates upd = new Updates();
				upd.setSender(rs.getString("sender"));
				upd.setRecipient(rs.getString("recipient"));
				upd.setUpdateMsgId(rs.getInt("updateMsgId"));
				upd.setDate(rs.getString("date"));
				upd.setTitle(rs.getString("title"));
				upd.setContent(rs.getString("content"));
				upd.setPicture(rs.getString("picture"));

				update.add(upd);
			}
			return update.toArray(new Updates[update.size()]);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Updates[] retrieveStaffSentUpdates(String username) {

		// Updates update = new Updates();
		ArrayList<Updates> update = new ArrayList<Updates>();
		// SQL Statement
		String sql = "SELECT u.*,sa.* FROM updates u INNER JOIN `staffaccount` sa ON u.sender = sa.username WHERE sa.username = ?";

		try {

			// Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);

			// Execute the SQL prepared statement to retrieve the records
			// The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();

			// Iterates through each record of the result set
			while (rs.next()) {
				Updates upd = new Updates();
				upd.setSender(rs.getString("sender"));
				upd.setRecipient(rs.getString("recipient"));
				upd.setUpdateMsgId(rs.getInt("updateMsgId"));
				upd.setDate(rs.getString("date"));
				upd.setTitle(rs.getString("title"));
				upd.setContent(rs.getString("content"));
				upd.setPicture(rs.getString("picture"));

				update.add(upd);
			}
			return update.toArray(new Updates[update.size()]);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Updates retrieveIndividualUpdates(String username,
			String updateMessageId) {

		Updates update = new Updates();

		// SQL Statement
		String sql = "SELECT u.* FROM updates u INNER JOIN `group` g ON g.teamCode = u.recipient INNER JOIN `account` a ON a.fk_grpCode = g.teamCode WHERE u.updateMsgId = ? AND a.accUsername = ?";

		try {

			// Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, updateMessageId);
			ps.setString(2, username);

			// Execute the SQL prepared statement to retrieve the records
			// The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();

			// Iterates through each record of the result set
			if (rs.next()) {
				update.setSender(rs.getString("sender"));
				update.setRecipient(rs.getString("recipient"));
				update.setUpdateMsgId(rs.getInt("updateMsgId"));
				update.setDate(rs.getString("date"));
				update.setTitle(rs.getString("title"));
				update.setContent(rs.getString("content"));
				update.setPicture(rs.getString("picture"));
			}
			return update;
		} catch (Exception e) {
			e.printStackTrace();
			return update;
		}/*
			while (rs.next()) {
				Updates upd = new Updates();
				upd.setSender(rs.getString("sender"));
				upd.setRecipient(rs.getString("recipient"));
				upd.setUpdateMsgId(rs.getInt("updateMsgId"));
				upd.setDate(rs.getString("date"));
				upd.setTitle(rs.getString("title"));
				upd.setContent(rs.getString("content"));
				upd.setPicture(rs.getString("picture"));

			}
			return update.toArray(new Updates[update.size()]);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
	}

	public Registration[] retrieveGroupMemberDetails(String username) {

		// Create an array list for our participants of the same group
		ArrayList<Registration> grpMembers = new ArrayList<Registration>();

		// SQL Statement
		String sql = "SELECT p.*, a.* FROM account a INNER JOIN `participant` p ON a.fk_grpCode=p.groupCode WHERE a.accUsername= ?";

		try {

			// Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);

			// Execute the SQL prepared statement to retrieve the records
			// The records are returned as a ResultSet
			ResultSet rs = ps.executeQuery();

			// Iterates through each record of the result set
			while (rs.next()) {
				// Each record will be changed into a Java bean
				// rs.getXXX() methods retrieve a value from
				// a column in the current record
				Registration reg = new Registration();
				reg.setMemberName(rs.getString("teamMember"));
				reg.setGender(rs.getString("gender"));
				reg.setDOB(rs.getString("dob"));
				reg.setNric(rs.getString("nric"));
				reg.setTic(rs.getString("tic"));
				reg.setContactNo(rs.getString("contactNo"));
				reg.setEmailAdd(rs.getString("email"));
				reg.setFoodPre(rs.getString("foodpre"));
				reg.setFoodOtherCom(rs.getString("foodOtherCom"));
				reg.setAttendance(rs.getString("attendance"));
				reg.setLeaderStatus(rs.getString("leaderStatus"));

				// Each Java bean is then added to the array list "grpMembers"
				grpMembers.add(reg);

			}

			// Return the array list as a product array (Registration[])
			return grpMembers.toArray(new Registration[grpMembers.size()]);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// public int updateParticipantProfile(String nric, Registration reg){
	public int updateParticipantProfile(Registration reg) {
		
		int status = 0;
		
		// SQL Statement
		String sql = "UPDATE participant SET contactNo = ?, email = ?, foodpre = ?, foodOtherCom = ?, attendance = ? WHERE nric = '" + reg.getNric() + "'";

		try {
			// Use the connection to create a PreparedStatement object
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, reg.getContactNo());
			ps.setString(2, reg.getEmailAdd());
			ps.setString(3, reg.getFoodPre());
			ps.setString(4, reg.getFoodOtherCom());
			ps.setString(5, reg.getAttendance());
			status = ps.executeUpdate();
			System.out.println(status);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
