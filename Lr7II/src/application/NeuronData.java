package application;

public class NeuronData {
	
	private String animalName;
	private Double quantityLegs;
	private Double placeLiving;
	private Double isFlying;
	private Double feathers;
	private Double fromEgg;
	
	public NeuronData(String animalName, Double quantityLegs, Double placeLiving, Double isFlying, Double feathers,
			Double fromEgg) {
		this.animalName = animalName;
		this.quantityLegs = quantityLegs;
		this.placeLiving = placeLiving;
		this.isFlying = isFlying;
		this.feathers = feathers;
		this.fromEgg = fromEgg;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public Double getQuantityLegs() {
		return quantityLegs;
	}

	public void setQuantityLegs(Double quantityLegs) {
		this.quantityLegs = quantityLegs;
	}

	public Double getPlaceLiving() {
		return placeLiving;
	}

	public void setPlaceLiving(Double placeLiving) {
		this.placeLiving = placeLiving;
	}

	public Double getIsFlying() {
		return isFlying;
	}

	public void setIsFlying(Double isFlying) {
		this.isFlying = isFlying;
	}

	public Double getFeathers() {
		return feathers;
	}

	public void setFeathers(Double feathers) {
		this.feathers = feathers;
	}

	public Double getFromEgg() {
		return fromEgg;
	}

	public void setFromEgg(Double fromEgg) {
		this.fromEgg = fromEgg;
	}
	
}
