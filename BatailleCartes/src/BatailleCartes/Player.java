package BatailleCartes;

public class Player {
	private Deck deck;
	
	/**
	 * Constructor
	 */
	public Player() {
		this.deck = new Deck();
	}
	
	/**
	 * Pick a card from the player's deck
	 * 
	 * @return
	 */
	public Card pickCard() {
		Card c;
		if(deck.getSize() > 0) {
			c = deck.removeCard(0);
		}
		else {
			c = null;
		}
		
		return c;
	}
	
	/**
	 * Add card to player's deck
	 * 
	 * @param c Card
	 */
	public void addCard(Card c) {
		deck.addCard(c);
	}
	
	/**
	 * Get player's deck size
	 * 
	 * @return int
	 */
	public int getDeckSize() {
		return deck.getSize();
	}
	
	/**
	 * Check if player has won
	 * 
	 * @return boolean
	 */
	public boolean hasWon() {
		return deck.getSize() == 52;
	}
}
