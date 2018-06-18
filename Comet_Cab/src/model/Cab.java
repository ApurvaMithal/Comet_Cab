package model;

public class Cab {
	private String licensePlateNo;
	private String model;
	private CabType cabType;
	private boolean availabilty;

	public Cab(String licensePlateNo, String model, CabType cabType) {
		super();
		this.licensePlateNo = licensePlateNo;
		this.model = model;
		this.cabType = cabType;
	}

	public String getLicensePlateNo() {
		return licensePlateNo;
	}

	public void setLicensePlateNo(String licensePlateNo) {
		this.licensePlateNo = licensePlateNo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public CabType getCabType() {
		return cabType;
	}

	public void setCabType(CabType cabType) {
		this.cabType = cabType;
	}

	public boolean isAvailabilty() {
		return availabilty;
	}

	public void setAvailabilty(boolean availabilty) {
		this.availabilty = availabilty;
	}
}
