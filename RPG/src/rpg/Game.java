package rpg;

import java.util.Scanner;

import rpg.items.Item;
import rpg.map.Map;
import rpg.movable.destructibles.characters.Character;
import rpg.movable.destructibles.characters.player.Player;
import rpg.movable.destructibles.obstacles.Obstacle;
import rpg.utils.DiceResult;

public class Game {
	/**
	 * Roll dice of number of faces
	 * @param numberOfFaces
	 * @return dice result
	 */
	public static DiceResult rollDice(int numberOfFaces, int bonus) {
		DiceResult diceResult = new DiceResult(numberOfFaces, bonus);
		
		String message = "Dice result (D" + numberOfFaces + ") : " + diceResult.getRoll();
		
		if (bonus != 0) {
			message += " + " + bonus + " = " + diceResult.getTotal();
		}
		
		System.out.println(message);
		
		return diceResult;
	}
	
	/**
	 * Fight between c1 and c2
	 * 
	 * @param c1
	 * @param c2
	 * @param sc Scanner (need to pass it in parameters to avoid exception)
	 * 
	 * @return If you beat it or not
	 */
	public static boolean fight(Character c1, Character c2, Scanner sc) {
		System.out.println("\n--------------------------------------------");
		System.out.println("\t\tFight");
		System.out.println("--------------------------------------------");
		
		int choice;
		int turnCount = 1;
		c1.rollsInitiative();
		c2.rollsInitiative();
		
		// While neither c1 and c2 are dead
		while(!c1.isDead() && !c2.isDead()) {
			System.out.println("\n---- TURN " + turnCount + " ----");
			
			// Display who moves first (character with the best initiative)
			if (c1.getInitiative() >= c2.getInitiative()) {
				System.out.println(c1.getName() + " moves first !");
			} else {
				System.out.println(c2.getName() + " moves first !");
			}
			
			System.out.println("\n" + c1.getName() + " : " + c1.getHealth() + " / " + c1.getMaxHealth() + " HP");
			System.out.println(c2.getName() + " : " + c2.getHealth() + " / " + c2.getMaxHealth() + " HP");
			
			do {
				System.out.println("\nChoose : \n");
				System.out.println("1 : Fight the " + c2.getName());
				System.out.println("2 : Inventory");
				System.out.println("3 : Run");
				try {
					choice = sc.nextInt();
				} catch(Exception e) {
					System.out.println("Invalid : " + e);
					choice = 0;
				}
			} while (choice < 1 || choice > 3);
			
			// If choice == 1, they fight
			if (choice == 1) {
				// The character with the better initiative hits first
				if (c1.getInitiative() >= c2.getInitiative()) {
					c1.attack(c2);
					
					// We check if he's dead so that he doesn't attack while being dead
					if (!c2.isDead()) {
						c2.attack(c1);
					}
				} else {
					c2.attack(c1);
					
					// We check if he's dead so that he doesn't attack while being dead
					if (!c1.isDead()) {
						c1.attack(c2);
					}
				}
			}
			// If choice == 2, browse inventory
			else if (choice == 2) {
				Item itemToUse = c1.getInventory().browse(sc);
				
				if (c1.getInitiative() < c2.getInitiative()) {
					c2.attack(c1);
					if (!c1.isDead()) {
						if (itemToUse != null) {
							itemToUse.beUsed(c1);
						}
					}
				} else {
					if (itemToUse != null) {
						itemToUse.beUsed(c1);
					}
					c2.attack(c1);
				}
			}
			// If choice == 3, you escape
			else if (choice == 3) {
				if (c1.getInitiative() < c2.getInitiative()) {
					c2.attack(c1);
				}
				
				if (!c1.isDead()) {
					System.out.println(c1.getName() + " escaped !");
					return false;
				}
			}
				
			turnCount++;
		}
		
		return !c1.isDead();
	}
	
	/**
	 * Fight between c1 and obstacle
	 * 
	 * @param c1
	 * @param o
	 * @param sc Scanner (need to pass it in parameters to avoid exception)
	 * 
	 * @return If you beat it or not
	 */
	public static boolean fight(Character c1, Obstacle o, Scanner sc) {
		System.out.println("\n--------------------------------------------");
		System.out.println("\t\tFight");
		System.out.println("--------------------------------------------");
		
		int choice;
		int turnCount = 1;
		
		// While neither c1 and c2 are dead
		while(!c1.isDead() && !o.isDestroyed()) {
			System.out.println("\n---- TURN " + turnCount + " ----");
			
			System.out.println(o);
			
			do {
				System.out.println("\nChoose : \n");
				System.out.println("1 : Hit the " + o);
				System.out.println("2 : Inventory");
				System.out.println("3 : Leave");
				try {
					choice = sc.nextInt();
				} catch(Exception e) {
					System.out.println("Invalid : " + e);
					choice = 0;
				}
			} while (choice < 1 || choice > 3);
			
			// If choice == 1, they fight
			if (choice == 1) {
				c1.attack(o);
			}
			// If choice == 2, browse inventory
			else if (choice == 2) {
				Item itemToUse = c1.getInventory().browse(sc);
				
				if (itemToUse != null) {
					itemToUse.beUsed(c1);
				}
			}
			// If choice == 3, you escape
			else if (choice == 3) {
				System.out.println(c1.getName() + " left !");
				return false;
			}
				
			turnCount++;
		}
		
		return !c1.isDead();
	}
	
	/**
	 * Display map and valid moves of player
	 * @param m Map
	 * @param p Player
	 */
	private static void displayMap(Map m, Player p) {
		System.out.println("\n--------------------------------------------");
		System.out.println("\t\tMap");
		System.out.println("--------------------------------------------");
		
		System.out.println(m);
		
		m.displayValidMoves(p);
		System.out.println("Type the direction where you want to move to move there");
	}
	
	/**
	 * Handles movement on map
	 * @param p Player
	 * @param m Map
	 * @param sc Scanner
	 */
	private static void playerMove(Player p, Map m, Scanner sc) {
		String choice;
		//do {
			Game.displayMap(m, p);
			System.out.println("\nYou can also check your inventory by typing 'inventory'");
			System.out.println("Or check your character sheet by typing 'character'");
			choice = sc.next();
			
			if (choice.equalsIgnoreCase("inventory")) {
				Item item = p.getInventory().browse(sc);
				if (item != null) {
					item.beUsed(p);
				}
			} else if (choice.equalsIgnoreCase("character")) {
				System.out.println("\n" + p);
			}
		//} while (!Map.isValidMove(p, choice));
		
		p.moveTo(m, choice, sc);
	}
	
	/**
	 * Start the game and play 
	 * @param p Player
	 * @param m Map
	 * @param sc Scanner
	 */
	public static void play(Player p, Map m, Scanner sc) {
		while (!p.isDead()) {
			playerMove(p, m, sc);
			if ( p.hasWon() ) {
				break;
			}
		}
		
		if ( p.hasWon() ) {
			System.out.println("\n--------------------------------------------");
			System.out.println("\t\tVictory");
			System.out.println("--------------------------------------------");
		} else {
			System.out.println("\n--------------------------------------------");
			System.out.println("\t\tGame over");
			System.out.println("--------------------------------------------");
		}
		
		System.out.println("\n" + p);
	}
}
