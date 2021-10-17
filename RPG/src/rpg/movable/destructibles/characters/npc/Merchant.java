package rpg.movable.destructibles.characters.npc;

import java.util.ArrayList;
import java.util.Scanner;

import rpg.Game;
import rpg.items.Item;
import rpg.items.equipment.Armor;
import rpg.items.equipment.weapons.Sword;
import rpg.movable.Movable;
import rpg.movable.destructibles.characters.Character;

public class Merchant extends NPC {
	public Merchant(int x, int y) {
		super("Merchant", x, y, 30, 3, 3, 2, 2);
		this.weapon = new Sword(2);
		this.armor = new Armor("Leather", 3);
	}
	
	/**
	 * Display shop and let the player chose which item he wants to buy
	 * 
	 * @param c Character (player)
	 * @param sc Scanner
	 */
	public void talk(Character c, Scanner sc) {
		ArrayList<Item> allItems = this.getInventory().getItems();
		int choice;
		
		do {
			System.out.println("\nChoose what you want to buy (or 0 to exit): ");
			for(int i = 0; i < allItems.size(); i++) {
				System.out.println((i+1) + " : " + allItems.get(i) + " (" + allItems.get(i).price() + " GP)");
			}
		
			
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				choice = -1;
			}
		} while(choice < 0 || choice > allItems.size());
		
		switch(choice) {
			case 0 :
				return;
			default :
				this.buy(c, choice-1);
		}
	}
	
	/**
	 * Buy item to the merchant for the character c
	 * 
	 * @param c Character
	 * @param itemIndex In the merchant's inventory
	 */
	public void buy(Character c, int itemIndex) {
		Item item = this.getInventory().getItem(itemIndex);
		int price = item.price();
		
		if (c.getInventory().getGold() >= price) {
			System.out.println("You bought a " + item + " for " + price + " GP");
			c.getInventory().addGold(-price);
			c.getInventory().addItem(item);
			this.getInventory().removeItem(item);
		} else {
			System.out.println("You don't have the gold to buy that !");
		}
	}
	
	@Override
	public String toMapSymbol() {
		return "$";
	}

	@Override
	public boolean moveOnto(Movable source, Scanner sc) {
		System.out.println("\n--------------------------------------------");
		System.out.println("\t\tMerchant");
		System.out.println("--------------------------------------------");
		
		System.out.println("Welcome traveler ! Look at my wares !");
		
		int choice;
		do {
			System.out.println("\n1 : Talk to");
			System.out.println("2 : Attack");
			System.out.println("3 : Leave");
			
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				choice = 0;
			}
		} while(choice < 1 || choice > 3);
		
		// If choice == 1, talk
		if (choice == 1) {
			this.talk((Character)source, sc);
			return false;
		} 
		// If choice == 2, attack
		else if (choice == 2) {
			return Game.fight((Character)source, this, sc);
		}
		// If choice ==3, leave
		else {
			return false;
		}
	}

}
