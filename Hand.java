import java.util.List;
import java.util.ArrayList;

public class Hand{
   private List<Card> h;
   private int value=0;
   private Card[] c;
   
   public Hand(){
      c= new Card[4];  // nevojitet per kontrollim dhe ndryshim te vleres se ace
      c[0]= new Card("1c",1);                   
      c[1]= new Card("1d",1);
      c[2]= new Card("1h",1);
      c[3]= new Card("1s",1);
      h= new ArrayList<Card>();
   }
   
   // shton nje leter ne Hand
   protected void add(Card c){
      h.add(c);
      value+=c.value;
   }
   
   //kontrollon nese lojtari permban nje ace dhe ndryshon vleren e saj nese nevojitet
   private void ace(){
      for(int i=0; i!=h.size(); i++){
         if(h.get(i).count==1 && h.get(i).value==11){
            h.get(i).value=1;
            this.value-=10;
            break;
         }
      } 
   }
   
   // kthen si rezultat vleren e Hand-it (letrave qe permban nje hand)
   protected int getValue(){
      if(value>21){ ace(); }
      return value;
   }
   
   // vendos vleren e nje hand-i
   protected void setValue(int i){
      value=i;
   }
   
   // fshin te gjitha elementet (letrat) nga List dhe ben vleren e hand 0
   protected void clear(){
      h.clear();
      value=0;
   }
   
   // kthen si rezultat listen qe permban letrat e nje lojtari
   protected List<Card> getList(){
      return h;
   }
}
