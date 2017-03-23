
//**************************************************************
// Author: nimou
// Date: Jan 31, 2017
// 
// Program: PokerHand2.java
// 
// 
// 
//**************************************************************

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.IntStream;

public class PokerHand2{

  public enum Result { TIE, WIN, LOSS } 

  public enum HandRank {
      HIGH_CARD, ONE_PAIR, TWO_PAIR,
      TRHEE_OF_A_KIND, STRAIGHT, FLUSH, 
      FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH
  }
  
  private static Map<Character,Integer> valueMap = new HashMap<>();;
  static{
    char[] cardValues = {'2','3','4','5','6','7','8','9','T','J','Q','K','A'};
    int[] realValues = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    for(int i=0;i<cardValues.length;i++)
      valueMap.put(cardValues[i],realValues[i]);
  }
  
  private static class Card{
    Integer value;
    char suit;
    public Card(char value,char suit){
      this.value = valueMap.get(value);
      this.suit = suit;
    }
  }
  
  private List<Card> cards;
  private HandRank rank; 
  
  PokerHand2(String hand){
    cards = new ArrayList<>();
    for(String card:hand.split(" "))
      cards.add(new Card(card.charAt(0),card.charAt(1)));
    cards.sort((c1,c2)->c1.value.compareTo(c2.value));
    rank = getRank();
  }
  
  public Result compareWith(PokerHand2 hand){        
    int result = 0;
    if(rank == hand.rank){
      for(int i=0;i<cards.size();i++){
        Integer val1 = cards.get(i).value;
        Integer val2 = hand.cards.get(i).value;
        if(val1!=val2) result = val1.compareTo(val2);
      }
    }
    else
      result = rank.compareTo(hand.rank);
    return result>0 ? Result.WIN : result<0 ? Result.LOSS : Result.TIE;
  }
  
  private HandRank getRank(){
    List<Integer> groups = getGroups();
    int value = groups.size()>0?groups.get(0):0;
    
    switch(groups.size()){
    case 0:
      boolean isStraight = IntStream.range(0, cards.size()-1).allMatch(i->cards.get(i+1).value-cards.get(i).value == 1);
      boolean isFlush = IntStream.range(0, cards.size()-1).allMatch(i->cards.get(i+1).suit==cards.get(i).suit);
      if(isStraight && isFlush)
        return HandRank.STRAIGHT_FLUSH;
      if(isStraight)
        return HandRank.STRAIGHT;
      if(isFlush)
        return HandRank.FLUSH;
      return HandRank.HIGH_CARD;
    case 1:
      return value==2 ? HandRank.ONE_PAIR : value==3 ? HandRank.TRHEE_OF_A_KIND : HandRank.FOUR_OF_A_KIND;
    case 2:
      return value == 3 ? HandRank.FULL_HOUSE : HandRank.TWO_PAIR;
    }
    throw new IllegalArgumentException("ERROR");
  }
  
  private List<Integer> getGroups(){
    List<Integer> groups = new ArrayList<>();
    for(int i=1,count=0;i<cards.size();i++){
      int val = cards.get(i-1).value;
      int nextVal = cards.get(i).value;
      if(val == nextVal)
        count++;
      if(count > 0 && (nextVal != val || i==cards.size()-1)){
        groups.add(++count);
        count = 0;  
      }
    }
    groups.sort((a,b)->b.compareTo(a));
    return groups;
  }
}
