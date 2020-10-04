package services;


import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.appengine.api.utils.SystemProperty;
import java.sql.Statement;
import conn.Connections;
import data.Pizza;
import data.Order;



@Path("/pizzaservice")
public class PizzaService {
	
	
	
	   @POST
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes("application/x-www-form-urlencoded")
       @Path("/addpizza")
	   public Pizza addDogBreedByPost(@FormParam("name") String name, @FormParam("birthyear") float birthyear, @FormParam("country") String country) {
		   Pizza pizza=new Pizza();
		   pizza.setName(name);
		   pizza.setBirthyear(birthyear);
		   pizza.setCountry(country);
		   String sql="insert into cloud(name, birthyear, country) values(?,?,?)";
		   
		 
		   
	   Connection conn=null;   
	   try {
		   if(SystemProperty.environment.value()  == SystemProperty.Environment.Value.Production) {
			   conn = Connections.getProductionConnection();
		   }
		   else {
			   conn = Connections.getDevConnection();
		   }
		  
	   }   catch (Exception e) {
		   
		   e.printStackTrace();
	   }
	   PreparedStatement pstmt;
	   try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, name);
		   pstmt.setFloat(2, birthyear);
		   pstmt.setString(3, country);
		   pstmt.execute();
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   return pizza;
	   
	   
	   
	   }
	   
	   
	   @POST
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes("application/x-www-form-urlencoded")
       @Path("/addorder")
	   public Order addOrderByPost(@FormParam("pizza") String pizza, @FormParam("price") float price, @FormParam("first_name") String first_name, @FormParam("last_name") String last_name, @FormParam("email") String email, @FormParam("phonenumber") String phonenumber, @FormParam("address") String address) {
		   Order order=new Order();
		   order.setPizza(pizza);
		   order.setPrice(price);
		   order.setFirstname(first_name);
		   order.setLastname(last_name);
		   order.setEmail(email);
		   order.setPhonenumber(phonenumber);
		   order.setAddress(address);
		   String sql="insert into orders(pizza, price, first_name, last_name, email, phonenumber, address) values(?,?,?,?,?,?,?)";
		   
		 
		   
	   Connection conn=null;   
	   try {
		   if(SystemProperty.environment.value()  == SystemProperty.Environment.Value.Production) {
			   conn = Connections.getProductionConnection();
		   }
		   else {
			   conn = Connections.getDevConnection();
		   }
		  
	   }   catch (Exception e) {
		   
		   e.printStackTrace();
	   }
	   PreparedStatement pstmt;
	   try {
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, pizza);
		   pstmt.setFloat(2, price);
		   pstmt.setString(3, first_name);
		   pstmt.setString(4, last_name);
		   pstmt.setString(5, email);
		   pstmt.setString(6, phonenumber);
		   pstmt.setString(7, address);

		   pstmt.execute();
	   } catch (SQLException e) {
		   e.printStackTrace();
	   }
	   return order;
	   
	   
	   
	   }
}