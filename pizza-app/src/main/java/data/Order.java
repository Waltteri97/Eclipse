package data;


public class Order {
	private int id;
	private String pizza;
	private float price;
	private String restaurant;
	private String first_name;
	private String last_name;
	private String email;
	private String phonenumber;
	private String address;

	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPizza() {
		return pizza;
	}
	public void setPizza(String pizza) {
		this.pizza = pizza;
	}
	
	public String getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	
	
	
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setPrice(String s) {
		try {
			this.price = Float.parseFloat(s);
		}
		catch(NumberFormatException e) {
			price=-1;
		}
	}
		

	public String getFirstname() {
		return first_name;
	}
	public void setFirstname(String first_name) {
		this.first_name = first_name;
	}
	
	public String getLastname() {
		return last_name;
	}
	public void setLastname(String last_name) {
		this.last_name = last_name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	


	
	
	public String toString() {
		return id+" "+pizza+" "+price+" "+first_name+" "+last_name+" "+email+" "+phonenumber+" "+address+" "+restaurant+"\n";
	}
	
	
	
		
	
}