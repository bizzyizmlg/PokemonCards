package bbrown2022;

public class Vmax extends Card {
	
	public String type;

	Vmax(String name, int id, int health, double price, int amount) {
		super(name, id, health, price, amount);
		
	}
	Vmax(String type, String name, int id, int health, double price, int amount) {
		super(name, id, health, price, amount);
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Type: " + type + super.toString();
	}
	
	
}
