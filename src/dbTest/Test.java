package dbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.BugReport;

public class Test {
private static Connection conn = null;
	
	public static void connect() throws Exception
	{
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:./DB/ZXING","sa","");
//		System.out.println("-------- CONNECT WITH "+Property.getInstance().getTargetProduct()+" DB ----------");;

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
			ResultSet rs = q.executeQuery("SELECT * from Initial_BUG_REPORT");
			while(rs.next()){
				BugReport bugReport = new BugReport();
				bugReport.setBugID(rs.getInt("BUG_ID"));
				bugRepository.add(bugReport);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR: GET DUP BUG ID LIST");
		}
		return (ArrayList<BugReport>) bugRepository.clone();
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		connect();
		
		Statement q = conn.createStatement();
		ResultSet rs = q.executeQuery("SELECT bug_id FROM BUG_INFO");
	
		while(rs.next()){
			System.out.println(rs.getInt("bug_id"));
		}
		
	}

}
