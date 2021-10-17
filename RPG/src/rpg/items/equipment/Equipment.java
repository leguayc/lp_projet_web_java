package rpg.items.equipment;

import rpg.items.Item;
import rpg.movable.destructibles.characters.Character;

public abstract class Equipment extends Item {
	
	@Override
	public void beUsed(Character c) {
		c.equip(this);
		System.out.println("Equipped " + this);
	}
}
