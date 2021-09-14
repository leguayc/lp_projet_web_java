package BatailleCartes;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> deck;
	
	/**
	 * Constructor without parameters
	 * 
	 * @param withInit
	 */
	public Deck() {
		this.deck = new ArrayList<Card>();
	}
	
	/**
	 * Initialize the deck with all the cards
	 */
	public void initDeck() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++) {
				this.deck.add(new Card(i, j));
			}
		}
	}
	
	/**
	 * Shuffle the deck and distribute the cards to the player
	 * 
	 * @param p1 Player
	 * @param p2 Player
	 */
	public void shuffleAndDistribute(Player p1, Player p2) {
		int random = 0;
		while(deck.size() != 0) {
			random = (int)(Math.random() * deck.size());
			p1.addCard(deck.get(random));
			deck.remove(random);
			random = (int)(Math.random() * deck.size());
			p2.addCard(deck.get(random));
			deck.remove(random);
		}
	}
	
	/**
	 * Get card from deck at the chosen index
	 * 
	 * @param index int
	 * 
	 * @return Card
	 */
	public Card getCard(int index) {
		return this.deck.get(index);
	}
	
	/**
	 * Add chosen card to deck
	 * 
	 * @param c Card
	 */
	public void addCard(Card c) {
		this.deck.add(c);
	}
	
	/**
	 * Remove card from deck at the chosen index
	 * 
	 * @param index
	 * 
	 * @return Card
	 */
	public Card removeCard(int index) {
		return this.deck.remove(index);
	}
	
	/**
	 * Get deck size
	 * 
	 * @return int
	 */
	public int getSize() {
		return this.deck.size();
	}
}
