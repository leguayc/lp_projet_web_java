package rpg.items;

import rpg.movable.destructibles.characters.Character;

public abstract class Item {
	/**
	 * Compute the price of the item and return it
	 * 
	 * @return price of item
	 */
	public abstract int price();
	
	/**
	 * Declares what to do when a character use the item
	 * 
	 * @param c Character that use it
	 */
	public abstract void beUsed(Character c);
}
