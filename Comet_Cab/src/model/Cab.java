package model;

public class Cab {
	private String vehicleType;
	private String licensePlateNo;
	private float ratePerMile;
	private String model;
	private CabType cabType;
	private boolean availabilty;

	public Cab(String vehicleType, String licensePlateNo, float ratePerMile, String model, CabType cabType) {
		super();
		this.vehicleType = vehicleType;
		this.licensePlateNo = licensePlateNo;
		this.ratePerMile = ratePerMile;
		this.model = model;
		this.cabType = cabType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getLicensePlateNo() {
		return licensePlateNo;
	}

	public void setLicensePlateNo(String licensePlateNo) {
		this.licensePlateNo = licensePlateNo;
	}

	public float getRatePerMile() {
		return ratePerMile;
	}

	public void setRatePerMile(float ratePerMile) {
		this.ratePerMile = ratePerMile;
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
