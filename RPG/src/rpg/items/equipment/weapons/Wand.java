package rpg.items.equipment.weapons;

public class Wand extends Weapon {
	public Wand(int level) {
		super(8, "intelligence", level-1, 19);
	}
	
	@Override
	public int price() {
		return this.bonusDamage * 15 + 10;
	}
}
