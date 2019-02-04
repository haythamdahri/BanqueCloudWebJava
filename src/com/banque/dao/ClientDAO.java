package com.banque.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.banque.entities.Client;
import com.banque.metier.IClient;

public class ClientDAO implements IClient{
	
	private Connection connection;
	
	public ClientDAO(String driver, String url, String username, String password) {
		this.connection = ConfigDB.getInstance().getConnection(driver, url, username, password);
	}

	@Override
	public Client find(long code) {
		if( connection != null ) {
			PreparedStatement st;
			try {
				st = connection.prepareStatement("select * from client where code=?");
				st.setLong(1, code);
				ResultSet rs = st.executeQuery();
				if( rs.next() )
					return new Client(code, rs.getString("nom"), rs.getString("prenom"), rs.getString("ville"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Client add(Client client) {
		Client returned_client = null;
		if( connection != null ) {
			PreparedStatement st;
			try {
				st = connection.prepareStatement("insert into client values(NULL, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				st.setString(1, client.getNom());
				st.setString(2, client.getPrenom());
				st.setString(3, client.getVille());
				if( st.executeUpdate() != -1 ) {
					System.out.println("Client ajouté avec succé");
					ResultSet rs = st.getGeneratedKeys();
					if( rs.next() )
						returned_client = this.find(rs.getLong(1));
				}else
					System.err.println("Une erreure est survenue, votre client n'est pas ajouté!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returned_client;
	}
	

	@Override
	public boolean update(Client client) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("update client set nom=?, prenom=?, ville=? where code=?");
			st.setString(1, client.getNom());
			st.setString(2, client.getPrenom());
			st.setString(3, client.getVille());
			st.setLong(4, client.getCode());
			if( !st.execute() ) 
				return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(long code) {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("delete from client where code=?");
			st.setLong(1, code);
			if( !st.execute() ) 
				return true;
				
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Collection<Client> findAll() {
		Statement st;
		Collection<Client> clients = new ArrayList<>();
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery("select *from client");
			while( rs.next() )
				clients.add(new Client(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			return clients;
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	

}
