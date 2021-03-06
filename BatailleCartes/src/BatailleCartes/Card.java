package BatailleCartes;

public class Card {
	private static String[] colors = {"spades", "clubs", "diamonds", "hearts"};
	private static int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
	private String color;
	private int value;
	
	/**
	 * Constructor without parameters
	 */
	public Card() {
		int randomCouleur = (int)(Math.random() * 3);
		int randomValue = (int)(Math.random() * 12);
		this.color = colors[randomCouleur];
		this.value = values[randomValue];
	}
	
	/**
	 * Constructor taking value and checking them
	 * 
	 * @param color String
	 * @param value int
	 */
	public Card(String color, int value) {
		for(int i = 0; i < colors.length; i++) {
			if(color == colors[i]) {
				this.color = color;
				break;
			}
		}
		
		if(this.color != color) {
			this.color = colors[0];
		}
		
		for(int i = 0; i < values.length; i++) {
			if(value == values[i]) {
				this.value = value;
				break;
			}
		}
		
		if(this.value != value) {
			this.value = values[0];
		}
	}
	
	/**
	 * Constructor using ID of colors and values
	 * 
	 * @param colorID
	 * @param valueID
	 */
	public Card(int colorID, int valueID) {
		if (colorID >= 0 && colorID < colors.length) {
			this.color = colors[colorID];
		}
		
		if (valueID >= 0 && valueID < values.length) {
			this.value = values[valueID];
		}
	}
	
	/**
	 * Compare this card with Card c, -1 = this card is greater, 0 = tie, 1 = c is greater
	 * 
	 * @param c Card
	 * 
	 * @return int
	 */
	public int compareCard(Card c) {
		if(this.value > c.value) {
			return -1;
		}
		else if(this.value == c.value) {
			return 0;
		}
		else return 1;
	}
	
	/**
	 * Get color of card
	 * 
	 * @return String
	 */
	public String getColor() {
		return this.color;
	}
	
	/**
	 * Get value of card
	 * 
	 * @return int
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * ToString of card, translate values to name of picture cards
	 */
	public String toString() {
		String str = "";
		
		switch(this.value) {
			case 11: { str = "Jack"; break; }
			case 12: { str = "Queen"; break; }
			case 13: { str = "King"; break; }
			case 14: { str = "Ace"; break; }
			default: { str = "" + this.value; break; }
		}
		
		return str + " of " + this.color;
	}
}
