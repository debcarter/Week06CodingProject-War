package week06CodingProject;


import java.util.ArrayList;
import java.util.List;


public class Player {

	 private List<Card> hand;
	    private String name;
	    private int score;

	    // Creates a player.
	    public Player(String name) {
	        this.name = name;
	        hand = new ArrayList<>();
	        score = 0;
	    }
	    //Generated getters and setters for player name, hand, and score.
		public List<Card> getHand() {
			return hand;
		}

		public void setHand(List<Card> hand) {
			this.hand = hand;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

	    // Adds a card to the player's hand.
	    public void addCardToHand(Card card) {
	        hand.add(card);
	    }
		 // Plays a card from the top of the hand.
	    public Card playCard() {
	        if (!hand.isEmpty()) {
	            return hand.remove(0);
	        }
	        return null;
	    }

	    // Increases the player's score.
	    public void incrementScore() {
	        score++;
	    }

	    // Describes the player and their hand.
	    public void describe() {
	        System.out.println("Player: " + name);
	        System.out.println("Score: " + score);
	        System.out.println("Hand:");
	        for (Card card : hand) {
	            card.describe();
	        }
	        
	    }
	    
	    // Returns the number of cards in the player's hand.
	    public int getNumberOfCardsInHand() {
	        return hand.size();
	    }
	    
}
