package com.danny.store.java.servicesImp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.danny.store.java.entities.Order;
import com.danny.store.java.exceptions.PathNotFoundException;
import com.danny.store.java.exceptions.UserBadRequestException;
import com.danny.store.java.services.OrderService;

@Service
public class OrderServiceImp implements OrderService{

	@Override
	public Order addOrder(Order order) throws UserBadRequestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateOrder(Order order) throws PathNotFoundException, UserBadRequestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteOrder(Long id) throws PathNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(Long id) throws PathNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllUserOrder(Long userId) throws PathNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
