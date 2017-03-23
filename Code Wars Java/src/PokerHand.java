/*
 * Ranking Poker Hands
 * https://www.codewars.com/kata/ranking-poker-hands
 * 
 * This kata is 4 kyu difficulty
 * 
 * Fernando Balandran
 * 
 */

import java.util.*;

public class PokerHand
{      
    
	class Card implements Comparable<Card>
	{
			/**
		    * This card's suit, one of the constants SPADES, HEARTS, DIAMONDS,
		    * CLUBS, or JOKER.  The suit cannot be changed after the card is
		    * constructed.
		    */
		private int suit; 
		
		/**
		    * The card's value.  For a normal card, this is one of the values
		    * 1 through 13, with 1 representing ACE.  For a JOKER, the value
		    * can be anything.  The value cannot be changed after the card
		    * is constructed.
		    */
		private int value;
		private char rankValueChar;
		private char suitChar;
		   
		public final int getSuit() {
			return suit;
		}

		public final int getValue() {
			return value;
		}

		public final void setSuit(char suit) {
			
			// Compare char suit
			switch(suit){
			case 'S':
				this.suit = 0;
				break;
			case 'H':
				this.suit = 1;
				break;
			case 'D':
				this.suit = 2;
				break;
			case 'C':
				this.suit = 3;
				break;
			default:
				System.out.println("Wrong suit type input");
			}
		
		}

		public final void setValue(char value) {
			
			// Compare char value
			switch(value){
			case 'A':
				this.value = 14; // according to wiki the Ace is the highest card so I gave it a 14.
				break;
			case '2':
				this.value = 2;
				break;
			case '3':
				this.value = 3;
				break;
			case '4':
				this.value = 4;
				break;
			case '5':
				this.value = 5;
				break;
			case '6':
				this.value = 6;
				break;
			case '7':
				this.value = 7;
				break;
			case '8':
				this.value = 8;
				break;
			case '9':
				this.value = 9;
				break;
			case 'T':
				this.value = 10;
				break;
			case 'J':
				this.value = 11;
				break;
			case 'Q':
				this.value = 12;
				break;
			case 'K':
				this.value = 13;
				break;
			default:
				System.out.println("Wrong rank type input");
			}
			
		}

		   /**
		    * Creates a Card, takes a string i.e. 2H, then
		    * it sets rank, and suit in the constructor 
		    * Card [value=6, suit=1]
		    */
		public Card(String card) {
		      
			// Split two character string into two chars
			rankValueChar = card.charAt(0);
			suitChar = card.charAt(1);
					
			setValue(rankValueChar);
			setSuit(suitChar);
			
		}

		@Override
		public String toString() {
			return "Card [value=" + value + ", suit=" + suit + "]\n";
		}

		/*
		 * This method overrides compareTo, and card class implements Comparator
		 * compareTo is enough if only need to sort with a single property
		 * 
		 * Custom comparator will be needed when doing multiple properties
		 * 
		 */
		@Override
		public int compareTo(Card compareCard) {
			
			int compareValue = ((Card) compareCard).getValue();
			
			// ascending order
			return this.value - compareValue;

		}
		
	}
	
	
	public enum Result { TIE, WIN, LOSS }
    public List<Card> pokerHand = new ArrayList<Card>();
    public List<Card> sortedPokerHand = new ArrayList<Card>();
    public String splitHand[] = new String[5];
    public int pokerHandRanking;
    public int pokerHandSum;
    public int highestCard;
    public int onePairSum;
    public boolean hasRanking;
   
    PokerHand(String hand)
    {
    	// Split the hand input values into 5 Cards
    	splitHand = hand.split(" ");
    	
    	// From the splitHand array need to initialize 5 Cards with those values
    	for(int i = 0; i < 5; i++)
    	{
    		pokerHand.add(new Card(splitHand[i])); // Create 5 Cards
    	}
    	
    	Collections.sort(pokerHand);
    	
    	 /*
    	  * Check poker hand rankings here, assign hand ranking 1-10
    	  * Also assign sum of all values 
    	  * Add here all possible ranking hands from higher to lower ranking
    	  * Check if a high ranking is made if it is do not run all methods 	
    	 */
    	
    	if(!hasRanking)
    	{
    		isRoyalFlush(pokerHand); // 1
    	}
    	
    	if(!hasRanking)
    	{
    		isStraightFlush(pokerHand); // 2
    	}
    	
    	if(!hasRanking)
    	{
    		isFourOfAKind(pokerHand); // 3
    	}
    	
    	if(!hasRanking)
    	{
        	isFullHouse(pokerHand); // 4
    	}
    	
    	if(!hasRanking)
    	{
        	isFlush(pokerHand); // 5
    	}
    	
    	if(!hasRanking)
    	{
        	isStraight(pokerHand); // 6
    	}
    	
    	if(!hasRanking)
    	{
        	isThreeOfAKind(pokerHand); // 7
    	}
    	
    	if(!hasRanking)
    	{
        	isTwoPairs(pokerHand); // 8
    	}
    	
    	if(!hasRanking)
    	{
        	isOnePair(pokerHand); // 9
    	}
    	
    	if(!hasRanking)
    	{
        	setHighCard(pokerHand); // 10
    	}
    	 	
    }
    
    // High card
    // ranking 10
    public void setHighCard(List<Card> cards)
    {
    	this.highestCard = cards.get(4).getValue();
    	this.pokerHandRanking = 10;
    	sumAllValues(pokerHand);
    }
    
    // One pair
    // ranking 9
    public boolean isOnePair(List<Card> cards)
    {
    	boolean isOnePair = false;
    	int pairCounter = 0;
    	
    	for(int i = 0; i < cards.size() - 1; i++)
    	{
    		if(cards.get(i).getValue() == cards.get(i + 1).getValue())
    		{
    			pairCounter++;
    			this.onePairSum = cards.get(i).getValue() + cards.get(i + 1).getValue();
    		}
    		
    	}
    	
    	if(pairCounter == 1)
    	{
    		isOnePair = true;
    		this.pokerHandRanking = 9;
    		sumAllValues(pokerHand);
    		this.hasRanking = true;
    	}
    	
    	return isOnePair;
    }
    
    // Two different pairs
    // ranking 8
    public boolean isTwoPairs(List<Card> cards)
    {
    	boolean isTwoPairs = false;
    	    	
    	// check pairs in the begining, OR end consecutive OR one pair in the beginning and one pair at the end    	
    	if(cards.get(0).getValue() == cards.get(1).getValue() &&
    			cards.get(2).getValue() == cards.get(3).getValue() ||
    				cards.get(1).getValue() == cards.get(2).getValue() &&
    					cards.get(3).getValue() == cards.get(4).getValue() ||
    						cards.get(0).getValue() == cards.get(1).getValue() &&
    							cards.get(3).getValue() == cards.get(4).getValue())
    	{
    		isTwoPairs = true;
    		this.pokerHandRanking = 8;
    		sumAllValues(pokerHand);
    		this.hasRanking = true;
    	}
    	
    	return isTwoPairs;
    }
    
    // Three of a kind of same rank value
    // ranking 7
    public boolean isThreeOfAKind(List<Card> cards)
    {
    	boolean isThreeOfAKind = false;
    	
    	// check if it has three , at the begining OR ending of hand OR middle
    	// TODO refactor
    	if(cards.get(2).getValue() == cards.get(3).getValue() &&
    			cards.get(3).getValue() == cards.get(4).getValue()	||
    				cards.get(0).getValue() == cards.get(1).getValue() &&
    					cards.get(1).getValue() == cards.get(2).getValue() ||
    						cards.get(1).getValue() == cards.get(2).getValue() &&
    							cards.get(2).getValue() == cards.get(3).getValue())
    	{
    		isThreeOfAKind = true;
    		this.pokerHandRanking = 7;
    		sumAllValues(pokerHand);
    		this.hasRanking = true;
    	}    	
    	
    	return isThreeOfAKind;
    }
    
    // Straight: Five cards in sequence, but not same suit
    // ranking 6
    public boolean isStraight(List<Card> cards)
    {
    	boolean isStraight = false;
    	
    	if(isHandAscending(cards))
    	{
    		isStraight = true;
    		this.pokerHandRanking = 6;
    		sumAllValues(pokerHand);
    		this.hasRanking = true;
    	}
    	
    	return isStraight;
    }
    
    // Flush: If all cards are the same suit, but not in sequence
    // ranking 5
    public boolean isFlush(List<Card> cards)
    {
    	boolean isFlush = false;    	
    	
    	if(!isHandAscending(cards) && isSameSuit(cards))
    	{
    		isFlush = true;
    		this.pokerHandRanking = 5;
    		sumAllValues(pokerHand);
    		this.hasRanking = true;
    	}   	
    	    	
    	return isFlush;
    }
    
    // Full house: Three of a kind with a pair.
    // Since cards are sorted i.e. 9, 9, 10, 10, 10.
    // ranking = 4
    public boolean isFullHouse(List<Card> cards)
    {
    	boolean isFullHouse = false;
    	boolean hasPair = false;
    	boolean hasThree = false;
    	List<Card> copyOfCards = new ArrayList<>(cards);
    	
    	// Make copy of cards list, remove three, then check for pair
    	// Need a better way to check for full house!
    	
    	// check if it has three , at the begining OR ending of hand
    	if(copyOfCards.get(2).getValue() == copyOfCards.get(3).getValue() &&
    			copyOfCards.get(3).getValue() == copyOfCards.get(4).getValue()	||
    					copyOfCards.get(0).getValue() == copyOfCards.get(1).getValue() &&
    							copyOfCards.get(1).getValue() == copyOfCards.get(2).getValue())
    	{
    		hasThree = true;
    		
    		
    		// Then check for a pair
    		if(copyOfCards.get(0).getValue() == copyOfCards.get(1).getValue() &&
        			copyOfCards.get(1).getValue() == copyOfCards.get(2).getValue())
    		{
    			// check if it has pair on the remaining cards
    	    	if(copyOfCards.get(3).getValue() == copyOfCards.get(4).getValue())
    	    	{
    	    		hasPair = true;
    	    	}
    		}
    		
    		// Then check for a pair
    		if(copyOfCards.get(2).getValue() == copyOfCards.get(3).getValue() &&
        			copyOfCards.get(3).getValue() == copyOfCards.get(4).getValue())
    		{
    			// check if it has pair on the remaining cards
    	    	if(copyOfCards.get(0).getValue() == copyOfCards.get(1).getValue())
    	    	{
    	    		hasPair = true;
    	    	}
    		}
    	}    	
    	
    	if(hasPair && hasThree)
    	{
    		isFullHouse = true;
    		this.pokerHandRanking = 4;
    		sumAllValues(pokerHand);
    		this.hasRanking = true;
    	}
    	
    	return isFullHouse;
    }
    
    // Four of a kind: Four cards of the same rank.
    // ranking 3
    public boolean isFourOfAKind(List<Card> cards)
    {
    	boolean isFourOfAKind = false;
    	int fourCounter = 0;
    	
    	for(int i = 0; i < cards.size() -1; i++)
    	{
    		if(cards.get(i).getValue() == cards.get(i + 1).getValue())
    		{
    			fourCounter++;
    		}
    	}
    	
    	if(fourCounter == 3)
    	{
    		isFourOfAKind = true;
    		this.pokerHandRanking = 3;
    		sumAllValues(pokerHand);
    		this.hasRanking = true;
    	}
    	
    	return isFourOfAKind;
    }
    
    // Cards in sequence, and same suit.
    // ranking 2
    public boolean isStraightFlush(List<Card> cards)
    {
		
    	boolean isStraighFlush = false;
    	
    	if(isHandAscending(cards) && isSameSuit(cards))
    	{
    		isStraighFlush = true;
    		this.pokerHandRanking = 2;
    		sumAllValues(pokerHand);
    		this.hasRanking = true;
    	}
    	    	
    	return isStraighFlush;
    }
    
    // Royal Flush 10, 11, 12, 13, 14. And all same suit.
    // ranking 1
    public boolean isRoyalFlush(List<Card> cards)
    {
    	boolean isRoyalFlush = false;
    	int ascendingCounter = 0;
    	
    	for(int i = 0; i < cards.size(); i++)
    	{
    		// cards should match 10 - 14
    		if(cards.get(i).getValue() == 10 + i)
    		{
    			ascendingCounter++;
    		}
    		
    	}
    	
    	if(ascendingCounter == 5 && isSameSuit(cards))
    	{
    		isRoyalFlush = true;
    		this.pokerHandRanking = 1;
    		sumAllValues(pokerHand);
    		this.hasRanking = true;
    	}
    	
    	return isRoyalFlush;
    }
    
    // Helper methods
    // Sum all values
    public void sumAllValues(List<Card> cards)
    {
    	// Sum all values
    	for(int i = 0; i < 5; i++)
    	{
    		this.pokerHandSum = this.pokerHandSum + cards.get(i).getValue();
    	}
    	
    }
    
    // Are cards ascending all the way - i.e. 4,5,6,7,8 should return true
    // 4,6,8,10,12 should return false
    public boolean isHandAscending(List<Card> sortedCards)
    {
    	boolean isAscending = false;
    	int ascendingCounter = 0;
    	
    	for(int i = 0; i < sortedCards.size() -1; i++)
    	{
    		// If cards are not ascending break out the loop
    		if(sortedCards.get(i).getValue() + 1 == sortedCards.get(i + 1).getValue())
    		{
    			ascendingCounter++;
    		}
    		
    		//isAscending = true;
    	}
    	
    	if(ascendingCounter == sortedCards.size() -1)
    	{
    		isAscending = true;
    	}
    	
    	return isAscending;
    }
    
    // Are all cards same suit
    public boolean isSameSuit(List<Card> cards)
    {
    	boolean isSameSuit = false;
    	int sameSuitCounter = 0;
    	
    	// Are all the cards one suit?
    	for(int i = 0; i < cards.size() - 1; i++)
    	{
    		// Check if all suits match
    		if(cards.get(i).getSuit() == cards.get(i + 1).getSuit())
    		{
    			sameSuitCounter++;
    		}
    		
    		// isSameSuit = true;
    	}
    	
    	if(sameSuitCounter == cards.size() -1)
    	{
    		isSameSuit = true;
    	}
    	
    	return isSameSuit;
    }
    
    // Print hand method
    public void printHand(List<Card> cards)
    {
    	System.out.println(cards.toString());
    }
    
    public Result checkValues(PokerHand hand)
    {
    	// check values
		
		if(this.pokerHandSum > hand.pokerHandSum)
		{
			return Result.WIN;
		}
		if(this.pokerHandSum < hand.pokerHandSum)
		{
			return Result.LOSS;
		}
		if(this.pokerHandSum == hand.pokerHandSum)
		{
			return Result.TIE;
		}
		
		return Result.TIE;
    }
    
    // Highest card in the hand

    public Result compareWith(PokerHand hand) {
    	
    	// If this.powerHandRanking is lower is a straight win
    	if(this.pokerHandRanking < hand.pokerHandRanking)
    	{
    		return Result.WIN;
    	}
    	
    	if(this.pokerHandRanking > hand.pokerHandRanking)
    	{
    		return Result.LOSS;
    	}
    	
    	// Special case here to check for highest card
    	if(this.pokerHandRanking == 10 && hand.pokerHandRanking == 10)
    	{
    		if(this.highestCard > hand.highestCard)
    		{
    			return Result.WIN;
    		}
    		if(this.highestCard < hand.highestCard)
    		{
    			return Result.LOSS;
    		}
    		if(this.highestCard == hand.highestCard)
    		{
    			// check if is a tie first
    			if(this.pokerHandSum == hand.pokerHandSum)
    			{
    				return Result.TIE;
    			}
    			
    			// Need to check the next card down...
    			int i = 4;
    			do{
    				
    				if(this.pokerHand.get(i).getValue() == hand.pokerHand.get(i).getValue())
    				{
    					i--;
    				}
    				
    			}while(this.pokerHand.get(i).getValue() == hand.pokerHand.get(i).getValue());
    			
    			    			
    			// Once the loop stops, get i and compare.
    			if(this.pokerHand.get(i).getValue() > hand.pokerHand.get(i).getValue())
    			{
    				return Result.WIN;
    			}
    			
    			if(this.pokerHand.get(i).getValue() < hand.pokerHand.get(i).getValue())
    			{
    				return Result.LOSS;
    			}
    			
    			// ...Should not get here
    			if(this.pokerHand.get(i).getValue() == hand.pokerHand.get(i).getValue())
    			{
    				return Result.TIE;
    			}
    		}
    	}
    	
    	// Check highest pair
    	if(this.pokerHandRanking == 9 && hand.pokerHandRanking == 9)
    	{
    		if(this.onePairSum > hand.onePairSum)
    		{
    			return Result.WIN;
    		}
    		
    		if(this.onePairSum < hand.onePairSum)
    		{
    			return Result.LOSS;
    		}
    		
    		if(this.onePairSum == hand.onePairSum)
    		{
    			return checkValues(hand); 
    		}
    	}
    	
    	// Check highest two pairs
    	if(this.pokerHandRanking == 8 && hand.pokerHandRanking == 8)
    	{
    		return checkValues(hand);
    	}
    	
    	// Check highest three of a kind
    	if(this.pokerHandRanking == 7 && hand.pokerHandRanking == 7)
    	{
    		return checkValues(hand);
    	}
    	
    	// Check highest three of a kind
    	if(this.pokerHandRanking == 6 && hand.pokerHandRanking == 6)
    	{
    		return checkValues(hand);
    	}
    	
    	if (this.pokerHandRanking == hand.pokerHandRanking && this.pokerHandRanking <= 5) {
			// if both hands have the same ranking, then check who has the highest value
			// will need different conditions depending on the handRanking
			if (this.pokerHandRanking == hand.pokerHandRanking) {
				// check pokerHandSum
				return checkValues(hand);
			}
		}
		return Result.TIE;
    }
    
    // Main method
    public static void main(String[] args)
    {
    	PokerHand myHand = new PokerHand("2H 3H 4H 5H 6H");
    	PokerHand opposingHand = new PokerHand("KS AS TS QS JS");
    }
	
}
