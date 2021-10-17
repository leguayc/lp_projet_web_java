package rpg.movable.destructibles.characters.npc.monster;

import rpg.items.equipment.Armor;
import rpg.items.equipment.weapons.Sword;

public class Demon extends Monster {
	
	public Demon(int x, int y) {
		super("Demon", x, y, 60, 5, 6, 2, 3);
		this.weapon = new Sword(4);
		this.armor = new Armor("Iron", 5);
		
		this.inventory.addItem(this.weapon);
		this.inventory.addItem(this.armor);
	}
}
