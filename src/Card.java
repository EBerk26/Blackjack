public class Card {
    boolean inDeck = true; //is it discarded?
    int value; //2-11
    String suit;
    String cardType; //number, jack, queen, king, ace
    public Card(){

    }
    void printInfo(){
        if(value<10) {
            System.out.println(value+" of "+suit+" - "+value);
        } else{
            if(cardType.equals("number")){
                System.out.println("10 of "+suit+" - "+value);
            } else{
                System.out.println(cardType+" of "+suit+" - "+value);
            }
        }
    }
}