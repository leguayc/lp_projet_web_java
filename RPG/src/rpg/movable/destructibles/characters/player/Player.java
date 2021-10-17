package rpg.movable.destructibles.characters.player;

import java.util.Scanner;

import rpg.Game;
import rpg.items.Potion;
import rpg.movable.Movable;
import rpg.movable.destructibles.characters.Character;

public class Player extends Character {
	/** Base hp to roll hp dice when leveling up */
	protected int basehp;
	/** Store scanner to avoid errors */
	protected Scanner scanner;
	/** Check if player has won */
	protected boolean hasWon;
	
	public Player(String name, int hp, int strength, int dexterity, int intelligence, Scanner scanner) {
		super(name, 0, 0, hp, 1, strength, dexterity, intelligence);
		this.basehp = hp;
		this.scanner = scanner;
		this.hasWon = false;
		this.inventory.addGold(20);
		this.inventory.addItem(new Potion(6));
	}
	
	@Override
	public void levelUp() {
		super.levelUp();
		
		System.out.println("\n" + this.name + " leveled up ! " + this.name + "'s level is now " + this.level + " !");
		this.maxhp += Game.rollDice(this.basehp, this.level).getTotal();
		this.hp = this.maxhp;
		System.out.println(this.name + " now has " + this.maxhp + " HP !");
		
		// We choose which caracteristic to level up
		int choice;
		do {
			System.out.println("Choose the caracteristic to level up :");
			System.out.println("1 : Strength");
			System.out.println("2 : Dexterity");
			System.out.println("3 : Intelligence");
			try {
				choice = this.scanner.nextInt();
			} catch (Exception e) {
				choice = 0;
			}
		} while(choice < 1 || choice > 3);
		
		// We level up the chosen caracteristic
		switch(choice) {
			case 1:
				this.strength++;
				System.out.println(this.name + "'s strength goes up by 1 ! " + this.name + "'s strength is now " + this.strength);
				break;
			case 2:
				this.dexterity++;
				System.out.println(this.name + "'s dexterity goes up by 1 ! " + this.name + "'s dexterity is now " + this.dexterity);
				break;
			case 3:
				this.intelligence++;
				System.out.println(this.name + "'s intelligence goes up by 1 ! " + this.name + "'s intelligence is now " + this.intelligence);
				break;
		}
		
	}
	
	/**
	 * Set hasWon to true (to check if player has won)
	 */
	public void win() {
		hasWon = true;
	}
	
	/**
	 * Check if player has won
	 * @return boolean has won
	 */
	public boolean hasWon() {
		return hasWon;
	}

	@Override
	public String toMapSymbol() {
		return "o";
	}

	@Override
	public boolean moveOnto(Movable source, Scanner sc) {
		// Cannot happen as the only thing that moves is the player
		return true;
	}
	
	public String toString() {
		String str = this.name + ", " + this.getClass().getSimpleName() + " level " + this.level;
		str += "\nHealth : " + this.hp + " / " + this.maxhp + " HP";
		str += "\nXP : " + this.xp + " / " + (Character.BASE_XP_NEEDED * this.level);
		str += "\n\nStrength : " + this.strength;
		str += "\nDexterity : " + this.dexterity;
		str += "\nIntelligence : " + this.intelligence;
		str += "\n\nWeapon : " + this.weapon;
		str += "\nArmor : " + this.armor;
		str += "\n\n" + this.inventory;
		
		return str;
	}
}
