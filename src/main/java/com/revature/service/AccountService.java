package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.dto.AddOrEditAccountDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.model.Account;

public class AccountService {
	
	private AccountDAO accountDao;
	private ClientDAO clientDao;
	
	public AccountService() {
		this.accountDao = new AccountDAOImpl();
		this.clientDao = new ClientDAOImpl();
	}
	
	
	public AccountService(AccountDAO accountDao, ClientDAO clientDao) {
		this.accountDao = accountDao;
		this.clientDao = clientDao;
	}
	
	public List<Account> getAllAccountsFromClient(String clientId) throws BadParameterException, DatabaseException, ClientNotFoundException {
		try {
			int id = Integer.parseInt(clientId);
			
			if (clientDao.getClientById(id) == null) {
				throw new ClientNotFoundException("Client with id " + clientId + " does not exist");
			}
			
			List<Account> accounts = accountDao.getAllAccounts(id);
			
			return accounts;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new BadParameterException(clientId + " was given as the id, but it is not an integer.");
		}
	}
	
	public Account getAccountFromClientById(String clientId, String accId) throws BadParameterException, DatabaseException, ClientNotFoundException {
		
		try {
			int id = Integer.parseInt(clientId);
			int accNum = Integer.parseInt(accId);
			
			if (accountDao.getAccountById(id, accNum) == null) {
				throw new ClientNotFoundException(clientId + " was given as the id, but it is not an integer.");
			}
			
			return accountDao.getAccountById(id, accNum);
		
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new NumberFormatException(e.getMessage());
		}
		
	}


	public Account addAccount(String clientId ,AddOrEditAccountDTO account) throws DatabaseException, ClientNotFoundException, BadParameterException, SQLException {
		
		
		if (account.getAccType().trim().equals("") && account.getBalance() < 0) {
			throw new BadParameterException("account type can't be blank, and balance cannot be negative.");
		}
		
		if (account.getAccType().trim().equals("")) {
			throw new BadParameterException("Account type can't be blank.");
		}
		
		if (account.getBalance() < 0) {
			throw new BadParameterException("Account balance cannot be negative.");
		}
		
		try {
			int id = Integer.parseInt(clientId);
			Account addedAccount = accountDao.addAccount(id, account);
			
			return addedAccount;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new NumberFormatException(e.getMessage());
		}
	}
	
	public Account editAccount(String clientId, String accountId, AddOrEditAccountDTO account) throws DatabaseException, ClientNotFoundException, BadParameterException, SQLException {
		
		int id = Integer.parseInt(clientId);
		int accNum = Integer.parseInt(accountId);
		
		if (clientDao.getClientById(id) == null) {
			throw new ClientNotFoundException("Client with id " + clientId + " was not found");
		}
		
		try {
			
			
			if (accountDao.getAccountById(id, accNum) == null) {
				throw new ClientNotFoundException("Account with id " + accountId + " was not found");
			}
			
			Account editedAccount = accountDao.editAccount(id, accNum, account);
			
			return editedAccount;
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new NumberFormatException(e.getMessage());
		}
	}
	
	public void deleteAccount(String clientId, String accountId) throws DatabaseException, ClientNotFoundException, BadParameterException, SQLException {
		int id = Integer.parseInt(clientId);
		int accNum = Integer.parseInt(accountId);
		
		if (clientDao.getClientById(id) == null) {
			throw new ClientNotFoundException("Client with id " + clientId + " was not found");
		}
		
		try {
			
			
			if (accountDao.getAccountById(id, accNum) == null) {
				throw new ClientNotFoundException("Account with id " + accountId + " was not found");
			}
			
			accountDao.deleteAccount(id, accNum);
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new NumberFormatException(e.getMessage());
		}
	}
	
	
	

}
