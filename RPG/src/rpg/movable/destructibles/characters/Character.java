package rpg.movable.destructibles.characters;

import rpg.Game;
import rpg.items.Inventory;
import rpg.items.equipment.Armor;
import rpg.items.equipment.Equipment;
import rpg.items.equipment.weapons.Weapon;
import rpg.movable.destructibles.Destructible;
import rpg.utils.DiceResult;

public abstract class Character extends Destructible {
	protected static final int BASE_XP_NEEDED = 50;
	/** Name of character */
	protected String name;
	/** Strength modifier of character */
	protected int strength;
	/** Dexterity modifier of character */
	protected int dexterity;
	/** Intelligence modifier of character */
	protected int intelligence;
	/** Initiative of character (rolled before each fight) */
	protected int initiative;
	/** Level of character */
	protected int level;
	/** XP of character */
	protected int xp;
	/** Equipped weapon of character */
	protected Weapon weapon;
	/** Equipped armor of character */
	protected Armor armor;
	/** Inventory of character */
	protected Inventory inventory;
	
	/**
	 * Constructor
	 * 
	 * @param name
	 * @param hp
	 * @param level
	 * @param strength
	 * @param dexterity
	 * @param intelligence
	 */
	public Character(String name, int x, int y, int hp, int level, int strength, int dexterity, int intelligence) {
		super(x, y, hp);
		this.name = name;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.level = level;
		this.xp = 0;
		this.inventory = new Inventory();
	}
	
	/**
	 * Compute and get defence of character
	 * 
	 * @return int Defence of the character
	 */
	public int getDefence() {
		return 10 + this.dexterity + this.armor.getDefence();
	}
	
	/**
	 * Rolls initiative of character
	 * @return int Initiative
	 */
	public int rollsInitiative() {
		int initiativeBonus = this.dexterity + this.level;
		this.initiative = Game.rollDice(20, initiativeBonus).getTotal();
		System.out.println(this.name + "'s initiative is " + this.initiative);
		return this.initiative;
	}
	
	
	/**
	 * Attack enemy and apply damage to him
	 * 
	 * @param enemy Destructible
	 */
	public void attack(Destructible enemy) {
		System.out.println("\n" + this.name + " attacks " + enemy + " !");
		
		int attackBonus = this.level + this.weapon.getModifier(this);
		DiceResult attack = Game.rollDice(20, attackBonus);

		int damage = this.weapon.getDamage(this, enemy, attack.getRoll());
		enemy.applyDamage(damage, this);
	}
	
	/**
	 * Tries to attack enemy and apply damage to him if the attack is successful
	 * 
	 * @param enemy Character
	 */
	public void attack(Character enemy) {
		System.out.println("\n" + this.name + " attacks " + enemy.name + " !");
		
		int attackBonus = this.level + this.weapon.getModifier(this);
		DiceResult attack = Game.rollDice(20, attackBonus);
		
		
		if (attack.getTotal() > enemy.getDefence()) {
			int damage = this.weapon.getDamage(this, enemy, attack.getRoll());
			enemy.applyDamage(damage, this);
		} else {
			System.out.println("The attack misses !");
			if (attack.getRoll() == 1) {
				System.out.println("Critical failure ! Slipping in the mud, " + this.name + " hurts itself !");
				this.applyDamage(1, this);
			}
		}
	}
	
	@Override
	public void applyDamage(int damageTaken, Character enemy) {
		super.applyDamage(damageTaken, enemy);
		
		System.out.println(this.name + " takes " + damageTaken + " damage !");
		
		if (this.isDead()) {
			this.dies(enemy);
		} else {
			System.out.println(this.name + " has " + this.hp + " health left.");
		}
	}
	
	/**
	 * What to do when the character dies
	 * 
	 * @param enemy Enemy that killed the character
	 */
	public void dies(Character enemy) {
		System.out.println("\n" + this.name + " dies !");
	}
	
	/**
	 * Check if character is dead
	 * 
	 * @return boolean
	 */
	public boolean isDead() {
		return super.isDestroyed();
	}
	
	/**
	 * Heal the character
	 * @param heal Heal amount
	 */
	public void heal(int heal) {
		this.hp += heal;
		
		if (this.hp > this.maxhp) {
			this.hp = this.maxhp;
		}
		
		System.out.println("You healed for " + heal + " HP");
		System.out.println("You have " + this.hp + " HP left");
	}
	
	/**
	 * Adds xp and computes level
	 * 
	 * @param xp Xp gained
	 */
	public void gainXp(int xp) {
		System.out.println(this.name + " gained " + xp + " xp !");
		this.xp += xp;
		
		// While and not a if to be able to level up multiple times in one shot
		while (this.xp >= Character.BASE_XP_NEEDED * this.level ) {
			this.levelUp();
			this.xp = this.xp%(Character.BASE_XP_NEEDED * this.level); // We add the remaining xp points
		}
	}
	
	/**
	 * Handles leveling up
	 */
	public void levelUp() {
		this.level++;
	}
	
	/**
	 * Equips new equipment on character and stock the old one in the inventory (NOT SUPPORTED (ABSTRACT CLASS))
	 * 
	 * @param equipment New equipment
	 */
	public void equip(Equipment equipment) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Equips new weapon on character and stock the old one in the inventory
	 * 
	 * @param equipment New weapon
	 */
	public void equip(Weapon equipment) {
		this.inventory.removeItem(equipment); // Remove item if it's in the inventory
		this.inventory.addItem(this.weapon);
		
		this.weapon = equipment;
	}
	
	/**
	 * Equips new armor on character and stock the old one in the inventory
	 * 
	 * @param equipment New armor
	 */
	public void equip(Armor equipment) {
		this.inventory.removeItem(equipment); // Remove item if it's in the inventory
		this.inventory.addItem(this.armor);
		
		this.armor = equipment;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getInitiative() {
		return this.initiative;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public int getDexterity() {
		return this.dexterity;
	}
	
	public int getIntelligence() {
		return this.intelligence;
	}

	public int getLevel() {
		return this.level;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public int getHealth() {
		return this.hp;
	}
	
	public int getMaxHealth() {
		return this.maxhp;
	}
}
