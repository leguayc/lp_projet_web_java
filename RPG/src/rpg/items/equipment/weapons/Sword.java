package rpg.items.equipment.weapons;

import rpg.movable.destructibles.characters.Character;
import rpg.movable.destructibles.obstacles.Obstacle;

public class Sword extends Weapon {
	public Sword(int level) {
		super(6, "strength", level-1, 20);
	}
	
	public int getDamage(Character character, Obstacle target, int diceResult) {
		int damage = super.getDamage(character, target, diceResult) * 2;
		System.out.println("It's very effective on an obstacle !");
		return damage;
	}
	
	@Override
	public int price() {
		return this.bonusDamage * 15 + 10;
	}
}
