package bbrown2022;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Start {
	
	static Scanner sc = new Scanner(System.in);
	public ArrayList<Card> cards = new ArrayList<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Welcome to Bizzy's Pokemon Card Collection!");
		System.out.println("================");
		
		
		
		Start s = new Start();
		s.login();
		
		

	}
	
	public void demo() {
		
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
				System.out.println(name);
				
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
		
		for(Card c: cards) {
			System.out.println(c);
		}
		
		
	}
	
	public void login() {
		System.out.println("Admin Menu");
		System.out.println("1) View");
		System.out.println("2) Add");
		System.out.println("Choose which option");
		
		String name = sc.nextLine();
		if(name.equals("1"))
			demo();
		else 
			add();
	
	


}

	private void add() {
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
