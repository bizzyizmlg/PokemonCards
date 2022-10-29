package bbrown2022;

public class Card {

	private String name;
	private double price;
	private int idCard;
	private int health;
	private int amount;

	Card(int idCard, String name, double price, int health, int amount) {
		super();
		this.idCard = idCard;
		this.name = name;
		this.health = health;
		this.price = price;
		this.amount = amount;
	}

	public Card(String name2, int id, int health2, double price2, int amount2) {
		// TODO Auto-generated constructor stub
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setId(int idCard) {
		this.idCard = idCard;
	}

	public int getId() {
		return this.idCard;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmount() {
		return this.amount;
	}

	@Override
	public String toString() {
		return "\n" + "NAME: " + name + "\n"+ "PRICE: $" + price  + "\n"+ "ID#: " + idCard + "\n" + "HEALTH: " + health + "\n" + "Stock: " + amount + "\n" + "================";
	}

}
