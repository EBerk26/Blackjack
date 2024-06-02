import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
class Main {
    Card[] deck = new Card[52];
    Player player = new Player();
    Player dealer = new Player();
    int numCardsDealt = 0;
    int bank = 1000;
    int pot;
     Main() {
        dealer.name = "Jeffrey the Dealer";
        dealer.isDealer = true;
        System.out.println("Welcome to Eli's Casino!");
        System.out.println("What's your name?");
        Scanner scanner = new Scanner(System.in);
        player.name = scanner.next();
        for(int x = 0; x<=51; x++) {
            deck[x] = new Card();
        }
        initializeDeck();
        shuffle();
        deal();
        betAsk();
        play();
    }
    public static void main(String[] args) {
        new Main();
    }
    void initializeDeck() {
        for (int suits = 1; suits <= 4; suits++) {
            for (int cardnumber = 1; cardnumber <= 13; cardnumber++) {
                if (suits == 1) {
                    deck[cardnumber - 1].suit = "hearts";
                } else if (suits == 2) {
                    deck[13 * (suits - 1) + cardnumber - 1].suit = "spades";
                } else if (suits == 3) {
                    deck[13 * (suits - 1) + cardnumber - 1].suit = "diamonds";
                } else {
                    deck[13 * (suits - 1) + cardnumber - 1].suit = "clubs";
                }
                if (cardnumber > 1 && cardnumber < 11) {
                    deck[13 * (suits - 1) + cardnumber - 1].value = cardnumber;
                    deck[13 * (suits - 1) + cardnumber - 1].cardType = "number";
                } else if (cardnumber > 10) {
                    deck[13 * (suits - 1) + cardnumber - 1].value = 10;
                    if (cardnumber == 11) {
                        deck[13 * (suits - 1) + cardnumber - 1].cardType = "jack";
                    } else if (cardnumber == 12) {
                        deck[13 * (suits - 1) + cardnumber - 1].cardType = "queen";
                    } else {
                        deck[13 * (suits - 1) + cardnumber - 1].cardType = "king";
                    }
                } else {
                    deck[13 * (suits - 1) + cardnumber - 1].cardType = "ace";
                    deck[13 * (suits - 1) + cardnumber - 1].value = 11;
                }
            }
        }
    }
    void shuffle(){
        List<Card> cardList = Arrays.asList(deck);
        Collections.shuffle(cardList);
        cardList.toArray(deck);
    }
    void deal(){
        //first and second card
        for(int x = 0;x<=1;x++){
            player.addCard(deck[x]);
            numCardsDealt++;
        }
        for(int x = 2;x<=3;x++){
            dealer.addCard(deck[x]);
            numCardsDealt++;
        }
        player.updateHandValue();
        dealer.updateHandValue();
    }
    void play(){
        Scanner scanner = new Scanner(System.in);
        player.printInfo();
        dealer.printInfo();
        System.out.println("Do you want to stand or hit?");
        String decision = scanner.next();
        if(decision.equals("hit")){
            System.out.println("You decided to hit");
            player.addCard(deck[numCardsDealt]);
            System.out.print("The card you received is the ");
            deck[numCardsDealt].printInfoSameLine();
            System.out.println();
            System.out.println("Your hand is now worth "+player.handValue);
            if(player.handValue>21){
                System.out.println("You busted!");
                reset();
                betAsk();
                play();
            } else {
                numCardsDealt++;
                play();
            }
        } else if (decision.equals("stand")){
            System.out.println("You decided to stand");
            pause(400);
            System.out.print(dealer.name+"'s hidden card was ");
            dealer.hand[1].printInfoSameLine();
            System.out.println();
            System.out.println(dealer.name+" has "+dealer.handValue+" points");
            while(dealer.handValue<17){
                pause(1000);
                dealer.addCard(deck[numCardsDealt]);
                System.out.print(dealer.name+" drew the ");
                dealer.hand[dealer.numCards-1].printInfoSameLine();
                System.out.println();
                System.out.println(dealer.name+" now has "+dealer.handValue+" points");
                numCardsDealt++;
            }
            if(dealer.handValue>21){
                pause(400);
                System.out.println(dealer.name+" busted!");
                reset();
                betAsk();
                play();
            } else{
                pause(400);
                System.out.println(dealer.name+" stands");
                System.out.println(dealer.name+" has a hand value of "+dealer.handValue);
                System.out.println(player.name+" has a hand value of "+player.handValue);
                if(dealer.handValue> player.handValue){
                    System.out.println(dealer.name+" wins!");
                    reset();
                    betAsk();
                    play();
                } else if (dealer.handValue< player.handValue){
                    System.out.println(player.name+" wins! Congratulations!");
                    bank+=(2*pot);
                    reset();
                    betAsk();
                    play();
                } else{
                    System.out.println("Push! No one wins this round");
                    bank+=pot;
                    reset();
                    betAsk();
                    play();
                }
                if(bank<=0){
                    System.out.println("You lose.");
                }
            }
        }
    }
    void pause(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e){
            //ignore the exception
        }
    }
    void reset(){
         for(int x = 0; x<=11;x++){
             player.hand[x] = new Card();
             dealer.hand[x] = new Card();
         }
        pot = 0;
        player.numAces = 0;
        player.numCards = 0;
        dealer.numAces = 0;
        dealer.numCards = 0;
        numCardsDealt = 0;
        shuffle();
        deal();
    }
    void betAsk(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much do you want to bet? You have $"+bank);
        int betAmount = Integer.parseInt(scanner.next());
        if(betAmount>bank){
            System.exit(0);
        }
        pot = betAmount;
        bank -=betAmount;
    }
}