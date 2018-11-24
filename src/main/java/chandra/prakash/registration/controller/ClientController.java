package chandra.prakash.registration.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import chandra.prakash.registration.dto.ClientDTO;
import chandra.prakash.registration.service.ClientService;

@RestController
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/clients/{clientID}")
	public ClientDTO getClientByID(
			@PathVariable Long clientID) {
		return clientService.getClientByID(clientID);
	}
	
	@PostMapping("/clients")
	public ClientDTO addNewClient(
			@RequestBody @Valid ClientDTO clientDTO) {
		return clientService.addNewClient(clientDTO);
	}
	
	@PostMapping("/clients/login")
	public ClientDTO getClientLogin(
			@RequestBody @Valid ClientDTO clientDTO) {
		return clientService.getClientLogin(clientDTO);
	}
}
