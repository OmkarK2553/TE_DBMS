//import java.sql.DriverManager;
//import java.sql.Connection;
//import java.sql.SQLException;

import java.io.*;
import java.util.Scanner;
import java.sql.*;

public class Mysql_Conn {
	
	Scanner sc = new Scanner(System.in);
	
	
	void Display() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms_prac","root","Omkar@25");
			
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from jdbc_stud");
			
			System.out.println("Rollno    "+"Name    "+"Class");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"        "+rs.getString(2)+"        "+rs.getString(3));
			}
			rs.close();
			stmt.close();
			connection.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	void Insert(int rn,String n,String c) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms_prac","root","Omkar@25");
			
			String query = "insert into jdbc_stud values("+rn+",'"+n+"','"+c+"')";
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			System.out.println("Data inserted successfully!");
			
			pstmt.execute();
			
			pstmt.close();
			connection.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void Update(int rn) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms_prac","root","Omkar@25");
			
			Statement stmt = connection.createStatement();
//			Scanner sc = new Scanner(System.in);
			
			System.out.println("Which field is to be updated: ");
			System.out.println("1) Roll no");
			System.out.println("2) Name");
			System.out.println("3) Class");
			int u_ch;
			u_ch = sc.nextInt();
			
			switch(u_ch) {
			case 1:
				int to_u_roll;
				System.out.println("Enter new roll: ");
				to_u_roll = sc.nextInt();
				stmt.executeUpdate("update jdbc_stud set rollno="+to_u_roll+" where rollno="+rn);
				break;
			case 2:
				String to_u_name;
				System.out.println("Enter new name: ");
				to_u_name = sc.next();
				stmt.executeUpdate("update jdbc_stud set name='"+to_u_name+"' where rollno="+rn);
				break;
			case 3:
				String to_u_class;
				System.out.println("Enter mew class: ");
				to_u_class = sc.next();
				stmt.executeUpdate("update jdbc_stud set class='"+to_u_class+"' where rollno="+rn);
				break;
			}
			
			System.out.println("Data updated successfully!");
			
//			sc.close();
			stmt.close();
			connection.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	void Delete(int rn) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms_prac","root","Omkar@25");
			
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("delete from jdbc_stud where rollno="+rn);
			
			System.out.println("Data deleted successfully!");
			
			stmt.close();
			connection.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String url = "jdbc:mysql://localhost:3306/dbms_prac";
//		String username="root";
//		String password="Omkar@25";
//		
//		try {
//			Connection connection = DriverManager.getConnection(url, username,password);
//			
//			System.out.println("Connected to the database");
//		}
//		catch(SQLException e) {
//			System.out.println("Error!");
//			e.printStackTrace();
//		}	
		
		
		
		Scanner sc = new Scanner(System.in);
		Mysql_Conn obj = new Mysql_Conn();
		
		int ch;
		do {
		
			System.out.println("Enter the operation you want to perform: ");
			System.out.println("1) Insert");
			System.out.println("2) Display");
			System.out.println("3) Update");
			System.out.println("4) Delete");
			System.out.println("5) Exit");
			
			ch = sc.nextInt();
			
			switch(ch) {
				
			case 1:
				int sroll;
				String sname,sclass;
				
				System.out.println("Enter the rollno, name and class of the student: ");
				sroll = sc.nextInt();
				sname = sc.next();
				sclass = sc.next();
				
				obj.Insert(sroll, sname, sclass);
				break;
				
			case 2:
				obj.Display();
				break;
				
			case 3:
				int uroll;
				System.out.println("Enter the rollno for which data is to be updated: ");
				uroll = sc.nextInt();
				obj.Update(uroll);
				break;
			case 4:
				int droll;
				System.out.println("Enter the rollno for which data is to be deleted: ");
				droll = sc.nextInt();
				obj.Delete(droll);
				break;
			}
		}while(ch!=5);
		
		System.out.println("Thank you!");
		sc.close();
	}

}
