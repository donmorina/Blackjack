import java.awt.Image;
import javax.swing.ImageIcon;

public class Card{
   public static final String SPADES = "s";
   public static final String HEARTS = "h";
   public static final String DIAMONDS = "d";
   public static final String CLUBS = "c";

   public static final int ACE = 1;
   public static final int JACK = 11;
   public static final int QUEEN = 12;
   public static final int KING = 13;

   public static final int SIZE_OF_ONE_SUIT = 13;  
   
   protected String suit;
   protected int count;
   protected int value;
   protected Image image;
   
   // krijon nje leter ne baz te Stringut (suit) dhe numrit
   public Card(String s, int c){
      suit = s;
      count = c;
      setImage(c+s+".gif");
      setValue();
   }
   
   // Vendos foton e njejt mirpo te rrotuluar ne 90 shkalle
   public void setFlipedImage(){
      setImage("f"+count+suit+".png");
   }

   // vendos foton e letres perkatese
   public void setImage(String imageName){
      ImageIcon i= new ImageIcon(imageName);
      image= i.getImage();
   }
      
   // vendos vleren e nje letre bazuar ne rregullat e lojes
   public void setValue(){
      value = count;
      if(value>10){ value=10; }
      else if(value==1){ value=11; }
   }
}
