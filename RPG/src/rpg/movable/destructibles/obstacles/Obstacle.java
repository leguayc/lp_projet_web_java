package rpg.movable.destructibles.obstacles;

import java.util.Scanner;

import rpg.Game;
import rpg.movable.Movable;
import rpg.movable.destructibles.Destructible;
import rpg.movable.destructibles.characters.Character;

public class Obstacle extends Destructible {
	public Obstacle(int x, int y, int hp) {
		super(x, y, hp);
	}

	@Override
	public String toMapSymbol() {
		return "M";
	}

	@Override
	public boolean moveOnto(Movable source, Scanner sc) {
		return Game.fight((Character)source, this, sc);
	}
	
	@Override
	public String toString() {
		return "Boulder (" + this.hp + " / " + this.maxhp + " HP)";
	}
	
	@Override
	public void destroys(Character enemy) {
		enemy.gainXp(this.maxhp);
		enemy.getInventory().addGold(1);
	}
}
