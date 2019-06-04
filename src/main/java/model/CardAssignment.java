package model;

public class CardAssignment {

    private int ID;
    private int rank;

    private Card card;


    public CardAssignment(int iD, int rank, Card card, CardRule cardRule) {
        super();
        ID = iD;
        this.rank = rank;
        this.card = card;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


}