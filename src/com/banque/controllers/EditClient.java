package com.banque.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banque.dao.ClientDAO;
import com.banque.entities.Client;
import com.banque.metier.IClient;

@WebServlet("/edit")
public class EditClient extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
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
			if( request.getParameter("code") == null ) {
				response.sendRedirect(request.getContextPath());
			}else 
			{
				int code = Integer.parseInt(request.getParameter("code"));
				
				// Retrieving client from database using its id
				Client client = clientMetier.find(code);
				request.setAttribute("client", client);
				request.getRequestDispatcher("edit_client.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
				// Create a session
				HttpSession session = request.getSession();
				int code = Integer.parseInt(request.getParameter("code"));
				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String ville = request.getParameter("ville");
				Client client = new Client(code, nom, prenom, ville);
				IClient clientMetier = (IClient)request.getServletContext().getAttribute("clientMetier");
				
				// Updating client data
				if( clientMetier.update(client) ) {
					session.setAttribute("updated", true);
					session.setAttribute("icon", "done_all");
					session.setAttribute("message", "Le client dont CODE="+client.getCode()+" à été modifié avec succé");
					session.setAttribute("client_updated", client);
				}
					response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
