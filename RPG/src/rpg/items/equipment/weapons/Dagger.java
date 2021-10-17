package rpg.items.equipment.weapons;

import rpg.movable.destructibles.characters.Character;
import rpg.movable.destructibles.obstacles.Obstacle;

public class Dagger extends Weapon {
	public Dagger(int level) {
		super(4, "dexterity", level-1, 15);
	}
	
	public int getDamage(Character character, Obstacle target, int diceResult) {
		int damage = super.getDamage(character, target, diceResult) / 2;
		System.out.println("It's not very effective on an obstacle...");
		return damage;
	}
	
	public int price() {
		return this.bonusDamage * 15 + 10;
	}
}
