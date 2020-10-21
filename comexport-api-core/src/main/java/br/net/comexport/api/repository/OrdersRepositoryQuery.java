package br.net.comexport.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.net.comexport.api.model.Order;
import br.net.comexport.api.repository.filter.OrderFilter;

public interface OrdersRepositoryQuery {

	public Page<Order> filter(OrderFilter Filter, Pageable pageable);
	
}
