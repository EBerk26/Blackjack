import java.util.Arrays;

public class Player {
    Card[] hand = new Card[10]; //your hand as an array of different cards
    String name;
    int handValue;
    int numCards;
    boolean isDealer;
    void hit (){

    }
    void stay(){

    }
    public Player(){
        for(int x =0;x<=9;x++){
            hand[x] = new Card();
        }
        name = "Eli";
        handValue = 0;
        numCards = 0;
        isDealer = false;
    }
    void printInfo(){
        String dealerString;
        if(isDealer){
            dealerString = "";
        } else{
            dealerString = "not ";
        }
        System.out.println(name+" has a hand value of "+handValue+" and is "+dealerString+"the dealer.");
        System.out.println("Their hand contains the following cards:");
        for (int x = 0;x<hand.length;x++){
            if(hand[x].value>0) {
                hand[x].printInfo();
            }
        }
    }
    void updateHandValue(){
        handValue = 0;
        for (int x = 0;x<hand.length;x++){
            handValue+=hand[x].value;
        }
    }
    void addCard(Card param_card){
        hand[numCards] = param_card;
        numCards+=1;
        updateHandValue();
    }
}
