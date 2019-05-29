package model;

public class Tag {

	private String tag;
	private Minigame minigame;


	public Tag(String tag, Minigame minigame) {
		super();
		this.tag = tag;
		this.minigame = minigame;

	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Minigame getMinigame() {
		return minigame;
	}

	public void setMinigame(Minigame minigame) {
		this.minigame = minigame;
	}


}
