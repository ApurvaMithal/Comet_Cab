package model;

public enum CabType {

	HATCHBACK(1.50f), SEDAN(1.20f), SUV(1.00f);
	private float multiplier;

	CabType(float multiplier) {
		this.multiplier = multiplier;
	}

	public float getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}
	
}
