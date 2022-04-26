<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@page import="model.Payment" %>


<%
if (request.getParameter("firstname") != null)
{
	 Payment Obj = new Payment();
	 String stsMsg="";
	if (request.getParameter("hidItemIDSave") == "")
	 {
	 
	 stsMsg = Obj.insertPayment(request.getParameter("firstname"),
	 request.getParameter("lastname"),
	 request.getParameter("amount"),
	 request.getParameter("mobile"),
	 request.getParameter("PaymentMethod"));
	 
	 } else{
		 stsMsg =Obj.updatePayment(request.getParameter("hidItemIDSave"),
				         request.getParameter("firstname"),
						 request.getParameter("lastname"),
						 request.getParameter("amount"),
						 request.getParameter("mobile"),
						 request.getParameter("PaymentMethod"));
		 
		
	 }
	 session.setAttribute("statusMsg", stsMsg);
}

//Delete item----------------------------------
if (request.getParameter("hidItemIDDelete") != null)
{
Payment Obj = new Payment();
String stsMsg = Obj.deletePayment(request.getParameter("hidItemIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}
%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Details</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<script>
	function validate() {
		var CardHolderFirstname = document.form.firstname.value;
		var CardHolderLastname = document.form.lastname.value;
		var Amount = document.form.amount.value;
		var mobile = document.form.mobile.value;
		var PaymentMethod = document.form.Payment Method.value;

		if (CardHolderfirstname == null || CardHolderfirstname == "") {
			alert("Full Name can't be blank");
			return false;
		} else if (CardHolderlastname == null || CardHolderlastname == "") {
			alert("Last can't be blank");
			return false;
		} else if (Total Amount == null || Total Amount == "") {
			alert("amount can't be blank");
			return false;
		} else if (mobile == null || mobile == "") {
			alert("mobile number can't be blank");
			return false;
		} else if (Payment Method == null || Payment Method == "") {
			alert("Payment Method can't be blank");
			return false;
		} 
	}
</script>
<body>
<center>
		<h2>Payment Form</h2>
	</center>
	<form name="form" action="PaymentRegister.jsp" method="post"
		onsubmit="return validate()">
		<table align="center">
			<tr>
				<td><label for="Firstname">CardHolder's Firstname</label></td>
				<td><input type="text" class="form-control" name="firstname" /></td>
			</tr>
			<tr>
				<td><label for="lastname">CardHolder's Lastname</label></td>
				<td><input type="text" class="form-control" name="lastname" /></td>
			</tr>
			<tr>
				<td><label for="amount">Total Amount</label></td>
				<td><input type="text" class="form-control" name="amount" /></td>
			</tr>
			<tr>
				<td><label for="mobile">Mobile Number</label></td>
				<td><input type="text" class="form-control" name="mobile" /></td>
			</tr>
			<tr>
				<td><label for="PaymentMethod">Payment Method</label></td>
				<td><input type="text" class="form-control" name="PaymentMethod" /></td>
			</tr>
			
			
			<tr>
				<td><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></td>
			</tr>
			<tr>
				<td></td>
				<td><br>
				<input class="btn btn-success"  name="btnSubmit" type="submit" value="Pay"></input> &nbsp; &nbsp; &nbsp; &nbsp;
				<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
			</tr>
			<tr>
				<br>
				<br>
					</tr>
		</table>
	</form>
</body>
</html>