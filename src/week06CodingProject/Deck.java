package week06CodingProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Deck {
	
	private List<Card> cards;

    // Creates a standard 52-card deck.
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                int value = i + 2;
                String name = ranks[i] + " of " + suit;
                cards.add(new Card(value, name));
            }
        }
    }

    // Shuffles the deck.
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Deals a card from the deck.
    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    // Returns the number of cards in the deck.
    public int getNumberOfCards() {
        return cards.size();
    }
}

