package com.banque.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banque.entities.Client;
import com.banque.dao.*;
import com.banque.metier.*;

public class Home extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Servlet Params registered under <servlet> tag
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
		
		// Redirect to home page
		try {
			if( request.getParameter("code") != null ) 
			{
				request.setAttribute("client", clientMetier.find(Integer.parseInt(request.getParameter("code").toString())));
				request.setAttribute("one_client", true);
			}else 
			{
				request.setAttribute("clients", clientMetier.findAll());
			}
			
			request.getRequestDispatcher("home.jsp").forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
