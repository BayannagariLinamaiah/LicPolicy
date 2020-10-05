package com.nttdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Policy {
	//Scanner for taking input from the users.
	static Scanner scan=new Scanner(System.in);
	
	//JDBC driver name and driver url.
	static final String Driver="com.mysql.jdbc.Driver";
	static final String Url="jdbc:mysql://localhost:3306/ntt";
	
	//Database user name and password.
	static final String USER="root";
	static final String PASS="Linga32@";
	
	//Establishing the connection and statement.
	static Connection con=null;
	static PreparedStatement pst=null;
	//Insert operation method.
	public static int Insert()
	{
		int rowInsert=0;

		try {
			String Policyname,Policyholdername,Preimiumtype;
			int Policynumber;
			float Premiumamount;
			String Policystartdate;
			
			//Register database.
			Class.forName(Driver);
			
			//Open a connection.
			System.out.println("Establishing connection...");
			con=DriverManager.getConnection(Url,USER,PASS);
			
			//Taking input operation from user.
			System.out.println("Enter the Policy number.");
			Policynumber=scan.nextInt();
			System.out.println("Enter the Policy name.");
			Policyname=scan.next();
			System.out.println("Enter the Holder name.");
			Policyholdername=scan.next();
			System.out.println("Enter the Start date.(in YYYY_MM_DD)");
			Policystartdate=scan.next();
			System.out.println("Enter the Premium amount.");
			Premiumamount=scan.nextFloat();
			System.out.println("Enter the premium type.(In Yearly/half/Quarteyly Year)");
			Preimiumtype=scan.next();
			
			
			//Queury for insert operation.
			String sql="insert into LIC(Policynumber,Policyname,Policyholdername,Policystartdate,Premiumamount,Premiumtype) values(?,?,?,?,?,?)";
			
			//creating statement.
			pst=con.prepareStatement(sql);
			
			//setting values to column.
			pst.setInt(1, Policynumber);
			pst.setString(2, Policyname);
			pst.setString(3, Policyholdername);
			pst.setString(4, Policystartdate);
			pst.setFloat(5, Premiumamount);
			pst.setString(6, Preimiumtype);
			
			//updating the operation.
			rowInsert=pst.executeUpdate();
			if(rowInsert>0)
			{
				System.out.println("Data inserted sucessfully...");
			}
			else
				
			{
				System.out.println("Failed to insert the data....Please try again.");
			}
			//Clean up enviorment.
			con.close();
			pst.close();
			
		}
		catch(Exception e)
		{
			//Handling the JDBC Exception.
			System.out.println(e);
		}
		return rowInsert;
	}
	
	public static void Fetch()
	{
		//Getting policynumber to update.
		int Policynumber;
		try {
			Class.forName(Driver);
			con=DriverManager.getConnection(Url,USER,PASS);
			System.out.println("Enter the policy number to fetch");
			Policynumber=scan.nextInt();
			
			//Query to fetch the data using policynumber.
			String sql="select * from LIC where Policynumber=?";
			PreparedStatement pst=con.prepareStatement(sql);
			
			//setting the value to column.
			pst.setInt(1, Policynumber);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				//Retrieve by column name.
				int policyno=rs.getInt(1);
				String pname=rs.getString(2);
				String phname=rs.getString(3);
				String sdate=rs.getString(4);
				float amount=rs.getFloat(5);
				String ptype=rs.getString(6);
				
				//Display by name.
				System.out.println("Booking details..");
				System.out.println("Policy number: "+policyno);
				System.out.println("Policy name: "+pname+"\nPolicy holder name is : "+phname);
				System.out.println("Policy Start date is: "+sdate );
				System.out.println("Policy Type:"+ptype+"\nPolicy Amount: "+amount );
				System.exit(0);
				
			}
			System.out.println("Wrong credentilas....");
			
			//Clean-up environment.
			con.close();
			pst.close();
		}
		catch(Exception e)
		{
			//Handling exception occured.
			System.out.println(e);
		}
		
	}
	
	public static int Delete()
	{
		int rowDelete=0,Policynumber;
		try {
			Class.forName(Driver);
			con=DriverManager.getConnection(Url,USER,PASS);
			
			//Getting policy number to delete.
			System.out.println("Enter the policy number to delete the details");
			Policynumber=scan.nextInt();
			
			//Query for delete operation.
			String sql="delete from LIC where Policynumber=?";
			//Creating prepared statement.
			PreparedStatement pst=con.prepareStatement(sql);
			//deleting the value by Policynumber.
			pst.setInt(1, Policynumber);
			rowDelete=pst.executeUpdate();
			if(rowDelete>0)
			{
				System.out.println("Data is deleted successfully....");
			}
			else
			{
				System.out.println("Not deleted data successfully....");
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return rowDelete;
	}
	
	public static void Update()
	{
		//update using by Policynumber,Premiumamount.
		int Policynumber,rowDelete=0;
		float Premiumamount;
		try {
			Class.forName(Driver);
			//Establishing the connection.
			con=DriverManager.getConnection(Url,USER,PASS);
			System.out.println("Enter the policy number you want to update");
			Policynumber=scan.nextInt();
			System.out.println("Re-enter the premium amount ");
			Premiumamount=scan.nextFloat();
			String sql="update LIC set Premiumamount=200 where Policynumber=1";
			PreparedStatement pst=con.prepareStatement(sql);
			rowDelete=pst.executeUpdate();
			if(rowDelete>0)
			{
				System.out.println("Details are updated successfully!!!!!");
			}
			else
			{
				System.out.println("Details are failed to update....");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}	
}
