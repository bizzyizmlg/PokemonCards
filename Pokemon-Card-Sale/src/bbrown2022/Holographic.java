package bbrown2022;

public class Holographic extends Card{
	
	public String type;
	
	 Holographic(String name, int id, int health, double price, int amount) {
		 super(name, id, health, price, amount);
		
	}
	 Holographic(String type, String name, int id, int health, double price, int amount){
		 super(name, id, health, price, amount);
		 this.type = type;
	 }
	 public void setType(String type) {
		 this.type = type;
	 }
	 public String getType() {
		 return this.type;
	 }
	@Override
	public String toString() {
		return "Type: " + type + super.toString();
	}
	 

}
