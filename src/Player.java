class Player {
    Card[] hand = new Card[12]; //your hand as an array of different cards
    String name;
    int handValue;
    int numCards;
    boolean isDealer;
    int numAces = 0;
    Player(){
        for(int x =0;x<hand.length;x++){
            hand[x] = new Card();
        }
        name = "Eli";
        handValue = 0;
        numCards = 0;
        isDealer = false;
    }
    void printInfo(){
        if(!isDealer){
        System.out.println(name+" has a hand value of "+handValue+" and is not the dealer");
        System.out.println("Their hand contains the following cards:");
            for (Card card : hand) {
                if (card.value > 0) {
                    card.printInfo();
                }
            }
        } else{
            System.out.println(name+" is the dealer");
            System.out.print("He is showing the ");
            hand[0].printInfoSameLine();
            System.out.println();
        }
    }
    void updateHandValue(){
        handValue = 0;
        for (int x = 0;x<=11;x++){
            handValue+=hand[x].value;
        }
    }
    void addCard(Card param_card){
        hand[numCards] = param_card;
        numCards++;
        if(param_card.cardType.equals("ace")){
            numAces++;
        }
        updateHandValue();
        if(handValue>21&&numAces>0){
            numAces--;
            handValue-=10;
        }
    }
}
