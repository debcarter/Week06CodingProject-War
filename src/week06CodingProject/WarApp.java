package week06CodingProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarApp {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Gets player names
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine();

        // Creates players and deck
        Player player1 = new Player(player1Name);
        Player player2 = new Player(player2Name);
        Deck deck = new Deck();

        // Shuffles and deals cards
        deck.shuffle();
        while (deck.getNumberOfCards() > 0) {
            player1.addCardToHand(deck.dealCard());
            player2.addCardToHand(deck.dealCard());
        }

        // Game loop
        int round = 1;
        int totalNumberOfRounds = 1000; // To limit number of rounds to prevent an infinite loop.
        while (player1.getNumberOfCardsInHand() > 0 && player2.getNumberOfCardsInHand() > 0) {
            System.out.println("\n--- Round " + round + " ---");

            Card card1 = player1.playCard();
            Card card2 = player2.playCard();

            System.out.println(player1.getName() + " plays: " + card1.getName());
            System.out.println(player2.getName() + " plays: " + card2.getName());

            // Compares cards
            int comparison = card1.compareRank(card2);

            if (comparison > 0) {
                System.out.println(player1.getName() + " wins the round!");
                player1.addCardToHand(card1);
                player1.addCardToHand(card2);
                player1.incrementScore();
            } else if (comparison < 0) {
                System.out.println(player2.getName() + " wins the round!");
                player2.addCardToHand(card1);
                player2.addCardToHand(card2);
                player2.incrementScore();
            } else { // It's a tie (War)
                System.out.println("It's a tie! Beginning War!");
                List<Card> warPile = new ArrayList<>();
                warPile.add(card1);
                warPile.add(card2);

                // War loop
                int tieCount = 0;
                while (comparison == 0 && player1.getNumberOfCardsInHand() > 0 && player2.getNumberOfCardsInHand() > 0) {
                	 tieCount++; // Increment tie counter
                	    if (tieCount > 10) { // Limit consecutive ties to 10 to help eliminate infinite loop potential.
                	        System.out.println("Too many ties! The round is a draw.");
                	        warPile.clear(); // Discard the cards
                	        break; // Exit the War loop
                	    }
                    // Players lay down three cards face down and one face up
                    int warCardsToPlay = 3;
                    if (player1.getNumberOfCardsInHand() < warCardsToPlay + 1 || player2.getNumberOfCardsInHand() < warCardsToPlay + 1) {
                         System.out.println("One player doesn't have enough cards for War!");
                         break;
                    }

                    for (int i = 0; i < warCardsToPlay; i++) {
                        warPile.add(player1.playCard());
                        warPile.add(player2.playCard());
                    }

                    Card warCard1 = player1.playCard();
                    Card warCard2 = player2.playCard();
                    warPile.add(warCard1);
                    warPile.add(warCard2);

                    System.out.println(player1.getName() + " plays War card: " + warCard1.getName());
                    System.out.println(player2.getName() + " plays War card: " + warCard2.getName());

                    comparison = warCard1.compareRank(warCard2);

                    if (comparison > 0) {
                        System.out.println(player1.getName() + " wins the War!");
                        player1.getHand().addAll(warPile);
                        warPile.clear();
                    } else if (comparison < 0) {
                        System.out.println(player2.getName() + " wins the War!");
                        player2.getHand().addAll(warPile);
                        warPile.clear();
                    } else {
                        System.out.println("Another tie! Continuing War!");
                    }
                }
            }

            round++;
            if (round >= totalNumberOfRounds) {  //Compares current round with total number of rounds allowed 
            	System.out.println("Maximum number of rounds reached. The game is a draw.");
            	break;
            }
        }

        // Determines the winner
        System.out.println("\n--- Game Over ---");
        if (player1.getNumberOfCardsInHand() > 0) {
            System.out.println(player1.getName() + " wins the game!");
        } else {
            System.out.println(player2.getName() + " wins the game!");
        }

        System.out.println(player1.getName() + "'s final score: " + player1.getScore());
        System.out.println(player2.getName() + "'s final score: " + player2.getScore());

        scanner.close();
  
       }	
	}
	
	