package rpg.movable.destructibles.characters.npc;

import rpg.movable.destructibles.characters.Character;

public abstract class NPC extends Character {

	public NPC(String name, int x, int y, int hp, int level, int strength, int dexterity, int intelligence) {
		super(name, x, y, hp, level, strength, dexterity, intelligence);
	}
	
	@Override
	public void dies(Character enemy) {
		super.dies(enemy);
		double xpGained = 20;
		int levelDiff = this.level - enemy.getLevel();
		
		if (levelDiff != 0) {
			if (levelDiff > 0) {
				xpGained *= levelDiff + 1;
			} else {
				xpGained /= (-levelDiff) + 1;
			}
		}
		
		enemy.gainXp((int)(xpGained));
		enemy.getInventory().addItems(this.inventory.getItems());
		enemy.getInventory().addGold(this.inventory.getGold());
	}

}
