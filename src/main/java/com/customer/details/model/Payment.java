package com.customer.details.model;

public class Payment {

	private int paymentId;
	private int customerId;
	private String transcationId;
	private String paymentType;
	private double ammount;
	private String status;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getTranscationId() {
		return transcationId;
	}

	public void setTranscationId(String transcationId) {
		this.transcationId = transcationId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment [paymentId=");
		builder.append(paymentId);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", transcationId=");
		builder.append(transcationId);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", ammount=");
		builder.append(ammount);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
}
