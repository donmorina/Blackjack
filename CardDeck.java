import java.util.ArrayList;
import java.util.List;

public class CardDeck{
   protected int card_count;  
   private List<Card> deck = new ArrayList<Card>();
   
   public CardDeck(){
      createSuit(Card.SPADES);
      createSuit(Card.HEARTS);
      createSuit(Card.CLUBS);
      createSuit(Card.DIAMONDS);
      card_count= deck.size();
   }
   
   // merr nje leter nga pakoja ne menyre te rastesishme
   protected Card newCard(int f){
      Card next_card = null;
      int index = (int)(Math.random() * card_count);  
      next_card = deck.get(index);
      deck.remove(index);
      card_count = deck.size();
      if(f==1){ next_card.setFlipedImage(); }
      return next_card;
   }
   
   // krijon letrat me nje logo (suit) ne baz te parametrit hyres
   private void createSuit(String which_suit){ 
      for ( int i=1;  i<=Card.SIZE_OF_ONE_SUIT;  i++){ 
         deck.add(new Card(which_suit, i));
      }
   }
   
   // ben krijimin e nje pakoje te re
   protected void newDeck(int d, List<Card> p1, List<Card> p2, List<Card> p3){
      createSuit(Card.SPADES);
      createSuit(Card.HEARTS);
      createSuit(Card.CLUBS);
      createSuit(Card.DIAMONDS);
      if(d==1){                                                        //eleminimi nga pakoja e letrave qe lojtaret veq se jan duke i perdorur
         for(int i=0; i!=p1.size(); i++){
            deck.remove(new Card(p1.get(i).suit,p1.get(i).count));
         }
         for(int i=0; i!=p2.size(); i++){
            deck.remove(new Card(p2.get(i).suit,p2.get(i).count));
         }
         for(int i=0; i!=p3.size(); i++){
            deck.remove(new Card(p3.get(i).suit,p3.get(i).count));
         }
      }
      card_count= deck.size();
   }
}