package data;

public class Menu {
	private int id;
	private String pizzaname;
	private String tayte1;
	private String tayte2;
	private String tayte3;
	private double price;
	private String restaurant;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPizzaname() {
		return pizzaname;
	}
	public void setPizzaname(String pizzaname) {
		this.pizzaname = pizzaname;
	}
	public String getTayte1() {
		return tayte1;
	}
	public void setTayte1(String tayte1) {
		this.tayte1 = tayte1;
	}
	public String getTayte2() {
		return tayte2;
	}
	public void setTayte2(String tayte2) {
		this.tayte2 = tayte2;
	}
	public String getTayte3() {
		return tayte3;
	}
	public void setTayte3(String tayte3) {
		this.tayte3 = tayte3;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
}