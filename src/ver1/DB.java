package ver1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.BugReport;
import common.Property;
import common.StructuredBugReport;

public class DB {
private Connection conn = null;
	
	DB() throws Exception
	{
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:./DB/ZXING","sa","");
		System.out.println("-------- CONNECT WITH  DB ----------");;

	}		
	
	public Connection getConn()
	{
		return conn;
	}

	

	public ArrayList<BugReport> getBugReports() {
		ArrayList<BugReport> bugRepository = new ArrayList<BugReport>();
		try
		{
			Statement q = conn.createStatement();
			ResultSet rs = q.executeQuery("SELECT * FROM BUG_INFO");
			while(rs.next()){
				BugReport bugReport = new BugReport();
				bugReport.setBugID(rs.getInt("BUG_ID"));
				bugReport.setReporter(rs.getString("BUG_AUT"));
				bugReport.setProduct(rs.getString("PRD_NAME"));
				bugReport.setComponent(rs.getString("COMP_NAME"));
				bugReport.setProductVer(rs.getString("PRD_VER"));
				bugReport.setHardware(rs.getString("BUG_HW"));
				bugReport.setAssignee(rs.getString("BUG_ASSIGNEE"));
				bugReport.setOpenDate(String.valueOf(rs.getDate("BUG_OPEN_DATE")));
				bugReport.setModifiedDate(String.valueOf(rs.getDate("BUG_MODIFY_DATE")));
				bugReport.setStatus(rs.getString("BUG_STATUS"));
				bugReport.setPriority(rs.getString("BUG_PRIOR"));
				bugReport.setSever(rs.getString("BUG_SEVER"));
				bugReport.setSummury(rs.getString("BUG_SUM"));
				bugReport.setDescription(rs.getString("BUG_DES"));
				bugRepository.add(bugReport);
			}
			
		}
		catch(Exception e)
		{
			//System.out.println("ERROR: GET DUP BUG ID LIST");
		}
		return (ArrayList<BugReport>) bugRepository.clone();
	}
	public BugReport getBugReport(String bugID) {
		BugReport bugReport = new BugReport();
		try
		{
			Statement q = conn.createStatement();
			ResultSet rs = q.executeQuery("SELECT * FROM BUG_INFO WHERE BUG_ID = "+bugID);
			while(rs.next()){
				bugReport.setBugID(rs.getInt("BUG_ID"));
				bugReport.setReporter(rs.getString("BUG_AUT"));
				bugReport.setProduct(rs.getString("PRD_NAME"));
				bugReport.setComponent(rs.getString("COMP_NAME"));
				bugReport.setProductVer(rs.getString("PRD_VER"));
				bugReport.setHardware(rs.getString("BUG_HW"));
				bugReport.setAssignee(rs.getString("BUG_ASSIGNEE"));
				bugReport.setOpenDate(String.valueOf(rs.getDate("BUG_OPEN_DATE")));
				bugReport.setModifiedDate(String.valueOf(rs.getDate("BUG_MODIFY_DATE")));
				bugReport.setStatus(rs.getString("BUG_STATUS"));
				bugReport.setPriority(rs.getString("BUG_PRIOR"));
				bugReport.setSever(rs.getString("BUG_SEVER"));
				bugReport.setSummury(rs.getString("BUG_SUM"));
				bugReport.setDescription(rs.getString("BUG_DES"));
			}
			
		}
		catch(Exception e)
		{
			//System.out.println("ERROR: GET DUP BUG ID LIST");
		}
		return bugReport; 
	}
	
	
	public ArrayList<String> getBugIDs() {
		ArrayList<String> bugIdList = new ArrayList<String>();
		try
		{
			Statement q = conn.createStatement();
			ResultSet rs = q.executeQuery("SELECT BUG_ID FROM BUG_INFO");
			while(rs.next()){
				bugIdList.add(String.valueOf(rs.getInt("BUG_ID")));			
			}
			System.out.println(bugIdList);
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR: GET  BUG ID LIST");
		}
		return (ArrayList<String>) bugIdList.clone();
	}
	
}
