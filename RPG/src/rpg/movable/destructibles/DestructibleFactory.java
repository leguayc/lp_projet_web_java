package rpg.movable.destructibles;

import java.util.Scanner;

import rpg.items.ItemFactory;
import rpg.movable.destructibles.characters.Character;
import rpg.movable.destructibles.characters.npc.Merchant;
import rpg.movable.destructibles.characters.npc.monster.Demon;
import rpg.movable.destructibles.characters.npc.monster.Goblin;
import rpg.movable.destructibles.characters.player.Mage;
import rpg.movable.destructibles.characters.player.Player;
import rpg.movable.destructibles.characters.player.Rogue;
import rpg.movable.destructibles.characters.player.Warrior;
import rpg.movable.destructibles.obstacles.Obstacle;
import rpg.utils.DiceResult;

public class DestructibleFactory {
	/**
	 * Player Factory
	 * @param sc Scanner
	 * @return Player
	 */
	public static Player createPlayer(Scanner sc) {
		System.out.println("--------------------------------------------");
		System.out.println("\t\tWho are you ?");
		System.out.println("--------------------------------------------");
		
		String name;
		do {
			System.out.println("Enter your name :");
			name = sc.nextLine();
		} while (name.isEmpty() || name.trim() == "");
		
		int playerClass;
		do {
			System.out.println("\nChoose your class : \n");
			System.out.println("1 : Warrior\n2 : Rogue\n3 : Mage");
			try {
				playerClass = sc.nextInt();
			} catch(Exception e) {
				System.out.println("Invalid");
				playerClass = 0;
			}
		} while (playerClass < 1 || playerClass > 3);
		
		switch(playerClass) {
			case 1:
				System.out.println("Wish you glory and honor, Warrior " + name + " !");
				return new Warrior(name, sc);
			case 2:
				System.out.println("Wish you good fortune, Rogue " + name + " !");
				return new Rogue(name, sc);
			case 3:
				System.out.println("Wish you well in your research, Mage " + name + " !");
				return new Mage(name, sc);
			default:
				return new Player(name, 0, 0, 0, 0, sc);
		}
	}
	
	/**
	 * Create Merchant with generated store at coords x,y
	 * @param x
	 * @param y
	 * @return Merchant
	 */
	public static Merchant createMerchant(int x, int y) {
		Merchant m = new Merchant(x, y);
		
		for (int i = 0; i < 5; i++) {
			m.getInventory().addItem(ItemFactory.createRandomItem());
		}
		
		m.getInventory().addGold(100);
		
		return m;
	}

	/**
	 * Create map boss
	 * 
	 * @return Character Boss of the game
	 */
	public static Character createBoss() {
		Demon d = new Demon(10, 10);
		
		d.getInventory().addGold(300);
		
		return d;
	}
	
	/**
	 * Create basic mob at coords x,y
	 * @param x
	 * @param y
	 * @return Character
	 */
	public static Character createMob(int x, int y) {
		Character c = new Goblin(x, y);
		
		c.getInventory().addGold(DiceResult.rollDice(20)+5);
		
		return c;
	}
	
	/**
	 * Create obstacle at coords x,y
	 * @param x
	 * @param y
	 * @return Obstacle
	 */
	public static Obstacle createObstacle(int x, int y) {
		int roll = DiceResult.rollDice(20);
		Obstacle o = new Obstacle(x, y, roll);
		
		return o;
	}
	
	/**
	 * Create a random destructible at coords x, y
	 * @param x
	 * @param y
	 * @return
	 */
	public static Destructible createRandomDestructible(int x, int y) {
		int roll = DiceResult.rollDice(10);
		
		if (roll == 1) {
			return createMerchant(x, y);
		} else if (roll < 6) {
			return createMob(x, y);
		} else {
			return createObstacle(x, y);
		}
	}
}
