package model;

public class Beoordeling {

	private int cijfer;
	private Minigame minigame;

	public Beoordeling(int cijfer, Minigame minigame) {
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
