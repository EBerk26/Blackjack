import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Main {
    Card[] deck = new Card[52];
    Player player = new Player();
    Player dealer = new Player();
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
        for(int x =0; x<=51; x++) {
            deck[x].printInfo();
        }
        deal();
        player.printInfo();
        dealer.printInfo();
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
            player.hand[x]=deck[x];
            deck[x].inDeck = false;
        }
        for(int x = 2;x<=3;x++){
            dealer.hand[x-2]=deck[x];
            deck[x].inDeck = false;
        }
        player.updateHandValue();
        dealer.updateHandValue();
    }
}