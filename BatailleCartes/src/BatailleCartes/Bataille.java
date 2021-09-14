package BatailleCartes;

public class Bataille {

	public static void main(String[] args) {
		// Declaration of variables
		Deck deck = new Deck();
		Player p1 = new Player();
		Player p2 = new Player();
		Card c1;
		Card c2;
		int compareCard = 0;
		String str = "";
		
		// Initialize deck, then shuffle and distribute cards to players
		deck.initDeck();
		deck.shuffleAndDistribute(p1, p2);
		
		// While nobody has won, do stuff
		while(!p1.hasWon() && !p2.hasWon()) {
			// We pick a card from both player's deck and compare them
			c1 = p1.pickCard();
			c2 = p2.pickCard();
			compareCard = c1.compareCard(c2);
			
			switch(compareCard) {
				case -1: { 
					str = "P1 wins !";
					// If player 1 won, then you add cards to player 1's deck
					p1.addCard(c1);
					p1.addCard(c2);
					break;
				}
				case 0: { 
					str = "Tie !";
					// If it's a tie, then the cards go back to their owner's deck
					p1.addCard(c1);
					p2.addCard(c2);
					break;
				}
				case 1: { 
					str = "P2 wins !";
					// If player 2 won, then you add cards to player 2's deck
					p2.addCard(c1);
					p2.addCard(c2);
					break; 
				}
				default: { str = "Error !"; break; }
			}

			System.out.println(str + " " + c1 + " (P1) versus " + c2 + " (P2).");
		}
		
		// Check who won and display it
		if(p1.hasWon()) {
			System.out.println("P1 wins the game !");
		}
		else if(p2.hasWon()) {
			System.out.println("P2 wins the game !");
		}
	}

}
