package model;

import java.util.ArrayList;

public class CardRule {

	private String type;
	private boolean isDraggable;
	private String group;

	private Minigame minigame;
	private ArrayList<CardAssignment> allCardAssignments;

	public CardRule(String type, boolean isDraggable, String group, Minigame minigame,
			ArrayList<CardAssignment> allCardAssignments) {
		super();
		this.type = type;
		this.isDraggable = isDraggable;
		this.group = group;
		this.minigame = minigame;
		this.allCardAssignments = allCardAssignments;
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

	public Minigame getMinigame() {
		return minigame;
	}

	public void setMinigame(Minigame minigame) {
		this.minigame = minigame;
	}

	public ArrayList<CardAssignment> getAllCardAssignments() {
		return allCardAssignments;
	}

	public void setAllCardAssignments(ArrayList<CardAssignment> allCardAssignments) {
		this.allCardAssignments = allCardAssignments;
	}

}
