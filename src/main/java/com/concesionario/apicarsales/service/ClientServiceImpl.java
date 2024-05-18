package com.concesionario.apicarsales.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.concesionario.apicarsales.model.Client;
import com.concesionario.apicarsales.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getByClientId(Long clientId){
        return clientRepository.findById(clientId).orElse(null);
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long clientId, Client newclient){
        Client existentClient = getByClientId(clientId);
        if (existentClient != null) {
            newclient.setClientId(clientId);
            return clientRepository.save(newclient);
        }
        return null;
    }

    @Override
    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }

}
