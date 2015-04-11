package arduprocessingeclipse;

public class Led {
	private int pino;
	private boolean aceso = false;
	public int getPino() {
		return pino;
	}
	public void setPino(int pino) {
		this.pino = pino;
	}
	public boolean isAceso() {
		return aceso;
	}
	public void setAceso(boolean aceso) {
		this.aceso = aceso;
	}
}
