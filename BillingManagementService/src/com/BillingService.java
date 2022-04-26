package com;
import model.Billing;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Billing")

public class BillingService {
	Billing Obj = new Billing();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return Obj.readItems();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertbill(@FormParam("bill_number") String bill_number,
	 @FormParam("prev_read") String prev_read,
	 @FormParam("cur_read") String cur_read,
	 @FormParam("bill_amount") String bill_amount,
	@FormParam("due_amount") String due_amount,
    @FormParam("billing_date") String billing_date,
     @FormParam("user_id") String user_id)
	{
	 String output = Obj.insertbill(bill_number, prev_read, cur_read, bill_amount, due_amount, billing_date, user_id);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatebill(String billData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(billData).getAsJsonObject();
	//Read the values from the JSON object
	 String bill_id = Object.get("bill_id").getAsString();
	 String bill_number = Object.get("bill_number").getAsString();
	 String prev_read = Object.get("prev_read").getAsString();
	 String cur_read = Object.get("cur_read").getAsString();
	 String bill_amount = Object.get("bill_amount").getAsString();
	 String due_amount = Object.get("due_amount").getAsString();
	 String billing_date = Object.get("billing_date").getAsString();
	 String user_id = Object.get("user_id").getAsString();
	 String output = Obj.updatebill(bill_id, bill_number, prev_read, cur_read, bill_amount, due_amount, billing_date, user_id);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletebill(String billData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String bill_id = doc.select("bill_id").text();
	 String output = Obj.deletebill(bill_id);
	return output;
	}

	
}
