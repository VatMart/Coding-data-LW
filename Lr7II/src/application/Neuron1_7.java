package application;

public class Neuron1_7 {
	
	public RootPane1_7 rootPane;
	private NeuronData initMammal;
	private NeuronData initBird;
	private NeuronData initFish;
	private NeuronData userInput;
	private double sumM, sumB, sumF;
	public static final int p=5;
	
	public Neuron1_7(NeuronData initMammal, NeuronData initBird, NeuronData initFish) {
		this.initMammal = initMammal;
		this.initBird = initBird;
		this.initFish = initFish;
		userInput = new NeuronData("userAnimal", 0., 0., 0., 0., 0.);
	}
	public Neuron1_7() {
		initMammal = new NeuronData("Млекопитающие", 4.d, 0.01, 0.01, -1.d, -1.5);
		initBird = new NeuronData("Птица", 2.d, -1.d, 2.d, 2.5, 2.d);
		initFish = new NeuronData("Рыба", -1.d, 3.5, 0.01, -2.d, 1.5);
		userInput = new NeuronData("userAnimal", 0., 0., 0., 0., 0.);
	}
	
	private void calculateSum() {
		sumM = initMammal.getQuantityLegs()*userInput.getQuantityLegs() + initMammal.getPlaceLiving()*userInput.getPlaceLiving() + initMammal.getIsFlying()*userInput.getIsFlying() + initMammal.getFeathers()*userInput.getFeathers() + initMammal.getFromEgg()*userInput.getFromEgg();
		sumB = initBird.getQuantityLegs()*userInput.getQuantityLegs() + initBird.getPlaceLiving()*userInput.getPlaceLiving() + initBird.getIsFlying()*userInput.getIsFlying() + initBird.getFeathers()*userInput.getFeathers() + initBird.getFromEgg()*userInput.getFromEgg();
		sumF = initFish.getQuantityLegs()*userInput.getQuantityLegs() + initFish.getPlaceLiving()*userInput.getPlaceLiving() + initFish.getIsFlying()*userInput.getIsFlying() + initFish.getFeathers()*userInput.getFeathers() + initFish.getFromEgg()*userInput.getFromEgg();
	}
	
	public void sendResult() {
		if (rootPane != null) {
			if (((sumM>sumB) && (sumM>sumF)) && sumM>p)
				rootPane.setlResult("Это млекопитающее " + "val = " + sumM);
		    else if (((sumB>sumM) && (sumB>sumF)) && sumB>p)
		    	rootPane.setlResult("Это птица " + "val = " + sumB);
		    else if (((sumF>sumM) && (sumF>sumB)) && sumF>p)
		    	rootPane.setlResult("Это рыба " + "val = " + sumF);
		        else rootPane.setlResult("Неопознанный вид");
		}
	}
	
	public NeuronData getInitMammal() {
		return initMammal;
	}
	public void setInitMammal(NeuronData initMammal) {
		this.initMammal = initMammal;
	}
	public NeuronData getInitBird() {
		return initBird;
	}
	public void setInitBird(NeuronData initBird) {
		this.initBird = initBird;
	}
	public NeuronData getInitFish() {
		return initFish;
	}
	public void setInitFish(NeuronData initFish) {
		this.initFish = initFish;
	}
	public NeuronData getUserInput() {
		return userInput;
	}
	public void setUserInput(NeuronData userInput) {
		this.userInput = userInput;
		recalculate();
	}
	
	public void recalculate() {
		calculateSum();
		sendResult();
	}
}
