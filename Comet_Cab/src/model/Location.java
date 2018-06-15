package model;

public class Location {

	private Place pickUpLocation;
	private Place dropOffLocation;
	
	public Location(Place pickUpLocation, Place dropOffLocation) {
		super();
		this.pickUpLocation = pickUpLocation;
		this.dropOffLocation = dropOffLocation;
	}
	public Place getPickUpLocation() {
		return pickUpLocation;
	}
	public void setPickUpLocation(Place pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}
	public Place getDropOffLocation() {
		return dropOffLocation;
	}
	public void setDropOffLocation(Place dropOffLocation) {
		this.dropOffLocation = dropOffLocation;
	}

}