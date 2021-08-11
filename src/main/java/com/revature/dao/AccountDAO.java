package com.revature.dao;

import java.sql.SQLException;
import java.util.List;
import com.revature.dto.AddOrEditAccountDTO;
import com.revature.model.Account;

public interface AccountDAO {
	
	public abstract List<Account> getAllAccounts(int clientId) throws SQLException;
	
	public abstract Account getAccountById(int clientId, int accId) throws SQLException;
	
	public abstract Account addAccount(int clientId, AddOrEditAccountDTO acc) throws SQLException; 
	
	public abstract Account editAccount(int clientId, int accId, AddOrEditAccountDTO acc) throws SQLException; //Might need to have accountId AND clientId to get the specific account to edit per client.
	
	public abstract void deleteAccount(int clientId, int accId) throws SQLException;
	
}
