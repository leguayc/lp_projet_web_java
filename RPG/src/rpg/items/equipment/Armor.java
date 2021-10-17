package rpg.items.equipment;

public class Armor extends Equipment {
	private String name;
	private int defValue;
	
	public Armor(String name, int defValue) {
		this.name = name;
		this.defValue = defValue;
	}
	
	/**
	 * Get defence value
	 * @return
	 */
	public int getDefence() {
		return this.defValue;
	}

	@Override
	public String toString() {
		return this.name + " (+ " + this.defValue + " DEF)";
	}
	
	@Override
	public int price() {
		return defValue * 10;
	}
}
