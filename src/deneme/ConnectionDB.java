package deneme;

import java.sql.*;  
class ConnectionDB{  
	public static void main(String args[]){  
		try{  
			//The driver class loading
			Class.forName("oracle.jdbc.driver.OracleDriver");  
  
			//The connection object creation
			Connection con=DriverManager.getConnection(  
					"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","cns96");  
  
			//The statement object creation
			Statement stmt=con.createStatement();  
  
			//Query execution
			/*
			stmt.executeUpdate("INSERT INTO MOVIES " 
					+ "VALUES (1955, 90, 'Doctor at Sea', 'Comedy', 'Bogarde, Dirk', 'Bardot, Brigitte', 'Thomas, Ralph', 83, 'No', 'brigitteBardot.png')");
			*/
			ResultSet rs=stmt.executeQuery("SELECT * FROM MOVIES");  
			while(rs.next())  
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)
						+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)
						+"  "+rs.getString(7)+"  "+rs.getString(8)+"  "+rs.getString(9)
						+"  "+rs.getString(10));  
  
			//Close the connection object  
			
			con.close();  
  
		}catch(Exception e){ System.out.println(e);}  
	}  
}  