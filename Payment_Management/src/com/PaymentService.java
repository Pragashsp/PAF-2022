package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Payment;
//service
@Path("/Payment")
public class PaymentService {
	
	
		Payment Obj = new Payment();

		@GET
	    @Path("/")
	    @Produces(MediaType.TEXT_HTML)
		public String readPayment(){
	 
			return Obj.readPayment();
	 
		}
		
		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(@FormParam("CardHolderFirstname") String CardHolderFirstname,@FormParam("CardHolderLastname") String CardHolderLastname,@FormParam("Amount") String Amount,@FormParam("mobile") String mobile,@FormParam("PaymentMethod") String PaymentMethod) {
			String output = Obj.insertPayment(CardHolderFirstname, CardHolderLastname, Amount, mobile, PaymentMethod);
			return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String PaymentData)
		{
		//Convert the input string to a JSON object
		 JsonObject Object = new JsonParser().parse(PaymentData).getAsJsonObject();
		//Read the values from the JSON object
		//ID CardHolderFirstname CardHolderLastname Amount mobile PaymentMethod
		 String ID = Object.get("ID").getAsString();
		 String CardHolderFirstname = Object.get("CardHolderFirstname").getAsString();
		 String CardHolderLastname = Object.get("CardHolderLastname").getAsString();
		 String Amount = Object.get("Amount").getAsString();
		 String mobile = Object.get("mobile").getAsString();
		 String PaymentMethod = Object.get("PaymentMethod").getAsString();
		 String output = Obj.updatePayment(ID, CardHolderFirstname, CardHolderLastname, Amount, mobile, PaymentMethod);
		
		 return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String PaymentData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String ID = doc.select("ID").text();
		 String output = Obj.deletePayment(ID);
		return output;
		}


}
