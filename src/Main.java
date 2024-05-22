import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Main {
    Card[] deck = new Card[52];
    Player player = new Player();
    Player dealer = new Player();
    public int numCardsDealt = 0;
    public Main() {
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
        /*for(int x =0; x<=51; x++) {
            deck[x].printInfo();
        }*/
        deal();
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
            System.out.println("You decided to hit.");
            player.addCard(deck[numCardsDealt]);
            System.out.print("The card you received is the ");
            deck[numCardsDealt].printInfoSameLine();
            System.out.println();
            if(player.handValue>21){
                System.out.println("You busted!");
            } else {
                numCardsDealt++;
                play();
            }
        } else if (decision.equals("stand")){
            System.out.println("You decided to stand.");
            while(dealer.handValue<17){
                /*try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
                dealer.addCard(deck[numCardsDealt]);
                numCardsDealt++;
            }
            if(dealer.handValue>21){
                System.out.println(dealer.name+" busted!");
            } else{
                System.out.println(dealer.name+" has a hand value of "+dealer.handValue);
                System.out.println(player.name+" has a hand value of "+player.handValue);
            }
            //TODO: compare values at end
        }
    }
}