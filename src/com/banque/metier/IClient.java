package com.banque.metier;

import java.util.Collection;

import com.banque.entities.Client;

public interface IClient {
	public Client find(long code);
	public Client add(Client client);
	public boolean update(Client client);
	public boolean delete(long code);
	public Collection<Client> findAll();
}
