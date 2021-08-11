package com.revature.service;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.ClientDAO;
import com.revature.dao.ClientDAOImpl;
import com.revature.dto.AddOrEditClientDTO;
import com.revature.exception.BadParameterException;
import com.revature.exception.ClientNotFoundException;
import com.revature.exception.DatabaseException;
import com.revature.model.Account;
import com.revature.model.Client;

public class ClientService {
	
	private Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	private ClientDAO clientDao;
	private AccountDAO accountDao;
	
	public ClientService() {
		this.clientDao = new ClientDAOImpl();
		this.accountDao = new AccountDAOImpl();
	}
	
	public List<Client> getAllClients() throws DatabaseException {
		
		List<Client> clients;
		
		//Attempts to retrieve all the clients, and the accounts associated with each client.
		try {
			clients = clientDao.getAllClients();
			
			for (Client client : clients) {
				List<Account> accounts = accountDao.getAllAccounts(client.getId());
				client.setAccounts(accounts);
			}
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
	
		
		return clients;
	}
	
	public Client getClientById(String stringId) throws DatabaseException, ClientNotFoundException, BadParameterException {
		try {
			int id = Integer.parseInt(stringId);
			
			Client client = clientDao.getClientById(id);
			
			if (client == null) {
				throw new ClientNotFoundException("Client ID " + id + " cannot be found");
			}
			
				List<Account> accounts = accountDao.getAllAccounts(client.getId());
				client.setAccounts(accounts);
			
			return client;
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		} catch (NumberFormatException e) {
			throw new BadParameterException(stringId + " was passed in by the user as the id, " + "but it is not an int");
		}
		
	}
	
	
	public Client addClient(AddOrEditClientDTO client) throws DatabaseException, BadParameterException {
		
		if (client.getName().trim().equals("") && client.getAge() < 0) {
			throw new BadParameterException("Client name can't be blank, and age must be over 0.");
		}
		
		if (client.getName().trim().equals("")) {
			throw new BadParameterException("Client must have a name.");
		}
		
		if (client.getAge() < 0) {
			throw new BadParameterException("Client's age must be greater than 0.");
		}
		
		try {
			Client newClient = clientDao.addClient(client);
			
			return newClient;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public Client editClient(String clientId, AddOrEditClientDTO client) throws DatabaseException, ClientNotFoundException, BadParameterException, SQLException {
		try {
			
			int id = Integer.parseInt(clientId);
			
			if (clientDao.getClientById(id) == null) {
				throw new ClientNotFoundException("There is no client with an id of " + clientId);
			}
			Client editedClient = clientDao.editClient(id, client);
			
			return editedClient;
			
		} catch(SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		
	}
	
	public void deleteClient(String clientId) throws DatabaseException, ClientNotFoundException {
		
		try {
			int id = Integer.parseInt(clientId);
			
			if (clientDao.getClientById(id) == null) {
				throw new ClientNotFoundException("There is no client with an id of " + clientId);
				
			}
			
			clientDao.deleteClient(id);
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		
		
		
		
	}
	
	
}
