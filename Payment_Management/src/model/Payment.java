package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	
	private Connection connect()
	 {
	 
		Connection con = null;
	
	try{
	    Class.forName("com.mysql.jdbc.Driver");

	    //Provide the correct details: DBServer/DBName, username, password
	    con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
	    
	 }catch (Exception e){
		 e.printStackTrace();}
	     return con;
	 } 
	
	public String insertPayment(String CardHolderFirstname, String CardHolderLastname, String Amount, String mobile,String PaymentMethod)
	 {
	
		String output = "";
	 
		try{
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for inserting.";
				}
	 
			// create a prepared statement
	
			String query = " INSERT INTO `payment`(`ID`, `CardHolderFirstname`, `CardHolderLastname`, `Amount`, `mobile`, `PaymentMethod`)  VALUES  (?, ?, ?,?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	        // binding values
	        preparedStmt.setInt(1, 0);
	        preparedStmt.setString(2, CardHolderFirstname);
	        preparedStmt.setString(3,CardHolderLastname );
	        preparedStmt.setString(4, Amount);
	        preparedStmt.setString(5, mobile);
	        preparedStmt.setString(6, PaymentMethod);
	        // execute the statement
	
	        preparedStmt.execute();
	        con.close();
	        output = "Inserted successfully";
	
		}catch (Exception e){
	 
			output = "Error while inserting the item.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String readPayment(){
	 
		String output = "";
	 
		try{
	
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for reading."; }
	 
			// Prepare the html table to be displayed
	
			output = "<table border='1'><tr><th>CardHolder Firstname</th><th>CardHolder Lastname</th>" +
	                "<th>Amount</th>" +
	                "<th>mobile</th>" +
	                "<th>PaymentMethod</th><th>Update</th><th>Remove</th></tr>";

	 
			String query = "SELECT `ID`, `CardHolderFirstname`, `CardHolderLastname`, `Amount`, `mobile`, `PaymentMethod` FROM `payment`";
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        // iterate through the rows in the result set
	     
	        while (rs.next()){
	        	 //ID CardHolderFirstname CardHolderLastname Amount mobile PaymentMethod
	            String ID=rs.getString("ID");
	        	String CardHolderFirstname = rs.getString("CardHolderFirstname");
	            String CardHolderLastname = rs.getString("CardHolderLastname");
	            String Amount = rs.getString("Amount");
	            String mobile = rs.getString("mobile");
	            String PaymentMethod = rs.getString("PaymentMethod");
	            
	            // Add into the html table
	
	            output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + ID + "'>"
	            		 + CardHolderFirstname + "</td>";
	            output += "<td>" + CardHolderLastname + "</td>";
	            output += "<td>" + Amount + "</td>";
	            output += "<td>" + mobile + "</td>";
	            output += "<td>" + PaymentMethod + "</td>";
	
	            // buttons
	           output +="<td><input name='btnUpdate' type='button' value='Update' class='btn btn-success'></td>"
	        			 + "<td><form method='post' action='employee.jsp'>"
	        			 +"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	        			 + "<input name='hidItemIDDelete' type='hidden' value='" + ID + "'> </form></td></tr>";
	  
	        }
	 
	        con.close();
	        // Complete the html table
	        output += "</table>";
	 
		}catch (Exception e){
	 
			output = "Error while reading the items.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String updatePayment(String ID ,String CardHolderFirstname ,String cardHolderLastname, String Amount ,String mobile  ,String PaymentMethod){
	 
		String output = "";
	 
		try{
	 
			Connection con = connect();
	 
			if (con == null){
				return "Error while connecting to the database for updating.";
				}
	 
			// create a prepared statement
	        String query = "UPDATE `payment` SET `CardHolderFirstname`=? ,`CardHolderLastname`=?,`Amount`=?,`mobile`=?,`PaymentMethod`=? WHERE `ID`=?";
	        PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	        // binding values
	        //ID CardHolderFirstname CardHolderLastname Amount mobile PaymentMethod
	        preparedStmt.setString(1, CardHolderFirstname);
	        preparedStmt.setString(2, cardHolderLastname);
	        preparedStmt.setString(3, Amount);
	        preparedStmt.setString(4, mobile);
	        preparedStmt.setString(5, PaymentMethod);
	        preparedStmt.setInt(6, Integer.parseInt(ID));
	
	        // execute the statement
	        preparedStmt.execute();
	        con.close();
	        output = "Updated successfully";
	 
		}catch (Exception e){
	 
			output = "Error while updating the item.";
	        System.err.println(e.getMessage());
	 
		}
	
		return output;
	 } 
	
	public String deletePayment(String ID){
	 
		String output = "";
	
		try {
	 
			Connection con = connect();
	
			if (con == null){
				return "Error while connecting to the database for deleting.";
				}
	
			 // create a prepared statement
	         String query = "DELETE FROM `payment` WHERE `ID`=?";
	         PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	         // binding values
	         preparedStmt.setInt(1, Integer.parseInt(ID));
	 
	         // execute the statement
	         preparedStmt.execute();
	         con.close();
	         output = "Deleted successfully";
	
		}catch (Exception e){
			
	 
			output = "Error while deleting the item.";
	        System.err.println(e.getMessage());
	
		}
	
		return output;
	 } 
	
}
