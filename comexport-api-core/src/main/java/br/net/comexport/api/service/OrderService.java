package br.net.comexport.api.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.net.comexport.api.model.Order;
import br.net.comexport.api.model.Product;
import br.net.comexport.api.model.enumeration.Status;
import br.net.comexport.api.repository.OrdersRepository;
import br.net.comexport.api.repository.ProductsRepository;
import br.net.comexport.api.repository.filter.OrderFilter;
import br.net.comexport.api.service.exceptions.ResourceNotFoundException;
import br.net.comexport.api.service.util.Util;

@Service
public class OrderService {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private ProductsRepository productsRepository;
	
	public Order save(Order order) {
		
		definePriceAndStatus(order);
		
		order.setCreatedAt(LocalDateTime.now());
		
		return ordersRepository.save(order);
	}

	private void definePriceAndStatus(Order order) {
			
		Optional<Product> product = productsRepository.findById(order.getId_product());
		
		if (!product.isPresent()) {
			throw new ResourceNotFoundException("Product in your order is not found");
		}
		BigDecimal price = product.get().getPrice();
		BigDecimal finalPrice = null;
		switch (order.getSalesChannel()) {
		case ECOMMERCE:
			order.setStatus(Status.AGUARDANDO_ENTREGA);
	        finalPrice = Util.calculatePrice(price, 0D, 5.37);
	        order.setPrice(finalPrice);
			break;
		case PARCEIROS:
			order.setStatus(Status.AGUARDANDO_RETIRADA_PARCEIRO);
			finalPrice = Util.calculatePrice(price, 10.34, 8.78);
			order.setPrice(finalPrice);
			break;
		case LOJA_FISICA:
			order.setStatus(Status.ENTREGUE);
			finalPrice = Util.calculatePrice(price, 0D, 0D);
			order.setPrice(finalPrice);
			break;
		}
	}

	public Page<Order> search(OrderFilter orderFilter, Pageable pageable) {
		return ordersRepository.filter(orderFilter, pageable);
	}

}
