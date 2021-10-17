package rpg.items;

import rpg.items.equipment.Armor;
import rpg.items.equipment.weapons.Dagger;
import rpg.items.equipment.weapons.Sword;
import rpg.items.equipment.weapons.Wand;
import rpg.items.equipment.weapons.Weapon;
import rpg.utils.DiceResult;

public class ItemFactory {
	/**
	 * Create random potion (random numberOfFaces)
	 * @return Potion
	 */
	public static Potion createPotion() {
		int roll = DiceResult.rollDice(6) + 3; // Between 4 and 10
		int numberOfFaces = (roll%2== 0 ? roll : roll+1);
		return new Potion(numberOfFaces);
	}
	
	/**
	 * Create random armor (with random name and random def value)
	 * @return Armor
	 */
	public static Armor createArmor() {
		String[] array = {"Robe", "Bad Leather", "Leather", "Iron", "Steel", "Plate"};
		int roll = DiceResult.rollDice(6);
		
		return new Armor(array[roll-1], roll);
	}
	
	/**
	 * Create random weapon with random level
	 * @return Weapon
	 */
	public static Weapon createRandomWeapon() {
		int roll = DiceResult.rollDice(3);
		int levelRoll = DiceResult.rollDice(5);
		
		switch(roll) {
			case 1:
				return new Sword(levelRoll);
			case 2:
				return new Dagger(levelRoll);
			case 3:
				return new Wand(levelRoll);
			default:
				return new Sword(levelRoll);
		}
	}
	
	/**
	 * Create random item between armor, potion and weapon
	 * @return Item
	 */
	public static Item createRandomItem() {
		int roll = DiceResult.rollDice(3);
		
		switch(roll) {
			case 1:
				return createPotion();
			case 2:
				return createRandomWeapon();
			case 3:
				return createArmor();
			default:
				return createPotion();
		}
	}
}
