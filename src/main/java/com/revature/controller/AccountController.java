package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrEditAccountDTO;
import com.revature.model.Account;

import com.revature.service.AccountService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AccountController implements Controller {
	
	private AccountService accountService;
	
	public AccountController() {
		this.accountService = new AccountService();
		}
	
	private Handler getAllClientAccounts = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		
		List<Account> accountsOfClient = accountService.getAllAccountsFromClient(clientId);
		
		ctx.status(200);
		ctx.json(accountsOfClient);
	};
	
	private Handler getClientAccountById = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		String accountId = ctx.pathParam("accountid");
		
		Account clientAccountById = accountService.getAccountFromClientById(clientId, accountId);
		
		ctx.status(200);
		ctx.json(clientAccountById);
	};
	
	private Handler addAccount = (ctx) -> {
		AddOrEditAccountDTO accountToAdd = ctx.bodyAsClass(AddOrEditAccountDTO.class);
		String clientId = ctx.pathParam("clientid");
		
		Account newAccount = accountService.addAccount(clientId, accountToAdd);
		
		ctx.status(200);
		ctx.json(newAccount);
	};
	
	private Handler editAccount = (ctx) -> {
		AddOrEditAccountDTO accountToEdit = ctx.bodyAsClass(AddOrEditAccountDTO.class);
		String clientId = ctx.pathParam("clientid");
		String accountId = ctx.pathParam("accountid");
		
		Account editedAccount = accountService.editAccount(clientId, accountId, accountToEdit);
		ctx.status(200);
		ctx.json(editedAccount);
	};
	
	private Handler deleteAccount = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		String accountId = ctx.pathParam("accountid");
		ctx.status(200);
		
		accountService.deleteAccount(clientId,accountId);
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		// TODO Auto-generated method stub
		app.get("/clients/:clientid/accounts", getAllClientAccounts);
		app.get("/clients/:clientid/accounts/:accountid", getClientAccountById);
		app.post("/clients/:clientid/accounts", addAccount);
		app.put("/clients/:clientid/accounts", editAccount);
		app.delete("/clients/:clientid/accounts/:accountid", deleteAccount);
	}
	
}
