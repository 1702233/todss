package model;

import java.util.ArrayList;

public class CardRule {

    private int ID;
    private String type;
    private boolean isDraggable;
    private String group;
    private ArrayList<Card> cards;

    public CardRule(int iD, String type, boolean isDraggable, String group, ArrayList<Card> cards) {
        super();
        ID = iD;
        this.type = type;
        this.isDraggable = isDraggable;
        this.group = group;
        this.cards = cards;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDraggable() {
        return isDraggable;
    }

    public void setDraggable(boolean isDraggable) {
        this.isDraggable = isDraggable;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
