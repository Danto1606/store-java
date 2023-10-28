package com.danny.store.java.services;

import java.util.List;

import com.danny.store.java.entities.Order;
import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.exceptions.UserBadRequestException;

public interface OrderService {
	
	public Order addOrder(Order order) 
			throws UserBadRequestException;
	
	public Order updateOrder(Order order)
			throws PathNotFoundException, UserBadRequestException;
	
	public String deleteOrder(Long id) throws PathNotFoundException;
	
	public Order getOrder(Long id) throws PathNotFoundException;
	
	public List<Order> getAllUserOrder(Long userId) throws PathNotFoundException;
}
