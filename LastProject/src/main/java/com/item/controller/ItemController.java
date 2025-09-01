package com.item.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.item.ServiceImpl.ItemServiceImpl;
import com.item.Services.ItemService;
import com.item.model.Item;

/**
 * Servlet implementation class ItemController
 */
@WebServlet("/ItemController")
public class ItemController extends HttpServlet {
	@Resource(name="jdbc/connection")
	DataSource dataSource;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(Objects.isNull(action)) {
			action="Show Items";
		}
		switch(action) {
		case "Show Item":
			showItem(request,response);
			break;
		case"Show Items":	
			showItems(request,response);
			break;
		case"Add Item":
			addItem(request,response);
			break;
		case"Update Item":
			updateItem(request,response);
			break;
		case"Delete Item":
			deleteItem(request,response);
			break;
		default:
		    showItems(request,response);
			
		}
	}
	

	
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) {
		Integer id= Integer.parseInt(request.getParameter("id"));
		ItemService itemService=new ItemServiceImpl(dataSource);
		itemService.deleteItem(id);
	}



	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		Integer id=Integer.parseInt(request.getParameter("id"));
		String name =request.getParameter("name");
		Double price=Double.parseDouble(request.getParameter("price"));
		Integer totalNumber=Integer.parseInt(request.getParameter("totalNumber"));
		Item item=new Item(id,name,price,totalNumber);
		ItemService itemService=new ItemServiceImpl(dataSource);
		itemService.updateItem(item);
		showItems(request, response);
		
	}



	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		String name =request.getParameter("name");
		Double price=Double.parseDouble(request.getParameter("price"));
		Integer totalNumber=Integer.parseInt(request.getParameter("totalNumber"));
		Item item=new Item(name,price,totalNumber);
		ItemService itemService=new ItemServiceImpl(dataSource);
		itemService.addItem(item);
		
		showItems(request, response);
		 
	}



	private void showItems(HttpServletRequest request, HttpServletResponse response) {
		ItemService itemService =new ItemServiceImpl(dataSource);
		List<Item>item=itemService.getItems();
		request.setAttribute("allItems", item);
		System.out.println("-> res"+item.size());
		try {
		request.getRequestDispatcher("/show-items.jsp").forward(request, response);
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
		
	}



	private void showItem(HttpServletRequest request, HttpServletResponse response) {
		int id= Integer.parseInt(request.getParameter("id"));
		ItemService itemService =new ItemServiceImpl(dataSource);
		Item item=itemService.getItemById(id);
		request.setAttribute("item", item);
		try {
			request.getRequestDispatcher("/update-item.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
