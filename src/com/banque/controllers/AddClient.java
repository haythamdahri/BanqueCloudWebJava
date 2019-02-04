package com.banque.controllers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banque.dao.ClientDAO;
import com.banque.entities.Client;
import com.banque.metier.IClient;

@WebServlet("/add")
public class AddClient extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("add_client.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {// Servlet Params registered under <servlet> tag
			ServletConfig config = this.getServletConfig();
			
			// Retrieving saved parameters with their values
			String driver = config.getInitParameter("driver");
			String url = config.getInitParameter("url");
			String username = config.getInitParameter("db_user");
			String password = config.getInitParameter("db_password");
			
			// APplicationScope , Their is no need to inject clientMetier in ApplicationScope Because this attribute depends on Client Entity not all
			ServletContext context = request.getServletContext();
			
			// SessionScope to retrieve registred attributes that depend on Client Entity
			HttpSession session = request.getSession();
			
			// Declaring new interface variable in order to concretize by implementers
			IClient clientMetier;
			
			if( context.getAttribute("clientMetier") == null ) {
				// Client Job Layer, it is used to apply action on Client Model
				clientMetier = new ClientDAO(driver, url, username ,password);
				context.setAttribute("clientMetier", clientMetier);
			}else {
				// Contretize clientMetier
				clientMetier = (IClient)context.getAttribute("clientMetier");
			}
			
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String ville = request.getParameter("ville");
				Client client = new Client(nom, prenom, ville);
				
				// Updating client data
				client = clientMetier.add(client);
				if( client != null ) {
					System.out.println("Client: "+client.toString());
					session.setAttribute("added", true);
					session.setAttribute("icon", "add");
					session.setAttribute("message", "Le client "+nom+" "+prenom+" à été ajouté avec succé");
					session.setAttribute("client_added", client);
				}
				response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
