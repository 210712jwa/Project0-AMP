package com.revature.dto;

import java.util.Objects;

public class AddOrEditAccountDTO {
	private double balance;
	private String accType;
	private int clientId;
	
	public AddOrEditAccountDTO() {
		super();
	}
	
	public AddOrEditAccountDTO(double balance, String accType, int clientId) {
		this.balance = balance;
		this.accType = accType;
		this.clientId = clientId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accType, balance, clientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrEditAccountDTO other = (AddOrEditAccountDTO) obj;
		return Objects.equals(accType, other.accType)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance)
				&& clientId == other.clientId;
	}

	@Override
	public String toString() {
		return "AddOrEditAccountDTO [balance=" + balance + ", accType=" + accType + ", clientId=" + clientId + "]";
	}
	
	
	
	
}
