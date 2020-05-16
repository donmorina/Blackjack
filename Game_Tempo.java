import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Game_Tempo{
   private GameView v;
   private Hand player[], h2;
   private CardDeck d1,d2;
   
   public Game_Tempo(Hand p[], Hand h2, CardDeck d1, CardDeck d2){
      player = new Hand[3];
      this.h2=h2;
      for(int i=0; i!=p.length; i++){ player[i]=p[i]; }
      v= new GameView(p,h2,d1,d2);
      this.d1=d1;
      this.d2=d2;
      start();
      tempo();
   }
   
   public void tempo(){
      while(true){
         System.out.print("");
         if(!v.b[2].getText().equals("Two hands") && h2.getValue()==0 && player[0].getList().size()==2 && v.p1==0 && player[0].getList().get(0).count==player[0].getList().get(1).count){
            v.b[2].setText("Two hands");                                       //rasti kur dy letrat e para jane te njejta
            v.addButton2();
         }
         else if(v.b[2].getText().equals("Two hands") && v.turn!=0){          //rasti kur eshte shfaqur buttoni "Two Hands" dhe injorohet nga shfrytzuesi
            v.remove(v.b[2]);
            v.b[2].setText("");
         }
         if(v.turn==1 || v.turn==2){                                        //pjesa ku luan player2 dhe player3 (player[1] dhe player[2])
            stopGameFor(400);
            if(player[v.turn].getValue()<17){
               player[v.turn].add(v.getCard(v.turn-1));
            }
            v.turn++;
            v.repaint();
         }
         if(v.turn==3 && h2.getValue()!=0 && v.p2==0 && v.p1!=0){                       // rasti kur turn = 3 dhe hand1>21 dhe hand2 ende luan
            v.turn=-1;
            v.hand2("2");
         }
         if(v.turn==3 && (v.p1==0 || player[1].getValue()<17 || player[2].getValue()<17)){            // rasti kur player3  kryen radhen e tij dhe i vie player1 apo 2
            v.turn=v.p1;                                                              
         }
         else if(v.turn==3){                              //rasti i perfundimit te nje raundi         
            winner();
            v.addButton2();
            v.turn++;
            v.repaint();
            
            
            
            
            
         }
         else if(v.turn==5){                           
            v.remove(v.b[2]);
            if(h2.getValue()==0 || v.p2==1){           // rasti kur shtypet buttoni winners
               start();
            }
            else {                                    // rasti hur shtypet butoni "two hands"
               v.turn=0;
            }
         }
      }
   }
  
   // percakton fituesin e lojes 
   private void winner(){                                       
      if(h2.getValue()!=0 && h2.getValue()<22 && h2.getValue()>player[0].getValue()){
         player[0].setValue(h2.getValue());
      }
      int s[] = new int[3];
      for(int i=0; i!=s.length; i++){
         if(player[i].getValue()<22){
            s[i]=player[i].getValue()*10+i;
         }
      } 
      Arrays.sort(s);
      if(s[0]/10==s[1]/10 && s[1]/10==s[2]/10){
         v.b[2].setText("This round is tie");
      }
      else if(s[1]/10==s[2]/10){
         v.b[2].setText("Player"+(s[1]%10+1)+" = Player"+(s[2]%10+1));
         v.w[s[1]%10]++; v.w[s[2]%10]++;
      }
      else{
         v.b[2].setText("Player"+(s[2]%10+1)+" is winner");
         v.w[s[2]%10]++;
      }
   }
   
   // merret me startimin e qdo raundi
   private void start(){
      v.turn=0;
      v.p1=0;
      v.p2=0;
      v.hand2("");
      h2.clear();
      for(int i=0; i!=player.length; i++){
         player[i].clear();
         player[i].add(v.getCard(i-1));
         player[i].add(v.getCard(i-1));
      }
      v.repaint();
   }
   
   // ndalon ekzekutimin e kodit per nje kohe te caktuar  
   public void stopGameFor(int miliSec){
      try{
         Thread.sleep(miliSec);
      }
      catch(Exception e){}
   }
   
   // ekzekuton lojen
   public static void main(String [] args){
      Hand player[] = new Hand[3];
      for(int i=0; i!=player.length; i++){
         player[i]= new Hand();
      }
      Hand h2 = new Hand();
      CardDeck d1 = new CardDeck();
      CardDeck d2 = new CardDeck();
      new Game_Tempo(player,h2,d1,d2);
   }
}