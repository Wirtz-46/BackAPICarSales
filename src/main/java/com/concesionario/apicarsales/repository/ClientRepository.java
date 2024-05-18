package com.concesionario.apicarsales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.concesionario.apicarsales.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
