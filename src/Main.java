public class Main {
    Card[] deck = new Card[52];
    public Main() {
        System.out.println("Welcome to Eli's Casino!");
        for(int x = 0; x<=51; x++) {
            deck[x] = new Card();
        }
        initializeDeck();
        shuffle();
        for(int x =0; x<=51; x++) {
            deck[x].printInfo();
        }
        Player p = new Player();
        p.printInfo();
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
        for(int x = 51; x>0; x--) { //note: since I'm not swapping a card twice, this allows for better randomness.
            int randomIndex = (int) (Math.random() * 52);
            Card temp = deck[x];
            deck[x] = deck[randomIndex];
            deck[randomIndex] = temp;
        }
    }
}