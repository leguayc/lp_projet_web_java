package rpg.movable.destructibles.characters.npc.monster;

import java.util.Scanner;

import rpg.Game;
import rpg.movable.Movable;
import rpg.movable.destructibles.characters.Character;
import rpg.movable.destructibles.characters.npc.NPC;

public class Monster extends NPC {

	public Monster(String name, int x, int y, int hp, int level, int strength, int dexterity, int intelligence) {
		super(name, x, y, hp, level, strength, dexterity, intelligence);
	}

	@Override
	public boolean moveOnto(Movable source, Scanner sc) {
		return Game.fight((Character)source, this, sc);
	}
	
	@Override
	public String toMapSymbol() {
		return "x";
	}

}
