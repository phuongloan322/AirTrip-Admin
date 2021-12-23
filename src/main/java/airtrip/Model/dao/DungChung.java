package airtrip.Model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DungChung {
	public Connection cn;
    public void KetNoi() throws Exception{
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("Da xac dinh!");
		String url="jdbc:sqlserver://DESKTOP-E754EJE\\SQLEXPRESS:1433;databaseName=AirTrip;user=sa; password=123456";
    	cn= DriverManager.getConnection(url);
    	System.out.println("Da ket noi");
    }
}
