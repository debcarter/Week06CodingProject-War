package week06CodingProject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class WareGameTest {

	 // Tests the Card class
    @Test
    void testCard() {
        Card card = new Card(10, "Ten of Hearts");
        assertEquals(10, card.getValue());
        assertEquals("Ten of Hearts", card.getName());
    }

    // Tests the Deck class
    @Test
    void testDeck() {
        Deck deck = new Deck();
        assertEquals(52, deck.getNumberOfCards());

        deck.shuffle();
        assertEquals(52, deck.getNumberOfCards()); // Still 52 after shuffling

        Card dealtCard = deck.dealCard();
        assertNotNull(dealtCard);
        assertEquals(51, deck.getNumberOfCards()); // Should be 51 after dealing one card

        // Deals all cards and ensures deck is empty
        for (int i = 0; i < 51; i++) {
            deck.dealCard();
        }
        assertNull(deck.dealCard()); // Should be null when the deck is empty
    }

    // Tests the Player class
    @Test
    void testPlayer() {
        Player player = new Player("Test Player");
        assertEquals("Test Player", player.getName());
        assertEquals(0, player.getScore());
        assertTrue(player.getHand().isEmpty());

        Card card = new Card(5, "Five of Clubs");
        
        player.addCardToHand(card);
        assertEquals(1, player.getNumberOfCardsInHand());
        assertEquals(card, player.getHand().get(0));

        player.incrementScore();
        assertEquals(1, player.getScore());

        Card playedCard = player.playCard();
        assertEquals(card, playedCard);
        assertTrue(player.getHand().isEmpty());
        assertEquals(0, player.getNumberOfCardsInHand());
    }

    // Tests a basic game scenario (simplified)
    @Test
    void testGameScenario() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Manually creates hands for a simple test
        List<Card> hand1 = new ArrayList<>();
        hand1.add(new Card(10, "Ten of Hearts"));
        hand1.add(new Card(5, "Five of Clubs"));
        player1.getHand().addAll(hand1);

        List<Card> hand2 = new ArrayList<>();
        hand2.add(new Card(8, "Eight of Spades"));
        hand2.add(new Card(13, "King of Diamonds"));
        player2.getHand().addAll(hand2);

        // Simulates a round
        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        int comparison = card1.compareRank(card2);
        if (comparison > 0) {
            player1.addCardToHand(card1);
            player1.addCardToHand(card2);
            player1.incrementScore();
        } else if (comparison < 0) {
            player2.addCardToHand(card1);
            player2.addCardToHand(card2);
            player2.incrementScore();
        }

        // Asserts based on the simulated round
        if (comparison > 0) {
            assertEquals(1, player1.getScore());
            assertEquals(0, player2.getScore());
            assertEquals(3, player1.getNumberOfCardsInHand());
            assertEquals(1, player2.getNumberOfCardsInHand());
        } else if (comparison < 0) {
            assertEquals(0, player1.getScore());
            assertEquals(1, player2.getScore());
            assertEquals(1, player1.getNumberOfCardsInHand());
            assertEquals(2, player2.getNumberOfCardsInHand());
        }
    }
}


