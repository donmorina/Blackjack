import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameView extends JPanel {
   private JFrame f;
   private CardDeck d1, d2;
   protected JButton b[];
   private Hand player[], h2;
   private Image table, back, back2;
   protected int turn=0, w[], p1=0, p2=0;
   
   public GameView(Hand p[], Hand h2, CardDeck d1, CardDeck d2){
      b= new JButton[3];
      w= new int[3];
      player= new Hand[3];
      this.h2=h2;
      this.d1=d1;
      this.d2=d2;
      for(int i=0; i!=b.length; i++){ 
         b[i]= new JButton(); 
         b[i].setFont(new Font("Gadugi",Font.BOLD,15));
         w[i]=0;
         player[i]=p[i];
      }
      table= getImage("table.png");
      back= getImage("backk.png");
      back2= getImage("fbackk.png");
      
      //merret me dritaren grafike
      f = new JFrame("21 Game");
      f.setSize(798+16,530+39);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(null);
      addButtons();
      f.getContentPane().add(this);
      f.setResizable(false);
      f.setLocationRelativeTo(null);
      f.setVisible(true);
   }
   
   // merret me pjesen e panelit pra fotove dhe butonave qe shihen ne dritare
   public void paintComponent(Graphics g){
      g.drawImage(table,0,0,this);
      g.setColor(Color.WHITE);
      g.setFont(new Font("Malgun Gothic",Font.BOLD,25));
      g.drawString("Player1: "+w[0]+"  |  "+"Player2: "+w[1]+"  |  "+"Player3: "+w[2],170,30);
      g.drawString("Deck1: "+d1.card_count,640,170);
      g.drawString("Deck2: "+d2.card_count,640,390);
      
      // ben paraqitjen e 2 pakove (decks)
      for(int i=0; i!=d1.card_count; i++){
         g.drawImage(back2,630+1*i,180+1*i,this);
         if(i==15){ 
            break; }
      }
      for(int i=0; i!=d2.card_count; i++){
         g.drawImage(back2,630+1*i,270+1*i,this);
         if(i==15){ 
            break; }
      }
      
      // paraqitja e letrave te player1
      if(h2.getValue()==0){
         for(int i=0; i!=player[0].getList().size(); i++){
            g.drawImage(player[0].getList().get(i).image,359+14*i-player[0].getList().size()*7,330-3*i,this);
         }}
      else{
         for(int i=0; i!=player[0].getList().size(); i++){
            g.drawImage(player[0].getList().get(i).image,359+14*i-player[0].getList().size()*14-40,330-3*i,this);
         }
         for(int i=0; i!=h2.getList().size(); i++){
            g.drawImage(h2.getList().get(i).image,359+14*i+40,330-3*i,this);
         }
      }
      
      // paraqitja e letrave te player2 dhe player 3
      if(turn<3){
         for(int i=0; i!=player[1].getList().size(); i++){
            g.drawImage(back,359-14*i+player[1].getList().size()*7,50+3*i,this);
         }
         for(int i=0; i!=player[2].getList().size(); i++){
            g.drawImage(back2,30+3*i,225+14*i-player[2].getList().size()*7,this);
         }
      }
      else{
         for(int i=0; i!=player[1].getList().size(); i++){
            g.drawImage(player[1].getList().get(i).image,359-14*i+player[1].getList().size()*7,50+3*i,this);
         }
         for(int i=0; i!=player[2].getList().size(); i++){
            g.drawImage(player[2].getList().get(i).image,30+3*i,225+14*i-player[2].getList().size()*7,this);
         }
      }
   }
   
   // shton button[2] ne Panel
   protected void addButton2(){
      this.add(b[2]);
   }
   
   // merret me shtimin, madhesine, ngjyren dhe shkrimin e butonave
   private void addButtons(){
      for(int i=0; i!=b.length; i++){
         b[i].setRequestFocusEnabled(false);
         b[i].setBackground(new Color(255,255,255));
      }
      b[0].setText("Hit");
      b[1].setText("Stand");
      b[2].setText("");
      b[0].setBounds(255,450,90,30);
      b[1].setBounds(455,450,90,30);
      b[2].setBounds(324,225,180,50);
      b[0].addActionListener(                           //aksioni i butonit "Hit"
         e-> { 
            if(turn==0 && p1==0){
               player[0].add(getCard(0));
               if(player[0].getValue()>21){ 
                  p1=1; 
               }
               if(h2.getValue()==0 || p2!=0){
                  turn=1;
               }
               else{
                  hand2("2");
                  turn=-1;
               }
               repaint();
            }
            else if(turn==-1 && h2.getValue()!=0 && p2==0){
               h2.add(getCard(0));
               if(h2.getValue()>21){ 
                  p2=1; 
               }
               turn=1;
               hand2("");
               repaint();
            } 
         });
      b[1].addActionListener(                               // aksioni i butonit "Stand"
         e->{
            if(turn==0 && p1==0){
               p1=1;
               if(h2.getValue()!=0 && p2==0){
                  turn=-1;
                  hand2("2");
               }
               else{
                  turn++;
               }
            }
            else if(turn==-1 && p2==0){
               p2=1;
               turn=1;
               hand2("");
            } 
         });
      b[2].addActionListener(                                   // aksioni i butonit per fitues dhe per ndarje ne "two hands"
         e->{
            if(turn==4){
               turn=5;
            }
            else if(turn==0){
               b[2].setText("");
               h2.add(player[0].getList().get(1));
               player[0].setValue(player[0].getValue()-player[0].getList().get(1).value);
               player[0].getList().remove(1);
               turn=5;
               repaint();
            }
         });
      add(b[0]);
      add(b[1]);
   }
   
   // kthen si rezultat nje image ne baz te Stringut hyres
   public Image getImage(String imageName){
      ImageIcon i= new ImageIcon(imageName);
      return i.getImage();
   }
   
   // merr nje leter nga njera nga pakot ne menyr te rastesishme
   protected Card getCard(int f){
      Card c= null;
      int i= (int)(Math.random()*10);
      if((i%2==0 || d2.card_count==0) && d1.card_count!=0){ c=d1.newCard(f); }
      else if((i%2==1 || d1.card_count==0) && d2.card_count!=0) { c=d2.newCard(f); }
      else{
         d1.newDeck(1,player[0].getList(),player[1].getList(),player[2].getList());
         d2.newDeck(0,player[0].getList(),player[1].getList(),player[2].getList());
         c= getCard(f);
      }
      return c;
   }
   
   // merret me ndryshimin e tekstit ne buttona per Hand1 dhe Hand2
   protected void hand2(String s){
      b[0].setText("Hit"+s);
      b[1].setText("Stand"+s);
   }
}