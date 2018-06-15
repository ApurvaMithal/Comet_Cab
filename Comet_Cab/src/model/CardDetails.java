package model;

public class CardDetails {

	private String netId;
	private float balance;
	private String cardNo;

	public CardDetails(String netId, float balance, String cardNo) {
		super();
		this.netId = netId;
		this.balance = balance;
		this.cardNo = cardNo;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

}
