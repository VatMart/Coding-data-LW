package application;

public class Neuron1_3 {
	public RootPane1_3 rootPane;
	private int r;
	private int w1;
	private int w2;
	private int u1;
	private int u2;
	
	public void activationR(int w1, int w2, int u1, int u2) {
		r = u1*w1 + u2*w2;
	}
	
	public void sendResult() {
		if (rootPane != null) {
			if (Math.abs(r) < 0.2*(Math.abs(u1)+Math.abs(u2))) {
				rootPane.setlResultInt("Безраличен");
			} else if (r < 0) 
				rootPane.setlResultInt("Цветок не нравится");
				else rootPane.setlResultInt("Цветок нравится");
		}
	}
	
	private void recalculate() {
		activationR(w1, w2, u1, u2);
		sendResult();
	}

	public RootPane1_3 getRootPane() {
		return rootPane;
	}

	public void setRootPane(RootPane1_3 rootPane) {
		this.rootPane = rootPane;
	}

	public int getW1() {
		return w1;
	}

	public void setW1(int w1) {
		this.w1 = w1;
		recalculate();
	}

	public int getW2() {
		return w2;
	}

	public void setW2(int w2) {
		this.w2 = w2;
		recalculate();
	}

	public int getU1() {
		return u1;
	}

	public void setU1(int u1) {
		this.u1 = u1;
		recalculate();
	}

	public int getU2() {
		return u2;
	}

	public void setU2(int u2) {
		this.u2 = u2;
		recalculate();
	}
}
