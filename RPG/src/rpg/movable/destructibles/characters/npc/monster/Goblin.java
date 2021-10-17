package rpg.movable.destructibles.characters.npc.monster;

import rpg.items.equipment.Armor;
import rpg.items.equipment.weapons.Sword;

public class Goblin extends Monster {

	public Goblin(int x, int y) {
		super("Goblin", x, y, 6, 1, 2, 2, 1);
		this.weapon = new Sword(1);
		this.armor = new Armor("Bad Leather", 2);
	}
	
}
