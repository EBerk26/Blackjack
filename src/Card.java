class Card {
    int value; //2-11
    String suit;
    String cardType; //number, jack, queen, king, ace
    Card(){

    }
    Card(int param_value,String param_suit, String param_cardType){
        value = param_value;
        suit = param_suit;
        cardType = param_cardType;
    }
    void printInfo(){
        if(value<10) {
            System.out.println(value+" of "+suit+", worth "+value+" points");
        } else{
            if(cardType.equals("number")){
                System.out.println("10 of "+suit+", worth "+value+" points");
            } else{
                System.out.println(cardType+" of "+suit+", worth "+value+" points");
            }
        }
    }
    void printInfoSameLine(){
        if(value<10) {
            System.out.print(value+" of "+suit+", worth "+value+" points");
        } else{
            if(cardType.equals("number")){
                System.out.print("10 of "+suit+", worth "+value+" points");
            } else{
                System.out.print(cardType+" of "+suit+", worth "+value+" points");
            }
        }
    }
}