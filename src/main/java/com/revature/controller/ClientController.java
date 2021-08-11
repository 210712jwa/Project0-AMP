package com.revature.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.ClientService;
import com.revature.dto.AddOrEditClientDTO;
import com.revature.model.Client;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ClientController implements Controller{

	private ClientService clientService;
	
	public ClientController() {
		this.clientService = new ClientService();
	}
	
	private Handler getAllClients = (ctx) -> {
		List<Client> clients = clientService.getAllClients();
		
		ctx.status(200);
		ctx.json(clients);
	};
	
	private Handler getClientById = (ctx) -> {
		
		String clientId = ctx.pathParam("clientid");
//		System.out.println(clientId);
		Client client = clientService.getClientById(clientId);
		ctx.status(200);
		ctx.json(client);
	};
	
	private Handler addClient = (ctx) -> {
		AddOrEditClientDTO clientToAdd = ctx.bodyAsClass(AddOrEditClientDTO.class);
		
		Client newClient = clientService.addClient(clientToAdd);
		ctx.status(200);
		ctx.json(newClient);
	};
	
	private Handler editClient = (ctx) -> {
		AddOrEditClientDTO clientToUpdate = ctx.bodyAsClass(AddOrEditClientDTO.class);
		String clientId = ctx.pathParam("clientid");
		Client updatedClient = clientService.editClient(clientId, clientToUpdate);
		ctx.status(200);
		ctx.json(updatedClient);
	};
	
	private Handler deleteClient = (ctx) -> {
		String clientId = ctx.pathParam("clientid");
		clientService.deleteClient(clientId);
		ctx.status(200);
	};
	
	
	@Override
	public void mapEndpoints(Javalin app) {
		// TODO Auto-generated method stub
		app.get("/clients", getAllClients);
		app.get("/clients/:clientid", getClientById);
		app.post("/clients", addClient);
		app.put("/clients/:clientid", editClient);
		app.delete("/clients/:clientid", deleteClient);
		
	}
	
}
