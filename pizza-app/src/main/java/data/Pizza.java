package data;


public class Pizza {
	private int id;
	private String name;
	private float birthyear;
	private String country;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	public float getBirthyear() {
		return birthyear;
	}
	public void setBirthyear(float birthyear) {
		this.birthyear = birthyear;
	}
	
	public void setBirthyear(String s) {
		try {
			this.birthyear = Float.parseFloat(s);
		}
		catch(NumberFormatException e) {
			birthyear=-1;
		}
	}
		
		
		
		
	
	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country =country;
	}
	
		
		
	
	
	public String toString() {
		return id+" "+name+" "+birthyear+" "+country+"\n";
	}
	
	
		
	
}