package rpg.movable.destructibles.characters.player;

import java.util.Scanner;

import rpg.items.equipment.Armor;
import rpg.items.equipment.weapons.Dagger;

public class Rogue extends Player {
	public Rogue(String name, Scanner scanner) {
		super(name, 8, 1, 2, 1, scanner);
		this.weapon = new Dagger(1);
		this.armor = new Armor("Leather", 2);
	}
}
