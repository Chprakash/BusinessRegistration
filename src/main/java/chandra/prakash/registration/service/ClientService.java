package chandra.prakash.registration.service;

import chandra.prakash.registration.dto.ClientDTO;
import chandra.prakash.registration.entity.Client;

public interface ClientService {
	public ClientDTO getClientByID(Long id);
	public ClientDTO addNewClient(ClientDTO clientDTO);
	public ClientDTO getClientLogin(ClientDTO clientDTO);
	public Client getClientByEmailID(String emailID);
}
