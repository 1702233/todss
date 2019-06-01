package model;

public class Grade {

	private int cijfer;
	private Minigame minigame;

	public Grade(int cijfer, Minigame minigame) {
		super();
		this.cijfer = cijfer;
		this.minigame = minigame;
	}

	public int getCijfer() {
		return cijfer;
	}

	public void setCijfer(int cijfer) {
		this.cijfer = cijfer;
	}

	public Minigame getMinigame() {
		return minigame;
	}

	public void setMinigame(Minigame minigame) {
		this.minigame = minigame;
	}

}
