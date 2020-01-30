package Databse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {
		/*
		 * // Prova delle funzioni 
		 * createTable("Magazzino");
		 * InserisciArticoloNellaTabellaMagazzino("nome", 0, 0); get();
		 */

	}
	
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/giraffe";
			String username = "root";
			String password = "password";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println ("connected");
			return conn;
		} catch (Exception e) { System.out.println(e); }
		
	
		
		return null;
		
	}
	
	public static void CreaReport() {
		
	}

	public static void createTable(String tablename) throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS Magazzino (id int NOT NULL AUTO_INCREMENT,"
					+ "nome varchar(255), prezzo double, PRIMARY KEY (id))");
			create.executeUpdate();	
		} catch (Exception e) {System.out.println(e);}
		finally{System.out.println("Function Completed");}
	}
	
	public static void InserisciArticoloNellaTabellaMagazzino(String nome, int id, double prezzo) throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement posted = con.prepareStatement( " INSERT INTO Magazzino VALUES ('" +id+ "', '" +nome+ "', '" +prezzo+ "') ");
			posted.executeUpdate();
			
		} catch (Exception e) { System.out.println(e); }
	}
	
	public static ArrayList <String> get() throws Exception{
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM Magazzino");
			
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()) {
				int i=1;
				System.out.println(result.getString(i));
				array.add(result.getString(i));
				i++;
				}
			return array;
			
		} catch (Exception e) { System.out.println(e); }
		return null;
	}
	
	
	
	
}
