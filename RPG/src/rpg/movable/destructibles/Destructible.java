package rpg.movable.destructibles;

import rpg.movable.Movable;
import rpg.movable.destructibles.characters.Character;

public abstract class Destructible extends Movable {
	/** Health points of destructible */
	protected int hp;
	/** Maximum health points of destructible */
	protected int maxhp;
	
	public Destructible(int x, int y, int hp) {
		super(x, y);
		this.hp = hp;
		this.maxhp = hp;
	}
	
	/**
	 * Apply damage to the destructible
	 * 
	 * @param damageTaken Number of damage taken
	 * @param enemy Enemy that did the damage
	 */
	public void applyDamage(int damageTaken, Character enemy) {
		this.hp -= damageTaken;
		
		if (this.hp <= 0) {
			destroys(enemy);
		}
	}
	
	/**
	 * Return if destructible is destroyed
	 * 
	 * @return boolean
	 */
	public boolean isDestroyed() {
		return this.hp <= 0;
	}
	
	/**
	 * What to do when the destructible is destroyed
	 * 
	 * @param enemy Enemy that destroyed the destructible
	 */
	public void destroys(Character enemy) {
		// Do nothing
	}
}
