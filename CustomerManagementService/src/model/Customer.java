package model;

import java.sql.*;

public class Customer {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Lab", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertcustomer(String password, String name, String customerPhone, String email) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customerdetails (`customerID`,`customerPassword`,`customerName`,`customerPhone`,`customerEmail`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, customerPhone);
			preparedStmt.setString(5, email);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>customer Name</th>" + "<th>customer Phone</th>"
					+ "<th>customer Email</th>" + "<th>customer Password</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from customerdetails";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String ID = Integer.toString(rs.getInt("customerID"));
				String Password = rs.getString("customerPassword");
				String Name = rs.getString("customerName");
				String Phone = rs.getString("customerPhone");
				String Email = rs.getString("customerEmail");

				output += "<tr><td>" + Name + "</td>";
				output += "<td>" + Phone + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Password + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='customer.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='customerID' type='hidden' value='" + ID + "'>" + "</form></td></tr>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCustomer(String ID, String password, String name, String phone, String email)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE customerdetails SET customerPassword=?,customerName=?,customerPhone=?,customerEmail=? WHERE customerID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, password);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, phone);
			preparedStmt.setString(4, email);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCustomer(String ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from customerdetails where customerID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
