package rpg.items.equipment.weapons;

import rpg.Game;
import rpg.items.equipment.Equipment;
import rpg.movable.destructibles.Destructible;
import rpg.movable.destructibles.characters.Character;

public abstract class Weapon extends Equipment {
	/** Number of faces on the damage dice */
	private int damageDiceNumberFaces;
	/** Modifier on the damage and the attack used for this weapon (Example : Dexterity for a bow) */
	private String modifier;
	/** Bonus damage */
	protected int bonusDamage;
	/** Minimum number to get on a dice to get a critical hit */
	private int criticalScore;
	
	public Weapon(int numberOfFaces, String modifier, int bonusDamage, int criticalScore) {
		this.damageDiceNumberFaces = numberOfFaces;
		this.modifier = modifier;
		this.bonusDamage = bonusDamage;
		this.criticalScore = criticalScore;
	}

	/**
	 * Roll for damage and compute it
	 * @param character Character that needs the damage
	 * @param target Target of the damage
	 * @param diceResult Result of dice (to check if it's critical or not)
	 * @return
	 */
	public int getDamage(Character character, Destructible target, int diceResult) {
		int damageBonus = this.getModifier(character) + this.bonusDamage;
		int damage = Game.rollDice(this.damageDiceNumberFaces, damageBonus).getTotal();
		
		if (diceResult >= this.criticalScore) {
			System.out.println("Critical hit ! The damage doubles !");
			damage *= 2;
		}
		
		return damage;
	}
	
	/**
	 * Get according modifier of the character that must be used with this weapon
	 * @param character
	 * @return Modifier of the character
	 */
	public int getModifier(Character character) {
		switch(this.modifier)
        {
            case "strength":
                return character.getStrength();
            case "dexterity":
                return character.getDexterity();
            case "intelligence":
                return character.getIntelligence();
            default:
                return 0;
        }
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " (" + "1D" + this.damageDiceNumberFaces + " + " + this.bonusDamage + ")";
	}
}
