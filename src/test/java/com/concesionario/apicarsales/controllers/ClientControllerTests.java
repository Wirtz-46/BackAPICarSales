package com.concesionario.apicarsales.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.concesionario.apicarsales.controller.ClientController;
import com.concesionario.apicarsales.model.Client;
import com.concesionario.apicarsales.repository.ClientRepository;
import com.concesionario.apicarsales.service.ClientService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ClientController.class)
public class ClientControllerTests {
    
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllClients() throws Exception {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/apicarsales/clients"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testGetById() throws Exception {
        Client client = new Client();
        client.setClientId(1L);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        mockMvc.perform(MockMvcRequestBuilders.get("/apicarsales/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1));

        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateClient() throws Exception {
        Client client = new Client();
        client.setClientId(1L);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/apicarsales/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clientId\": null, \"names\": \"Pepito Rodriguez\", \"email\": \"pepito@gmail.com\", \"phone\": \"1657952\", \"address\": \"Cra 59\", \"birthdate\": \"1978-01-19\", \"identification\": \"215896649\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1));

        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testUpdateClient() throws Exception {
        Client client = new Client();
        client.setClientId(1L);
        when(clientService.updateClient(any(Long.class), any(Client.class))).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.put("/apicarsales/clients/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clientId\": null, \"names\": \"Pepito Rodriguez\", \"email\": \"pepito@gmail.com\", \"phone\": \"1657952\", \"address\": \"Cra 59\", \"birthdate\": \"1978-01-19\", \"identification\": \"215896649\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1));

        verify(clientService, times(1)).updateClient(any(Long.class), any(Client.class));
    }

    @Test
    public void testDeleteClient() throws Exception {
        doNothing().when(clientService).deleteClient(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/apicarsales/clients/1"))
                .andExpect(status().isNoContent());

        verify(clientService, times(1)).deleteClient(1L);
    }
}
