package rpg.movable.destructibles.characters.player;

import java.util.Scanner;

import rpg.items.equipment.Armor;
import rpg.items.equipment.weapons.Wand;

public class Mage extends Player {
	
	public Mage(String name, Scanner scanner) {
		super(name, 6, 0, 1, 3, scanner);
		this.weapon = new Wand(1);
		this.armor = new Armor("Robe", 1);
	}
}
