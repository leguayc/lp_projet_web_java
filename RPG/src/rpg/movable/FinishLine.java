package rpg.movable;

import java.util.Scanner;

import rpg.Game;
import rpg.movable.destructibles.DestructibleFactory;
import rpg.movable.destructibles.characters.Character;
import rpg.movable.destructibles.characters.player.Player;

public class FinishLine extends Movable {

	public FinishLine(int x, int y) {
		super(x, y);
	}
	
	@Override
	public String toMapSymbol() {
		return "!";
	}

	@Override
	public boolean moveOnto(Movable source, Scanner sc) {
		boolean res = false;
		
		if ( source instanceof Character ) {
			res = Game.fight((Character) source, DestructibleFactory.createBoss(), sc);
			
			// If the fight is won, the player has won the game !
			if (res) {
				if ( source instanceof Player ) {
					((Player) source).win();
				}
			}
		}
		
		return res;
	}

}
