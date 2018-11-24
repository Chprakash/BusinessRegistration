package chandra.prakash.registration.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import chandra.prakash.registration.dto.ClientDTO;
import chandra.prakash.registration.entity.Client;
import chandra.prakash.registration.repository.ClientRepository;
import chandra.prakash.registration.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private Client clientDTOToClient(ClientDTO clientDTO) {
		Client client = new Client();
		client.setId(clientDTO.getId());
		client.setFirstName(clientDTO.getFirstName());
		client.setLastName(clientDTO.getLastName());
		client.setEmailID(clientDTO.getEmailID());
		client.setPassword(clientDTO.getPassword());
		client.setStatus(clientDTO.getStatus());
		return client;
	}
	
	private ClientDTO clientToClinetDTO(Client client) {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId(client.getId());
		clientDTO.setFirstName(client.getFirstName());
		clientDTO.setLastName(client.getLastName());
		clientDTO.setEmailID(client.getEmailID());
		clientDTO.setPassword(client.getPassword());
		clientDTO.setStatus(client.getStatus());
		return clientDTO;
	}

	@Override
	public ClientDTO getClientByID(Long id) {
		Client client = clientRepository.getOne(id);
		System.out.println(bCryptPasswordEncoder.encode(client.getPassword()));
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId(client.getId());
		clientDTO.setEmailID(client.getEmailID());
		return clientDTO;
	}

	@Override
	public ClientDTO addNewClient(ClientDTO clientDTO) {
		Client client = clientDTOToClient(clientDTO);
		client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
		client.setCreatedDate(new Date());
		client.setUpdatedDate(new Date());
		clientDTO = clientToClinetDTO(clientRepository.save(client));
		if(clientDTO != null) {
			clientDTO.setPassword("");
		}
		return clientDTO;
	}
	

	@Override
	public ClientDTO getClientLogin(ClientDTO clientDTO) {
		Boolean validUser = false;
		Client client = clientRepository.getClientByEmailID(clientDTO.getEmailID());
		if(client != null) {
			validUser = bCryptPasswordEncoder.matches(clientDTO.getPassword(), client.getPassword());
		}
		clientDTO = clientToClinetDTO(client);
		if(clientDTO != null) {
			clientDTO.setPassword("");
		}
		return clientDTO;
	}

	@Override
	public Client getClientByEmailID(String emailID) {
		return clientRepository.getClientByEmailID(emailID);
	}

}
