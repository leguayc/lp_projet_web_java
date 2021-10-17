package rpg.utils;

public class DiceResult {
	/** Result of the roll */
	private int roll;
	/** Bonus to apply on the roll */
	private int bonus;
	/** Total on the roll */
	private int total;
	
	public DiceResult(int numberOfFaces, int bonus) {
		this.roll = rollDice(numberOfFaces);
		this.bonus = bonus;
		this.total = this.roll + this.bonus;
	}
	
	/**
	 * Roll a dice
	 * @param numberOfFaces Number of faces on the dice
	 * @return dice result
	 */
	public static int rollDice(int numberOfFaces) {
		return (int)(Math.random() * numberOfFaces) + 1;
	}
	
	public int getRoll() {
		return roll;
	}
	
	public int getBonus() {
		return bonus;
	}
	
	public int getTotal() {
		return total;
	}
}
