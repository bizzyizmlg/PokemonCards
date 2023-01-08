package bbrown2022;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Start {

	static Scanner sc = new Scanner(System.in);
	public ArrayList<Card> cards = new ArrayList<>();
	
	private static dataBase con;
	dataBase db = new dataBase();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Welcome to Bizzy's Pokemon Card Collection!");
		System.out.println("================");
		con = new dataBase();
		Start s = new Start();
		s.login();

	}

	@SuppressWarnings("unchecked")
	public void viewAllCards() {
		

		dataBase db = new dataBase();
		String sql = "SELECT * FROM card";

		try {
			PreparedStatement ps = db.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int idCard = rs.getInt("idCard");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int health = rs.getInt("health");
				int amount = rs.getInt("amount");

				cards.add(new Card(idCard, name, price, health, amount));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

//		Holographic holo = new Holographic("Holographic", "Raichu", 50203, 130, 9.99, 1);
//		System.out.println(holo.toString());
//		Holographic holo2 = new Holographic("Holographic", "Toxtricity", 63189, 120 ,4.99, 1);
//		System.out.println(holo2.toString());
//		
//		
//		V vcard1 = new V("V", "Melmetal", 47078, 220, 11.99, 1);
//		System.out.println(vcard1.toString());
//		

		for (Card c : cards) {
			ArrayList<Card> pCards = new ArrayList<Card>();
			for (Card i : pCards) {
				System.out.println(i);
			}
			System.out.println(c);
		}

	}

	public void login() throws Exception {

		System.out.println("Main Menu: ");
		System.out.println("===============");
		System.out.println("1) View All Cards");
		System.out.println("2) Add a Card");
		System.out.println("3) Update a card");
		System.out.println("4) Delete a card");
		System.out.println("5) Search for card by Name");
		System.out.println("Choose an option please...");
		System.out.println("==============");

		
		
		String name = sc.nextLine();
		if (name.equals("1")) {
//			viewAllCards();
			orderByName();
			

		} else if (name.equals("2")) {
			addCard();
		} else if (name.equals("3")) {
			updateCard();
			

		} else if (name.equals("4")) {
			deleteCard();

		} else if (name.equals("5")) {
			searchForCard();
			System.out.println();
			login();
		} else {
			login();
		}
		
	}

//		option = sc.nextInt();
//		sc.nextLine();
//		} while (option < 0 || option > 4);
//		return option;

//	public void admin() throws Exception {
//		switch (login()) {
//		case 0:
//			return;
//		case 1:
//			viewAllCards();
//			break;
//		case 2:
//			addCard();
//			break;
//		case 4:
//			deleteCard();
//			break;
//			default:
//				login();
//		}
//	}

	private void deleteCard() throws Exception {
		
		System.out.println("Which card would you like to delete?");
		viewAllCards();
		System.out.println("Choose by ID Card #");
		int id_Card = sc.nextInt();
		sc.nextLine();

		String sql2 = "DELETE FROM pokemoncards.card WHERE idCard = ?";
		dataBase db = new dataBase();

		try (PreparedStatement initiate = db.getConnection().prepareStatement(sql2)) {
			initiate.setInt(1, id_Card);
			initiate.executeUpdate();
			System.out.println(initiate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	

	}

	public void updateCard() throws Exception {
		viewAllCards();
		System.out.println("Which card would you like to update?");
		System.out.println("Choose by Card ID #");
		
		int idCard = sc.nextInt();
		sc.nextLine();
		

		String sql1 = "SELECT idCard, name, price, health, amount" + " FROM pokemoncards.card WHERE idCard = ?";
		
		try (PreparedStatement initiate1 = db.getConnection().prepareStatement(sql1)) {

			initiate1.setInt(1, idCard);
			ResultSet results = initiate1.executeQuery();
			results.next();

			System.out.println("Update your ID Card # : [" + results.getInt("idCard") + "] ? : ");
			int idNumber = sc.nextInt();
			sc.nextLine();
			System.out.println("Update card name : [" + results.getString("name") + "] ? : ");
			String cardName = sc.nextLine();
			
			System.out.println("Update card price : [" + results.getDouble("price") + "] ? : ");
			double cardPrice = sc.nextDouble();
			
			System.out.println("Update the card health : [" + results.getInt("health") + "] ? : ");
			int cardHealth = sc.nextInt();
			
			System.out.println("Update stock amount of card : [" + results.getInt("amount") + "] ? : ");
			int stockNumber = sc.nextInt();
			
			String sql2 = "UPDATE card SET idCard = ?, name = ?, price = ?, health = ?, amount = ? WHERE idCard = 21";

			try (PreparedStatement initiate2 = db.getConnection().prepareStatement(sql2)) {

				initiate2.setInt(1, idNumber);
				initiate2.setString(2, cardName);
				initiate2.setDouble(3, cardPrice);
				initiate2.setInt(4, cardHealth);
				initiate2.setInt(5, stockNumber);
				initiate2.executeUpdate();

				String sql3 = "SELECT * FROM pokemoncards.card WHERE idCard = ?";
				try (PreparedStatement initiate3 = db.getConnection().prepareStatement(sql3)) {
					initiate3.setInt(1, idNumber);
					ResultSet results4 = initiate3.executeQuery();
					results4.next();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	private void searchForCard() {
		System.out.println("Search for card by name");
		System.out.println("=============");
		System.out.println("Name: ");
		String name1 = sc.nextLine();
		
		String sql4 = "SELECT * FROM card WHERE name LIKE ?";	
		
			try {
				PreparedStatement ps = db.getConnection().prepareStatement(sql4);
				
				ps.setString(1, name1);
//				System.out.println(ps.toString());
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					System.out.println("Name : " + rs.getString("name") + " | $" +rs.getDouble("price")
					+ " | Qnty | "+ rs.getInt("amount"));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
	}
	
	
	
	private void orderByName() {
		String sql5 = "SELECT * FROM card ORDER BY name";
		
		try {
			PreparedStatement ps = db.getConnection().prepareStatement(sql5);
			ResultSet rs = ps.executeQuery(sql5);
			
			while(rs.next()) {
				int id = rs.getInt("idCard");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				int health = rs.getInt("health");
				int amt = rs.getInt("amount");
				System.out.println("ID | #" + id + "\n" + "Name | " + name + "\n"
						+ "Price | $" + price + "\n" + "Health | " + health + "\n"
						+ "Quantity | " + amt + "\n");
				
				
			}
			login();

	} catch (Exception e) {
		e.printStackTrace();
	}
		

	}

	private void addCard() throws Exception {
		System.out.println("Name");
		String name = sc.nextLine();
		System.out.println("Price");
		double price = sc.nextDouble();
		System.out.println("Health");
		int health = sc.nextInt();
		System.out.println("Amount");
		int amount = sc.nextInt();

		String sql = "INSERT into Card(name,price,health,amount) values (?,?,?,?)";
		dataBase db = new dataBase();

		try {
			PreparedStatement ps = db.getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setInt(3, health);
			ps.setInt(4, amount);
			int x = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		login();
	}
}
