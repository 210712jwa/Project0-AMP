package com.revature.model;

import java.util.List;
import java.util.Objects;

public class Client {
	
	private String name; //Client name
	private int id; //Client id number
	private int age; //Client age
	
	List<Account> accounts;
	
	public Client() {
		super();
	}

	public Client(String name, int id, int age) {
		this.name = name;
		this.id = id;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accounts, age, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(accounts, other.accounts) && age == other.age && id == other.id
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", id=" + id + ", age=" + age + ", accounts=" + accounts + "]";
	}

	

	
	
	
}
