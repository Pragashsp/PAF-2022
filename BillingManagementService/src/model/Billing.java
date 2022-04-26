package model;
import java.sql.*;
public class Billing {
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bill", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertbill(String bill_number, String prev_read, String cur_read, String bill_amount, String due_amount, String billing_date, String user_id )
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for inserting."; 
		 }
		 
		 // create a prepared statement
		 String query = " insert into bill (`bill_id`,`bill_number`,`prev_read`,`cur_read`,`bill_amount`, `due_amount`, `billing_date`, `user_id`)" + " values (?, ?, ?, ?, ?,?,?,?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, bill_number);
		 preparedStmt.setString(3, prev_read);
		 preparedStmt.setString(4, cur_read);
		 preparedStmt.setString(5, bill_amount);
		 preparedStmt.setString(6, due_amount);
		 preparedStmt.setString(7, billing_date);
		 preparedStmt.setString(8, user_id);
		// execute the statement
		
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String readItems()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
			 
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>bill_number</th>" +
		 "<th>prev_read</th>" +
		 "<th>cur_read</th>" +
		 "<th>bill_amount</th>"+
		 "<th>due_amount</th>"+
		 "<th>billing_date</th>"+
		 "<th>user_id</th>"+
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from bill";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String bill_id = Integer.toString(rs.getInt("bill_id"));
		 String bill_number = rs.getString("bill_number");
		 String prev_read = rs.getString("prev_read");
		 String cur_read =rs.getString("cur_read");
		 String bill_amount = rs.getString("bill_amount");
		 String due_amount = rs.getString("due_amount");
		 String billing_date = rs.getString("billing_date");
		 String user_id = rs.getString("user_id");
		 
		 
		 output += "<tr><td>" + bill_number + "</td>";
		 output += "<td>" + prev_read + "</td>";
		 output += "<td>" + cur_read + "</td>";
		 output += "<td>" + bill_amount + "</td>";
		 output += "<td>" + due_amount + "</td>";
		 output += "<td>" + billing_date + "</td>";
		 output += "<td>" + user_id + "</td>";
		
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='customer.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='bill_id' type='hidden' value='" + bill_id
				 + "'>" + "</form></td></tr>";
				 }
				 con.close();
				
				 output += "</table>";
				 } 
		 catch (Exception e)
		 {
		 output = "Error while reading the Datas.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updatebill(String bill_id, String bill_number, String prev_read, String cur_read, String bill_amount, String due_amount, String billing_date, String user_id )
		
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE bill SET bill_number=?,prev_read=?, cur_read=?, bill_amount=?, due_amount=?, billing_date=?, user_id=? WHERE bill_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 
		 preparedStmt.setString(1, bill_number);
		 preparedStmt.setString(2, prev_read);
		 preparedStmt.setString(3, cur_read);
		 preparedStmt.setString(4, bill_amount);
		 preparedStmt.setString(5, due_amount);
		 preparedStmt.setString(6, billing_date);
		 preparedStmt.setString(7, user_id);
		 preparedStmt.setInt(8, Integer.parseInt(bill_id));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.out.println(e.getMessage());
		 }
		 return output;
		 }
		public String deletebill(String bill_id)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from bill where bill_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(bill_id));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.out.println(e.getMessage());
		 }
		 return output;
		 }
		} 

