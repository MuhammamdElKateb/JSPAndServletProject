package com.item.ServiceImpl;

import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.item.Services.ItemService;
import com.item.model.Item;

public class ItemServiceImpl implements ItemService {
	
private DataSource dataSource;
	

	public ItemServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Override
	public Item getItemById(int id) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection=dataSource.getConnection();
			statement=connection.createStatement();
		String sql="SELECT * FROM ITEM WHERE id = "+id;
		ResultSet resultSet=statement.executeQuery(sql);
		Item item=new Item();
		if(resultSet.next()) {
			item.setId(resultSet.getInt("id"));
			item.setName(resultSet.getString("name"));
			item.setPrice(resultSet.getDouble("price"));
			item.setTotalNumber(resultSet.getInt("total_number"));
		}
		return item;
	}catch(SQLException e) {
		System.out.println(e.getMessage());
	}finally {
		try {
			if(statement != null) statement.close();
	        if(connection != null) connection.close();
		
	}catch(SQLException e){
		System.out.println(e.getMessage());
	}
}
		return null;
	}


	@Override
	public List<Item> getItems() {
		Connection connection = null;
		Statement statement = null;
		try {
			connection=dataSource.getConnection();
			statement=connection.createStatement();
			String sql="SELECT * FROM ITEM";
			ResultSet resultSet=statement.executeQuery(sql);
			List<Item>items=new ArrayList<>();
			while(resultSet.next()) {
				Item item=new Item();
				System.out.println("hi");
				
				item.setId(resultSet.getInt("ID"));
				item.setName(resultSet.getString("NAME"));
				item.setPrice(resultSet.getDouble("PRICE"));
				item.setTotalNumber(resultSet.getInt("TOTAL_NUMBER"));
				
				
				items.add(item);
			}
			return items;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				if(statement != null) statement.close();
		        if(connection != null) connection.close();
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
			
		}
		return new ArrayList();
	}


	@Override
	public void addItem(Item item) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection=dataSource.getConnection();
			statement=connection.createStatement();
			String sql="INSERT INTO item (NAME,PRICE,TOTAL_NUMBER) VALUES ('"
					+item.getName() +"',"
					+item.getPrice() +", "
					+item.getTotalNumber() + ")";
			statement.executeUpdate(sql);
		}catch(Exception e ) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(statement != null) statement.close();
		        if(connection != null) connection.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}


	@Override
	public void updateItem(Item item) {
		Connection connection = null;
		Statement statement = null;
		try {
			connection=dataSource.getConnection();
			statement=connection.createStatement();
			String sql="UPDATE ITEM SET NAME = '"+item.getName()+"', "
					+"PRICE = "+item.getPrice()+","
					+"TOTAL_NUMBER = "+item.getTotalNumber()
					+"WHERE ID = "+item.getId();
			statement.executeUpdate(sql);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(statement != null) statement.close();
		        if(connection != null) connection.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}


	@Override
	public void deleteItem(int id) {
		
		
	}


}
