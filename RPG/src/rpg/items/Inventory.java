package rpg.items;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
	private ArrayList<Item> items;
	private int gold;
	
	public Inventory() {
		this.items = new ArrayList<Item>();
		this.gold = 0;
	}
	
	/**
	 * Add item to inventory
	 * @param item
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}
	
	/**
	 * Add multiple items to inventory
	 * @param items
	 */
	public void addItems(ArrayList<Item> items) {
		for(int i = 0; i < items.size(); i++) {
			this.items.add(items.get(i));
		}
	}
	
	/**
	 * Remove item in inventory
	 * @param item
	 */
	public void removeItem(Item item) {
		this.items.remove(item);
	}
	
	public Item getItem(int i) {
		return this.items.get(i);
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	/**
	 * Add gold to inventory
	 * @param gold
	 */
	public void addGold(int gold) {
		this.gold += gold;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	/**
	 * Display inventory and choose an item to get
	 * @param sc
	 * @return
	 */
	public Item browse(Scanner sc) {
		int choice;
		do {
			System.out.println("\n" + this);
			System.out.println("\nType 0 to exit");
			try {
				choice = sc.nextInt();
			} catch (Exception e) {
				choice = -1;
			}
		} while(choice < -1 || choice > this.items.size());
		
		try {
			Item item = this.items.get(choice-1);
			this.items.remove(choice-1);
			return item;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public String toString() {
		String str = "Inventory :\n\tItems : {\n";
		
		for(int i = 0; i < this.items.size(); i++) {
			str += "\t\t" + (i+1) + " : " + this.items.get(i) + "\n";
		}
		
		str += "\t}\n\tMoney : " + this.gold + " GP";
		
		return str;
	}
	
}
