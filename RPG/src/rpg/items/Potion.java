package rpg.items;

import rpg.Game;
import rpg.movable.destructibles.characters.Character;

public class Potion extends Item {
	/** Number of faces on the heal dice */
	private int numberOfFaces;
	
	public Potion(int numberOfFaces) {
		this.numberOfFaces = numberOfFaces;
	}

	@Override
	public int price() {
		return (int)Math.floor((numberOfFaces/2) * 3);
	}

	@Override
	public void beUsed(Character c) {
		System.out.println("You drink a potion");
		int heal = Game.rollDice(numberOfFaces, 0).getTotal();
		c.heal(heal);
	}
	
	public String toString() {
		return "Potion (1D" + this.numberOfFaces + ")";
	}

}
