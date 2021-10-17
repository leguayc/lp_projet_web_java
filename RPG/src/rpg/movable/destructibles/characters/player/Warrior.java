package rpg.movable.destructibles.characters.player;

import java.util.Scanner;

import rpg.items.equipment.Armor;
import rpg.items.equipment.weapons.Sword;

public class Warrior extends Player {
	public Warrior(String name, Scanner scanner) {
		super(name, 10, 3, 1, 0, scanner);
		this.weapon = new Sword(1);
		this.armor = new Armor("Leather", 3);
	}
}
